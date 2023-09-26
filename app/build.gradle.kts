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

//    Room
    val versi_room = "2.5.2"
    implementation("androidx.room:room-runtime:$versi_room")
    implementation ("androidx.room:room-ktx:$versi_room")

//    View Model
    val versi_lifecycle = "2.6.2"
    implementation ("androidx.lifecycle:lifecycle-viewmodel-ktx:$versi_lifecycle")
//    Live Data
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.6.2")

//    Yang Dibutuhkan ViewModel
    val versi_activity_ktx = "1.7.2"
    implementation ("androidx.activity:activity-ktx:$versi_activity_ktx")


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