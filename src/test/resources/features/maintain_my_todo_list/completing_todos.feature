@cucumber
@completing
Feature: Completing todos

  Scenario: Mark a task as completed
    Given that userTwo has a todo list containing Clean room, File Tax
    When she completes the task called 'File Tax'
    And she filters her list to show only Completed tasks
    Then her todo list should contain File Tax

  Scenario: List of completed items should be empty if nothing has been completed
    Given that userTwo has a todo list containing Clean room, File Tax
    When she filters her list to show only Completed tasks
    Then her todo list should be empty

