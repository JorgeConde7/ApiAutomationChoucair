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

public class CrearEmpleadoStep {
    private Empleado empleado;
    private EmpleadoAPI empleadoAPI;
    private Response response;
    private Util util ;

    @Given("estoy usando la baseUri para crear un nuevo empleado{string}")
    public void estoyUsandoLaBaseUriParaCrearUnNuevoEmpleado(String URI) {
        empleadoAPI = new EmpleadoAPI(URI);
    }

    @When("envio un body con datos name {string} salario {int} edad {int}")
    public void envioUnBodyConDatosNameNameSalarioSalarioEdadEdad(String name, int salario, int edad) {
        empleado= new Empleado(name,salario,edad);
    }

    @And("hago un pedido post al API {string}")
    public void hagoUnPedidoPostAlAPI(String basePath) {
        response=empleadoAPI.crearEmpleado(basePath,empleado);
    }

    @Then("valido el status code {int} del api crear Empleado")
    public void validoElStatusCodeCodeDelApiCrearEmpleado(int statusCode) {
        ValidacionUtils.validarStatusCode(response, statusCode);
    }

    @And("valido el response el status {string} y mensaje {string}")
    public void validoElResponseElStatusStatusYMensajeMensaje(String status, String mensaje) {
        ValidacionUtils.validarResponseEmpleado(response,empleado,status,mensaje,null);

    }



}
