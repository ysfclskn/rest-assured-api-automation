import com.github.javafaker.Faker;
import io.restassured.response.Response;

import java.util.ArrayList;

import static io.restassured.RestAssured.given;

public class Helper {
    public String[] generateEmployee(){
        Faker data = new Faker();
        String firstname = data.name().firstName();
        String lastname = data.name().lastName();
        String email = data.bothify("???????@?????.com");
        String city = data.address().city();

        String[] aEmplyoee = {firstname,lastname,email,city};
        return aEmplyoee;
    }
}
