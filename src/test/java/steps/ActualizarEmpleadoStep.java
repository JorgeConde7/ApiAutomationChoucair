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

public class ActualizarEmpleadoStep {
    private Empleado empleado;
    private EmpleadoAPI empleadoAPI;
    private Response response;
    private Util util ;

    @Given("estoy usando la baseUri para actualizar un empleado {string}")
    public void estoyUsandoLaBaseUriParaActualizarUnEmpleado(String URI) {
        empleadoAPI = new EmpleadoAPI(URI);
    }
    @When("envio un body para actualizar con datos name {string} salario {int} edad {int} id {int}")
    public void envioUnBodyParaActualizarConDatosNameNameSalarioSalarioEdadEdad(String name, int salario, int edad, int id) {
        empleado= new Empleado(id,name,salario,edad);
    }

    @And("hago un pedido put al API {string} con id {int}")
    public void hagoUnPedidoPutAlAPIConId(String basePath, int id) {
        response=empleadoAPI.actualizarEmpleado(basePath,id,empleado);
    }

    @Then("valido status code {int} del api actualizar Empleado")
    public void validoStatusCodeDelApiActualizarEmpleado(int statusCode) {
        ValidacionUtils.validarStatusCode(response, statusCode);
    }

    @And("valido el responsebody, el id {int}, el status {string} y el mensaje {string}")
    public void validoElResponsebodyElIdIdElStatusStatusYElMensajeMensaje(int id,String status, String mensaje) {
        ValidacionUtils.validarResponseEmpleado(response,empleado,status,mensaje,id);
        Util.pausar();
    }
}
