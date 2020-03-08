Feature: PostBook

  @api
  Scenario: Create a new Book Record
    Given Application is working
    When User creates new Book Record
    Then Books record is created
