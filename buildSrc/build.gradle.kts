plugins {
    `kotlin-dsl`
}

repositories {
    mavenCentral()
    google()
    jcenter()
}

dependencies {
    implementation(gradleApi())
}

gradlePlugin {
    plugins {
        register("dependencies") {
            id = "com.hernandazevedo.dependencies"
            implementationClass = "com.hernandazevedo.Dependencies"
        }
    }
}