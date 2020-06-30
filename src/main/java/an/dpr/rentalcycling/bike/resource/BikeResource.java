package an.dpr.rentalcycling.bike.resource;

import java.net.URI;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.bson.types.ObjectId;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.parameters.Parameter;

import an.dpr.rentalcycling.bike.model.Bike;
import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;

@Path("/bike")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class BikeResource {

    @GET
    public Multi<Bike> getBikes() {
        return Bike.streamAll();
    }

    @GET
    @Path("/{id}")
    public Uni<Bike> getBikeById(
        @Parameter(allowEmptyValue = false, required=true, name="id") 
        @PathParam("id") ObjectId id) {
        return Bike.findById(id);
    }

    @POST
    @Operation(operationId = "newBike", description = "add new bike")
    public Uni<Response> addBike(@Parameter(allowEmptyValue = false, required=true, name="bike") Bike bike) {
        return bike.persist()
            .onItem().apply(voidItem -> URI.create("/bike/"+bike.id))
            .onItem().apply(uri -> Response.created(uri).build());
    }
    
    @PUT
    @Operation(operationId = "newBike", description = "add new bike")
    public Uni<Response> updateBike(@Parameter(allowEmptyValue = false, required=true, name="bike") Bike bike) {
        return bike.update()
            .onItem().apply(voidItem -> URI.create("/bike/"+bike.id))
            .onItem().apply(uri -> Response.created(uri).build());
    }

}