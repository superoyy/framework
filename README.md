# dukla-base

`dukla-base` 是 Dukla 平台的 Java 公共框架工程。它的主要目的是：

1. **定义统一的父 POM**：为基于本框架的 Java 项目提供统一的依赖版本管理（Spring Boot / Spring Cloud BOM）、JDK 编译配置、编码插件与发布流程。
2. **提供可选工具模块**：按需引入，不引入即不产生任何依赖与配置。

## 技术栈

| 名称 | 版本 |
| --- | --- |
| JDK | 21 |
| Spring Boot | 3.5.6 |
| Spring Cloud | 2025.0.0 |
| SpringDoc OpenAPI | 2.5.0 |
| Apache POI | 3.16 |
| dom4j | 1.6.1 |
| HBase Client (shaded) | 1.3.1 |

## 模块说明

所有模块均为可选，业务项目按需依赖。

| 模块 | 说明 |
| --- | --- |
| `dukla-util` | 通用工具：字符串/数字判断、MD5、文件读取等；Excel 导入导出（基于 POI，支持列类型、校验规则与错误提示）；JSON、XML 生成 |
| `dukla-jpa` | 基于 Hibernate / Spring Data JPA 的 `BaseEntity`、`HibernateBaseDao` 通用 DAO 基类与查询封装 |
| `dukla-sql` | 原生 SQL 访问：`SqlHandler` 提供单值/单记录/列表查询、更新、批量执行、存储过程调用，默认提供 MySQL 实现 |
| `dukla-mongodb` | MongoDB 访问：`BaseDocumentEntity`、`MongodbBaseDao` 与 `MongodbHandler` 通用增删改查、GridFS 文件操作 |
| `dukla-hbase` | HBase 访问：`HBaseConfig`、`HbaseConnection`、`HBaseDao`、`HBaseRecord` 等通用封装 |
| `dukla-codec` | 车联网协议编解码：GB/T 32960、GB/T 17691 的消息、数据单元与 BCC 校验 |
| `dukla-rest` | REST 约定：`RestfulResponse` 统一响应、`StatusCode` 状态码、`PageVo` 分页结构、`QueryVo` 查询参数 |

## 快速开始

### 1. 继承父 POM

业务工程的 `pom.xml` 继承 `dukla-base` 即可获得统一的依赖版本与编译配置，**无需再声明版本号**：

```xml
<parent>
    <groupId>com.dukla</groupId>
    <artifactId>dukla-base</artifactId>
    <version>1.0.3-SNAPSHOT</version>
</parent>
```

### 2. 按需引入工具模块

```xml
<dependencies>
    <dependency>
        <groupId>com.dukla</groupId>
        <artifactId>dukla-rest</artifactId>
    </dependency>
    <dependency>
        <groupId>com.dukla</groupId>
        <artifactId>dukla-jpa</artifactId>
    </dependency>
</dependencies>
```

版本由父 POM 的 `dependencyManagement` 统一管理，子模块统一继承父 POM 的版本号，子模块自身不再声明 `<version>`。

## 构建

```bash
mvn clean install -DskipTests
```

仅校验 POM 与编译：

```bash
mvn validate
```

发布/热修复由 `jgitflow-maven-plugin` 管理，配置了 `autoVersionSubmodules`，release 时会自动同步所有子模块版本。
