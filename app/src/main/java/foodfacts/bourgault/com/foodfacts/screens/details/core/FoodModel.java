package foodfacts.bourgault.com.foodfacts.screens.details.core;

import android.content.Context;

import foodfacts.bourgault.com.foodfacts.base.BaseModel;
import foodfacts.bourgault.com.foodfacts.utils.NetworkUtils;
import foodfacts.bourgault.com.openfoodapi.api.OpenFoodApi;
import foodfacts.bourgault.com.openfoodapi.models.Food;
import foodfacts.bourgault.com.rx.utils.RxSchedulers;
import io.reactivex.Observable;

public class FoodModel extends BaseModel {

    private OpenFoodApi mApi;

    public FoodModel(OpenFoodApi api, RxSchedulers schedulers) {
        super(schedulers);
        mApi = api;
    }

    Observable<Boolean> isNetworkAvailable(Context context) {
        return NetworkUtils.isNetworkAvailableObservable(context);
    }

    Observable<Food> fetchFood(String barcodeNumber) {
        return mApi.getApiFood(barcodeNumber)
                .subscribeOn(mSchedulers.internet())
                .observeOn(mSchedulers.androidThread());
    }
}
