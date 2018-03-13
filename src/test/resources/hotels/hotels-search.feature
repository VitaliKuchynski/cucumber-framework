@Hotels
Feature: Hotels search future

  Background:
    Given I am on Hotels home page

  @Hotels-search-verification
  Scenario Outline: Verify user is able to successfully search for hotels
      When  I enter <city> into search field
      And   I select Check in date
      And   I select Check out date
      And   I select More options
      And   I select 1 from Rooms dropdown
      And   I select 2 from Adults dropdown
      And   I select 2 from Children dropdown
      And   I select 1 year's' for first child age
      And   I select 2 year's' for second child age
      And   I click on Search button
      Then  I verify city search result is displayed correctly
      And   I verify dates search result is displayed correctly
      And   I verify rooms, people search result is displayed correctly
      And   I verify nights search result is displayed correctly


    Examples:
      |city         |
      |San Francisco|



