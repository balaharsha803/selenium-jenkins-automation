# Automated Software Testing of Web Applications Using Selenium and Jenkins

## Project overview
This project implements browser-based regression testing using Java, Selenium WebDriver, TestNG, Maven and Jenkins. A small demo web application is included so the project can be run without depending on an external website.

## Requirements
- JDK 17 or later
- Apache Maven 3.9+
- Git
- Google Chrome
- Jenkins with a JDK 17 tool named `JDK17` and Maven tool named `Maven3`
- Python 3 (used only to serve the included demo application in the Jenkins pipeline)

Selenium Manager can resolve a compatible browser driver when network access is available.

## Run locally (Windows)
1. Extract the project and open the folder in VS Code or Eclipse.
2. Start a terminal in the project root.
3. Start the demo application in a separate terminal:
   ```powershell
   cd src/test/resources/webapp
   python -m http.server 8080
   ```
4. Open another terminal at the project root and run:
   ```powershell
   mvn clean test -DbaseUrl=http://127.0.0.1:8080 -Dbrowser=chrome
   ```

## Demo credentials
- Username: `demo`
- Password: `Password123!`

These are synthetic credentials for the local demo application only.

## Jenkins setup
1. Install Jenkins and configure JDK 17 and Maven in **Manage Jenkins → Tools**.
2. Push this project to a GitHub repository.
3. Create a **Pipeline** job and select **Pipeline script from SCM**.
4. Select Git, provide your repository URL and branch, and set the script path to `Jenkinsfile`.
5. Ensure the Jenkins agent has Chrome/Chromium, Python 3, `curl`, and network access for Maven dependencies.
6. Run **Build Now**. The Jenkinsfile starts the demo web server, runs Maven tests and publishes TestNG/Surefire XML reports.

The included Jenkinsfile uses Linux shell commands. For a Windows Jenkins agent, replace the `sh` steps with equivalent `bat` steps and configure Chrome for that agent.

## Test scenarios
- Successful login with valid credentials
- Rejected login with invalid credentials
- Required-field validation on the feedback form
- Successful feedback form submission
- Navigation between Home and About sections

## Project structure
```text
selenium-jenkins-automation/
├── pom.xml
├── testng.xml
├── Jenkinsfile
├── README.md
└── src/
    └── test/
        ├── java/com/devops/
        │   ├── base/BaseTest.java
        │   ├── driver/DriverFactory.java
        │   ├── pages/LoginPage.java
        │   ├── pages/FeedbackPage.java
        │   └── tests/
        │       ├── LoginTests.java
        │       ├── FormValidationTests.java
        │       └── NavigationTests.java
        └── resources/webapp/index.html
```

## Reports and screenshots
- TestNG/Surefire XML reports: `target/surefire-reports/`
- Failure screenshots: `target/screenshots/`
- Jenkins publishes the XML reports and screenshots as build artifacts.

## Security
Use Jenkins Credentials for real test credentials. Never commit passwords, tokens or personal data. Run tests only against the included demo app or an approved development/staging environment.
