package an.dpr.rentalcycling.bike.model;

import org.bson.types.ObjectId;

import io.quarkus.mongodb.panache.reactive.ReactivePanacheMongoEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Bike extends ReactivePanacheMongoEntity{

    public String name;

    public String type; //TODO build enum ROAD, BTT, GRAVEL, KIDS

    public boolean ebike;

    public String frame; //TODO enum: CARBON, ALLUMINIUM, STEAL

    public String description; 

    public ObjectId renterId;

    // rest of data like renter, etc... is in other services

}