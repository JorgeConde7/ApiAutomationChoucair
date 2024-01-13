package steps;

import Utilitarios.Util;
import Utilitarios.ValidacionUtils;
import api.Empleado.EmpleadoAPI;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;

import org.apache.http.HttpStatus;
import static org.hamcrest.Matchers.equalTo;

public class ListarEmpleadoStep {
    private EmpleadoAPI empleadoAPI;
    private Response response;
    private ValidatableResponse json;

    @Given("estoy usando la baseUri {string}")
    public void estoyUsandoLaBaseUri(String URI) {
        empleadoAPI = new EmpleadoAPI(URI);
    }

    @When("hago una solicitu GET a basePath {string}")
    public void hagoUnaSolicituGETABasePath(String basePath) {
        response= empleadoAPI.obtenerListaEmpleados(basePath);
    }
    @Then("valido status code {int}")
    public void valido_status_code(int statusCode) {
        ValidacionUtils.validarStatusCode(response, statusCode);
    }

    //Listar Empleado por Id

    @When("hago un pedido get a basePath {string} con id {int}")
    public void hagoUnPedidoGetABasePathConId(String basePath, int id) {
        response= empleadoAPI.obtenerEmpleadoPorId(basePath,id);
    }

    @Then("valido el response  data.id sea igual a {int}")
    public void validoElResponseDataIdSeaIgualA(int id) {
        json=response.then().statusCode(HttpStatus.SC_OK).body("data.id",equalTo(id));
        Util.pausar();
    }


}
