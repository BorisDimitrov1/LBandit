Feature: LBandit

  @smoke @regression
  Scenario:Login and validate balance
    When I navigate to LBandit
    And I click login button in the header of the page
    And I populate username field with "tu_boris"
    And I populate password field with "Pass112#"
    And I get the value from "getMemberBalance" body with path "$.data.1.info.amount" and save it as "Player Balance"
    And I click login button
    And I close any modals
    Then I validate the balance shown in the header is one saved as "Player Balance"
