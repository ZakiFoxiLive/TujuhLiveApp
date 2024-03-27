object Version{
    const val core = "1.9.0"
    const val lifecycle = "2.6.2"
    const val activityCompose = "1.8.1"
    const val composeBoom = "2023.03.00"
    const val junit = "4.13.2"
    const val junitTest = "1.1.5"
    const val espressoCore = "3.5.1"
    const val retrofit = "2.10.0"
    const val okHttp = "4.12.0"
    const val gsonConverter = "2.10.0"
    const val hilt = "2.51"
    const val hiltCompiler = "2.51"
    const val appcompat = "1.6.1"
    const val material = "1.11.0"
    const val serialization = "1.6.0"
    const val composeNavigation = "2.7.7"
    const val coil = "2.6.0"
    const val hiltComposeNavigation = "1.0.0"
    const val eventBus = "3.0.0"
    const val liteAv = "latest.release"
}
object Core{
    const val core = "androidx.core:core-ktx:${Version.core}"
}

object Appcompat{
    const val appcompat = "androidx.appcompat:appcompat:${Version.appcompat}"
}

object Material{
    const val material = "com.google.android.material:material:${Version.material}"
}

object Lifecycle{
    const val  lifecycle = "androidx.lifecycle:lifecycle-runtime-ktx:${Version.lifecycle}"
}

object Compose{
    const val activityCompose = "androidx.activity:activity-compose:${Version.activityCompose}"
    const val composeBoom = "androidx.compose:compose-bom:${Version.composeBoom}"
    const val composeUi = "androidx.compose.ui:ui"
    const val composeUiGraphic = "androidx.compose.ui:ui-graphics"
    const val composeUiToolingPreview = "androidx.compose.ui:ui-tooling-preview"
    const val composeMaterial3 = "androidx.compose.material3:material3"
    const val composeJunitUiTest = "androidx.compose.ui:ui-test-junit4"
    const val composeUiTooling = "androidx.compose.ui:ui-tooling"
    const val composeUiTestManifest = "androidx.compose.ui:ui-test-manifest"
    const val composeNavigation = "androidx.navigation:navigation-compose:${Version.composeNavigation}"
}

object Junit{
    const val junit = "junit:junit:${Version.junit}"
    const val junitTest = "androidx.test.ext:junit:${Version.junitTest}"
}

object Espresso{
    const val espressoCore = "androidx.test.espresso:espresso-core:${Version.espressoCore}"
}

object Retrofit{
    const val retrofit = "com.squareup.retrofit2:retrofit:${Version.retrofit}"
    const val okHttp = "com.squareup.okhttp3:okhttp:${Version.okHttp}"
    const val gsonConverter = "com.squareup.retrofit2:converter-gson:${Version.gsonConverter}"
}

object DaggerHilt {
    const val hilt = "com.google.dagger:hilt-android:${Version.hilt}"
    const val hiltCompiler = "com.google.dagger:hilt-android-compiler:${Version.hiltCompiler}"
    const val hiltComposeNavigation = "androidx.hilt:hilt-navigation-compose:${Version.hiltComposeNavigation}"
}

object Kotlin{
    const val serialization = "org.jetbrains.kotlinx:kotlinx-serialization-json:${Version.serialization}"
}

object Coil{
    const val coil = "io.coil-kt:coil-compose:${Version.coil}"
}

object EventBus{
    const val eventBus = "org.greenrobot:eventbus:${Version.eventBus}"
}
object LiteAv{
    const val liteAv = "com.tencent.liteav:LiteAVSDK_Professional:${Version.liteAv}"
}