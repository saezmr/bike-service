package an.dpr.rentalcycling.bike.service;

import javax.enterprise.context.RequestScoped;

import an.dpr.rentalcycling.bike.model.Bike;
import io.smallrye.mutiny.Uni;

@RequestScoped
@Deprecated
public class BikeService {

	public Uni<Void> createBike(Bike bike) {
        return bike.persist();
	}

}