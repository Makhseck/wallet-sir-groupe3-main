Feature: Wallet API
  As a fintech developer
  I want to test the Wallet API
  So that I can ensure it is working correctly

  Scenario: Create wallet
    Given a wallet with name "My Wallet" and balance 100
    When I POST the wallet to the "/wallets" endpoint
    Then the response status should be 201
    And the response body should contain the wallet

  Scenario: Get wallet
    Given a wallet with ID 1 and name "My Wallet" and balance 100
    When I GET the wallet from the "/wallets/1" endpoint
    Then the response status should be 200
    And the response body should contain the wallet

  Scenario: Update wallet
    Given a wallet with ID 1 and name "My Wallet" and balance 100
    When I PUT the wallet to the "/wallets/1" endpoint with name "Updated Wallet" and balance 200
    Then the response status should be 200
    And the response body should contain the updated wallet

  Scenario: Delete wallet
    Given a wallet with ID 1 and name "My Wallet" and balance 100
    When I DELETE the wallet from the "/wallets/1" endpoint
    Then the response status should be 204
    And the wallet should not be retrievable