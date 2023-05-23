FROM maven:3.9.0-eclipse-temurin-17-focal

# Copy source folder
COPY src src

# Copy maven pom.xml
COPY pom.xml .

# Build the package
RUN mvn clean install assembly:single

# expose the used port
EXPOSE 28888

# Run the code
ENTRYPOINT ["java","-jar","target/proj-ingsw-The_Compiler_Coalition-1.0-SNAPSHOT-jar-with-dependencies.jar"]