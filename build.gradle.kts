// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    id("com.android.application") version "8.1.2" apply false
    id("org.jetbrains.kotlin.android") version "1.8.10" apply false
//    id("com.facebook.react.rootproject")
}

buildscript {

    repositories {
        google()
        mavenCentral()
        maven { url = uri("https://jitpack.io") }

    }
    dependencies {
        classpath("com.android.tools.build:gradle:8.1.0")
//        classpath("com.facebook.react:react-native-gradle-plugin")
//        classpath("com.facebook.react:react-native-gradle-plugin")
    }
}
allprojects {
    repositories {
        google()
        mavenCentral()
        maven { url = uri("https://jitpack.io") }  // Include JitPack if necessary
        maven { url = uri("https://maven.google.com") }
        maven { url = uri("https://www.jitpack.io") }

        // Additional repositories if needed
    }
}
