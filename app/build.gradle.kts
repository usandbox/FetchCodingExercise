plugins {
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.serialization)
    alias(libs.plugins.android.application)
    id("kotlin-kapt")
}

android {
    namespace = "com.github.usandbox.fetchcodingexercise"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.github.usandbox.fetchcodingexercise"
        minSdk = 26
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
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
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)

    // DI
    implementation(libs.dagger)
    kapt(libs.dagger.compiler)

    // Retrofit
    implementation(libs.retrofit)
   // implementation("com.squareup.retrofit2:converter-gson:2.x.x")

    // Kotlinx Serialization for JSON parsing
    implementation(libs.kotlinx.serialization.json)

    // Retrofit Converter for Kotlinx Serialization
    implementation(libs.retrofit2.kotlinx.serialization.converter)

    // OkHttp
    implementation(libs.okhttp)
    //implementation("com.squareup.okhttp3:logging-interceptor:4.x.x")

    // Gson for JSON serialization/deserialization (Retrofit uses Gson)
    //implementation("com.google.code.gson:gson:2.x.x")

    // Coroutine support for Retrofit (optional, if using coroutines)
    //implementation("com.squareup.retrofit2:adapter-kotlin-coroutines:2.x.x")  // If using Kotlin coroutines (preferred)

    implementation(libs.javax.inject)
    debugImplementation(libs.androidx.ui.tooling)

}
