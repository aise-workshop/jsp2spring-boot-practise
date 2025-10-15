# JSP 2 Spring Boot Practice

## Initial Prompt

```
Project Goal: Create a Java CLI application that automatically converts legacy JSP projects to modern Spring Boot applications.

Technology Stack Selection:
1. Build System: Gradle
2. CLI Framework: Picocli (Command Line Interface)
3. Code Generation: JavaPoet or Freemarker
4. Parsing Tools:
   - JavaParser: Parse Java source code
   - jsp-parser: Parse JSP files
   - ASM: Parse compiled bytecode (jar packages)
   - Need to combine JavaParser + ASM to build code dependency graph
5. HTML Processing: Jsoup (if needed)
6. Code Migration: OpenRewrite (for language version migration)

Project Structure Design:

jsp2springboot-cli/
├── build.gradle
├── settings.gradle
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/workshop/legacy/
│   │   │       ├── CliApp.java                # Main program entry (Picocli)
│   │   │       ├── parser/                    # Source code and bytecode parsing module
│   │   │       │   ├── JavaCodeAnalyzer.java # Java code analyzer
│   │   │       │   ├── JspAnalyzer.java      # JSP file analyzer
│   │   │       │   └── BytecodeAnalyzer.java # Bytecode analyzer
│   │   │       ├── generator/                 # Code generation module
│   │   │       │   └── SpringBootCodeGenerator.java
│   │   │       ├── model/                     # Data models
│   │   │       ├── converter/                 # Conversion logic
│   │   │       └── utils/                     # Utility classes
│   │   └── resources/
│   └── test/
└── README.md

Please help create the basic scaffolding for this project, including Gradle configuration, main class framework structure, and basic CLI command interface.
```
