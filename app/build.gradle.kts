
plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.jetbrainsKotlinAndroid)

    id("com.google.devtools.ksp")
    id("com.google.dagger.hilt.android")
    id("androidx.navigation.safeargs.kotlin")
}

android {
    namespace = "com.yp2048.repositories"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.yp2048.repositories"
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
        kotlinCompilerExtensionVersion = "1.5.14"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1,*.md}"
        }
    }
}

dependencies {
    // androidx.hilt:hilt-navigation-compose
    implementation(libs.androidx.hilt.navigation.compose)
    // com.google.dagger:hilt-android
    implementation(libs.hilt.android)
    // com.google.dagger:hilt-android-compiler
    ksp(libs.hilt.android.compiler)

    // androidx.compose.ui:ui
    implementation(libs.androidx.ui)
    // androidx.compose.ui:ui-tooling
    implementation(libs.androidx.ui.tooling)
    // androidx.compose:compose-bom
    implementation (platform(libs.androidx.compose.bom))
    // com.squareup.retrofit2:retrofit
    implementation(libs.retrofit)
    // com.squareup.moshi:moshi
//    implementation(libs.moshi)
    // com.squareup.moshi:moshi-kotlin
    implementation(libs.moshi.kotlin)
    // com.squareup.retrofit2:converter-moshi
    implementation(libs.converter.moshi)
    // androidx.room:room-ktx
//    implementation(libs.androidx.room.ktx)
    // androidx.test
//    implementation(libs.androidx.rules)
//    implementation(libs.androidx.junit.ktx)
//    ksp(libs.androidx.room.compiler)

    // ViewModel
    // androidx.lifecycle:lifecycle-viewmodel-ktx
    implementation(libs.androidx.lifecycle.viewmodel.ktx)
    // ViewModel utilities for Compose
    // androidx.lifecycle:lifecycle-viewmodel-compose
    implementation(libs.androidx.lifecycle.viewmodel.compose)
    // LiveData
    // androidx.lifecycle:lifecycle-livedata-ktx
//    implementation(libs.androidx.lifecycle.livedata.ktx)
    // runtime-livedata
    // androidx.compose.runtime:runtime-livedata
    implementation(libs.androidx.runtime.livedata)
    // androidx.lifecycle:lifecycle-compiler
    ksp(libs.androidx.lifecycle.compiler)

    // Lifecycles only (without ViewModel or LiveData)
    // androidx.lifecycle
    implementation(libs.androidx.lifecycle.runtime.ktx)
    // Lifecycle utilities for Compose
    // androidx.lifecycle:lifecycle-runtime-compose
    implementation(libs.androidx.lifecycle.runtime.compose)
    // Saved state module for ViewModel
    // androidx.lifecycle.viewmodel.savedstate
    implementation(libs.androidx.lifecycle.viewmodel.savedstate)
    // optional - Test helpers for Lifecycle runtime
    // androidx.lifecycle:lifecycle-runtime-testing
//    testImplementation (libs.androidx.lifecycle.runtime.testing)

    // Kotlin
//    implementation(libs.androidx.navigation.fragment.ktx)
//    implementation(libs.androidx.navigation.ui.ktx)

    // Feature module Support
//    implementation(libs.androidx.navigation.dynamic.features.fragment)

    // CameraX core library using the camera2 implementation
    // The following line is optional, as the core library is included indirectly by camera-camera2
    // androidx.camera:camera-core
    implementation(libs.androidx.camera.core)
    // androidx.camera:camera-camera2
    implementation(libs.androidx.camera.camera2)
    // If you want to additionally use the CameraX Lifecycle library
    // androidx.camera:camera-lifecycle
    implementation(libs.androidx.camera.lifecycle)
    // If you want to additionally use the CameraX VideoCapture library
//    implementation(libs.androidx.camera.video)
    // If you want to additionally use the CameraX View class
    // androidx.camera:camera-view
    implementation(libs.androidx.camera.view)
    // If you want to additionally add CameraX ML Kit Vision Integration
    // implementation(libs.androidx.camera.mlkit.vision)
    // If you want to additionally use the CameraX Extensions library
//    implementation(libs.androidx.camera.extensions)
//    implementation(libs.androidx.core.ktx)
//    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.navigation.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.material3)

    // Required -- JUnit 4 framework
//    testImplementation(libs.junit)
//    testImplementation(libs.mockito.core)
//    testImplementation(libs.mockito.kotlin)
//    testImplementation(libs.androidx.core)
//    testImplementation(libs.mockk)
    // optional - Test helpers for LiveData
//    testImplementation(libs.androidx.core.testing)
//    testImplementation(libs.mockito.inline)
//    testImplementation(libs.kotlinx.coroutines.test)

//    androidTestImplementation(libs.androidx.junit)
//    androidTestImplementation(libs.androidx.espresso.core)
//    androidTestImplementation(platform(libs.androidx.compose.bom))
//    androidTestImplementation(libs.androidx.uiautomator)

    // Testing Navigation
//    androidTestImplementation(libs.androidx.navigation.testing)
//    androidTestImplementation(libs.mockk.android)

    // androidx.compose.ui:ui-test-junit4
    androidTestImplementation(libs.androidx.ui.test.junit4)
//    debugImplementation(libs.androidx.ui.tooling)
    // androidx.compose.ui:ui-test-manifest
    debugImplementation(libs.androidx.ui.test.manifest)

    // For instrumentation tests
    // com.google.dagger:hilt-android-testing:2.50.1
    androidTestImplementation(libs.hilt.android.testing)
    // com.google.dagger:hilt-android-compiler:2.50.1
    kspAndroidTest(libs.hilt.android.compiler)

    // For local unit tests
    // com.google.dagger:hilt-android-testing:2.50.1
    testImplementation(libs.hilt.android.testing)
    // com.google.dagger:hilt-compiler:2.50.1
    kspTest(libs.hilt.compiler)
}