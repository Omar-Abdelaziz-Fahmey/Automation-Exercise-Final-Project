# Automation Exercise Test Automation Framework

## 📌 Project Overview
This project is a robust **Test Automation Framework** designed to automate the testing of the [Automation Exercise](https://automationexercise.com/) website. It utilizes a hybrid approach, combining **UI automation** with **Selenium WebDriver** and **API automation** with **RestAssured**, all structured within a **Page Object Model (POM)** design pattern.

The framework is built using **Java 21** and **Maven**, ensuring scalability, maintainability, and ease of use. It incorporates comprehensive reporting via **Allure**, detailed logging with **Log4j2**, and follows best practices like the **Singleton pattern** for driver management and **Fluent Interface** for readable tests.

## 🚀 Key Features
-   **Hybrid Framework**: Seamlessly integrates UI and API testing capabilities.
-   **Page Object Model (POM)**: Promotes code reusability and maintainability by separating page objects from test logic.
-   **Singleton Driver**: Ensures a single instance of the WebDriver per thread, optimizing resource usage.
-   **Fluent Interface**: Enables method chaining for cleaner and more readable test code.
-   **Data-Driven Testing**: Utilizes JSON files for externalizing test data, making tests flexible and easy to update.
-   **Cross-Browser Support**: Configurable to run on different browsers (Chrome, Firefox, Edge).
-   **Robust Reporting**: Generates detailed **Allure Reports** with steps, logs, screenshots, and videos of test executions.
-   **CI/CD Integration**: Ready for integration with CI/CD pipelines (e.g., GitHub Actions).
-   **Parallel Execution**: Supports parallel test execution using TestNG.

## 🛠️ Technology Stack
-   **Programming Language**: [Java 21](https://www.oracle.com/java/technologies/javase/jdk21-archive-downloads.html)
-   **Build Tool**: [Maven](https://maven.apache.org/)
-   **UI Automation**: [Selenium WebDriver](https://www.selenium.dev/)
-   **API Automation**: [RestAssured](https://rest-assured.io/)
-   **Test Runner**: [TestNG](https://testng.org/doc/)
-   **Reporting**: [Allure Report](https://docs.qameta.io/allure/)
-   **Logging**: [Log4j2](https://logging.apache.org/log4j/2.x/)
-   **JSON Parsing**: [JSON Simple](https://code.google.com/archive/p/json-simple/) / [Jayway JsonPath](https://github.com/json-path/JsonPath)
-   **Utilities**: Apache Commons IO, Apache POI (for Excel if needed)

## 📋 Prerequisites
Before you begin, ensure you have the following installed on your machine:
-   **Java Development Kit (JDK) 21**: [Download Here](https://www.oracle.com/java/technologies/downloads/#java21)
-   **Maven**: [Download Here](https://maven.apache.org/download.cgi)
-   **Integrated Development Environment (IDE)**: IntelliJ IDEA (Recommended) or Eclipse.
-   **Allure Commandline**: [Installation Guide](https://docs.qameta.io/allure/#_installing_a_commandline) (Optional, for viewing reports locally without Maven plugin)

## ⚙️ Installation
1.  **Clone the Repository**:
    ```bash
    git clone https://github.com/Omar-Abdelaziz-Fahmey/Automation-Exercise-Final-Project.git
    ```
2.  **Navigate to the Project Directory**:
    ```bash
    cd Automation-Exercise-Final-Project
    ```
3.  **Install Dependencies**:
    ```bash
    mvn clean install -DskipTests
    ```

## 🏃‍♂️ Running Tests
You can run tests using Maven commands or directly from your IDE.

### Run All Tests
To execute the entire test suite:
```bash
mvn clean test
```

### Run Specific Tests
To run a specific test class (e.g., `LoginTest`):
```bash
mvn clean test -Dtest=LoginTest
```

### Run via TestNG XML
To run tests defined in a TestNG XML file (e.g., `testng.xml`):
```bash
mvn clean test -DsuiteXmlFile=testng.xml
```

## 📊 Generating Reports
This framework uses **Allure** for reporting. After running the tests, you can generate and view the report.

### View Report Immediately
This command generates the report and opens it in your default browser:
```bash
mvn allure:serve
```

### Generate Report for Later View
To generate the report in the `target/site/allure-maven-plugin` directory:
```bash
mvn allure:report
```

## 📂 Project Structure
```
Automation-Exercise-Project
├── .github/                # GitHub Actions workflows (if applicable)
├── src/
│   ├── main/java/          # Source code
│   │   └── automationexercices/
│   │       ├── apis/       # API implementation classes
│   │       ├── drivers/    # WebDriver initialization and management
│   │       ├── listeners/  # TestNG listeners for reporting and logs
│   │       ├── pages/      # Page Object classes
│   │       ├── utils/      # Utility classes (JSON reader, Actions, etc.)
│   │       └── validations/# Assertion and Verification classes
│   └── test/java/          # Test code
│       └── automationexercices/
│           └── tests/
│               ├── api/    # API Test classes
│               └── ui/     # UI Test classes
├── src/test/resources/     # Test resources
│   ├── testData/           # JSON data files
│   └── properties/         # Configuration properties
├── pom.xml                 # Maven dependencies and build configuration
└── README.md               # Project documentation
```

## 👨‍💻 Author
**Omar Abdelaziz**
-   [GitHub Profile](https://github.com/Omar-Abdelaziz-Fahmey)

---
*Happy Testing!* 🚀
