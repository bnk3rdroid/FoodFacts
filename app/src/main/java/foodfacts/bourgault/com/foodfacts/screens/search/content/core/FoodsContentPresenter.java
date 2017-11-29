package foodfacts.bourgault.com.foodfacts.screens.search.content.core;

import foodfacts.bourgault.com.foodfacts.app.FoodFactsApp;
import foodfacts.bourgault.com.foodfacts.base.BasePresenter;
import foodfacts.bourgault.com.foodfacts.db.AppDatabase;
import foodfacts.bourgault.com.openfoodapi.models.Food;
import foodfacts.bourgault.com.openfoodapi.models.Product;
import io.reactivex.MaybeObserver;
import io.reactivex.disposables.Disposable;

public class FoodsContentPresenter extends BasePresenter<FoodsContentFragment, FoodsContentModel> {

    public FoodsContentPresenter(FoodsContentFragment view, FoodsContentModel model) {
        super(view, model);
    }

    @Override
    protected void onCreate() {
        mView.showFood();
    }

    private void saveProductInDatabase(AppDatabase db, Product product) {
        new Thread() {
            @Override
            public void run() {
                db.productDao().insertProduct(product);
            }
        }.start();
    }

    void saveProduct(Food food) {
        AppDatabase db = FoodFactsApp.getDatabase();
        db.productDao().loadProduct(food.getCode())
                .subscribeOn(getSchedulers().compute())
                .observeOn(getSchedulers().androidThread())
                .subscribe(new MaybeObserver<Product>() {

                    private boolean success = false;

                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onSuccess(Product product) {
                        success = true;
                    }

                    @Override
                    public void onError(Throwable e) {
                    }

                    @Override
                    public void onComplete() {
                        if (!success) {
                            saveProductInDatabase(db, food.getProduct());
                        }
                    }
                });
    }
}
