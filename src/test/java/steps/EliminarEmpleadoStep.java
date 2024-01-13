package steps;

import Utilitarios.Util;
import Utilitarios.ValidacionUtils;
import api.Empleado.EmpleadoAPI;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;

import static org.hamcrest.Matchers.equalTo;

public class EliminarEmpleadoStep {
    private EmpleadoAPI empleadoAPI;
    private Response response;
    private Util util ;

    @Given("estoy usando la baseUri para eliminar un empleado {string}")
    public void estoyUsandoLaBaseUriParaEliminarUnEmpleado(String URI) {
        empleadoAPI = new EmpleadoAPI(URI);
    }

    @When("hago un pedido delete al API {string} con id {string}")
    public void hagoUnPedidoDeleteAlAPIConIdId(String basePath,String id) {
        response=empleadoAPI.eliminarEmpleado(basePath,id);
    }
    @Then("valido status code {int} del api eliminar Empleado")
    public void validoStatusCodeDelApiEliminarEmpleado(int statusCode) {
        ValidacionUtils.validarStatusCode(response, statusCode);
    }
    @And("valido el response el status {string} y mensaje {string} y id{string}")
    public void validoElResponseElStatusStatusYMensajeMensajeYIdId(String status, String mensaje,String id) {
       ValidacionUtils.validarResponseStatusYmensaje(response,status,mensaje,id);
        Util.pausar();
    }



}
