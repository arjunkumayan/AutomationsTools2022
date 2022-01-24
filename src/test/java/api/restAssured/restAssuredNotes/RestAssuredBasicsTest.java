package api.restAssured.restAssuredNotes;

import io.restassured.RestAssured;
import io.restassured.config.JsonConfig;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.path.json.config.JsonPathConfig;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.checkerframework.checker.units.qual.A;
import org.testng.Assert;
import org.testng.annotations.Test;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;
import static io.restassured.module.jsv.JsonSchemaValidator.*;



public class RestAssuredBasicsTest {

    @Test
    public void test1(){
        RequestSpecification requestSpecification  = RestAssured.given().log().all();
        requestSpecification.baseUri("https://restful-booker.herokuapp.com/");
        requestSpecification.basePath("booking/2");
        requestSpecification.contentType(ContentType.JSON);
        Response response = requestSpecification.get();
       Assert.assertEquals(response.getStatusCode(), 200);
      ValidatableResponse res = response.then();

    }

    @Test
    public void createBookingRequestNonBDD(){
       RequestSpecification request =  RestAssured.given().log().ifValidationFails();
        request.baseUri("https://restful-booker.herokuapp.com/");
        request.basePath("booking");
        request.contentType(ContentType.JSON);
        request.accept(ContentType.JSON);
        String payload = "{\n" +
                "    \"firstname\" : \"Arjun\",\n" +
                "    \"lastname\" : \"singh\",\n" +
                "    \"totalprice\" : 111,\n" +
                "    \"depositpaid\" : true,\n" +
                "    \"bookingdates\" : {\n" +
                "        \"checkin\" : \"2022-02-01\",\n" +
                "        \"checkout\" : \"2022-03-01\"\n" +
                "    },\n" +
                "    \"additionalneeds\" : \"Breakfast\"\n" +
                "}";
        request.body(payload);

        Response response = request.post();
        response.getStatusCode();
        Assert.assertEquals(response.getStatusCode(), 200);

        response.then().assertThat().statusCode(200);
        String responseValueAsString = response.then().extract().asString();
        System.out.println(responseValueAsString);

        response.then().body("firstname", equalTo("Arjun"));
        response.then().body("bookingdates.checkin", hasItem("2018-01-01"));

        response.then().assertThat().body(matchesJsonSchemaInClasspath(""));
        // Using Json Path
        JsonPath jsonPath = response.jsonPath();
        Assert.assertEquals(jsonPath.get("firstname"), "Arjun");
        Assert.assertEquals(jsonPath.get("bookingDates.checkin"),"2018-01-01");

    }

    @Test
    public void configureRest(){
        RestAssured.given().config(RestAssured.config().jsonConfig(JsonConfig.jsonConfig().numberReturnType(JsonPathConfig.NumberReturnType.BIG_DECIMAL)));
    }
}
