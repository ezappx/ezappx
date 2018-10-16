[EzappxBuilder](../../index.md) / [com.ezappx.builder.services](../index.md) / [AbstractMobileAppBuilder](./index.md)

# AbstractMobileAppBuilder

`abstract class AbstractMobileAppBuilder`

编译移动应用的抽象类

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | `AbstractMobileAppBuilder()`<br>编译移动应用的抽象类 |

### Properties

| Name | Summary |
|---|---|
| [addResources](add-resources.md) | `lateinit var addResources: () -> `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>添加WWW资源到[projectWWWDir](project-w-w-w-dir.md) |
| [project](project.md) | `lateinit var project: `[`MobileAppProject`](../../com.ezappx.builder.models/-mobile-app-project/index.md)<br>移动应用工程 |
| [projectDir](project-dir.md) | `lateinit var projectDir: Path`<br>Cordova工程目录，应位于[userProjectDir](user-project-dir.md)下 |
| [projectPackage](project-package.md) | `lateinit var projectPackage: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)<br>移动应用包名 |
| [projectWWWDir](project-w-w-w-dir.md) | `lateinit var projectWWWDir: Path`<br>Cordova工程的WWW目录，应位于[projectDir](project-dir.md)下 |
| [userProjectDir](user-project-dir.md) | `lateinit var userProjectDir: Path`<br>用户工程目录 |

### Functions

| Name | Summary |
|---|---|
| [addCordovaPlugins](add-cordova-plugins.md) | `abstract fun addCordovaPlugins(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>添加Cordova插件 |
| [addPlatform](add-platform.md) | `abstract fun addPlatform(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>添加移动应用平台 |
| [build](build.md) | `abstract fun build(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>编译移动应用 |
| [debug](debug.md) | `fun debug(msg: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>debug级别调试 |
| [info](info.md) | `fun info(msg: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>info级别调试 |
| [initBuilderArgs](init-builder-args.md) | `fun initBuilderArgs(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>初始化包名、Cordova工程目录、Cordova工程WWW目录 |
| [initProject](init-project.md) | `abstract fun initProject(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>初始化Cordova工程 |

### Companion Object Properties

| Name | Summary |
|---|---|
| [BASE_PACKAGE](-b-a-s-e_-p-a-c-k-a-g-e.md) | `const val BASE_PACKAGE: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |
| [PREFIX](-p-r-e-f-i-x.md) | `const val PREFIX: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |
| [log](log.md) | `val log: Log` |

### Inheritors

| Name | Summary |
|---|---|
| [AndroidAppBuilder](../-android-app-builder/index.md) | `class AndroidAppBuilder : `[`AbstractMobileAppBuilder`](./index.md)<br>编译Android应用 |
