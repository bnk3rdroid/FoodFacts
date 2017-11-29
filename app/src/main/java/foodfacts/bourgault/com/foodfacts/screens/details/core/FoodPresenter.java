package foodfacts.bourgault.com.foodfacts.screens.details.core;

import foodfacts.bourgault.com.foodfacts.base.BasePresenter;
import foodfacts.bourgault.com.openfoodapi.models.Food;
import foodfacts.bourgault.com.openfoodapi.models.Product;
import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;

public class FoodPresenter extends BasePresenter<FoodActivity, FoodModel> {

    public FoodPresenter(FoodActivity view, FoodModel model) {
        super(view, model);
    }

    @Override
    protected void onCreate() {
        mView.showFood();
    }

    private Disposable getFood(String barcodeNumber) {
        return mModel.isNetworkAvailable(mView)
                .doOnNext(isNetworkAvailable -> {
                    if (!isNetworkAvailable) {
                        mView.showLoader(false);
                        mView.showFood(false, null);
                        mView.showNetworkError(true);
                    }
                })
                .flatMap(isNetworkAvailable -> {
                    if (isNetworkAvailable) {
                        return mModel.fetchFood(barcodeNumber);
                    } else {
                        return Observable.just(new Food());
                    }
                })
                .subscribe(food -> {
                    mView.showLoader(false);
                    if (food != null && food.getStatus().equals(Product.STATUS_FOUND)) {
                        mView.showFood(true, food);
                    } else {
                        mView.showFood(false, null);
                    }
                });
    }

    void refreshFood(String barcodeNumber) {
        mView.showLoader(true);
        mDisposables.add(getFood(barcodeNumber));
    }

}
