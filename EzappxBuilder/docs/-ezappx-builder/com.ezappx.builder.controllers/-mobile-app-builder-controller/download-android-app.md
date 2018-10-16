[EzappxBuilder](../../index.md) / [com.ezappx.builder.controllers](../index.md) / [MobileAppBuilderController](index.md) / [downloadAndroidApp](./download-android-app.md)

# downloadAndroidApp

`@GetMapping(["/download/{username:.+}/{projectName:.+}"]) fun downloadAndroidApp(@PathVariable username: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`, @PathVariable projectName: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): ResponseEntity<Resource>`

下载文件的接口

### Parameters

`username` - 用户名

`projectName` - 工程名

**Return**
文件类型的响应

