package Utilitarios;

import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import model.Empleado;
import org.hamcrest.Matchers;
import org.junit.Assert;

import static org.hamcrest.CoreMatchers.equalTo;

public class ValidacionUtils {
    static Util util = new Util();

    public static void validarStatusCode(Response response, int statusCode) {
        ValidatableResponse json = response.then().statusCode(statusCode);

        int status = response.statusCode();
        Assert.assertEquals(status, statusCode);

        String statusline = response.getStatusLine();
        System.out.println("status code: " + statusline);
    }

    public static void validarResponseEmpleado(Response response,Empleado empleado, String status, String mensaje,  Integer id) {
        response.then().statusCode(200)
                .body("status", equalTo(status))
                .body("data.name", equalTo(empleado.getName()))
                .body("data.salary", equalTo(empleado.getSalary()))
                .body("data.age", equalTo(empleado.getAge()))
                .body("message", equalTo(mensaje));
        if (id != null) {
            response.then().body("data.id", equalTo(id));
        }

        util.convertirjson(response);
    }

    public static void validarResponseStatusYmensaje(Response response,String status, String mensaje,String id){
        response.then()
                .body("status", Matchers.equalTo(status))

                .body("message", Matchers.equalTo(mensaje));
        if (id != null) {
            response.then().body("data", Matchers.equalTo(id));

        }

        util.convertirjson(response);
    }

    public static void validarCampoSeaIgualAlValor_String (Response response,String campo,String valor){
        response.then().body(campo, equalTo(valor));
    }

    public static void validarCampoSeaIgualAlValor_Int (Response response,String campo,int valor){
        response.then().body(campo, equalTo(valor));
    }


}
