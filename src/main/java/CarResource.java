import entity.Car;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("beers")
public class CarResource {


    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Car getCars() {
        return new Car();
    }


}
