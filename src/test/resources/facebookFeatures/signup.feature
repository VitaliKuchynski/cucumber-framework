@regression @signup
Feature: Verify Signup Feature

  Background:
    Given I am on home page

  @signup
  Scenario: Verify invalid signup
    When I enter mohammad into firstname text fields on home screen
    And I enter muntakim into lastname text fields on home screen
    And I enter 888888 into mobile number text fields on home screen
    And I enter test1234 into new password text fields on home screen
    And I click on create account button on home screen
    Then I verify invalid signup error message