pluginManagement {
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.PREFER_PROJECT)
    repositories {
        google()
        mavenCentral()
//        maven { setUrl("https://storage.zego.im/maven") }  // ✅ Zego Repository
//        maven { setUrl("https://jitpack.io") }  // ✅ JitPack Repository
    }
}




rootProject.name = "DIacare"
include(":app")
 