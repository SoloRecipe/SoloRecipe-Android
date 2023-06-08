import com.android.build.gradle.internal.cxx.configure.gradleLocalProperties

plugins {
    id(ProjectProperties.Gradle.LIBRARY)
    id(ProjectProperties.Gradle.KOTLIN)
    kotlin(ProjectProperties.Gradle.KAPT)
}

android {
    namespace = ProjectProperties.NameSpace.DATA
    compileSdk = ProjectProperties.Versions.COMPILE_SDK

    defaultConfig {
        minSdk = ProjectProperties.Versions.MIN_SDK

        testInstrumentationRunner = ProjectProperties.Test.TEST_RUNNER
        consumerProguardFiles(ProjectProperties.Files.CONSUMER_PROGUARD)

        buildConfigField("String", "BASE_URL", gradleLocalProperties(rootDir).getProperty("base_url"))
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

    implementation(project(":domain"))

    implementation(Dependencies.AndroidX.CORE_KTX)
    implementation(Dependencies.AndroidX.APPCOMPAT)
    implementation(Dependencies.AndroidX.PAGING)
    implementation(Dependencies.AndroidX.PAGING_COMPOSE)
    implementation(Dependencies.Google.MATERIAL)
    implementation(Dependencies.Google.HILT_ANDROID)
    kapt(Dependencies.Google.HILT_COMPILER)
    implementation(Dependencies.Libraries.RETROFIT)
    implementation(Dependencies.Libraries.RETROFIT_CONVERTER)
    implementation(Dependencies.UnitTest.JUNIT)
    implementation(Dependencies.AndroidTest.ANDROID_JUNIT)
    implementation(Dependencies.AndroidTest.ESPRESSO_CORE)
    implementation(Dependencies.AndroidX.DATASTORE)
}