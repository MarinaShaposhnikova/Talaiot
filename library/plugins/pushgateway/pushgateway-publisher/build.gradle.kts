plugins {
    id("kotlinLib")
}

dependencies {
    implementation(project(":library:core:talaiot"))
    implementation(project(":library:core:talaiot-request"))
    testImplementation(project(":library:core:talaiot-test-utils"))
    testImplementation("io.github.rybalkinsd:kohttp:0.10.0")
}
