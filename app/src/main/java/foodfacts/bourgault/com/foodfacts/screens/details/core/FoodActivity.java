package foodfacts.bourgault.com.foodfacts.screens.details.core;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ProgressBar;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import foodfacts.bourgault.com.foodfacts.R;
import foodfacts.bourgault.com.foodfacts.app.FoodFactsApp;
import foodfacts.bourgault.com.foodfacts.base.BaseView;
import foodfacts.bourgault.com.foodfacts.screens.details.content.core.FoodContentFragment;
import foodfacts.bourgault.com.foodfacts.screens.details.di.DaggerFoodComponent;
import foodfacts.bourgault.com.foodfacts.screens.details.di.FoodModule;
import foodfacts.bourgault.com.foodfacts.screens.search.content.core.FoodsContentFragment;
import foodfacts.bourgault.com.openfoodapi.models.Food;
import foodfacts.bourgault.com.openfoodapi.models.Product;

public class FoodActivity extends BaseView<FoodPresenter> {

    public static final String EXTRA_FOOD_OBJECT = "extra_food_object";

    private static final int FRAGMENT_CONTAINER = R.id.food_container;

    @Inject
    FoodPresenter mPresenter;

    @BindView(FRAGMENT_CONTAINER)
    FrameLayout mFoodContainer;

    @BindView(R.id.network_error)
    View mNetworkErrorLayout;

    @BindView(R.id.loader)
    ProgressBar mLoader;

    private Food mFood;

    @OnClick(R.id.network_error)
    public void onClickToRefresh() {
        mPresenter.refreshFood(mFood.getCode());
    }

    private FoodContentFragment mFragment;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        Intent intent = getIntent();
        if (intent == null) {
            throw new IllegalStateException("Intent is null");
        }
        mFood = intent.getParcelableExtra(EXTRA_FOOD_OBJECT);
        if (mFood == null) {
            throw new IllegalStateException("Food object is null");
        }
        super.onCreate(savedInstanceState);
    }

    @Override
    public void initInjection() {
        DaggerFoodComponent.builder()
                .apiComponent(FoodFactsApp.getApiComponent())
                .foodModule(new FoodModule(this))
                .build().inject(this);
    }

    @Override
    public int getLayout() {
        return R.layout.activity_food;
    }

    @Override
    public FoodPresenter getPresenter() {
        return mPresenter;
    }

    @Override
    protected void bindButterKnife() {
        ButterKnife.bind(this);
    }

    private void bindFood(boolean bind, Food food) {
        if (bind) {
            mFragment = FoodContentFragment.newInstance(food);
            getSupportFragmentManager().beginTransaction()
                    .replace(FRAGMENT_CONTAINER, mFragment).commit();
            setTitle(food.getProduct().getBrands().replace(",", " : "));
        } else {
            if (mFragment != null) {
                getSupportFragmentManager().beginTransaction().remove(mFragment).commit();
            }
        }
    }

    void showFood() {
        if (mFood != null) {
            bindFood(true, mFood);
            showNetworkError(false);
            mFoodContainer.setVisibility(View.VISIBLE);
        }
    }

    void showFood(boolean show, Food food) {
        if (show) {
            if (food.getStatus().equals(Product.STATUS_FOUND)) {
                bindFood(true, food);
                showNetworkError(false);
                mFoodContainer.setVisibility(View.VISIBLE);
            }
        } else {
            bindFood(false, null);
            mFoodContainer.setVisibility(View.GONE);
        }
    }

    void showLoader(boolean show) {
        mLoader.setVisibility(show ? View.VISIBLE : View.GONE);
    }

    void showNetworkError(boolean show) {
        mNetworkErrorLayout.setVisibility(show ? View.VISIBLE : View.GONE);
    }
}
