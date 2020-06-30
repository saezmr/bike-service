package an.dpr.rentalcycling.bike;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import an.dpr.rentalcycling.bike.model.Bike;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;

@QuarkusTest
public class BikeResourceTest {

    private static Jsonb jsonb;
    
    @BeforeAll
    public static void beforeAll() {
        jsonb = JsonbBuilder.create();
    }

    @Test
    public void testHelloEndpoint() {
        given()
          .when().get("/bike")
          .then()
             .statusCode(200)
             .body(is("bike-get"));
    }

    @Test
    public void testPostBike() {
        Bike bike = Bike.builder().name("test-name")
        .description("test-desc").ebike(false).frame("CARBON").type("GRAVEL").build();
        
        given()
        .contentType(ContentType.JSON)
            .body(jsonb.toJson(bike))
            .when().post("/bike")
            .then().statusCode(201);
    }
}