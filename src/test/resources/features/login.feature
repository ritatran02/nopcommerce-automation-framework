Feature: User Login

  Background:
    Given user is on the Home page

  @TC02 @positive
  Scenario: Login successfully with valid credentials
    When user clicks on Login link
    And user enters email "ssdsdsds@gmail.com"
    And user enters password "Ngan@123"
    And user clicks Login button
    Then user should be redirected to Home page
    And My Account link should be displayed

  @TC03 @negative
  Scenario: Login with invalid password
    When user clicks on Login link
    And user enters email "ritatran@gmail.com"
    And user enters password "wrongpassword"
    And user clicks Login button
    Then login error message should be displayed
