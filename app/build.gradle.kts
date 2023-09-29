plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
//    Kotlin KSP buat Room
    id("com.google.devtools.ksp")
//    Kotlin Parcelize biar bisa parcelable Otomatis
    id("kotlin-parcelize")

}

android {
    namespace = "com.enigma.yokiuserfinderv2"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.enigma.yokiuserfinderv2"
        minSdk = 28
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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

    buildFeatures {
        viewBinding = true
        dataBinding = true
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    buildFeatures {
        viewBinding = true
    }
}

dependencies {

implementation("androidx.legacy:legacy-support-v4:1.0.0")
    //    Room
    val versi_room = "2.5.2"
    implementation("androidx.room:room-runtime:$versi_room")
    implementation ("androidx.room:room-ktx:$versi_room")
    ksp("androidx.room:room-compiler:$versi_room")

//    View Model
    val versi_lifecycle = "2.6.2"
    implementation ("androidx.lifecycle:lifecycle-viewmodel-ktx:$versi_lifecycle")
//    Live Data
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:$versi_lifecycle")

//    Yang Dibutuhkan ViewModel
    val versi_activity_ktx = "1.7.2"
    implementation ("androidx.activity:activity-ktx:$versi_activity_ktx")

//    Retrofit
    val versi_retrofit = "2.9.0"
    implementation("com.squareup.retrofit2:retrofit:$versi_retrofit")
    implementation("com.squareup.retrofit2:converter-gson:$versi_retrofit")

//    Glide buat dapetin photo secara online
    val versi_glide = "4.16.0"
    implementation("com.github.bumptech.glide:glide:$versi_glide")

//    Logging pake OKHTTP
    val versi_okhttp = "4.11.0"
    implementation("com.squareup.okhttp3:logging-interceptor:$versi_okhttp")

//    Datastore
    implementation("androidx.datastore:datastore-preferences:1.0.0")

//    Coroutine
    val versi_coroutine = "1.6.4"
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:$versi_coroutine")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:$versi_coroutine")

//    Other
    androidTestImplementation ("androidx.room:room-testing:2.5.2")
    implementation ("androidx.room:room-ktx:2.5.2")

    implementation("androidx.core:core-ktx:1.9.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.9.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation("androidx.navigation:navigation-fragment-ktx:2.7.3")
    implementation("androidx.navigation:navigation-ui-ktx:2.7.3")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
}