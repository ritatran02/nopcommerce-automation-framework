# NopCommerce Automation Framework

UI Automation Testing Framework for [nopCommerce](https://demo.nopcommerce.com/) using Java, Selenium WebDriver, TestNG and Maven.

## Tech Stack

* Java 17
* Selenium WebDriver 4.46.0
* TestNG
* Maven
* Page Object Model (POM)
* JSON Test Data
* Allure Report
* Jenkins
* Jira
* SQL Server
* Git/GitHub
* Slack

## Key Features

* Page Object Model with reusable BaseTest and BasePage
* Data-driven testing with JSON
* Environment-based configuration
* Retry failed tests
* TestNG listeners
* Allure reporting
* Jira bug integration
* Jenkins CI/CD pipeline
* SQL Server database validation
* Cross-browser execution

## Project Structure

```text
src/
├── main/java/com/nopcommerce
│   ├── commons
│   ├── jiraConfigs
│   ├── pageObjects
│   ├── pageUIs
│   └── utilities
│
└── test/java/com/nopcommerce/testcases
    ├── user
    └── z_admin

src/test/resources/
├── test data
├── testing.xml
└── jenkins-testing.xml

environmentConfig/
uploadFiles/
pom.xml
Jenkinsfile
README.md
```

## Test Scenarios

* Register successfully
* Login successfully
* Login with invalid password
* Search product
* Sort product by price
* Add product to cart
* Edit product from Admin

## Test Data & Configuration

Test data is maintained separately using JSON files.

Sensitive environment configuration such as `env-dev.properties` is not committed to GitHub.

A sample configuration file can be provided as:

```text
env-dev.properties.example
```

For Jenkins, the real environment configuration is injected securely through Jenkins Credentials.

## Run Tests

### Clean project

```bash
mvn clean
```

### Run tests with Chrome

```bash
mvn test -DBROWSER=CHROME
```

## Jenkins CI/CD

The project is integrated with Jenkins and can be triggered automatically from GitHub through a webhook.

Pipeline flow:

```text
GitHub Push
    ↓
Jenkins Webhook
    ↓
Checkout Source Code
    ↓
Clean Project
    ↓
Prepare Environment
    ↓
Run TestNG Tests
    ↓
Publish Test Results
    ↓
Slack Notification
```

Jenkins uses a dedicated TestNG suite for CI execution:

```text
src/test/resources/jenkins-testing.xml
```

Sensitive environment configuration is injected through Jenkins Credentials instead of being stored in the repository.

## Reporting & Integration

* Allure Report for test execution results
* Jenkins for CI/CD and build monitoring
* Jira for automatic bug creation
* Slack for build notifications

## Database Validation

SQL Server is used for database-level validation in selected test cases, such as verifying registered user information.

## Demo

🎥 [Watch Automation Framework Demo](https://drive.google.com/file/d/1CJqN9KsOym1aYVJEXu0u6-wXesgGiWjj/view?usp=sharing)

## Notes

This project is created for automation testing practice and portfolio demonstration.
