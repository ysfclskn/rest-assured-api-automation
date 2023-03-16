import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

public class EmployeesTests extends BasePage {


    @Test
    public void createEmployeeTest()
    {
        Response response = createEmployee();
        response
                .then()
                .statusCode(201);
    }

    @Test
    public void getEmployeesTest() {
        Response response = getEmployees();
        response.then()
                .statusCode(200);

    }


    @Test
    public void getEmployeesByIdTest(){
        Response response =
                given(spec)
                        .when()
                        .get("/employees/" + getEmployeesId());
        response.then();
    }


    @Test
    public void getEmployeeFilterFirstName(){
        spec.queryParam("first_name","Hazle");

        Response response =
                given(spec)
                        .when()
                        .get("/employees");

        response
                .then()
                .statusCode(200);
    }


}
