@calc_sample
Feature: Basic Arithmetic

  Background: A Calculator
    Given a calculator I just turned on

  Scenario: Addition
    When I add 4 and 5
    Then the result is 9

  Scenario: Substraction
    When I substract 7 to 2 
    Then the result is 5

  Scenario: Multiplication
    When I multiply 3 and 4 
    Then the result is 12
    
  Scenario: Division
    When I divide 10 by 4 
    Then the result is 2.5