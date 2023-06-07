import java.io.File
import java.io.FileInputStream
import java.util.Properties

plugins {
    id(Plugins.androidApp)
    id(Plugins.kotlinAndroid)
    id(Plugins.kotlinKapt)
    id(Plugins.safeArgs)
}

android {
    compileSdk = Versions.compileSdk

    defaultConfig {
        applicationId = "com.asad.themoviedb"
        minSdk = Versions.minSdk
        targetSdk = Versions.targetSdk
        versionCode = Versions.versionCode
        versionName = Versions.versionName

        buildConfigField("String", "BASE_URL", "\"https://api.themoviedb.org/3/\"")
        buildConfigField("String", "API_KEY", getApiKey())
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
    buildFeatures {
        viewBinding = true
    }
}

dependencies {
    with(Dependencies.Google) {
        implementation(material)
        implementation(flexBox)
    }
    with(Dependencies.Module) {
        implementation(project(core))
        implementation(project(data))
        implementation(project(domain))
    }
    with(Dependencies.Chucker) {
        debugImplementation(chucker)
        releaseImplementation(chuckerNoOp)
    }
    with(Dependencies.AndroidX) {
        implementation(appCompat)
        implementation(constraintLayout)
        implementation(navFragment)
        implementation(navUi)
        implementation(fragment)
        implementation(window)
        implementation(paging)
        implementation(workRuntime)
    }
    with(Dependencies.Koin){
        implementation(core)
        implementation(android)
    }
    with(Dependencies.Glide) {
        implementation(glide)
        annotationProcessor(compiler)
    }
    with(Dependencies.SquareUp) {
        implementation(retrofit2)
        implementation(retrofit2Converter)
        implementation(okhttp3Logging)
    }
    with(Dependencies.Test) {
        testImplementation(junit4)
        testImplementation(coroutinesTest)
        testImplementation(archTesting)
        testImplementation(truth)
        testImplementation(mockK)
        testImplementation(koinTest)
    }
}

fun getApiKey(): String {
    val prop = Properties().apply {
        load(FileInputStream(File(rootProject.rootDir, "./local.properties")))
    }
    return prop.getProperty("MOVIE_API_KEY")
}