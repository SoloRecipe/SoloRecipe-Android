plugins {
    id(ProjectProperties.Gradle.LIBRARY)
    id(ProjectProperties.Gradle.KOTLIN)
}

android {
    namespace = ProjectProperties.NameSpace.DOMAIN
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
}

dependencies {

    implementation(Dependencies.UnitTest.JUNIT)
    implementation(Dependencies.AndroidTest.ANDROID_JUNIT)
    implementation(Dependencies.AndroidTest.ESPRESSO_CORE)
}