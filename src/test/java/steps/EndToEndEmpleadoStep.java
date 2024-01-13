package steps;

import Utilitarios.Util;
import Utilitarios.ValidacionUtils;
import api.Empleado.EmpleadoAPI;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import io.restassured.response.Response;
import model.Empleado;
import org.hamcrest.Matchers;

import static Utilitarios.Constantes.*;

public class EndToEndEmpleadoStep {

    private Empleado empleado;
    private EmpleadoAPI empleadoAPI;
    private Response response;
    static Util util;
    private int idEmpleadoCreado;
    private String idEmpleado;

    @Given("quiero realizar un flujo  end to end a las APIs Empleado")
    public void quieroRealizarUnFlujoEndToEndALasAPIsEmpleado() {
        empleadoAPI = new EmpleadoAPI(BASE_URI_EMPLEADO);
    }

    @When("creo un nuevo empleado con nombre {string} salario {int} edad {int}")
    public void creoUnNuevoEmpleadoConNombreSalarioEdad(String name, int salario, int edad) {
        empleado = new Empleado(name, salario, edad);
        response = empleadoAPI.crearEmpleado(BASE_PATH_CREAR_EMPLEADO, empleado);

        idEmpleadoCreado = response.then().extract().path("data.id");
        System.out.println("Id empleado creado: " + idEmpleadoCreado);

        String responseCreate=response.getBody().asString();
        System.out.println("Response body Crear Empleado: +\n"+responseCreate);
    }

    @Then("obtengo detalles del empleado recien creado")
    public void obtengoDetallesDelEmpleadoRecienCreado() {
        response = empleadoAPI.obtenerEmpleadoPorId(BASE_PATH_FILTRAR_EMPLEADO, idEmpleadoCreado);
        ValidacionUtils.validarStatusCode(response, STATUS_CODE);

        String responseFiltrar=response.getBody().asString();
        System.out.println("Response body filtrar Empleado: +\n"+responseFiltrar);
    }

    @When("actualizo informacion del empleado a nombre {string} salario {int} edad {int}")
    public void actualizoInformacionDelEmpleadoANombreSalarioEdad(String nuevoNombre, int nuevoSalario, int nuevaEdad) {
        Empleado empleadoActualizado = new Empleado(nuevoNombre, nuevoSalario, nuevaEdad);
        response = empleadoAPI.actualizarEmpleado(BASE_PATH_ACTUALIZAR_EMPLEADO, idEmpleadoCreado, empleadoActualizado);
        ValidacionUtils.validarStatusCode(response, STATUS_CODE);

        String responseActualizar=response.getBody().asString();
        System.out.println("Response body actualizar Empleado: +\n"+responseActualizar);
    }

    @Then("verifico que la informacion se haya actualizado correctamente")
    public void verificoQueLaInformacionSeHayaActualizadoCorrectamente() {
        //response = empleadoAPI.obtenerEmpleadoPorId(BASE_PATH_FILTRAR_EMPLEADO, idEmpleadoCreado);
    }

    @Then("valido el response data.name sea igual a {string}")
    public void validoElResponseDataNameSeaIgualA(String expectedName) {

        ValidacionUtils.validarCampoSeaIgualAlValor_String(response,"data.name",expectedName);

    }

    @And("valido el response data.salary sea igual a {int}")
    public void validoElResponseDataSalarySeaIgualA(int expectedSalary) {
        //response.then().body("data.salary", equalTo(expectedSalary));
        ValidacionUtils.validarCampoSeaIgualAlValor_Int(response,"data.salary",expectedSalary);
    }

    @And("valido el response data.age sea igual a {int}")
    public void validoElResponseDataAgeSeaIgualA(int expectedAge) {
        //response.then().body("data.age", equalTo(expectedAge));
        ValidacionUtils.validarCampoSeaIgualAlValor_Int(response,"data.age",expectedAge);
    }
    @When("elimino el empleado creado")
    public void eliminoElEmpleadoCreado() {

         idEmpleado=Util.convertirAString(idEmpleadoCreado);
        response= empleadoAPI.eliminarEmpleado(BASE_PATH_ELIMINAR_EMPLEADO,idEmpleado);
    }
    @Then("valido que ya no exista el empleado")
    public void validoQueYaNoExistaElEmpleado() {
        ValidacionUtils.validarResponseStatusYmensaje(response,"success","Successfully! Record has been deleted",idEmpleado);

    }

}


