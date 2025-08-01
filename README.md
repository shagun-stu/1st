🧪 NHS Jobs Search Automation Suite – shagun-stu/NHSJobSearch-BDD
🔍 Overview
This project automates the job search flow on the NHS Jobs Portal using a Behavior-Driven Development (BDD) approach. Built with Java, Selenium WebDriver, Cucumber, and JUnit/TestNG, this suite simulates real-world job search interactions, validates functionality, and ensures UI behavior across different browsers.

👥 User Story
As an NHS jobseeker,
I want to search for vacancies using filters and keywords,
So that I can view roles that match my needs.

✅ Acceptance Criteria
Given I access the NHS Jobs search page

When I input job preferences like title, location, or employer

Then I should see relevant job listings

And I can refine or sort results by latest postings

🧰 Tech Stack
Tool	Description
Java (17/21)	Core programming language
Maven	Build automation and dependency manager
Selenium WebDriver	Web interaction and automation
Cucumber	BDD framework for feature-based testing
JUnit / TestNG	Test execution framework
WebDriverManager	Auto-management of browser drivers
Log4j (optional)	Logging test events and outcomes

📁 Project Structure
bash
Copy
Edit
NHSJobSearch-BDD/
├── src/
│   ├── main/
│   │   └── java/
│   │       └── utils/
│   │           ├── DriverFactory.java        # Driver setup (Chrome, Firefox)
│   │           └── LoggerHelper.java         # Log configuration (if used)
│
│   └── test/
│       ├── java/
│       │   ├── steps/                         # Step definitions
│       │   ├── pages/                         # Page Object classes
│       │   └── runners/                       # Cucumber test runner
│       └── resources/
│           ├── features/                      # .feature files (BDD)
│           └── logs/                          # Log4j file (if used)
│
├── logs/                                      # Automation logs
├── pom.xml                                    # Maven config
└── README.md                                  # Project documentation
🔍 Functional Coverage
The automation suite covers:

✅ Search Functionality
Job keyword, location, distance

Employer, job title, pay band, reference number

Contract type and working pattern filters

Search with partial, invalid, or empty inputs

✅ UI Behavior Checks
Default dropdown selections (e.g., Distance)

Input field placeholders and hints

Suggestion logic for locations

Validations for incorrect user inputs

✅ Edge Case Handling
Typos in location or keywords

Fake employer/reference values

Special characters and random entries

No suggestion or results scenarios

✅ Browser Compatibility
Executed on Chrome and Firefox

Browser drivers managed via WebDriverManager

▶️ Running the Suite
🔧 Prerequisites
Java 17+ or 21

Maven 3.8+

Chrome or Firefox browser

Internet connection

📥 Clone & Run
bash
Copy
Edit
git clone https://github.com/shagun-stu/NHSJobSearch-BDD.git
cd NHSJobSearch-BDD
mvn clean test -Dbrowser=chrome
🔁 Replace chrome with firefox to switch browsers

🔖 Run by Tags
Tags	Command Example
Only positive tests	mvn test "-Dcucumber.filter.tags=@happy" -Dbrowser=chrome
UI validation	mvn test "-Dcucumber.filter.tags=@ui" -Dbrowser=chrome
Negative cases	mvn test "-Dcucumber.filter.tags=@unhappy" -Dbrowser=chrome
Mixed tags	mvn test "-Dcucumber.filter.tags=@happy or @ui" -Dbrowser=chrome
All tests	mvn test "-Dcucumber.filter.tags=@happy or @ui or @unhappy" -Dbrowser=chrome

📊 Test Reports & Logging
HTML Test Report:
target/cucumber-reports/report.html

Execution Logs (if Log4j configured):
logs/automation.log

🚀 Future Scope
⏱️ Enable parallel execution (TestNG/Cucumber-JUnit)

👁️ Add accessibility testing via Axe-core or Lighthouse

🌍 Cloud grid execution via BrowserStack/SauceLabs

📦 Dockerize test runs for CI pipelines

📈 Allure/ExtentReports integration

📤 Email notification post test completion

🔄 Retry logic for flaky tests

📄 Use external data files (CSV/Excel) for dynamic inputs

☁️ Push reports to AWS S3 or other shared storage

👤 Author
shagun-stu
📧 (Your email here if desired)
🔗 GitHub Profile

