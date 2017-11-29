package foodfacts.bourgault.com.foodfacts.screens.search.core;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.RecyclerView;
import android.text.InputType;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.SearchView;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import foodfacts.bourgault.com.foodfacts.R;
import foodfacts.bourgault.com.foodfacts.app.FoodFactsApp;
import foodfacts.bourgault.com.foodfacts.base.BaseView;
import foodfacts.bourgault.com.foodfacts.screens.search.content.core.FoodsContentFragment;
import foodfacts.bourgault.com.foodfacts.screens.search.di.DaggerFoodsComponent;
import foodfacts.bourgault.com.foodfacts.screens.search.di.FoodsModule;
import foodfacts.bourgault.com.foodfacts.screens.search.history.HistoryAdapter;
import foodfacts.bourgault.com.openfoodapi.models.Food;
import foodfacts.bourgault.com.openfoodapi.models.Product;

public class FoodsActivity extends BaseView<FoodsPresenter> {

    private static final int LAYOUT_CONTENT = R.id.content;

    @Inject
    FoodsPresenter mPresenter;

    @Inject
    HistoryAdapter mAdapter;

    @BindView(R.id.network_error)
    View mNetworkErrorLayout;

    @BindView(R.id.nougat_incompatible_container)
    ConstraintLayout mNougatIncompatibleLayout;

    @BindView(R.id.loader)
    ProgressBar mLoader;

    @BindView(R.id.history_rv)
    RecyclerView mRvHistory;

    @BindView(R.id.no_history)
    ConstraintLayout mNoHistory;

    private boolean mNougatVersion = false;

    @OnClick(R.id.refresh)
    public void onClickRefresh() {
        if (mLastQuery != null) {
            mPresenter.findProduct(mLastQuery);
        }
    }

    @OnClick(R.id.incompatible_link)
    public void onClickVisitNougatSslIssue() {
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse("https://issuetracker.google.com/issues/37122132"));
        startActivity(i);
    }

    private String mLastQuery;

    private FoodsContentFragment mFragment;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        handleIntent(getIntent());
        checkNougatVersion();
        if (!mNougatVersion) {
            mRvHistory.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.HORIZONTAL));
            mRvHistory.setAdapter(mAdapter);
        }
    }

    @Override
    protected void onNewIntent(Intent intent) {
        handleIntent(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_search, menu);
        // Associate searchable configuration with the SearchView
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView = (SearchView) menu.findItem(R.id.search).getActionView();
        if (searchManager != null) {
            searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
            searchView.setInputType(InputType.TYPE_CLASS_NUMBER);
        }
        return true;
    }

    @Override
    protected void bindButterKnife() {
        ButterKnife.bind(this);
    }

    @Override
    public void initInjection() {
        DaggerFoodsComponent.builder()
                .apiComponent(FoodFactsApp.getApiComponent())
                .foodsModule(new FoodsModule(this))
                .build()
                .inject(this);
    }

    @Override
    public int getLayout() {
        return R.layout.activity_foods;
    }

    @Override
    public FoodsPresenter getPresenter() {
        return mPresenter;
    }

    private void handleIntent(Intent intent) {
        checkNougatVersion();
        if (!mNougatVersion && Intent.ACTION_SEARCH.equals(intent.getAction())) {
            mLastQuery = intent.getStringExtra(SearchManager.QUERY);
            mPresenter.findProduct(mLastQuery);
        }
    }

    private void checkNougatVersion() {
        if (Build.VERSION.SDK_INT == Build.VERSION_CODES.N) {
            mNougatVersion = true;
            showNougatIncompatible();
        }
    }

    private void showNougatIncompatible() {
        mNougatIncompatibleLayout.setVisibility(View.VISIBLE);
    }

    void showContent(boolean show, Food food) {
        if (show) {
            mFragment = FoodsContentFragment.newInstance(food);
            getSupportFragmentManager().beginTransaction().replace(LAYOUT_CONTENT, mFragment).commit();
        } else if (mFragment != null) {
            getSupportFragmentManager().beginTransaction().remove(mFragment).commit();
        }
    }

    void showLoader(boolean show) {
        mLoader.setVisibility(show ? View.VISIBLE : View.GONE);
    }

    void showNetworkError(boolean show) {
        mNetworkErrorLayout.setVisibility(show ? View.VISIBLE : View.GONE);
    }

    void showHistory(List<Product> products) {
        mAdapter.update(products);
    }

    void showNoHistory() {
        mRvHistory.setVisibility(View.GONE);
        mNoHistory.setVisibility(View.VISIBLE);
    }
}
