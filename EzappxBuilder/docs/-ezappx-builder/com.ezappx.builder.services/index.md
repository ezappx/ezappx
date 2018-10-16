[EzappxBuilder](../index.md) / [com.ezappx.builder.services](./index.md)

## Package com.ezappx.builder.services

### Types

| Name | Summary |
|---|---|
| [AbstractMobileAppBuilder](-abstract-mobile-app-builder/index.md) | `abstract class AbstractMobileAppBuilder`<br>编译移动应用的抽象类 |
| [AndroidAppBuilder](-android-app-builder/index.md) | `class AndroidAppBuilder : `[`AbstractMobileAppBuilder`](-abstract-mobile-app-builder/index.md)<br>编译Android应用 |
| [MobileAppProjectService](-mobile-app-project-service/index.md) | `class MobileAppProjectService`<br>[MobileAppProject](../com.ezappx.builder.models/-mobile-app-project/index.md)工具类 |

### Functions

| Name | Summary |
|---|---|
| [androidBuilder](android-builder.md) | `fun androidBuilder(init: `[`AndroidAppBuilder`](-android-app-builder/index.md)`.() -> `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)`): `[`AndroidAppBuilder`](-android-app-builder/index.md)<br>Type-Safe构建函数，用于调用[AndroidAppBuilder](-android-app-builder/index.md) |
