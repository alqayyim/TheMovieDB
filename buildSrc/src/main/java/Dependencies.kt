object Dependencies {

    object AndroidX {
        const val core = "androidx.core:core-ktx:1.7.0"
        const val appCompat =
            "androidx.appcompat:appcompat:1.6.1"
        const val constraintLayout =
            "androidx.constraintlayout:constraintlayout:2.1.4"
        const val workRuntime = "androidx.work:work-runtime-ktx:2.7.1"

        const val paging = "androidx.paging:paging-runtime:3.0.0"
        const val navFragment = "androidx.navigation:navigation-fragment-ktx:${Versions.navVersion}"
        const val navUi = "androidx.navigation:navigation-ui-ktx:${Versions.navVersion}"
        const val fragment = "androidx.fragment:fragment-ktx:1.5.2"

        const val window = "androidx.window:window:1.1.0-alpha03"
    }

    object Kotlin {
        const val coroutinesCore =
            "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutinesVersion}"
        const val coroutinesAndroid =
            "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutinesVersion}"
    }

    object Glide {
        const val glide = "com.github.bumptech.glide:glide:${Versions.glideVersion}"
        const val compiler = "com.github.bumptech.glide:compiler:${Versions.glideVersion}"
    }

    object Koin {
        const val android = "io.insert-koin:koin-android:${Versions.koin}"
        const val core = "io.insert-koin:koin-core:${Versions.koin}"
    }

    object Chucker {
        const val chucker = "com.github.chuckerteam.chucker:library:${Versions.chucker_version}"
        const val chuckerNoOp = "com.github.chuckerteam.chucker:library-no-op:${Versions.chucker_version}"
    }

    object Google {
        const val gson = "com.google.code.gson:gson:2.9.1"
        const val material =
            "com.google.android.material:material:${Versions.materialVersion}"
        const val flexBox = "com.google.android.flexbox:flexbox:${Versions.flexBoxVersion}"
    }


    object Test {
        const val koin = "io.insert-koin:koin-test:${Versions.koin}"
        const val junit4 = "junit:junit:${Versions.junit4Version}"
        const val coroutinesTest =
            "org.jetbrains.kotlinx:kotlinx-coroutines-test:${Versions.coroutinesVersion}"
        const val mockK = "io.mockk:mockk:${Versions.mockkVersion}"
        const val truth = "com.google.truth:truth:${Versions.truthVersion}"
        const val archTesting =
            "androidx.arch.core:core-testing:${Versions.archTestingVersion}"
        const val koinTest = "io.insert-koin:koin-test:${Versions.koinTestVersion}"
    }

    object Module {
        const val core = ":core"
        const val data = ":data"
        const val domain = ":domain"
    }

    object SquareUp {
        const val okhttp3Logging = "com.squareup.okhttp3:logging-interceptor:4.9.3"
        const val retrofit2 = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
        const val retrofit2Converter =
            "com.squareup.retrofit2:converter-gson:${Versions.retrofit}"
    }
}