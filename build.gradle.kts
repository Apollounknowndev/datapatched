plugins {
    kotlin("jvm") version "2.1.0"
    id("earth.terrarium.cloche") version "0.16.20"
}

repositories {
    cloche {
        mavenNeoforgedMeta()
        mavenNeoforged()
        mavenForge()
        mavenFabric()
        mavenParchment()
        librariesMinecraft()
        main()
    }
    mavenLocal()
    mavenCentral()
    maven("https://api.modrinth.com/maven")
}

group = "dev.worldgen"
version = "2.0.2"

cloche {
    targets.all {
        mappings {
            official()
            custom(minecraftVersion.map {
                project.dependencies.create(files("mappings/$it.tiny"))
            })
        }
    }

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

        data()
    }

    val shared1211 = common("shared:1.21.1")
    val shared12111 = common("shared:1.21.11")

    fabric("fabric:1.21.1") {
        dependsOn(shared1211)

        loaderVersion = "0.17.0"
        minecraftVersion = "1.21.1"
        datagenDirectory = file("src/common/main/generated")

        dependencies {
            fabricApi("0.116.1")
        }

        includedClient()
        runs {
            client()
            server()
            data {
                mixins.from(file("src/fabric/1.21.1/data/datapatched_datagen.mixins.json"))
            }
        }
        data()

        metadata {
            entrypoint("main") {
                value = "dev.worldgen.datapatched.impl.DatapatchedEntrypoint"
            }
            entrypoint("fabric-datagen") {
                value = "dev.worldgen.datapatched.data.DatapatchedDatagen"
            }
        }
    }

    fabric("fabric:1.21.11") {
        dependsOn(shared12111)

        loaderVersion = "0.18.2"
        minecraftVersion = "1.21.11"
        datagenDirectory = file("src/common/main/generated")

        dependencies {
            fabricApi("0.139.4")
        }

        includedClient()
        runs {
            client()
            server()
            data {
                mixins.from(file("src/fabric/1.21.11/data/datapatched_datagen.mixins.json"))
            }
        }
        data()

        metadata {
            entrypoint("main") {
                value = "dev.worldgen.datapatched.impl.DatapatchedEntrypoint"
            }
            entrypoint("fabric-datagen") {
                value = "dev.worldgen.datapatched.data.DatapatchedDatagen"
            }
        }
    }

    neoforge("neoforge:1.21.1") {
        dependsOn(shared1211)

        loaderVersion = "21.1.206"
        minecraftVersion = "1.21.1"
        datagenDirectory = file("src/common/main/generated")

        data()
        runs {
            client()
            server()
        }
    }

    /*neoforge("neoforge:1.21.10") {
        dependsOn(shared12111)

        loaderVersion = "21.10.49-beta"
        minecraftVersion = "1.21.10"
        datagenDirectory = file("src/common/main/generated")

        data()
        runs {
            client()
            server()
        }
    }*/
}