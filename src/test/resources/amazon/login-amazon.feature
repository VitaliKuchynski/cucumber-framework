@amazonLogin
Feature: Amazon login feature

  Background:
    Given I am on home page of Amazon

  @amazon-login-1
  Scenario: Verify user should not be able to login using invalid credentials
    When  I hover over to Accounts & List
    And   I click on Sign in button
    And   I enter invalid semen.testerov@gmail.com into email address
    And   I click on continue button
    Then  I verify invalid error message

  @amazon-login-2
Scenario: Verify user should not be able to create empty profile
    When I click on Accounts & List link
    And  I click on Create your Amazon account button
    Then I verify error message