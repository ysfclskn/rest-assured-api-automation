import com.github.javafaker.Faker;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import static io.restassured.RestAssured.*;

public class BasePage {
    RequestSpecification spec;
    Helper helper = new Helper();

    @BeforeEach
    public void setup() {
        spec = new RequestSpecBuilder()
                .setBaseUri("http://localhost:3000")
                .addFilters(Arrays.asList(new RequestLoggingFilter(), new ResponseLoggingFilter()))
                .build();
    }

    public Response getEmployees(){
        Response response =
                given(spec)
                        .when()
                        .get("/employees");
        return response;
    }
    public int getEmployeesId() {
        Random rand = new Random();
        Response response =
                given(spec)
                        .when()
                        .get("/employees");
        response.then()
                .statusCode(200);
        List<Integer> ids = response.jsonPath().getList("id");
        return ids.get(rand.nextInt(50));
    }

    public int getEmployeesCount(){
        ArrayList <JSONObject> json = getEmployees().jsonPath().get();

        return json.size();
    }


    public String employeeObjectBody(){
        Faker data = new Faker();
        JSONObject body = new JSONObject();
        body.put("id",getEmployeesCount()+1);
        body.put("first_name",helper.generateEmployee()[0]);
        body.put("last_name",helper.generateEmployee()[1]);
        body.put("email",helper.generateEmployee()[2]);
        body.put("city",helper.generateEmployee()[3]);

        return body.toString();
    }

    public Response createEmployee(){
        Response response =
                given(spec)
                        .when()
                        .contentType(ContentType.JSON)
                        .body(employeeObjectBody())
                        .post("/employees");
        return response;
    }
}
