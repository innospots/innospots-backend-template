# innospots-backend-template
后端工程模板


## 示例账户

默认账户：

系统管理员：sadmin/12345678

项目管理员：padmin/12345678

普通用户：dev/dev12345,  opt/opt12345, test/test12345


## 工程模块说明

### backend-template-core
业务核心逻辑功能模块，包含核心业务逻辑、领域模型、服务接口等基础功能实现。

模块内容：
- **dao**: 数据访问对象，负责数据库操作
- **operator**: 数据库的增删改查操作定义
- **converter**: 数据转换器，负责不同数据模型之间的转换
- **model**: 业务模型定义
- **entity**: 数据库实体定义
- **service**: 通用业务服务层
- **scenario**: 业务场景定义
- **module**: 模块定义

### backend-template-console
管理平台模块，包含管理平台的controller和管理端功能，提供后台管理相关的接口和业务逻辑。

模块内容：
- **controller**: 管理平台的控制器层，处理HTTP请求
- **service**: 管理平台专用的业务服务

### backend-template-runtime
独立API运行时模块，包含单独独立的API所需要的相关endpoint和模块定义，用于支持独立运行的API服务。

模块内容：
- **module**: 独立API服务的模块定义
- **scenario**: 独立API的业务场景
- **service**: 独立API服务层
- **endpoint**: API端点定义

### backend-template-server
服务定义模块，包含以下子模块：
- **backend-template-server-admin**: 管理平台服务，提供后台管理系统的启动和运行环境
- **backend-template-server-api**: API服务，提供对外API接口服务的启动和运行环境
- 可扩展其他独立服务的定义


## 应用扩展

应用扩展在 **backend-template-console** 模块中添加，在 `resource/META-INF` 目录下，增加 `innospots-extension-meta.json` 扩展文件定义。

### 扩展配置文件说明

扩展配置文件路径：`backend-template-console/src/main/resources/META-INF/innospots-extension-meta.json`

#### 关键字段说明

| 字段名 | 类型 | 必填 | 说明 |
|--------|------|------|------|
| `name` | String | 是 | 扩展名称，支持配置变量引用 |
| `i18nNames` | Object | 否 | 国际化名称，支持多语言配置（如 en_US, zh_CN） |
| `version` | String | 是 | 扩展版本号，建议使用语义化版本 |
| `icon` | String | 否 | 扩展图标路径，相对于静态资源目录 |
| `publishTime` | String | 是 | 发布时间，格式：YYYY-MM-DD |
| `vendor` | String | 是 | 供应商/开发者名称 |
| `extKey` | String | 是 | 扩展唯一标识符，用于系统识别 |
| `kernelVersion` | String | 是 | 依赖的内核版本 |
| `description` | String | 否 | 扩展描述信息 |
| `endpoints` | Array | 是 | 扩展提供的API端点列表 |
| `basePackages` | Array | 是 | 扩展的基础包路径，用于组件扫描 |
| `modules` | Array | 否 | 扩展包含的功能模块配置 |

#### modules 模块配置说明

| 字段名 | 类型 | 必填 | 说明 |
|--------|------|------|------|
| `name` | String | 是 | 模块名称，支持配置变量引用 |
| `itemKey` | String | 是 | 模块唯一标识符 |
| `uri` | String | 是 | 模块访问路径 |
| `icon` | String | 否 | 模块图标 |
| `i18nNames` | Object | 否 | 模块国际化名称 |
| `items` | Array | 否 | 模块包含的子菜单项 |

#### items 子菜单项配置说明

| 字段名 | 类型 | 必填 | 说明 |
|--------|------|------|------|
| `name` | String | 是 | 菜单项名称，支持配置变量引用 |
| `itemKey` | String | 是 | 菜单项唯一标识符 |
| `uri` | String | 是 | 菜单项访问路径 |
| `i18nNames` | Object | 否 | 菜单项国际化名称 |

#### 配置示例

参考 `backend-template-console/src/main/resources/META-INF/innospots-extension-meta.json` 文件中的完整配置示例。



## 前端微应用配置

前端微应用模板工程：[innospots-micro-app-template](https://github.com/innospots/innospots-micro-app-template)

### 配置步骤

#### 1. 修改环境配置文件

在前端工程的 `.env` 配置文件中修改 `UMI_APP_PUBLIC_PATH` 变量：

```bash
UMI_APP_PUBLIC_PATH=/mf/backend-template
```

**说明**：此变量为微应用的访问路径，作为微应用的唯一标识。

#### 2. 配置 WebJar 打包路径

在前端工程的 `pom.xml` 文件中配置资源文件的目标路径，将前端构建产物打包为 WebJar：

```xml
<build>
    <resources>
        <resource>
            <directory>dist</directory>
            <targetPath>META-INF/resources/mf/backend-template</targetPath>
        </resource>
    </resources>
</build>
```

**关键配置说明**：
- `<directory>`：前端构建输出目录（通常为 `dist`）
- `<targetPath>`：WebJar 中的目标路径，格式为 `META-INF/resources/{endpoints路径}`
- `targetPath` 的路径必须与 `UMI_APP_PUBLIC_PATH` 和后端 `endpoints` 保持一致

**路径对应关系**：
- 前端 `UMI_APP_PUBLIC_PATH`：`/mf/backend-template`
- 前端 pom.xml `targetPath`：`META-INF/resources/mf/backend-template`
- 后端 `endpoints`：`["/mf/backend-template"]`

#### 3. 保持路径一致性

前后端路径必须保持一致，确保微应用能够正确加载：

**前端 .env 配置**：
```bash
UMI_APP_PUBLIC_PATH=/mf/backend-template
```

**前端 pom.xml 配置**：
```xml
<targetPath>META-INF/resources/mf/backend-template</targetPath>
```

**后端 innospots-extension-meta.json 配置**：
```json
{
  "endpoints": ["/mf/backend-template"],
  ...
}
```

#### 4. URI 路径规范

所有 `modules` 和 `items` 中的 `uri` 定义必须满足以下要求：

1. **必须以 `endpoints` 路径开头**
   ```json
   {
     "endpoints": ["/mf/backend-template"],
     "modules": [
       {
         "uri": "/mf/backend-template/module",
         "items": [
           {
             "uri": "/mf/backend-template/hello-world"
           }
         ]
       }
     ]
   }
   ```

2. **全局唯一性**：每个 `uri` 在整个系统中必须唯一，避免路径冲突

3. **endpoints 不冲突**：`endpoints` 的定义不能与其他微应用的 `endpoints` 冲突

### 配置检查清单

- [ ] 前端 `.env` 中的 `UMI_APP_PUBLIC_PATH` 已正确配置
- [ ] 前端 `pom.xml` 中的 `targetPath` 路径已正确配置（格式：`META-INF/resources{UMI_APP_PUBLIC_PATH}`）
- [ ] 后端 `innospots-extension-meta.json` 中的 `endpoints` 与前端路径一致
- [ ] 所有 `modules` 和 `items` 的 `uri` 都以 `endpoints` 路径开头
- [ ] 所有 `uri` 在系统中全局唯一
- [ ] `endpoints` 路径不与其他微应用冲突

### 完整配置示例

#### 前端工程配置

**前端 .env 文件**：
```bash
UMI_APP_PUBLIC_PATH=/mf/backend-template
```

**前端 pom.xml 文件**：
```xml
<project>
    <groupId>io.innospots</groupId>
    <artifactId>innospots-micro-app-ui</artifactId>
    <version>1.0.0-SNAPSHOT</version>
    <packaging>jar</packaging>

    <build>
        <resources>
            <resource>
                <directory>dist</directory>
                <targetPath>META-INF/resources/mf/backend-template</targetPath>
            </resource>
        </resources>
    </build>
</project>
```

#### 后端工程配置

**后端 innospots-extension-meta.json 文件**：
```json
{
  "extKey": "backend-template",
  "endpoints": ["/mf/backend-template"],
  "modules": [
    {
      "itemKey": "tpl-module",
      "uri": "/mf/backend-template/module",
      "items": [
        {
          "itemKey": "template-hello-world",
          "uri": "/mf/backend-template/hello-world"
        }
      ]
    }
  ]
}
```

**注意事项**：
- 路径必须以 `/` 开头
- 建议使用 `/mf/` 作为微应用的统一前缀
- 路径命名建议使用小写字母和连字符（kebab-case）



