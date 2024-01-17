package api.Empleado;
import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import model.Empleado;

import java.util.function.Supplier;

import static io.restassured.RestAssured.given;

public class EmpleadoAPI {

    private RequestSpecification request;
    int intentosMaximos = 8;

    public EmpleadoAPI(String baseURI) {
        this.request = given().baseUri(baseURI).contentType(ContentType.JSON);
        RestAssured.filters(new RequestLoggingFilter(), new ResponseLoggingFilter());
    }

    public Response obtenerListaEmpleados(String basePath) {
        return volverEjecutar(basePath, () -> request.when().get(basePath));
    }

    public Response obtenerEmpleadoPorId(String basePath, int id) {
        return volverEjecutar(basePath, () -> request.when().get(basePath + "/" + id));
    }

    public Response crearEmpleado(String basePath, Empleado empleado) {
        return volverEjecutar(basePath, () -> request.body(empleado).when().post(basePath));
    }

    public Response actualizarEmpleado(String basePath, int id, Empleado empleado) {
        return volverEjecutar(basePath, () -> request.body(empleado).when().put(basePath + "/" + id));
    }

    public Response eliminarEmpleado(String basePath, String id) {
        return volverEjecutar(basePath, () -> request.when().delete(basePath + "/" + id));
    }

    private Response volverEjecutar(String basePath, Supplier<Response> operation) {
        int intentoActual = 0;
        Response response;

        do {
            intentoActual++;
            response = operation.get();

            if (response.getStatusCode() == 429) {
                System.out.println("Se recibio un codigo de estado 429. Reintentando...");

                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } else {
                break;
            }
        } while (intentoActual < intentosMaximos);

        return response;
    }

}
