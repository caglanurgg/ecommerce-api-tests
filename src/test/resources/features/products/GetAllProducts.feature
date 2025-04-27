@getProducts
Feature: Get all products

  As a user
  I want to retrieve the list of all available products
  So that I can see what is currently in the catalog

  Scenario: Get all products successfully
    Given the API is available
    When I send a GET request to "/products"
    Then the response status code should be "200"
    And the response should contain a list of products
