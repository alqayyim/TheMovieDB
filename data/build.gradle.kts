plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
}

android {
    compileSdk = 32

    defaultConfig {
        minSdk = Versions.minSdk
        targetSdk = Versions.targetSdk

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {
    with(Dependencies.Module) {
        implementation(project(core))
        implementation(project(domain))
    }
    with(Dependencies.Koin){
        implementation(core)
        implementation(android)
    }
    with(Dependencies.Google){
        implementation(gson)
        implementation(material)
    }
    with(Dependencies.AndroidX){
        implementation(paging)
        implementation(core)
        implementation(appCompat)
    }
    with(Dependencies.SquareUp) {
        implementation(retrofit2Converter)
        implementation(retrofit2)
        implementation(okhttp3Logging)
    }
}