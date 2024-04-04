
**Annotating Tests**

1. **Unit Tests:**
   Mark your unit tests with the following annotation:

   ```java
   @EnabledIfSystemProperty(named = "test-profile", matches = "unit")
   ```

2. **Integration Tests:** 
   Mark your integration tests with the following annotation:

   ```java 
   @EnabledIfSystemProperty(named = "test-profile", matches = "integration")
   ```

**Maven Commands**

1. **Running Unit Tests:**
   Execute the following Maven command to run only unit tests:

   ```bash
   mvn clean install -Dtest-profile=unit
   ```

2. **Running Integration Tests:**
   Execute the following Maven command to run only integration tests:

   ```bash
   mvn clean install -Dtest-profile=integration
   ```