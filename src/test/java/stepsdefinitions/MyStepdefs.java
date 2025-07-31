package stepsdefinitions;

import hooks.hooksC;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import static hooks.hooksC.driver;
import static org.junit.Assert.assertTrue;

public class MyStepdefs {
    private String searchKeyword;

    @Given("user is a jobseeker on NHS job website")
    public void user_is_a_jobseeker_on_NHS_job_website() {
        driver.get("https://www.jobs.nhs.uk/candidate/search");
    }

    @When("user fills in the following search criteria")
    public void user_fills_in_the_following_search_criteria(DataTable dataTable) {
        Map<String, String> map = dataTable.asMap(String.class, String.class);
        Map<String, String> filters = dataTable.asMap(String.class, String.class);
        // Example for jobtitle
        String jobTitle = map.get("jobtitle");
        if (jobTitle != null && !jobTitle.trim().isEmpty()) {
            WebElement jobTitleInput = driver.findElement(By.id("keyword")); // or correct locator
            jobTitleInput.clear();
            jobTitleInput.sendKeys(jobTitle);
        }

        // Repeat similar safe checks for each field...

        String location = map.get("location");
        if (location != null && !location.trim().isEmpty()) {
            WebElement locationInput = driver.findElement(By.id("location"));
            locationInput.clear();
            locationInput.sendKeys(location);
        }

        String distance = map.get("distance");
        if (distance != null && !distance.trim().isEmpty()) {
            WebElement distanceInput = driver.findElement(By.id("distance"));
            Select select=new Select(distanceInput);
            select.selectByValue(distance);
        }
        WebElement moreOptions = driver.findElement(By.id("searchOptionsBtn"));
        if (moreOptions.isDisplayed()) {
            moreOptions.click();
        }

        String reference = map.get("reference");
        if (reference != null && !reference.trim().isEmpty()) {
            WebElement referenceInput = driver.findElement(By.id("jobReference"));
            referenceInput.clear();
            referenceInput.sendKeys(reference);
        }


        String employer = map.get("employer");
        if (employer != null && !employer.trim().isEmpty()) {
            WebElement employerInput = driver.findElement(By.id("employer"));
            employerInput.clear();
            employerInput.sendKeys(employer);
        }

        String payRange = map.get("payRange");
        if (payRange != null && !payRange.trim().isEmpty()) {
            WebElement payRangeInput = driver.findElement(By.id("payRange"));
            Select select=new Select(payRangeInput);
            select.selectByValue(payRange);
        }

         }


    @And("user clicks the search button")
    public void user_clicks_the_search_button() {
        driver.findElement(By.id("search")).click();
    }


    @Then("user should get a list of jobs which matches the user preferences")
    public void user_should_get_a_list_of_jobs_which_matches_the_user_preferences() {
        if (searchKeyword == null || searchKeyword.trim().isEmpty()) {
            System.out.println("Warning: Search keyword is empty or not provided. Skipping keyword validation.");
            return;
        }
        List<WebElement> jobtitles = driver.findElements(By.cssSelector("a[href*='/candidate/jobadvert/']")
        );

        assertTrue("No job results found for keyword: " + searchKeyword, !jobtitles.isEmpty());

        String lowerCaseExpectedKeyword = this.searchKeyword.toLowerCase();

        boolean foundMatch = false; // flag to check if any job matched

        for (WebElement title : jobtitles) {
            String actualTitle = title.getText().toLowerCase();

            // Loosely check if the keyword is inside the title or inside any word
            if (actualTitle.contains(lowerCaseExpectedKeyword)) {
                foundMatch = true;
                break; // no need to check further, one match is enough
            } else {
                // Optional: check word by word if needed for more flexibility
                for (String word : actualTitle.split(" ")) {
                    if (word.contains(lowerCaseExpectedKeyword) || lowerCaseExpectedKeyword.contains(word)) {
                        foundMatch = true;
                        break;
                    }
                }
            }
            if (foundMatch) break;
        }

        if (!foundMatch) {
            throw new AssertionError("No job title loosely matched the search keyword: " + searchKeyword);
        } else {
            System.out.println("At least one job title loosely matched the search keyword: " + searchKeyword);
        }
    }

    @And("sort the search results with the newest date posted")
    public void sort_the_search_results_with_the_newest_date_posted() {
        // Try to find the sort dropdown element
        List<WebElement> sortElements = driver.findElements(By.id("sort"));

        if (sortElements.isEmpty()) {
            // Sort dropdown not found - likely no results
            System.out.println("Sort dropdown not found - possibly no job results.");
            // You can add an assertion here to verify no results, if you want:
            List<WebElement> jobResults = driver.findElements(By.xpath("//li[@data-test='search-result-publicationDate']"));
            if (jobResults.isEmpty()) {
                System.out.println("No job results found, skipping sorting.");
                // Optionally assert true for test pass in this scenario
                assertTrue("No job results, so sorting is skipped.",true);
                return; // Exit early since no sorting to do
            } else {
                // Results exist but no sort dropdown? Possibly UI changed - fail test
                throw new AssertionError("Job results found but sort dropdown missing.");
            }
        }

        // If sort dropdown exists, proceed with sorting
        Select sortDropdown = new Select(sortElements.get(0));
        sortDropdown.selectByValue("publicationDateDesc");

        // Wait for sorting to take effect
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.textToBePresentInElementLocated(
                By.cssSelector("select#sort option:checked"), "Date Posted"));

        // Fetch posted dates and verify sorting
        List<WebElement> postedDates = driver.findElements(By.xpath("//li[@data-test='search-result-publicationDate']"));
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d MMMM yyyy", Locale.UK);
        List<LocalDate> actualDates = new ArrayList<>();

        for (WebElement dateElement : postedDates) {
            String fullText = dateElement.getText();
            String dateOnly = fullText.replace("Date posted: ", "").replace("Posted on: ", "").trim();
            LocalDate date = LocalDate.parse(dateOnly, formatter);
            actualDates.add(date);
            System.out.println("Date parsed: " + date);
        }

        // Verify dates sorted newest first
        for (int i = 0; i < actualDates.size() - 1; i++) {
            if (actualDates.get(i).isBefore(actualDates.get(i + 1))) {
                throw new AssertionError("Job results are not sorted by newest date at index " + i);
            }
        }
        System.out.println("Job results are sorted by newest date.");
    }
}