package foodfacts.bourgault.com.foodfacts.screens.search.content.core;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import foodfacts.bourgault.com.foodfacts.R;
import foodfacts.bourgault.com.foodfacts.app.FoodFactsApp;
import foodfacts.bourgault.com.foodfacts.base.BaseFragmentView;
import foodfacts.bourgault.com.foodfacts.screens.details.core.FoodActivity;
import foodfacts.bourgault.com.foodfacts.screens.search.content.di.DaggerFoodsContentComponent;
import foodfacts.bourgault.com.foodfacts.screens.search.content.di.FoodsContentModule;
import foodfacts.bourgault.com.openfoodapi.models.Food;
import foodfacts.bourgault.com.openfoodapi.models.Product;

import static foodfacts.bourgault.com.foodfacts.screens.details.core.FoodActivity.EXTRA_FOOD_OBJECT;

public class FoodsContentFragment extends BaseFragmentView<FoodsContentPresenter> {

    public static final String ARG_FOOD = "argument food";

    @Inject
    FoodsContentPresenter mPresenter;

    @BindView(R.id.card_food)
    CardView mCardFood;

    @BindView(R.id.card_food_image)
    ImageView mCardImage;

    @BindView(R.id.card_food_name)
    TextView mCardName;

    @BindView(R.id.food_not_found)
    ConstraintLayout mFoodNotFound;

    @OnClick(R.id.card_food_see_details)
    public void onClickSeeFoodDetails() {
        Intent intent = new Intent(getActivity(), FoodActivity.class);
        intent.putExtra(EXTRA_FOOD_OBJECT, mFood);
        startActivity(intent);
    }

    private Unbinder mBinder;

    private Food mFood;

    public static FoodsContentFragment newInstance(Food food) {
        FoodsContentFragment fragment = new FoodsContentFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable(FoodsContentFragment.ARG_FOOD, food);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        if (bundle != null && bundle.containsKey(ARG_FOOD)) {
            mFood = bundle.getParcelable(ARG_FOOD);
        } else {
            throw new IllegalStateException("Food object is null !");
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mBinder != null) {
            mBinder.unbind();
        }
    }

    @Override
    public void initInjection() {
        DaggerFoodsContentComponent.builder()
                .apiComponent(FoodFactsApp.getApiComponent())
                .foodsContentModule(new FoodsContentModule(this))
                .build().inject(this);
    }

    @Override
    public int getLayout() {
        return R.layout.fragment_foods_content;
    }

    @Override
    public FoodsContentPresenter getPresenter() {
        return mPresenter;
    }

    @Override
    protected void bindButterKnife(View view) {
        mBinder = ButterKnife.bind(this, view);
    }

    public void showFood() {
        if (mFood.getStatus().equals(Product.STATUS_FOUND) && mFood.getProduct() != null &&
                mFood.getProduct().getProduct_name_fr() != null) {
            Glide.with(this).load(mFood.getProduct().getImage_front_url()).into(mCardImage);
            mCardName.setText(mFood.getProduct().getProduct_name_fr());
            mCardFood.setVisibility(View.VISIBLE);
            mFoodNotFound.setVisibility(View.GONE);
            //Save product in DB
            mPresenter.saveProduct(mFood);
        } else {
            mFoodNotFound.setVisibility(View.VISIBLE);
            mCardFood.setVisibility(View.GONE);
        }
    }

}
