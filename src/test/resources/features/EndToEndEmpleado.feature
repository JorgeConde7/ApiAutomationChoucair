@EndToEndAPIsEmpleado
Feature: Flujo End-to-End de APIs Empleado

    Background:
        Given quiero realizar un flujo  end to end a las APIs Empleado

    Scenario: Crear, Actualizar y Obtener Detalles de un Empleado

        When creo un nuevo empleado con nombre "Juan" salario 50000 edad 30
        Then obtengo detalles del empleado recien creado
        When actualizo informacion del empleado a nombre "Juan Actualizado" salario 55000 edad 31
        Then verifico que la informacion se haya actualizado correctamente
        And valido el response data.name sea igual a "Juan Actualizado"
        And valido el response data.salary sea igual a 55000
        And valido el response data.age sea igual a 31
        When elimino el empleado creado
        Then valido que ya no exista el empleado

    # Crear un nuevo empleado
        #When envio un body con datos name "Juan" salario 50000 edad 30
        #And hago un pedido post al API "/create"
        #Then valido status code 200
        #And valido el response el status "success" y mensaje "Empleado creado exitosamente"

    # Obtener detalles del empleado recién creado
        #When hago un pedido get a basePath "/employee" con id <id_del_empleado_creado>
        #Then valido el response data.name sea igual a "Juan"
        #And valido el response data.salary sea igual a 50000
        #And valido el response data.age sea igual a 30

    # Actualizar información del empleado
        #When envio un body con datos name "Juan Actualizado" salario 55000 edad 31
        #And hago un pedido put al API "/update" con id <id_del_empleado_creado>
        #Then valido status code 200
        #And valido el response el status "success" y mensaje "Empleado actualizado exitosamente"

    # Verificar que la información se haya actualizado correctamente
        #When hago un pedido get a basePath "/employee" con id <id_del_empleado_creado>
        #Then valido el response data.name sea igual a "Juan Actualizado"
        #And valido el response data.salary sea igual a 55000
        #And valido el response data.age sea igual a 31