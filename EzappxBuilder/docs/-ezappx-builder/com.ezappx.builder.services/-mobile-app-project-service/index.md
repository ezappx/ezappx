[EzappxBuilder](../../index.md) / [com.ezappx.builder.services](../index.md) / [MobileAppProjectService](./index.md)

# MobileAppProjectService

`@Service class MobileAppProjectService`

[MobileAppProject](../../com.ezappx.builder.models/-mobile-app-project/index.md)工具类

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | `MobileAppProjectService(mobileAppProjectRepository: `[`MobileAppProjectRepository`](../../com.ezappx.builder.repositories/-mobile-app-project-repository/index.md)`, mongoOperations: MongoOperations, mobileAppBuilderProperty: `[`MobileAppBuilderProperty`](../../com.ezappx.builder.properties/-mobile-app-builder-property/index.md)`)`<br>[MobileAppProject](../../com.ezappx.builder.models/-mobile-app-project/index.md)工具类 |

### Functions

| Name | Summary |
|---|---|
| [androidAppUri](android-app-uri.md) | `fun androidAppUri(username: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`, projectName: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): Path`<br>获取编译好的Android应用安装包 cordova-android@6.4 : platforms/android/build/outputs/apk/debug/android-debug.apk |
| [createMobileAppProjectFiles](create-mobile-app-project-files.md) | `fun createMobileAppProjectFiles(destinationDir: Path, mobileAppProject: `[`MobileAppProject`](../../com.ezappx.builder.models/-mobile-app-project/index.md)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>从[mobileAppProject](create-mobile-app-project-files.md#com.ezappx.builder.services.MobileAppProjectService$createMobileAppProjectFiles(java.nio.file.Path, com.ezappx.builder.models.MobileAppProject)/mobileAppProject)生成移动应用工程文件，并保存到[destinationDir](create-mobile-app-project-files.md#com.ezappx.builder.services.MobileAppProjectService$createMobileAppProjectFiles(java.nio.file.Path, com.ezappx.builder.models.MobileAppProject)/destinationDir)目录 |
| [loadFileAsResource](load-file-as-resource.md) | `fun loadFileAsResource(file: Path): Resource`<br>将文件作为Resource读取 |
| [prepareMobileAppProjectDir](prepare-mobile-app-project-dir.md) | `fun prepareMobileAppProjectDir(mobileAppProject: `[`MobileAppProject`](../../com.ezappx.builder.models/-mobile-app-project/index.md)`): Path`<br>创建 $UserProjects/$username文件夹作为Cordova工程目录 |
| [saveMobileAppProject2DB](save-mobile-app-project2-d-b.md) | `fun saveMobileAppProject2DB(mobileAppProject: `[`MobileAppProject`](../../com.ezappx.builder.models/-mobile-app-project/index.md)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>保存移动应用工程到数据库 |
