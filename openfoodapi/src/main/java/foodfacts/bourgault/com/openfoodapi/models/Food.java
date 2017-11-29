package foodfacts.bourgault.com.openfoodapi.models;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Food root model
 */
public class Food implements Parcelable {

    public static final String
            STATUS_NOT_FOUND = "0",
            STATUS_OK = "1";

    private Product product;

    private String status_verbose;

    private String status;

    private String code;

    public Food() {
    }

    protected Food(Parcel in) {
        product = in.readParcelable(Product.class.getClassLoader());
        status_verbose = in.readString();
        status = in.readString();
        code = in.readString();
    }

    public static final Creator<Food> CREATOR = new Creator<Food>() {
        @Override
        public Food createFromParcel(Parcel in) {
            return new Food(in);
        }

        @Override
        public Food[] newArray(int size) {
            return new Food[size];
        }
    };

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public String getStatus_verbose() {
        return status_verbose;
    }

    public void setStatus_verbose(String status_verbose) {
        this.status_verbose = status_verbose;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeParcelable(product, 0);
        parcel.writeString(status_verbose);
        parcel.writeString(status);
        parcel.writeString(code);
    }
}