package APITests.week6;

import APITests.utilities.SpartanTestBase;
import io.restassured.response.*;
import org.junit.jupiter.api.*;

import java.util.*;

import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

public class JSONtoJAVATest extends SpartanTestBase {
    @DisplayName("GET one Spartan and deserialize to Map")
    @Test
    public void oneSpartanToMap(){

        Response response = given().pathParam("id", 15)
                            .when().get("/api/spartans/{id}")
                            .then().statusCode(200).extract().response();

        //get the json and convert it to the map
        Map<String,Object> jsonMap = response.as(Map.class);//This can not convert the data by itself, requires dependency. Select and add one of the below-mentioned dependencies
        //Get the body and map it to a Java object. For JSON responses this requires that you have either
        //Jackson, or
        //Gson==> I used this.

        System.out.println(jsonMap.toString());

        //after we got the map, we can use hamcrest or junit assertions to do assertion
        String actualName = (String) jsonMap.get("name");
        assertThat(actualName,is("Meta"));
    }

    @DisplayName("GET all spartans to JAVA data structure")
    @Test
    public void getAllSpartan(){

        Response response = get("/api/spartans").then().statusCode(200).extract().response();

        //we need to convert json to java  which is deserialized
        List<Map<String,Object>> jsonList = response.as(List.class);

        System.out.println("jsonList.get(1).get(\"name\") = " + jsonList.get(2).get("name"));

        Map<String,Object> spartan3 = jsonList.get(2);
        System.out.println(spartan3);
    }
}