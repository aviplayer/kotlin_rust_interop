import org.jetbrains.kotlin.ir.backend.js.compile

plugins {
    kotlin("multiplatform") version "1.6.0"
}

group = "me.avisokih"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    commonMainImplementation( "org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.0-native-mt")
}

kotlin {
    val hostOs = System.getProperty("os.name")
    val isMingwX64 = hostOs.startsWith("Windows")
    val nativeTarget = when {
        hostOs == "Mac OS X" -> macosX64("native")
        hostOs == "Linux" -> linuxX64("native")
        isMingwX64 -> mingwX64("native")
        else -> throw GradleException("Host OS is not supported in Kotlin/Native.")
    }

    nativeTarget.apply {
        compilations["main"].cinterops {
            val libserver by creating {
                when (preset) {
                    presets["mingwX64"] -> includeDirs.headerFilterOnly("\\http-client\\src\\nativeInterop\\cinterop\\curl")
                }
            }
        }
        binaries {
            executable {
                entryPoint = "main"
            }
        }
    }
}
