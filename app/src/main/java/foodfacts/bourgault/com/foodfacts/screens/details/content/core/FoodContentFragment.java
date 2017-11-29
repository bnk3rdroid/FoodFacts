package foodfacts.bourgault.com.foodfacts.screens.details.content.core;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import foodfacts.bourgault.com.foodfacts.R;
import foodfacts.bourgault.com.foodfacts.app.FoodFactsApp;
import foodfacts.bourgault.com.foodfacts.base.BaseFragmentView;
import foodfacts.bourgault.com.foodfacts.screens.details.content.adapters.TagsAdapter;
import foodfacts.bourgault.com.foodfacts.screens.details.content.di.DaggerFoodContentComponent;
import foodfacts.bourgault.com.foodfacts.screens.details.content.di.FoodContentModule;
import foodfacts.bourgault.com.openfoodapi.models.Food;
import foodfacts.bourgault.com.openfoodapi.models.Product;

public class FoodContentFragment extends BaseFragmentView<FoodContentPresenter> {

    private static final String ARG_FOOD = "argument_food";

    @Inject
    FoodContentPresenter mPresenter;

    @Inject
    TagsAdapter mAllergensAdapter;

    @BindView(R.id.nested_scroll_view)
    NestedScrollView mNestedScrollView;

    @BindView(R.id.image)
    ImageView mImageFront;

    @BindView(R.id.product_name)
    TextView mProductName;

    @BindView(R.id.ingredients)
    TextView mIngredients;

    @BindView(R.id.ingredients_small_image)
    ImageView mIngredientsSmallImage;

    @BindView(R.id.ingredients_image)
    ImageView mIngredientsImage;

    @BindView(R.id.generic_name)
    TextView mGenericName;

    @BindView(R.id.tags_allergenes)
    RecyclerView mAllergensRv;

    @BindView(R.id.manufacturer)
    TextView mManufacturer;

    @BindView(R.id.grade)
    TextView mGrade;

    @OnClick(R.id.ingredients_small_image)
    public void onClickIngredientSmallImage() {
        Glide.with(this).load(mFood.getProduct().getImage_ingredients_url()).into(mIngredientsImage);
        mIngredientsImage.setVisibility(View.VISIBLE);
        mIngredientsImage.setClickable(true);
        mNestedScrollView.setOnScrollChangeListener(mOnScrollChangeListener);
    }

    @OnClick(R.id.ingredients_image)
    public void onClickIngredientsImage() {
        mIngredientsImage.setVisibility(View.GONE);
        mIngredientsImage.setClickable(false);
    }

    @OnClick(R.id.link)
    public void onClickVisitWebsite() {
        String url = mFood.getProduct().getLink();
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse(url));
        startActivity(i);
    }

    private Unbinder mBinder;

    private Food mFood;

    private NestedScrollView.OnScrollChangeListener mOnScrollChangeListener = (v, scrollX, scrollY, oldScrollX, oldScrollY) -> {
        if (mIngredientsImage.getVisibility() == View.VISIBLE && scrollY != 0) {
            onClickIngredientsImage();
        }
    };

    public static FoodContentFragment newInstance(Food food) {
        FoodContentFragment fragment = new FoodContentFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable(ARG_FOOD, food);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        if (bundle != null) {
            if (bundle.containsKey(ARG_FOOD)) {
                mFood = bundle.getParcelable(ARG_FOOD);
                if (mFood == null) {
                    throw new IllegalStateException("Fool element is null!");
                }
            }
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
        DaggerFoodContentComponent.builder()
                .rxComponent(FoodFactsApp.getRxComponent())
                .foodContentModule(new FoodContentModule(this))
                .build().inject(this);
    }

    @Override
    public int getLayout() {
        return R.layout.fragment_food_content;
    }

    @Override
    public FoodContentPresenter getPresenter() {
        return mPresenter;
    }

    @Override
    protected void bindButterKnife(View view) {
        mBinder = ButterKnife.bind(this, view);
    }

    private void bindStatus(@NonNull String foodStatus) {
        switch (foodStatus) {
            case Food.STATUS_NOT_FOUND:
                Snackbar.make(mView, R.string.food_not_found, Snackbar.LENGTH_SHORT).show();
                break;
        }
    }

    private void bindFrontImage(String imageUrl) {
        Glide.with(this).load(imageUrl).into(mImageFront);
    }

    private void bindProductName(String productName) {
        mProductName.setText(productName);
    }

    private void bindIngredients(String ingredients) {
        mIngredients.setText(mPresenter.transformIngredients(ingredients));
    }

    private void bindIngredientsSmallImage(String imageUrl) {
        Glide.with(this).load(imageUrl).into(mIngredientsSmallImage);
    }

    private void bindGenericName(String genericName) {
        mGenericName.setText(genericName);
    }

    private void bindAllergens(String allergens) {
        List<String> allergensList = mPresenter.transformAllergens(allergens);
        mAllergensAdapter.update(allergensList);
        mAllergensRv.setAdapter(mAllergensAdapter);
    }

    private void bindManufacturer(String manufacturer) {
        mManufacturer.setText(manufacturer);
    }

    private void bindGrade(String grade) {
        //noinspection ConstantConditions
        mGrade.setTextColor(ContextCompat.getColor(getActivity(), mPresenter.getGradeColor(grade)));
        mGrade.setText(grade.toUpperCase());
    }

    void bindFood() {
        bindStatus(mFood.getStatus());
        Product product = mFood.getProduct();
        bindFrontImage(product.getImage_front_url());
        bindProductName(product.getProduct_name_fr());
        bindGenericName(product.getGeneric_name_fr());
        bindIngredients(product.getIngredients_text_fr());
        bindIngredientsSmallImage(product.getImage_ingredients_small_url());
        bindAllergens(product.getAllergens());
        bindManufacturer(product.getManufacturing_places());
        bindGrade(product.getNutrition_grade_fr());
    }

}
