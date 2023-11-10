plugins {
    java
    id("org.springframework.boot") version "3.1.5"
    id("io.spring.dependency-management") version "1.1.3"
    id("com.google.protobuf") version "0.9.4"
    // Generate IntelliJ IDEA's .idea & .iml project files
    id("idea")
//    JvmPluginsHelper.configureAnnotationProcessorPath("org.mapstruct:mapstruct-processor:1.5.5.Final")
}

group = "kz.iceberg"
version = "0.2-PRERELEASE"

var grpcVersion = "1.59.0" // CURRENT_GRPC_VERSION
var protobufVersion = "3.24.0"
var protocVersion = protobufVersion

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

configurations {
    compileOnly {
        extendsFrom(configurations.annotationProcessor.get())
    }
}

repositories {
    mavenCentral()
}

dependencies {
    annotationProcessor("org.projectlombok:lombok")
    compileOnly("org.projectlombok:lombok")
    developmentOnly("org.springframework.boot:spring-boot-devtools")
    developmentOnly("org.springframework.boot:spring-boot-docker-compose")
    implementation("org.postgresql:postgresql")
    implementation("org.springframework.boot:spring-boot-starter-data-jdbc")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-graphql")
    implementation("org.springframework.boot:spring-boot-starter-jdbc")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-webflux")
    implementation("io.leangen.graphql:spqr:0.12.3")
    // https://mvnrepository.com/artifact/com.apollographql.federation/federation-graphql-java-support
    implementation("com.apollographql.federation:federation-graphql-java-support:4.2.0")
    // https://mvnrepository.com/artifact/com.fasterxml.jackson.core/jackson-databind
    implementation("com.fasterxml.jackson.core:jackson-databind:2.15.2")
    // https://mvnrepository.com/artifact/com.google.guava/guava
    implementation("com.google.guava:guava:32.1.3-jre")
    // https://mvnrepository.com/artifact/org.springframework/spring-context
    implementation("org.springframework:spring-context:6.0.13")
    // https://mvnrepository.com/artifact/org.mapstruct/mapstruct
    implementation("org.mapstruct:mapstruct:1.5.5.Final")
    implementation("org.mockito:mockito-core:5.7.0")
    // https://mvnrepository.com/artifact/org.mapstruct/mapstruct-processor
    annotationProcessor("org.mapstruct:mapstruct-processor:1.5.5.Final")

    testImplementation("io.projectreactor:reactor-test")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("org.springframework.graphql:spring-graphql-test")


    //AWS SDK Parts
    // https://mvnrepository.com/artifact/software.amazon.awssdk/s3
    testImplementation("software.amazon.awssdk:s3:2.21.13")
    // https://mvnrepository.com/artifact/software.amazon.awssdk/sqs
    implementation("software.amazon.awssdk:sqs:2.21.13")

    //gRPC
    implementation("io.grpc:grpc-protobuf:${grpcVersion}")
    implementation("io.grpc:grpc-stub:${grpcVersion}")
    runtimeOnly("io.grpc:grpc-netty-shaded:${grpcVersion}")
    compileOnly("org.apache.tomcat:annotations-api:6.0.53") // necessary for Java 9+

}

tasks.withType<Test> {
    useJUnitPlatform()
}

// Inform IDEs like IntelliJ IDEA, Eclipse or NetBeans about the generated code.
sourceSets {
    main {
        java {
            srcDirs("build/generated/source/proto/main/grpc")
            srcDirs("build/generated/source/proto/main/java")
        }
    }
}

protobuf {
    protoc {
        artifact = "com.google.protobuf:protoc:${protocVersion}"
    }
    plugins {
        create("grpc") {
            artifact = "io.grpc:protoc-gen-grpc-java:${grpcVersion}"
        }
    }
    generateProtoTasks {
        all().forEach {
            it.plugins {
                create("grpc")
            }
        }
    }
}
