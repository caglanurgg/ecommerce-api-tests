@addProducts
Feature: Product created successfully

  Scenario:
    Given the user sends a POST request to "/products" with valid product details
    Then the API should return a successful HTTP status code
    And the response body should contain the product details


