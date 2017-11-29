package foodfacts.bourgault.com.openfoodapi.api;

import foodfacts.bourgault.com.openfoodapi.models.Food;
import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Endpoints to the open food api.
 */
public interface OpenFoodApi {

    @GET("api/v0/product/{barcodeNumber}.json")
    Observable<Food> getApiFood(@Path("barcodeNumber") String barcodeNumber);

}
