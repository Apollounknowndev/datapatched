plugins {
    kotlin("jvm") version "2.1.0"
    id("earth.terrarium.cloche") version "0.16.10"
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
version = "1.0.0"

cloche {
    mappings {
        official()
    }

    metadata {
        modId = "datapatched"
        name = "Datapatched"
        description = "A library mod with a simple cross-loader loot modifier format."
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
    val shared12110 = common("shared:1.21.10")

    fabric("fabric:1.21.1") {
        dependsOn(shared1211)

        loaderVersion = "0.17.0"
        minecraftVersion = "1.21.1"
        datagenDirectory = file("build/generated/resources/main")

        dependencies {
            fabricApi("0.116.1")
        }

        includedClient()
        runs {
            client()
            server()
            data()
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

    fabric("fabric:1.21.10") {
        dependsOn(shared12110)

        loaderVersion = "0.17.3"
        minecraftVersion = "1.21.10"
        datagenDirectory = file("build/generated/resources/main")

        dependencies {
            fabricApi("0.138.0")
        }

        includedClient()
        runs {
            client()
            server()
            data {
                mixins.from(file("src/fabric/1.21.10/data/datapatched_datagen.mixins.json"))
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

        runs {
            client()
            server()
        }
    }

    neoforge("neoforge:1.21.10") {
        dependsOn(shared12110)

        loaderVersion = "21.10.49-beta"
        minecraftVersion = "1.21.10"

        runs {
            client()
            server()
        }
    }
}