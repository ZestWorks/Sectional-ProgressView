@file:Suppress("unused", "ClassName")

object Versions {
    internal const val kotlin = "1.3.72"
    internal const val coroutines = "1.3.7"
    internal const val navigation = "2.3.0"
    internal const val junit = "4.13"
    internal const val room = "2.2.5"
    internal const val koin = "2.0.1"
}

object Classpath {
    const val kotlin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlin}"
    const val gradle = "com.android.tools.build:gradle:4.2.0-alpha04"
    const val navigation =
        "androidx.navigation:navigation-safe-args-gradle-plugin:${Versions.navigation}"
    const val googleService = "com.google.gms:google-services:4.3.3"
    const val crashlytics = "com.google.firebase:firebase-crashlytics-gradle:2.1.1"
}

object Deps {

    object android {
        object stdLib {
            const val ktx = "androidx.core:core-ktx:1.2.0"
            const val appCompat = "androidx.appcompat:appcompat:1.1.0"
            const val fragmentKtx = "androidx.fragment:fragment-ktx:1.2.4"
            const val materialComponents = "com.google.android.material:material:1.2.0-alpha06"
            const val constraintLayout = "androidx.constraintlayout:constraintlayout:2.0.0-beta6"
        }

        object test {
            const val junit = "junit:junit:${Versions.junit}"
            const val ext = "androidx.test.ext:junit:1.1.1"
            const val archTesting = "androidx.arch.core:core-testing:2.1.0"
            const val espresso = "androidx.test.espresso:espresso-core:3.2.0"
        }
    }

    object kotlin {
        object stdlib {
            const val core = "org.jetbrains.kotlin:kotlin-stdlib:${Versions.kotlin}"
            const val jdk8 = "org.jetbrains.kotlin:kotlin-stdlib-jdk8:${Versions.kotlin}"
        }

        object coroutines {
            const val core = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutines}"
            const val android =
                "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutines}"
        }

        object test {
            const val coroutines =
                "org.jetbrains.kotlinx:kotlinx-coroutines-test:${Versions.coroutines}"
        }
    }
}