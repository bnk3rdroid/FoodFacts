package foodfacts.bourgault.com.foodfacts.screens.search.core;

import android.support.annotation.NonNull;

import foodfacts.bourgault.com.foodfacts.app.FoodFactsApp;
import foodfacts.bourgault.com.foodfacts.base.BasePresenter;
import foodfacts.bourgault.com.openfoodapi.models.Food;
import foodfacts.bourgault.com.openfoodapi.models.Product;
import io.reactivex.MaybeObserver;
import io.reactivex.disposables.Disposable;

public class FoodsPresenter extends BasePresenter<FoodsActivity, FoodsModel> {

    private String mBarcodeNumber = "";

    public FoodsPresenter(FoodsActivity view, FoodsModel model) {
        super(view, model);
    }

    @Override
    protected void onCreate() {
      mDisposables.add(getHistory());
    }

    private Disposable getHistory() {
       return FoodFactsApp.getDatabase().productDao().loadProducts()
                .subscribeOn(getSchedulers().compute())
                .observeOn(getSchedulers().androidThread())
                .subscribe(products -> {
                    if (products != null && !products.isEmpty()) {
                        mView.showHistory(products);
                    } else {
                        mView.showNoHistory();
                    }
                });
    }

    private void showNetworkError() {
        mView.showLoader(false);
        mView.showContent(false, null);
        mView.showNetworkError(true);
    }

    private void sendProductNotFound() {
        Food food = new Food();
        food.setStatus(Food.STATUS_NOT_FOUND);
        showProduct(food);
    }

    private void showProduct(@NonNull Food product) {
        mView.showLoader(false);
        mView.showNetworkError(false);
        mView.showContent(true, product);
    }

    private Disposable getProductFromNetwork() {
        return mModel.fetchFood(mBarcodeNumber)
                .subscribeOn(getSchedulers().internet())
                .observeOn(getSchedulers().androidThread())
                .onErrorReturnItem(new Food())
                .subscribe(food -> {
                    if (food != null && food.getStatus() != null && food.getProduct() != null &&
                            food.getProduct().getProduct_name_fr() != null) {
                        showProduct(food);
                    } else {
                        sendProductNotFound();
                    }
                });
    }

    private void getProductFromDatabase() {
        FoodFactsApp.getDatabase().productDao().loadProduct(mBarcodeNumber)
                .subscribeOn(getSchedulers().compute())
                .observeOn(getSchedulers().androidThread())
                .subscribe(new MaybeObserver<Product>() {

                    private boolean success = false;

                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onSuccess(Product product) {
                        Food food = new Food();
                        food.setCode(mBarcodeNumber);
                        food.setStatus(Food.STATUS_OK);
                        food.setProduct(product);
                        showProduct(food);
                        success = true;
                    }

                    @Override
                    public void onError(Throwable e) {
                        sendProductNotFound();
                    }

                    @Override
                    public void onComplete() {
                        if (!success) {
                            sendProductNotFound();
                        }
                    }
                });
    }

    private Disposable getProduct() {
        return mModel.isNetworkAvailable(mView)
                .subscribeOn(getSchedulers().internet())
                .observeOn(getSchedulers().androidThread())
                .subscribe(networkAvailable -> {
                    if (networkAvailable) {
                        mDisposables.add(getProductFromNetwork());
                    } else {
                        showNetworkError();
                        getProductFromDatabase();
                    }
                });
    }

    void findProduct(String query) {
        mView.showLoader(true);
        mBarcodeNumber = query;
        mDisposables.add(getProduct());
    }

}
