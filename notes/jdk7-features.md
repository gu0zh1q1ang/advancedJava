# JDK 7 知识点总结

涵盖：钻石语法（Diamond Operator）、ARM 自动资源管理（try-with-resources）、数字字面量下划线、运行时数据区（Runtime Data Area）。

---

## 一、钻石语法（Diamond Operator）`<>`

### 1. 是什么
JDK 7 引入的语法糖，允许在创建泛型对象时省略右侧类型参数，由编译器根据左侧声明自动推断。

```java
// JDK 7 之前
Map<String, List<Integer>> map = new HashMap<String, List<Integer>>();

// JDK 7 之后
Map<String, List<Integer>> map = new HashMap<>();
```

### 2. 核心要点
- **本质是类型推断**：`<>` 不等于 `<Object>`，也不等于裸类型（raw type）。
  - `new HashMap()` —— 裸类型，会触发 unchecked warning。
  - `new HashMap<>()` —— 推断为 `new HashMap<String, List<Integer>>()`。
- **作用域仅限构造器**：方法调用的类型推断在 JDK 7 中仍较弱（JDK 8 的 target typing 才显著增强）。
- **不能用于匿名内部类**（JDK 8 及之前）：
  ```java
  // JDK 7/8 编译报错；JDK 9 起允许
  Comparator<String> c = new Comparator<>() { ... };
  ```
- **左侧若为原始类型**，右侧用 `<>` 仍是泛型对象（不会变成 raw type）。

### 3. 收益
- 减少冗余、提升可读性。
- 避免手抄一长串泛型参数时的出错与不一致。

---

## 二、ARM 自动资源管理（try-with-resources）

### 1. 是什么
Automatic Resource Management。把实现 `java.lang.AutoCloseable` 的资源声明在 `try( ... )` 括号中，编译器自动生成 `close()` 调用，无论正常返回还是抛异常。

```java
try (BufferedReader br = new BufferedReader(new FileReader("a.txt"));
     BufferedWriter bw = new BufferedWriter(new FileWriter("b.txt"))) {
    String line;
    while ((line = br.readLine()) != null) {
        bw.write(line);
        bw.newLine();
    }
} catch (IOException e) {
    e.printStackTrace();
}
```

### 2. 等价的传统写法（说明编译器干了什么）
```java
BufferedReader br = new BufferedReader(new FileReader("a.txt"));
try {
    BufferedWriter bw = new BufferedWriter(new FileWriter("b.txt"));
    try {
        // body
    } finally {
        bw.close();
    }
} finally {
    br.close();
}
```

### 3. 关键规则
- **资源必须实现 `AutoCloseable`**（`java.io.Closeable` 是其子接口，区别在于 `Closeable.close()` 只抛 `IOException`，`AutoCloseable.close()` 抛 `Exception`）。
- **关闭顺序与声明顺序相反**（后声明的先关闭），符合"栈"语义。
- **抑制异常（Suppressed Exception）**：
  - 若 try 体中抛出异常 E1，随后 `close()` 又抛出 E2，则 **E1 是主异常**，E2 被附加到 E1 的 suppressed 列表中。
  - 通过 `Throwable#getSuppressed()` 可以获取被抑制的异常数组。
  - 这解决了传统 try/finally 中 finally 抛异常会"吞掉"原异常的老问题。
- **资源变量在 try 块中视为 final / effectively final**（JDK 9 起允许直接引用外部 final 变量，JDK 7/8 必须在括号内声明）。

### 4. 收益
- 代码更短、更安全，杜绝忘记 `close()` 或忘记空判后 `close()` 的资源泄漏。
- 异常信息更完整（不再丢失原始异常）。

---

## 三、数字字面量下划线

### 1. 是什么
JDK 7 允许在数字字面量中使用下划线 `_` 作为分隔符，仅作可读性用途，编译时被忽略。

```java
int      million   = 1_000_000;
long     creditNo  = 1234_5678_9012_3456L;
int      bin       = 0b1010_0001_1000_0101;
int      hex       = 0xFF_EC_DE_5E;
double   pi        = 3.141_592_653_589_793;
float    salary    = 3_000.50f;
```

### 2. 放置规则（编译器会拒绝的位置）
下划线**必须夹在两个数字之间**。以下都会编译失败：
- 数字开头或结尾：`_123`、`123_`
- 紧邻小数点：`3._14`、`3_.14`
- 紧邻类型后缀：`1000_L`、`3.14_F`
- 紧邻进制前缀：`0_x12`、`0x_12`、`0b_101`

合法位置示意：`0x  FF_EC  L`（前缀与后缀两端不能贴下划线，中间任意）。

### 3. 收益
- 大整数、二进制掩码、信用卡号、身份证号等可读性显著提升，避免数错位数。

---

## 四、运行时数据区（Runtime Data Area）

> JVM 规范定义的运行时内存划分。JDK 7 是一个重要分水岭 —— **字符串常量池从方法区（永久代 PermGen）迁移到了堆**，为 JDK 8 移除 PermGen、改用 Metaspace 做铺垫。

### 1. 整体结构（5 大区域）

| 区域 | 线程私有 / 共享 | 存放内容 | OOM 类型 |
|------|------------------|----------|----------|
| 程序计数器 PC Register | 线程私有 | 当前线程执行字节码的行号指示器 | **唯一不会 OOM** 的区域 |
| 虚拟机栈 JVM Stack | 线程私有 | 每个方法调用对应一个栈帧：局部变量表、操作数栈、动态链接、方法返回地址 | `StackOverflowError`（栈深度超限） / `OutOfMemoryError`（栈无法扩展） |
| 本地方法栈 Native Method Stack | 线程私有 | 为 native 方法服务（HotSpot 中与 JVM 栈合二为一） | 同上 |
| 堆 Heap | **线程共享** | 几乎所有对象实例、数组；GC 主战场 | `OutOfMemoryError: Java heap space` |
| 方法区 Method Area | **线程共享** | 类元信息、常量、静态变量、JIT 编译后的代码缓存 | `OutOfMemoryError: PermGen space`（JDK 7） / `Metaspace`（JDK 8+） |

### 2. 栈帧（Stack Frame）内部
- **局部变量表**：以 slot 为单位（一个 slot 32 位，`long`/`double` 占 2 个 slot）。`this` 占 slot 0（非 static 方法）。
- **操作数栈**：基于栈的字节码执行模型，所有计算都在这里进行。
- **动态链接**：指向运行时常量池中该方法的引用，用于支持动态分派（invokevirtual / invokeinterface）。
- **方法返回地址**：正常返回时恢复调用者 PC；异常返回时由异常表决定。

### 3. 堆的分代（HotSpot，JDK 7 视角）
```
+-------------------- Heap --------------------+
| Young Generation | Old Generation           |
|  Eden | S0 | S1  |                          |
+-------------------- PermGen (方法区实现) ----+   <- JDK 7 仍存在
```
- **Eden : S0 : S1 = 8 : 1 : 1**（默认）。
- 新对象分配在 Eden；Minor GC 后存活对象在 S0/S1 间复制，年龄 +1；达到 `MaxTenuringThreshold`（默认 15）晋升到 Old。
- 大对象（超过 `PretenureSizeThreshold`）直接进 Old。

### 4. 方法区与常量池（JDK 7 的关键变化）

| 项目 | JDK 6 及以前 | **JDK 7** | JDK 8 及以后 |
|------|--------------|-----------|--------------|
| 方法区实现 | PermGen（永久代，堆的一部分） | PermGen（仍存在） | **Metaspace**（本地内存） |
| 字符串常量池 StringTable | 永久代 | **移至 Java 堆** | Java 堆 |
| 类静态变量 | 永久代 | **移至 Java 堆** | Java 堆 |
| 符号引用 | 永久代 | 永久代 | Metaspace |

### 5. `String.intern()` 在 JDK 7 的行为变化
由于字符串常量池移到堆中，`intern()` 实现也变了：

```java
String s = new StringBuilder("go").append("od").toString();
System.out.println(s.intern() == s);   // JDK 6: false ；JDK 7+: true
```

- **JDK 6**：常量池在永久代，`intern()` 会把字符串**复制**到永久代再返回新引用。
- **JDK 7+**：常量池在堆中，`intern()` 发现池中没有时，**直接记录堆中已有对象的引用**，不再复制。因此池中存的可能就是原对象的引用本身。

### 6. 常见 OOM/SOF 触发示例（速查）

| 现象 | 触发方式 | 原因 |
|------|----------|------|
| `StackOverflowError` | 无限递归 | 栈帧深度超过 `-Xss` |
| `OOM: Java heap space` | 持续 `new` 大对象不释放 | 堆放不下 |
| `OOM: PermGen space` (JDK 7) | 大量动态生成类（CGLIB、JSP 热部署） | 永久代放不下类元数据 |
| `OOM: GC overhead limit exceeded` | 98% 时间在 GC，回收 < 2% | 堆几乎满 |
| `OOM: unable to create new native thread` | 创建过多线程 | 受 OS 线程数 / 栈大小限制 |

### 7. 常用 JVM 参数（JDK 7）
```
-Xms512m  -Xmx512m            初始/最大堆
-Xmn200m                      新生代大小
-Xss512k                      每线程栈大小
-XX:PermSize=64m -XX:MaxPermSize=128m   永久代初始/最大（JDK 7 专属，JDK 8 已移除）
-XX:SurvivorRatio=8           Eden:Survivor 比例
-XX:+PrintGCDetails           打印 GC 详情
```

---

## 五、串记忆口诀

- **钻石**：左有右省，类型推断；不可匿名（JDK 9 才放开）。
- **ARM**：括号声明、反序关闭、Suppressed 不丢异常。
- **下划线**：只夹数字中间，不挨前缀后缀小数点。
- **运行时数据区**：1 计数器 + 2 栈（JVM / Native） + 1 堆 + 1 方法区；JDK 7 把 **字符串常量池和静态变量挪进了堆**，为 JDK 8 干掉永久代铺路。
