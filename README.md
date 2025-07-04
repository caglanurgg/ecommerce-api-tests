# 🛒 Ecommerce API Test Automation Project

This project is designed to perform automated API tests for an e-commerce platform using Java, Rest Assured, JUnit, and Cucumber in a Maven framework.

---

## ✅ Technologies & Tools
- Java 17
- Maven
- Rest Assured
- JUnit 5
- Cucumber BDD
- Lombok
- Allure (for reporting)
- Apache POI (for Excel integration)
- Git & GitHub

---

## 🧪 Test Scenario Covered

### GET /products
- ✅ Returns 200 status code when products are successfully retrieved
- ✅ Verifies that a list of products is returned

---

## 🚀 How to Run the Tests

```bash
mvn clean test
```
## 🚧 Setup and Execution

1. **Install Dependencies:**

   * Run the following command to download project dependencies using Maven:

     ```bash
     mvn clean install
     ```

2. **Run Tests:**

   * To execute test scenarios under `src/test/java`, use:

     ```bash
     mvn test
     ```
   * Test results can be viewed via JUnit or Cucumber reports.

3. **Configuration:**

   * The `configuration.properties` file contains environment settings and other configurable parameters like base URLs and user credentials.

---
## 🌐 Reporting

### Cucumber HTML Reporting

Test results can be viewed using HTML reports generated by Cucumber. These reports allow detailed inspection of test execution and are saved under the `target/cucumber-reports/` directory in HTML format.

When you run the tests with a Maven command, the reports are automatically created and can be found at:

```
target/cucumber-reports/cucumber-html-reports.html
```

They allow you to review test execution details in a user-friendly format.

## 📂 Project Structure

```
src/test/java
├── base                     # Common base classes for requests
│   └── BaseRequest.java
├── hooks                    # Hooks for pre/post test execution
│   └── Hooks.java
├── pojos                    # POJOs representing request/response bodies
│   ├── Product.java
│   └── Rating.java
├── runners                  # Cucumber test runners
│   └── CukesRunner.java
├── stepdefinitions          # Step definition classes for feature files
│   ├── carts/
│   └── products/
│       └── GetProductsStepDefs.java
├── utilities                # Utility classes for API specs and config reading
│   ├── ApiUtils.java
│   ├── ConfigurationReader.java
│   └── SpecBuilder.java

src/test/resources
├── config/
│   └── configuration.properties   # Test environment configuration
├── features/
│   ├── carts/
│   └── products/
│       ├── AddProduct.feature
│       ├── GetAllProducts.feature
│       └── UpdateProduct.feature
```

> This modular structure ensures clean separation of responsibilities and easy maintainability for API test automation.


