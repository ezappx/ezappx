[EzappxBuilder](../../index.md) / [com.ezappx.builder.services](../index.md) / [AndroidAppBuilder](./index.md)

# AndroidAppBuilder

`class AndroidAppBuilder : `[`AbstractMobileAppBuilder`](../-abstract-mobile-app-builder/index.md)

编译Android应用

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | `AndroidAppBuilder()`<br>编译Android应用 |

### Properties

| Name | Summary |
|---|---|
| [androidMinSDK](android-min-s-d-k.md) | `var androidMinSDK: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`?` |

### Inherited Properties

| Name | Summary |
|---|---|
| [addResources](../-abstract-mobile-app-builder/add-resources.md) | `lateinit var addResources: () -> `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>添加WWW资源到[projectWWWDir](../-abstract-mobile-app-builder/project-w-w-w-dir.md) |
| [project](../-abstract-mobile-app-builder/project.md) | `lateinit var project: `[`MobileAppProject`](../../com.ezappx.builder.models/-mobile-app-project/index.md)<br>移动应用工程 |
| [projectDir](../-abstract-mobile-app-builder/project-dir.md) | `lateinit var projectDir: Path`<br>Cordova工程目录，应位于[userProjectDir](../-abstract-mobile-app-builder/user-project-dir.md)下 |
| [projectPackage](../-abstract-mobile-app-builder/project-package.md) | `lateinit var projectPackage: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)<br>移动应用包名 |
| [projectWWWDir](../-abstract-mobile-app-builder/project-w-w-w-dir.md) | `lateinit var projectWWWDir: Path`<br>Cordova工程的WWW目录，应位于[projectDir](../-abstract-mobile-app-builder/project-dir.md)下 |
| [userProjectDir](../-abstract-mobile-app-builder/user-project-dir.md) | `lateinit var userProjectDir: Path`<br>用户工程目录 |

### Functions

| Name | Summary |
|---|---|
| [addCordovaPlugins](add-cordova-plugins.md) | `fun addCordovaPlugins(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>添加Cordova插件 |
| [addPlatform](add-platform.md) | `fun addPlatform(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>添加Android平台 |
| [build](build.md) | `fun build(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>编译 |
| [initProject](init-project.md) | `fun initProject(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>初始化Cordova工程 |

### Inherited Functions

| Name | Summary |
|---|---|
| [debug](../-abstract-mobile-app-builder/debug.md) | `fun debug(msg: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>debug级别调试 |
| [info](../-abstract-mobile-app-builder/info.md) | `fun info(msg: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>info级别调试 |
| [initBuilderArgs](../-abstract-mobile-app-builder/init-builder-args.md) | `fun initBuilderArgs(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>初始化包名、Cordova工程目录、Cordova工程WWW目录 |
