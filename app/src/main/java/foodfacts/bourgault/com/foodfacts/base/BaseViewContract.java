package foodfacts.bourgault.com.foodfacts.base;

interface BaseViewContract<P extends BasePresenter> {
    void initInjection();
    int getLayout();
    P getPresenter();
}
