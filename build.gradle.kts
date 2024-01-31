import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id ("org.springframework.boot") version "3.2.2" apply false
    id("io.spring.dependency-management") version "1.1.4"
    kotlin("jvm") version "1.9.22"
    kotlin("plugin.spring") version "1.9.22"
}

allprojects {
    apply(plugin = "org.jetbrains.kotlin.jvm")
    apply (plugin= "io.spring.dependency-management")

    repositories {
        mavenCentral()
        jcenter()
        mavenLocal()
    }


    dependencyManagement {
        overriddenByDependencies(false)

        dependencies {
            dependency("org.springframework.boot:spring-boot-starter-actuator:3.2.2")
            dependency ("org.springframework.boot:spring-boot-starter-web:3.2.2")
        }
    }
}

subprojects {
    apply(plugin = "org.jetbrains.kotlin.jvm")
    tasks.withType<KotlinCompile> {
        kotlinOptions {
            freeCompilerArgs += "-Xjsr305=strict"
            jvmTarget = "17"
        }
    }

    dependencies {
        testImplementation ("org.junit.jupiter:junit-jupiter")

        testRuntimeOnly ("org.junit.platform:junit-platform-launcher")
    }

    tasks.withType<Test> {
        useJUnitPlatform()
    }

}


