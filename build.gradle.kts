plugins {
    kotlin("jvm") version "2.0.0"
    id("com.github.johnrengelman.shadow") version "8.1.1"
}

val artifact_group: String by project
val project_version: String by project

group = artifact_group
version = project_version

repositories {
    mavenCentral()
    maven("https://repo.opencollab.dev/maven-snapshots")
    maven("https://repo.opencollab.dev/maven-releases")
}

dependencies {
    val nukkit_api_version: String by project

    compileOnly("cn.nukkit:nukkit:$nukkit_api_version")
}

kotlin {
    jvmToolchain(8)
}

tasks {
    jar {
        enabled = false
    }

    shadowJar {
        archiveClassifier.set("")
        archiveBaseName.set(archiveBaseName.get())
        archiveVersion.set(version.toString())
    }
    build { dependsOn(shadowJar) }

    processResources {
        val project_name: String by project
        val project_license: String by project
        val project_author: String by project
        val project_description: String by project
        val project_id: String by project
        val project_website: String by project

        filesMatching(listOf("plugin.yml")) {
            expand(
                mapOf(
                    "project_id" to project_id,
                    "project_name" to project_name,
                    "project_license" to project_license,
                    "project_version" to version,
                    "project_author" to project_author,
                    "project_description" to project_description,
                    "artifact_group" to artifact_group,
                    "project_website" to project_website
                )
            )
        }
    }
}

java {
    withSourcesJar()
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}