@regression @Epam
Feature: This is the Table UI feature

  Scenario: Count the number of status in task table
    When User clicks onto the "Table"
    And User clicks onto the "Table Data Search"
    Then Validate the count of value table index 1 with column index 4
      | in progress | 3 |
      | deployed    | 1 |
      | completed   | 1 |
      | failed qa   | 2 |
