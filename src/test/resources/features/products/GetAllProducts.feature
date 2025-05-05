@getProducts
Feature: Get all products

  Background:
    Given the user sends a GET request to "/products"

  Scenario: User should be able to retrieve all products successfully and validate product fields
    Then the HTTP status code should be 200
    And the response body should be in JSON array format
    Then each product object should have an "id", "title", "price", "category", "description", "image", and "rating" field

  Scenario Outline: User should retrieve product by valid ID
    Given the user sends a GET request to "/products/<productId>"
    Then the HTTP status code should be 200
    And the response body should not be empty

    Examples:
      | productId |
      | 1         |
      | 20        |

  Scenario: API should respond within acceptable time
    Then the response time should be less than 2 seconds

  Scenario: Each product price should be a positive number
    Then each product should have a price greater than 0

  Scenario: Each product should contain valid rating information
    Then each product should contain rating with "rate" and "count" fields

  Scenario: API should return an error for invalid product ID
    Given the user sends a GET request to "/products/invalidProduct"
    Then the HTTP status code should be 200
    And the response body should be empty

