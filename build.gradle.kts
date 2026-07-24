plugins {
    alias(libs.plugins.moddevgradle)
}

val modId = rootProject.property("mod_id") as String
version = "${rootProject.property("mod_version")}+mc${libs.versions.minecraft.get()}.neoforge"

base {
    archivesName.set("${property("archives_base_name")}")
}

neoForge {
    version = libs.versions.neoforge.get()

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

repositories {
    //maven("https://maven.bawnorton.com/releases") // MixinSquared extension for MixinExtras
    //maven("https://maven.enjarai.dev/mirrors") // MixinSquared extension for MixinExtras
    maven("https://api.modrinth.com/maven")
    maven("https://maven.parchmentmc.org")
    maven("https://maven.su5ed.dev/releases")
    maven("https://maven.caffeinemc.net/releases")
}

dependencies {
    // Forgified Fabric API
    implementation(libs.forgified.fabric.api.get())

    // Sodium API for Sodium capable screen
    compileOnly("net.caffeinemc:sodium-neoforge-api:0.8.12+mc26.1.2")

    // NOTE: Download Sodium and Reese's Sodium Options into run/mods/ because NeoForge sucks ass at loading Sodium
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
                "neoforge_version" to libs.versions.neoforge.get(),
                "forgified_fabric_api" to libs.versions.forgified.fabric.api.get(),
                "minecraft_version_constraint" to rootProject.property("minecraft_version_constraint_forge"),
            ))
        }
    }
}

tasks.withType<JavaCompile>().configureEach {
    options.release = 25
}

java {
    sourceCompatibility = JavaVersion.VERSION_25
    targetCompatibility = JavaVersion.VERSION_25
}

tasks.jar {
    from("LICENSE") {
        rename { "${it}_${rootProject.base.archivesName.get()}" }
    }
}