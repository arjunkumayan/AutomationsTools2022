package api.restAssured.restAssuredNotes;

import io.restassured.RestAssured;
import io.restassured.authentication.FormAuthConfig;
import io.restassured.http.ContentType;

import java.util.HashMap;
import java.util.Map;

public class PathParameterExample {

    public static void main(String[] args) {

        // Named parameter
       /* String response = RestAssured
                .given()
                .log()
                .all()
                .baseUri("https://restful-booker.herokuapp.com")
                .basePath("{basePath}/{bookingId}")
                .when()
                .get("https://restful-booker.herokuapp.com/{basePath}/{bookingId}","booking",2)
                .then()
                .log().all().assertThat().statusCode(200).extract().response().asString();

        System.out.println(response);*/

        // Unnamed parameter
        RestAssured
                .given()
                .log()
                .all()
                .baseUri("https://restful-booker.herokuapp.com")
                .pathParam("basePath","booking")
                .when()
                .get("https://restful-booker.herokuapp.com/{basePath}/{bookingId}",2,"ddd")
                .then()
                .log().all().assertThat().statusCode(200);


// Path parameter can be only passed for the parameters but not for any of the basePath like below
        RestAssured
                .given()
                .log()
                .all()
                .pathParam("baseAddress","token")
                .when()
                .get("https://{baseAddress}.herokuapp.com/{basePath}/{bookingId}",2,3)
              // This will work since it has all the single slash
                // .get("https:/{baseAddress}.herokuapp.com/{basePath}/{bookingId}",2,3)

                .then()
                .log().all().assertThat().statusCode(200);

        Map<String, Object> pathParameter = new HashMap<>();
        pathParameter.put("basePath","booking");
        pathParameter.put("bookingId",2);
        RestAssured.given().log().all()
                .contentType(ContentType.JSON)
                .baseUri("https://restful-booker.herokuapp.com/")
                .basePath("{basePath}/{bookingId}")
                .pathParams(pathParameter)
                .when()
                .get()
                .then().log().all().statusCode(200);

        RestAssured.given().auth().basic("", "");
        RestAssured.given().auth().oauth2( "");
        RestAssured.given().auth().oauth( "","","","");

        RestAssured.given().auth()
                .form("", "", FormAuthConfig.springSecurity().withCsrfFieldName("_CSRF").sendCsrfTokenAsHeader());


    }
}
