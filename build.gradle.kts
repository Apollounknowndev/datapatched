plugins {
    kotlin("jvm") version "2.1.0"
    id("earth.terrarium.cloche") version "0.18.10"
}

repositories {
    cloche.librariesMinecraft()
    mavenCentral()
    cloche {
        main()
        mavenNeoforgedMeta()
        mavenNeoforged()
        mavenFabric()
    }
}

group = "dev.worldgen"
version = "2.2.0"

cloche {
    metadata {
        modId = "datapatched"
        name = "Datapatched"
        description = "A library mod focused on data-driven features with a simple cross-loader loot modifier and villager trade format."
        license = "MIT"
        icon = "pack.png"

        author("Apollo")
    }

    common {
        mixins.from(file("src/common/main/datapatched.mixins.json"))

        dependencies {
            compileOnly("org.spongepowered:mixin:0.8.3")
        }
    }

    val sharedOld = common("shared:21.1")
    val sharedNew = common("shared:26.1")

    fabric("fabric:21.1") {
        dependsOn(sharedOld)

        loaderVersion = "0.18.5"
        minecraftVersion = "1.21.1"
        
        mappings {
            official()
            custom(minecraftVersion.map {
                project.dependencies.create(files("mappings/$it.tiny"))
            })
        }

        dependencies {
            fabricApi("0.116.1")
        }

        includedClient()
        runs {
            client()
            server()
        }

        metadata {
            entrypoint("main") {
                value = "dev.worldgen.datapatched.impl.DatapatchedEntrypoint"
            }
        }
    }

    fabric("fabric:26.1") {
        dependsOn(sharedNew)

        loaderVersion = "0.18.5"
        minecraftVersion = "26.1"

        dependencies {
            fabricApi("0.144.3")
        }

        includedClient()
        runs {
            client()
            server()
        }

        metadata {
            entrypoint("main") {
                value = "dev.worldgen.datapatched.impl.DatapatchedEntrypoint"
            }
        }
    }

    neoforge("neoforge:21.1") {
        dependsOn(sharedOld)

        loaderVersion = "21.1.206"
        minecraftVersion = "1.21.1"
        
        mappings {
            official()
            custom(minecraftVersion.map {
                project.dependencies.create(files("mappings/$it.tiny"))
            })
        }

        runs {
            client()
            server()
        }
    }

    neoforge("neoforge:26.1") {
        dependsOn(sharedNew)

        loaderVersion = "26.1.0.8-beta"
        minecraftVersion = "26.1"

        runs {
            client()
            server()
        }
    }
}