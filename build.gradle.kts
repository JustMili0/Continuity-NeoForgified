plugins {
    alias(libs.plugins.moddevgradle)
}

val modId = rootProject.property("mod_id") as String

neoForge {
    version = libs.versions.neoforged.loader.get()

    parchment {
        mappingsVersion = libs.versions.parchment.get()
        minecraftVersion = libs.versions.minecraft.get()
    }

    mods {
        create(modId) {
            sourceSet(sourceSets.main.get())
        }
    }

    runs {
        create("client") {
            client()
            systemProperty("neoforge.enabledGameTestNamespaces", modId)
        }

        create("server") {
            server()
            systemProperty("neoforge.enabledGameTestNamespaces", modId)
        }
    }
}

base {
    archivesName.set("${property("archives_base_name")}")
}

version = "${rootProject.property("mod_version")}+mc${libs.versions.minecraft.get()}.neoforge"

repositories {
    //maven("https://maven.bawnorton.com/releases") // MixinSquared extension for MixinExtras
    //maven("https://maven.enjarai.dev/mirrors") // MixinSquared extension for MixinExtras
    maven("https://api.modrinth.com/maven")
    maven("https://maven.parchmentmc.org")
    maven("https://maven.su5ed.dev/releases")
}

dependencies {
    implementation(libs.neoforged.fabric.api.get())
}

tasks {
    processResources {
        filesMatching("META-INF/neoforge.mods.toml") {
            expand(mapOf(
                "mod_id" to rootProject.property("mod_id"),
                "mod_name" to rootProject.property("mod_name"),
                "mod_version" to rootProject.version,
                "mod_description" to rootProject.property("mod_description"),
                "mod_authors" to rootProject.property("mod_authors"),
                "mod_license" to rootProject.property("mod_license"),
                "neoforge_version" to libs.versions.neoforged.loader.get(),
                "forgified_fabric_api" to libs.versions.neoforged.fabric.api.get(),
                "minecraft_version_constraint" to rootProject.property("minecraft_version_constraint_forge"),
            ))
        }
    }
}

tasks.withType<JavaCompile>().configureEach {
    options.release = 21
}

java {
    sourceCompatibility = JavaVersion.VERSION_21
    targetCompatibility = JavaVersion.VERSION_21
}

tasks.jar {
    from("LICENSE") {
        rename { "${it}_${rootProject.base.archivesName.get()}" }
    }
}