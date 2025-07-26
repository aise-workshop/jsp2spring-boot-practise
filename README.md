# autodev-legacy

我正在创建一个 Java CLI 应用，以自动转换遗留的 JSP 项目为 Spring Boot 应用；我需要你帮助我创建基本的脚手架，我初步想到的技术栈：

- 使用 Gradle 用来作为构建系统
- Picocli 创建 CLI 接口
- JavaPoet 来生成 Java 文件，或者 Freemarker
- 源码与编译结果解析：JavaParser 解析 Java 代码，`jsp-parser` 来解析 JSP 代码，ASM 来解析 JSP 编译后的 jar 包，JavaParser + ASM 应该要能结合到 Graph 表示和使用，
- Jsoup 转换生成后的 HTML，如果有的话
- web.xml 解析
- OpenRewrite 以方便迁移特定语言版本。
- 包名：`com.phodal.legacy`

我预期的包分层类似于：

```java
jsp2springboot-cli/
├── build.gradle
├── settings.gradle
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/phodal/legacy/
│   │   │       ├── CliApp.java                # 主程序入口（Picocli）
│   │   │       ├── parser/                    # 源码与字节码解析
│   │   │       │   ├── JavaCodeAnalyzer.java
│   │   │       │   ├── JspAnalyzer.java
│   │   │       │   └── BytecodeAnalyzer.java
│   │   │       ├── generator/                 # 代码生成器
│   │   │       │   └── SpringBootCodeGenerator.java
│   │   │       ├── model/                     # 抽象模型（Graph、Component）
│   │   │       │   ├── GraphNode.java
│   │   │       │   ├── ComponentModel.java
│   │   │       │   └── DependencyGraph.java
│   │   │       ├── utils/                     # 工具函数
│   │   │       │   ├── FileUtils.java
│   │   │       │   └── LoggingUtils.java    
│   │   │       ├── ai/                        # AI 智能体模块
│   │   │       │   ├── PromptBuilder.java
│   │   │       │   └── LlmProvider.java       # LLM Prompt 执行器
│   │   │       ├── services/                    
│   │   │       │   └── ProcessManager.java
│   │   │       ├── agent/                     # AI 智能体模块
│   │   │       │   ├── BuildFixAgent.java
│   │   │       │   ├── GradleBuildFixAgent.java    
│   │   │       │   └── xxxAgent.java           
│   │   │       ├── tool/                      # AI 工具集（供 agent 使用）
│   │   │       │   ├── Tool.java
│   │   │       │   ├── WriteFileTool.java
│   │   │       │   ├── ...Tool.java
│   │   │       │   └── ToolExecutor.java           # 代码摘要/解释
│   │   │       ├── mcp/                           # MCP 协议
│   │   │       └── config/                    # 配置模块
│   │   │           └── AppConfig.java
│   │
│   └── test/
│       └── ...                                # JUnit 测试代码

```
