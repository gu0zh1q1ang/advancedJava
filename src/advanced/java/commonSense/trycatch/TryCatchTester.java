package advanced.java.commonSense.trycatch;

public class TryCatchTester {
    public static void main(String[] args) {
        System.out.println(test());//运行结果1,3,2
    }
    public static Person test(){
        Person person = new Person(28);
        try {
            System.out.println(person);
            return person;
        } catch (Exception e) {
            System.out.println(person);
            person.setAge(23);
            return person;
        } finally {
            person.setAge(18);//finally中对于值类型的修改不起作用，这个a在finally中是3，但是返回的还是2
            System.out.println("finally");
            System.out.println(person);
        }
    }
}

class Person {
    public Person(int age) {
        this.age = age;
    }

    private int age = 0;

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Person{" +
                "age=" + age +
                '}';
    }
}
