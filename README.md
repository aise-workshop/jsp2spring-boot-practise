# JSP 2 Spring Boot 练习

> **English Version**: For English documentation, please refer to [README_EN.md](README_EN.md)

## 初始提示词

```
项目目标：创建一个 Java CLI 应用，自动将遗留的 JSP 项目转换为现代化的 Spring Boot 应用。

技术栈选择：
1. 构建系统：Gradle
2. CLI 框架：Picocli（命令行接口）
3. 代码生成：JavaPoet 或 Freemarker
4. 解析工具：
   - JavaParser：解析 Java 源码
   - jsp-parser：解析 JSP 文件
   - ASM：解析编译后的字节码（jar 包）
   - 需要将 JavaParser + ASM 结合，构建代码依赖图
5. HTML 处理：Jsoup（如需要）
6. 代码迁移：OpenRewrite（用于语言版本迁移）

项目结构设计：

jsp2springboot-cli/
├── build.gradle
├── settings.gradle
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/workshop/legacy/
│   │   │       ├── CliApp.java                # 主程序入口（Picocli）
│   │   │       ├── parser/                    # 源码与字节码解析模块
│   │   │       │   ├── JavaCodeAnalyzer.java # Java 代码分析器
│   │   │       │   ├── JspAnalyzer.java      # JSP 文件分析器
│   │   │       │   └── BytecodeAnalyzer.java # 字节码分析器
│   │   │       ├── generator/                 # 代码生成模块
│   │   │       │   └── SpringBootCodeGenerator.java
│   │   │       ├── model/                     # 数据模型
│   │   │       ├── converter/                 # 转换逻辑
│   │   │       └── utils/                     # 工具类
│   │   └── resources/
│   └── test/
└── README.md

请帮助创建这个项目的基础脚手架，包括 Gradle 配置、主要类的框架结构，以及基本的 CLI 命令接口。
```
