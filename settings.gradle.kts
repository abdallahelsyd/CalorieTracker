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

rootProject.name = "CalorieTracker"
include(":app")
include(":core")
include(":onboarding")
include(":onboarding:onboarding_presentaion")
include(":onboarding:onboarding_domain")
include(":tracker")
include(":tracker:tracker_data")
include(":tracker:tracker_presentation")
include(":tracker:tracker_domain")
