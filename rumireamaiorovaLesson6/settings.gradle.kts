pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "ru.mirea.maiorova.Lesson6"
include(":app")
include(":app:securesharedpreferences")
include(":app:internalfilestorage")
include(":app:notebook")
include(":app:employeedb")
