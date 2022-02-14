@cucumber
@adding
Feature: Add new todos


  Scenario: Adding an item to an empty list in Cucumber
    Given that userOne has an empty todo list
    When he adds 'Clean room' to his list
    Then 'Clean room' should be recorded in his list

  Scenario: Adding an item to a list with other items in Cucumber
    Given that userTwo has a todo list containing Clean room, File Tax
    When she adds 'Buy some cereal' to her list
    Then her todo list should contain Clean room, File Tax, Buy some cereal

  Scenario: Adding items to several peoples lists in Cucumber
    Given that userOne has a todo list containing Clean room, File Tax
    And that Jill has a todo list containing Clean room, Wash car
    When she adds 'Buy some cereal' to her list
    Then Jill's todo list should contain Clean room, Wash car, Buy some cereal
    And userOne's todo list should contain Clean room, File Tax
