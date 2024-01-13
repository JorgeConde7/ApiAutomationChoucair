package api.Empleado;
import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import model.Empleado;

import static io.restassured.RestAssured.given;

public class EmpleadoAPI {

    private RequestSpecification request;
    public EmpleadoAPI(String baseURI) {
        this.request = given().baseUri(baseURI).contentType(ContentType.JSON);
        RestAssured.filters(new RequestLoggingFilter(), new ResponseLoggingFilter());
    }

    public Response obtenerListaEmpleados(String basePath) {
        return request.when().get(basePath);
    }

    public Response obtenerEmpleadoPorId(String basePath,int id) {

        return request.when().get(basePath+"/"+id);
    }

    public Response crearEmpleado(String basePath,Empleado empleado) {
        return request.body(empleado).when().post(basePath);
    }

    public Response actualizarEmpleado(String basePath,int id, Empleado empleado) {
        return request.body(empleado).when().put(basePath+"/"+id);
    }

    public Response eliminarEmpleado(String basePath, String id) {
        return request.when().delete(basePath+"/" + id);
    }

}
