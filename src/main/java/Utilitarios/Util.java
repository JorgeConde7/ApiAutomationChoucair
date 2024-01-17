package Utilitarios;

import io.cucumber.messages.internal.com.google.gson.Gson;
import io.cucumber.messages.internal.com.google.gson.GsonBuilder;
import io.restassured.response.Response;

public class Util {

    public void convertirjson(Response response){
        try {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            Object json = gson.fromJson(response.getBody().asString(), Object.class);
            String formattedJson = gson.toJson(json);
            System.out.println("Response : \n" + formattedJson);
        } catch (Exception e) {
            // Manejar excepciones
            e.printStackTrace();
        }
    }

    public static void pausar() {
        try {
            Thread.sleep(2000);
        }catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static String convertirAString(int numero) {
        return String.valueOf(numero);

    }

}
