[EzappxBuilder](../../index.md) / [com.ezappx.builder.controllers](../index.md) / [MobileAppBuilderController](./index.md)

# MobileAppBuilderController

`@CrossOrigin(["*"]) @RestController @RequestMapping(["/api/v1/android"]) class MobileAppBuilderController`

EzappxBuilder的REST接口

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | `MobileAppBuilderController(mobileAppProjectService: `[`MobileAppProjectService`](../../com.ezappx.builder.services/-mobile-app-project-service/index.md)`)`<br>EzappxBuilder的REST接口 |

### Functions

| Name | Summary |
|---|---|
| [availableMobileOS](available-mobile-o-s.md) | `fun availableMobileOS(): `[`List`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)`<`[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`>`<br>目前运行环境可编译的移动操作系统平台类型 |
| [buildAndroidMobileApp](build-android-mobile-app.md) | `fun buildAndroidMobileApp(mobileAppProject: `[`MobileAppProject`](../../com.ezappx.builder.models/-mobile-app-project/index.md)`): `[`MobileAppBuilderResponse`](../../com.ezappx.builder.responses/-mobile-app-builder-response/index.md)<br>编译Android应用的接口 |
| [downloadAndroidApp](download-android-app.md) | `fun downloadAndroidApp(username: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`, projectName: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): ResponseEntity<Resource>`<br>下载文件的接口 |
