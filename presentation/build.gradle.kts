plugins {
    id(ProjectProperties.Gradle.LIBRARY)
    id(ProjectProperties.Gradle.KOTLIN)
    kotlin(ProjectProperties.Gradle.KAPT)
}

android {
    namespace = ProjectProperties.NameSpace.PRESENTATION
    compileSdk = ProjectProperties.Versions.COMPILE_SDK

    defaultConfig {
        minSdk = ProjectProperties.Versions.MIN_SDK

        testInstrumentationRunner = ProjectProperties.Test.TEST_RUNNER
        consumerProguardFiles(ProjectProperties.Files.CONSUMER_PROGUARD)
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile(ProjectProperties.Files.DEFAULT_PROGUARD),
                ProjectProperties.Files.PROGUARD
            )
        }
    }
    compileOptions {
        sourceCompatibility = ProjectProperties.Versions.JAVA_VERSION
        targetCompatibility = ProjectProperties.Versions.JAVA_VERSION
    }
    kotlinOptions {
        jvmTarget = ProjectProperties.Versions.JVM_TARGET
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.4.1"
    }
}

dependencies {

    implementation(project(":design_system"))
    implementation(project(":domain"))

    implementation(Dependencies.AndroidX.CORE_KTX)
    implementation(Dependencies.AndroidX.APPCOMPAT)
    implementation(Dependencies.Google.MATERIAL)
    implementation(Dependencies.Google.HILT_ANDROID)
    implementation(Dependencies.AndroidX.PAGING)
    implementation(Dependencies.AndroidX.PAGING_COMPOSE)
    kapt(Dependencies.Google.HILT_COMPILER)
    implementation(Dependencies.AndroidX.COMPOSE_RUNTIME)
    implementation(Dependencies.AndroidX.COMPOSE_MATERIAL3)
    implementation(Dependencies.AndroidX.COMPOSE_TOOLING)
    implementation(Dependencies.UnitTest.JUNIT)
    implementation(Dependencies.AndroidTest.ANDROID_JUNIT)
    implementation(Dependencies.AndroidTest.ESPRESSO_CORE)
    implementation(Dependencies.AndroidX.NAVIGATION_COMPOSE)
    implementation(Dependencies.AndroidX.COMPOSE_UI_UTIL)
    implementation(Dependencies.AndroidX.LIFECYCLE_COMPOSE)
    implementation(Dependencies.AndroidX.HILT_NAVIGATION_COMPOSE)
    implementation(Dependencies.AndroidX.LIFECYCLE_RUNTIME_COMPOSE)
    implementation(Dependencies.Libraries.RETROFIT)
    implementation(Dependencies.Libraries.LANDSCAPIST_GLIDE)
}