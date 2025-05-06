@addProducts
Feature: Product created successfully

  Scenario:
    Given the user sends a POST request to "/products" with valid product details
    Then the API should return a successful HTTP status code
    And the response body should contain the product details

  Scenario: Measure response time for product creation
    Given the user sends a POST request to "/products" with valid product details
    Then the API should respond within 500 milliseconds

  Scenario: Confirm API returns the same response for identical requests
    Given the user sends a POST request to "/products" with valid product details
    And the user sends the same POST request again
    Then the API should return the exact same response for both requests
    And log a warning if the ID remains unchanged

