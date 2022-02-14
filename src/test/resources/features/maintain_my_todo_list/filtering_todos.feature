@cucumber
@filtering
Feature: Filtering todos

  Scenario: View only the completed items
    Given that userTwo has a todo list containing Buy some milk, File Tax
    And she has completed the task called 'File Tax'
    When she filters her list to show only Completed tasks
    Then her todo list should contain File Tax

  Scenario Outline: Viewing the items by status
    Given that userTwo has a todo list containing <tasks>
    And she has completed the task called 'File Tax'
    When she filters her list to show only <filter> tasks
    Then her todo list should contain <expected>
    Examples:
      | tasks                       | filter    | expected                     |
      | Buy some milk, File Tax | Active    | Buy some milk                |
      | Buy some milk, File Tax | Completed | File Tax                 |
      | Buy some milk, File Tax | All       | Buy some milk,  File Tax |
