Feature: User Login

  Background:
    Given Open browser and maximize window

  Scenario: Successful login with valid credentials
    Given the user is on the login page
    When the user enters valid credentials "emily.johnson57@example.com" and "EmilyPass123!"
    And clicks the login button
    Then the user should be redirected to the home page
    Then the user email "emily.johnson57@example.com" should be displayed on the home page

  Scenario: Successful registration and redirect to login page
    Given user is on the registration page
    When user inputs registration data:
      | First Name | Last Name | Date of Birth | Email                        | Password      | Confirm Password |
      | Emily      | Johnson   | 08/22/1995    | emily.johnson678@example.com | EmilyPass123! | EmilyPass123!    |
    And user submits the registration form
    Then user should be redirected to login page

  Scenario: New user cannot register with mismatched password
    Given user is on the registration page
    When user inputs registration data:
      | First Name | Last Name | Date of Birth | Email                       | Password      | Confirm Password |
      | Emily      | Johnson   | 08/22/1995    | emily.johnson93@example.com | EmilyPass123! | EmilyPass321!    |
    And user submits the registration form
    Then the error message "Passwords must match" should be displayed