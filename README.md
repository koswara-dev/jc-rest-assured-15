# Automation REST API Testing with Java, REST Assured, and TestNG

Welcome to the Automation REST API Testing project using Java, REST Assured, and TestNG. This project is designed to help you automate REST API testing processes efficiently.

## Prerequisites

Before you begin, ensure you have met the following requirements:
- **Java JDK 17**
- **IntelliJ IDEA** (Community or Ultimate edition)
- **Maven** (installed and configured)

## Installation

1. **Clone the repository:**
   ```bash
   git clone https://github.com/yourusername/api-testing-java-restassured-testng.git
   cd api-testing-java-restassured-testng
   ```

2. **Open the project in IntelliJ IDEA:**
   - Launch IntelliJ IDEA.
   - Click on `File` > `Open`.
   - Navigate to the cloned repository's directory and select it.

3. **Build the project with Maven:**
   - Open the Terminal window within IntelliJ IDEA.
   - Run the following command:
     ```bash
     mvn clean install
     ```

## Usage

### Writing Tests

1. **Create a Test Class:**
   Create a new test class under the `src/test/java` directory.

   Example test case:
   ```java
   import io.restassured.RestAssured;
   import io.restassured.response.Response;
   import org.testng.Assert;
   import org.testng.annotations.Test;

   public class ApiTest {

       @Test
       public void getUsersTest() {
           Response response = RestAssured
               .given()
               .baseUri("https://jsonplaceholder.typicode.com")
               .when()
               .get("/users")
               .then()
               .statusCode(200)
               .extract().response();

           Assert.assertEquals(response.getStatusCode(), 200);
           Assert.assertNotNull(response.jsonPath().getList("$"));
       }
   }
   ```

### Running Tests

1. **Run from IntelliJ IDEA:**
   - Right-click on the `testng.xml` file.
   - Select `Run 'testng.xml'`.

2. **Run using Maven:**
   - Open the Terminal window within IntelliJ IDEA.
   - Run the following command:
     ```bash
     mvn test
     ```

---

Happy testing! If you encounter any issues, feel free to open a new issue in the repository.

## Buy me a coffe

If you like this project and want to support its further development, buy me a coffee!

[![Buy Me a Coffee](https://www.buymeacoffee.com/assets/img/guidelines/download-assets-sm-1.svg)](https://www.buymeacoffee.com/kudajengke404)
