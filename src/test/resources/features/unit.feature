Feature: Unit CRUD operations
  Managing military units in the warfare simulation system.

  Scenario: Create a new unit
    Given the REST API is running
    When I create a unit with:
      | name     | Husaria |
      | type     | LAND    |
      | faction  | POLAND  |
      | strength | 100     |
      | status   | ACTIVE  |
    Then the response status should be 201
    And the response should contain field "name" with value "Husaria"

  Scenario: List all units
    Given the REST API is running
    When I send GET request to "/api/units"
    Then the response status should be 200
    And the response array should contain an object with field "name" = "Husaria"

  Scenario: Filter units by name
    Given the REST API is running
    When I send GET request to "/api/units/by-name?name=h"
    Then the response status should be 200
    And the response array should contain an object with field "name" = "Husaria"

  Scenario: Delete a unit
    Given the REST API is running
    When I send DELETE request to "/api/units/1"
    Then the response status should be 204
