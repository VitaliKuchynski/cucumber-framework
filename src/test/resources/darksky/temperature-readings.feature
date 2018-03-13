@DarkSky
Feature: Dark sky temperature future

  Background:
    Given I am on Dark sky home page

  @DarkSky-weekdays
    Scenario: Verify weekly forecast days are displayed correctly
    Then    I verify days of the week is displayed in correct order


  @DarkSky-temperature
    Scenario: Verify low to high value is displayed correctly on weekly forecast section
    When  I click on today bar
    Then  I verify low and high temperature displayed correctly on parent bar


  @DarkSky-TomorrowsDate
    Scenario: Select Tomorrow date from Time Machine
    When    I click on time machine
    And     I select tomorrow date
    Then    I verify selected date is not clickable
    And     I verify date is displayed in correct format