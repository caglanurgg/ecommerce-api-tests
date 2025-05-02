@getProducts
Feature: Get all products

  Scenario: User should be able to retrieve all products successfully and validate product fields
    Given the user sends a GET request to "/products"
    Then the HTTP status code should be 200
    And the response body should be in JSON array format
    Then each product object should have an "id", "title", "price", "category", "description", "image", and "rating" field


