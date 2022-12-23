/**
 *作为 application 运行时需要配置 plugin application applicationId versionCode versionName isDebuggable manifest
 *作为 library 运行时仅需配置 minSdk targetSdk
 *  */

plugins {
//    id("com.android.application")
    id("com.android.library")
    kotlin("android")
    kotlin("kapt")
    `maven-publish`
}

android {

    val isAloneRun = false

    namespace = "com.yuu.android.component.logbook"
    compileSdk = 31

    defaultConfig {
//        if(isAloneRun){
//            applicationId = "com.yuu.android.component.logbook"
//            versionCode = 1
//            versionName = "1.0.1"
//        }

        minSdk = 21
        targetSdk = 31

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    sourceSets {
        getByName("main") {
            manifest.srcFile("src/main/AndroidManifest.xml")
        }
    }

    buildTypes {
        getByName("release") {
            proguardFiles(getDefaultProguardFile("proguard-android.txt"), "proguard-rules.pro")
        }
        getByName("debug") {
            proguardFiles(getDefaultProguardFile("proguard-android.txt"), "proguard-rules.pro")
        }
    }

    packagingOptions {
        exclude("META-INF/LICENSE.txt")
        exclude("META-INF/NOTICE.txt")
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {

    implementation ("androidx.core:core-ktx:1.3.2")
    implementation ("androidx.appcompat:appcompat:1.3.0")
    implementation ("com.google.android.material:material:1.3.0")
    implementation("androidx.constraintlayout:constraintlayout:2.0.1")
    implementation(files("libs/logger.jar"))
    implementation(files("libs/blankj.jar"))
    testImplementation ("junit:junit:4.13.2")
    androidTestImplementation ("androidx.test.ext:junit:1.1.2")
    androidTestImplementation ("androidx.test.espresso:espresso-core:3.3.0")

    //协程
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.5.0")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.5.0")

    //room
    val room_version = "2.2.6"
    implementation("androidx.room:room-runtime:$room_version")
    annotationProcessor("androidx.room:room-compiler:$room_version")
    kapt("androidx.room:room-compiler:$room_version")
    implementation("androidx.room:room-ktx:$room_version")
    testImplementation("androidx.room:room-testing:$room_version")

    //work manager
    implementation("androidx.work:work-runtime:2.5.0")
    implementation("androidx.hilt:hilt-work:1.0.0")
    kapt("androidx.hilt:hilt-compiler:1.0.0")
    implementation("androidx.work:work-runtime-ktx:2.5.0")

    // gson
    implementation("com.google.code.gson:gson:2.8.6")


}

afterEvaluate {
    publishing{
        publications {
            create<MavenPublication>("release"){
                group = "com.yuu.android.component"
                artifactId = "logbook"
                version = "0.0.5-alpha"

                afterEvaluate { artifact(tasks.getByName("bundleReleaseAar")) }
            }
        }
    }
}