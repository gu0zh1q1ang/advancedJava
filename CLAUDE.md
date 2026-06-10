# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Project Nature

This is a personal **Java learning / experimentation repository**, not a deployable application. It is an IntelliJ IDEA project (module file `.idea/socket.iml`) — **not Maven or Gradle**. Each `main` method is typically a standalone demo of a Java concept (JVM internals, threading, design patterns, collections, algorithms, etc.).

## Build & Run

- **JDK**: 1.8 (configured in `.idea/misc.xml`, `LANGUAGE_LEVEL="JDK_1_8"`).
- **Source root**: `src/`. **Resource root**: `conf/`. **Output**: `target/` (excluded from VCS).
- **Dependencies**: plain `.jar` files in `repo/` — added via IntelliJ's module library (`jarDirectory` entry in `socket.iml`), not via a build tool. To add a new dependency, drop the jar into `repo/` and let IntelliJ refresh the library.
- **Build / run**: use IntelliJ IDEA — there is no CLI build script. To run a single class, execute its `main` method from the IDE. To compile manually:
  ```sh
  javac -d target -cp "repo/*;conf" src/<path>/<File>.java
  java -cp "target;conf;repo/*" <fully.qualified.ClassName>
  ```
  (Use `;` as classpath separator on Windows, `:` on Unix.)
- **No test framework wiring**: JUnit 3.8.1 and 4.12 jars are present in `repo/`, but there is no test source root, no test runner script, and no `pom.xml` / `build.gradle`. Treat "tests" as ad-hoc `main` methods.

## Source Layout (big picture)

Four top-level source trees under `src/`, each its own package root — they are independent collections of demos, not a layered application:

- `src/advanced/java/` — core Java study notes, grouped by topic:
  - `jvm/` (classloading, bytecode, string pool, VM specifics), `threads/` (incl. `threadpool/`), `Collection/`, `generic/`, `designPattern/` (`adapter`, `factoryMethod`, `singleton`), `commonSense/` (inner/outer classes, inheritance, `jol` object layout, `trycatch`, `size`, etc.).
- `src/gzq/byd/com/` — broad mixed package: `socket`, `dynamic` (proxies), `jedis`, `memcached`, `messages`, `kmp`, `sortAlgorithm`, `graph`, `BTree`, `annotation`, `enumeric`, `valves` (Tomcat valves), `luogu/` (algorithm problems, each in its own `Pxxxx/` package with a `.md` problem statement), `leet/`, etc.
- `src/gzq/csc/` — small standalone files (`IOFile`, `ScheduledTaskTest`).
- `src/org/gzq/algorithm/` — algorithm utilities (`def/`, `util/`).
- `src/JUCTest/` — concurrency experiments.

When asked to add an example for a concept, place it under the matching existing topic directory rather than creating a new top-level tree. Package declarations follow the on-disk path exactly (e.g. `package advanced.java.commonSense.size;`).

## Conventions worth knowing

- **Logging**: `conf/log4j.properties` configures log4j 1.x (`stdout` ConsoleAppender, UTF-8, pattern includes `%d %t [%-5p] %c.%M(%F:%L) - %m%n`). `conf/` is mounted as a resource root, so this is on the classpath at runtime.
- **Servlet/Tomcat artifacts** live under `webroot/` (e.g. `HServlet.java`) and `repo/` ships Tomcat jars (`catalina.jar`, `tomcat-coyote.jar`, `HeaderValve.jar`, `Basic.jar`) — these are referenced by the `valves/` package experiments.
- **Luogu problems**: each problem lives in `src/gzq/byd/com/luogu/P<id>/` with the Java solution and an adjacent `P<id>.md` describing the problem. Follow this pattern when adding new ones.
- **Commit message style** (from `git log`): short imperative, often Chinese, optionally prefixed with `feat:` / `fix:` (note: existing commits sometimes use the full-width colon `：`, e.g. `feat：...`).

## Git

- Working branch is `dev`; PRs target `master`.
- `.gitignore` excludes `.idea`, `target`, and `.iml` — never commit IDE/build artifacts.
