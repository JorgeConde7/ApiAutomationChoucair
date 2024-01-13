# new feature
# Tags: optional
@CrudEmpleado
Feature: Crud API empleado

  Scenario: Prueba listar empleados
    Given estoy usando la baseUri "https://dummy.restapiexample.com/api/v1"
    When hago una solicitu GET a basePath "/employees"
      Then valido status code 200


  Scenario: Prueba filtrar empleado
    Given estoy usando la baseUri "https://dummy.restapiexample.com/api/v1"
    When hago un pedido get a basePath "/employee" con id 2
    Then valido el response  data.id sea igual a 2

  Scenario Outline: Prueba crear empleado
    Given estoy usando la baseUri para crear un nuevo empleado"https://dummy.restapiexample.com/api/v1"
    When envio un body con datos name <name> salario <salario> edad <edad>
    And hago un pedido post al API "/create"
    Then valido el status code <code> del api crear Empleado
    And valido el response el status <status> y mensaje <mensaje>

    Examples:
      |name  |salario|edad| status |mensaje|code|
      |"jorge" |5500   |27 |   "success"    | "Successfully! Record has been added."| 200|

  @TestEmpleado
  Scenario Outline: Prueba actualizar empleado
    Given estoy usando la baseUri para actualizar un empleado "https://dummy.restapiexample.com/api/v1"
    When envio un body para actualizar con datos name <name> salario <salario> edad <edad> id <id>
    And hago un pedido put al API "/update" con id <id>
    Then valido status code <code> del api actualizar Empleado
    And valido el responsebody, el id <id>, el status <status> y el mensaje <mensaje>
    Examples:
      |id|name  |salario|edad| status |mensaje|code|
      |2|"jorge" |4000   |27 |   "success"    | "Successfully! Record has been updated."|200|


  Scenario Outline: Prueba eliminar empleado
    Given estoy usando la baseUri para eliminar un empleado "https://dummy.restapiexample.com/api/v1"
    When hago un pedido delete al API "/delete" con id <id>
    Then valido status code <code> del api eliminar Empleado
    And valido el response el status <status> y mensaje <mensaje> y id<id>
    Examples:
      |id| status |mensaje|code|
      |"3"|   "success"    | "Successfully! Record has been deleted"|200|