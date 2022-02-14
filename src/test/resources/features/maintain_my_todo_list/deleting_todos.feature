@cucumber
@deleting
Feature: Deleting todos


  Scenario: Delete an active item
    Given that userTwo has a todo list containing Clean room, File Tax
    When she deletes the task called 'File Tax'
    Then her todo list should contain Clean room

  Scenario: Delete all the items
    Given that userTwo has a todo list containing Clean room, File Tax
    When she deletes the task called 'File Tax'
    And she deletes the task called 'Clean room'
    Then her todo list should be empty
