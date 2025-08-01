# 🧪 NHS Job Search Automation Suite – `shagun-stu/NHSJobSearch-BDD`

## 🔍 Overview

This project automates the **job search flow** on the [NHS Jobs Portal](https://www.jobs.nhs.uk/candidate/search) using a **Behavior-Driven Development (BDD)** framework.  
It is implemented with **Java**, **Selenium WebDriver**, **Cucumber**, and **JUnit/TestNG**, and supports realistic search scenarios and browser compatibility.

---

## 👥 User Story

> **As an NHS jobseeker**  
> I want to search for jobs using my preferences  
> So that I can view jobs tailored to my interests

---

## ✅ Acceptance Criteria

- **Given** I am on the NHS Jobs site  
- **When** I provide job search inputs  
- **Then** I should see relevant job results  
- **And** I should be able to filter and sort them

---

## 💻 Tech Stack

| Technology         | Purpose                                      |
|--------------------|----------------------------------------------|
| Java (17/21)       | Core programming language                    |
| Maven              | Build automation & dependency management     |
| Selenium WebDriver | Browser automation                           |
| Cucumber (Gherkin) | BDD-style test case writing                  |
| JUnit / TestNG     | Test runner                                  |
| WebDriverManager   | Auto-handles browser driver setup            |
| Log4j *(optional)* | Logging execution details                    |

---

## 📂 Project Structure


---

## 🔎 Test Coverage

### 🔸 Search Fields Tested:
- ✅ Job title / keyword  
- ✅ Location and distance  
- ✅ Reference number  
- ✅ Employer  
- ✅ Pay range  
- ✅ Contract type  
- ✅ Working pattern  

### 🔸 UI Validation:
- ✅ Default values in dropdowns  
- ✅ Placeholder & hints  
- ✅ Suggestion logic for location  
- ✅ Input error validations  

### 🔸 Edge & Negative Cases:
- ✅ Misspelled location, fake employer  
- ✅ Random characters, blank inputs  
- ✅ Inputs that trigger no suggestions  

### 🔸 Cross-Browser:
- ✅ Chrome  
- ✅ Firefox  
- ✅ Handled dynamically via WebDriverManager  

---

## ▶️ Test Execution Guide

### 🔧 Prerequisites:
- Java 17+  
- Maven 3.8+  
- Chrome or Firefox browser  

### 📥 Clone the Repo:
```bash
git clone https://github.com/shagun-stu/NHSJobSearch-BDD.git
cd NHSJobSearch-BDD

---



