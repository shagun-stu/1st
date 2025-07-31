Feature: NHS Job Search Functionality

  Scenario Outline: NHS job search with various criteria
    Given user is a jobseeker on NHS job website
    When user fills in the following search criteria
      | jobtitle       | <jobtitle>       |
      | location       | <location>       |
      | distance       | <distance>       |
      | reference      | <reference>      |
      | employer       | <employer>       |
      | payRange       | <payRange>       |
      | workingPattern | <workingPattern> |
      | contractType   | <contractType>   |


    And user clicks the search button
    Then user should get a list of jobs which matches the user preferences
    And sort the search results with the newest date posted

    Examples:
      | jobtitle   | location   | distance | reference | employer                                  | payRange | workingPattern | contractType |
      | nurse      | london     | 10       | 123-AB    | Guy's and St Thomas' NHS Foundation Trust | 10-20    | Full time      | Permanent    |
      | doctor     | manchester | 20       |           | Manchester NHS Trust                      | 30-40    | Part time      | Fixed-term   |
      | pharmacist | birmingham |          |           |                                           | 0-10     | Full time      | Permanent    |
      | reception  | coventry   | 30       | 789-EF    | Birmingham Health NHS Foundation Trust    |          | Part time      | Bank         |
