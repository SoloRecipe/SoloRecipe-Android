plugins {
    id(ProjectProperties.Gradle.APPLICATION)
    id(ProjectProperties.Gradle.KOTLIN)
    id(ProjectProperties.Gradle.HILT)
    kotlin(ProjectProperties.Gradle.KAPT)
}

android {
    namespace = ProjectProperties.NameSpace.APP
    compileSdk = ProjectProperties.Versions.COMPILE_SDK

    defaultConfig {
        applicationId = ProjectProperties.Id.APPLICATION_ID
        minSdk = ProjectProperties.Versions.MIN_SDK
        targetSdk = ProjectProperties.Versions.TARGET_SDK
        versionCode = ProjectProperties.Versions.VERSION_CODE
        versionName = ProjectProperties.Versions.VERSION_NAME

        testInstrumentationRunner = ProjectProperties.Test.TEST_RUNNER
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
    lint {
        lintConfig = file("$rootDir/lint.xml")
    }
}

dependencies {

    implementation(project(":presentation"))
    implementation(project(":di"))

    implementation(Dependencies.AndroidX.CORE_KTX)
    implementation(Dependencies.AndroidX.APPCOMPAT)
    implementation(Dependencies.Google.MATERIAL)
    implementation(Dependencies.Google.HILT_ANDROID)
    kapt(Dependencies.Google.HILT_COMPILER)
    implementation(Dependencies.AndroidX.COMPOSE_RUNTIME)
    implementation(Dependencies.AndroidX.COMPOSE_MATERIAL3)
    implementation(Dependencies.AndroidX.ACTIVITY_COMPOSE)
    implementation(Dependencies.UnitTest.JUNIT)
    implementation(Dependencies.AndroidTest.ANDROID_JUNIT)
    implementation(Dependencies.AndroidTest.ESPRESSO_CORE)
    implementation(Dependencies.AndroidX.NAVIGATION_COMPOSE)
}
