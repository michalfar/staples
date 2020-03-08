Feature: GetBook

  @api
  Scenario Outline: View Single Book Record
    Given Application is working
    When User gets Book record Data by "<Id>"
    Then Books record is equal to "<Title>" and "<PageCount>"
    Examples:
      | Id | Title  | PageCount |
      | 2  | Book 2 | 200       |