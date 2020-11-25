@smoke
Feature: SUT Page titles and delivery
  If these pages do not draw fully, then further testing is very likely to give false failures.

  Scenario: Visit a page
    When I navigate to the page ""
    Then the page is fully drawn
    And the page title is "PBLF: Home"