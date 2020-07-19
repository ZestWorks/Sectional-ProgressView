@file:Suppress("unused", "ClassName")

object Versions {
    internal const val kotlin = "1.3.72"
    internal const val coroutines = "1.3.7"
    internal const val junit = "4.13"
}

object Classpath {
    const val kotlin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlin}"
    const val gradle = "com.android.tools.build:gradle:4.0.0"
}

object Deps {

    object android {
        object stdLib {
            const val appCompat = "androidx.appcompat:appcompat:1.1.0"
            const val constraintLayout = "androidx.constraintlayout:constraintlayout:1.1.3"
        }

        object test {
            const val junit = "junit:junit:${Versions.junit}"
            const val ext = "androidx.test.ext:junit:1.1.1"
            const val espresso = "androidx.test.espresso:espresso-core:3.2.0"
        }
    }

    object kotlin {
        object stdlib {
            const val core = "org.jetbrains.kotlin:kotlin-stdlib:${Versions.kotlin}"
        }

        object coroutines {
            const val core = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutines}"
            const val android =
                "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutines}"
        }
    }
}