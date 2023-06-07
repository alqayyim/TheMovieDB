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
    /*implementation 'androidx.core:core-ktx:1.7.0'
    implementation 'androidx.appcompat:appcompat:1.6.1'
    implementation 'com.google.android.material:material:1.9.0'
    implementation 'androidx.constraintlayout:constraintlayout:2.1.4'
    implementation 'androidx.navigation:navigation-fragment-ktx:2.5.3'
    implementation 'androidx.navigation:navigation-ui-ktx:2.5.3'*/
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")

    with(Dependencies) {
        implementation(shimmer)
    }
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
}