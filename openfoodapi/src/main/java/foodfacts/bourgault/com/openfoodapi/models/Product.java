package foodfacts.bourgault.com.openfoodapi.models;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;

/**
 * Products api model.
 */
@Entity
public class Product implements Parcelable {

    @SuppressWarnings("unused")
    public static final String
            STATUS_NOT_FOUND = "0",
            STATUS_FOUND = "1";

    //Product big picture
    @ColumnInfo(name = "image_front_url")
    private String image_front_url;

    //Ingredients (raw)
    @ColumnInfo(name = "ingredients_text_fr")
    private String ingredients_text_fr;

    //Allergen tags (raw)
    @ColumnInfo(name = "allergens")
    private String allergens;

    //Product picture for search history
    //Ie. https://static.openfoodfacts.org/images/products/332/977/005/7258/front_fr.25.200.jpg
    @ColumnInfo(name = "image_front_small_url")
    private String image_front_small_url;

    //Website
    @ColumnInfo(name = "link")
    private String link;

    //Ie. Brand,product
    @ColumnInfo(name = "brands")
    private String brands;

    //Ie. France
    @ColumnInfo(name = "manufacturing_places")
    private String manufacturing_places;

    //Ie. https://static.openfoodfacts.org/images/products/332/977/005/7258/ingredients_fr.15.400.jpg
    @ColumnInfo(name = "image_ingredients_url")
    private String image_ingredients_url;

    //Short description
    @ColumnInfo(name = "generic_name_fr")
    private String generic_name_fr;

    //Ie. Petits Filous Tub's Goût Fraise, Pêche, Framboise
    @ColumnInfo(name = "product_name_fr")
    private String product_name_fr;

    @PrimaryKey
    @NonNull
    private String code;

    //Ie. //https://static.openfoodfacts.org/images/products/332/977/005/7258/front_fr.25.400.jpg
    @ColumnInfo(name = "image_url")
    private String image_url;

    @ColumnInfo(name = "image_nutrition_small_url")
    private String image_nutrition_small_url;

    @ColumnInfo(name = "image_ingredients_small_url")
    private String image_ingredients_small_url;

    @ColumnInfo(name = "ingredients_text_with_allergens_fr")
    private String ingredients_text_with_allergens_fr;

    @Ignore
    private Nutriments nutriments;

    //Ie. https://static.openfoodfacts.org/images/products/332/977/005/7258/nutrition_fr.22.400.jpg
    @ColumnInfo(name = "image_nutrition_url")
    private String image_nutrition_url;

    @ColumnInfo(name = "image_small_url")
    private String image_small_url;

    //Ie. Yaourts à boire
    @ColumnInfo(name = "categories")
    private String categories;

    @ColumnInfo(name = "nutrition_grade_fr")
    private String nutrition_grade_fr;

    public Product() {
        code = "-1";
    }

    protected Product(Parcel in) {
        image_front_url = in.readString();
        ingredients_text_fr = in.readString();
        allergens = in.readString();
        image_front_small_url = in.readString();
        link = in.readString();
        brands = in.readString();
        manufacturing_places = in.readString();
        image_url = in.readString();
        image_ingredients_url = in.readString();
        code = in.readString();
        generic_name_fr = in.readString();
        image_nutrition_small_url = in.readString();
        image_ingredients_small_url = in.readString();
        ingredients_text_with_allergens_fr = in.readString();
        image_nutrition_url = in.readString();
        image_small_url = in.readString();
        product_name_fr = in.readString();
        categories = in.readString();
        nutrition_grade_fr = in.readString();
        nutriments = in.readParcelable(Nutriments.class.getClassLoader());
    }

    public static final Creator<Product> CREATOR = new Creator<Product>() {
        @Override
        public Product createFromParcel(Parcel in) {
            return new Product(in);
        }

        @Override
        public Product[] newArray(int size) {
            return new Product[size];
        }
    };

    public String getImage_front_url() {
        return image_front_url;
    }

    public void setImage_front_url(String image_front_url) {
        this.image_front_url = image_front_url;
    }

    public String getIngredients_text_fr() {
        return ingredients_text_fr;
    }

    public void setIngredients_text_fr(String ingredients_text_fr) {
        this.ingredients_text_fr = ingredients_text_fr;
    }

    public String getAllergens() {
        return allergens;
    }

    public void setAllergens(String allergens) {
        this.allergens = allergens;
    }

    public String getImage_front_small_url() {
        return image_front_small_url;
    }

    public void setImage_front_small_url(String image_front_small_url) {
        this.image_front_small_url = image_front_small_url;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getBrands() {
        return brands;
    }

    public void setBrands(String brands) {
        this.brands = brands;
    }

    public String getManufacturing_places() {
        return manufacturing_places;
    }

    public void setManufacturing_places(String manufacturing_places) {
        this.manufacturing_places = manufacturing_places;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public String getImage_ingredients_url() {
        return image_ingredients_url;
    }

    public void setImage_ingredients_url(String image_ingredients_url) {
        this.image_ingredients_url = image_ingredients_url;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getGeneric_name_fr() {
        return generic_name_fr;
    }

    public void setGeneric_name_fr(String generic_name_fr) {
        this.generic_name_fr = generic_name_fr;
    }

    public String getImage_nutrition_small_url() {
        return image_nutrition_small_url;
    }

    public void setImage_nutrition_small_url(String image_nutrition_small_url) {
        this.image_nutrition_small_url = image_nutrition_small_url;
    }

    public String getImage_ingredients_small_url() {
        return image_ingredients_small_url;
    }

    public void setImage_ingredients_small_url(String image_ingredients_small_url) {
        this.image_ingredients_small_url = image_ingredients_small_url;
    }

    public String getIngredients_text_with_allergens_fr() {
        return ingredients_text_with_allergens_fr;
    }

    public void setIngredients_text_with_allergens_fr(String ingredients_text_with_allergens_fr) {
        this.ingredients_text_with_allergens_fr = ingredients_text_with_allergens_fr;
    }

    public Nutriments getNutriments() {
        return nutriments;
    }

    public void setNutriments(Nutriments nutriments) {
        this.nutriments = nutriments;
    }

    public String getImage_nutrition_url() {
        return image_nutrition_url;
    }

    public void setImage_nutrition_url(String image_nutrition_url) {
        this.image_nutrition_url = image_nutrition_url;
    }

    public String getImage_small_url() {
        return image_small_url;
    }

    public void setImage_small_url(String image_small_url) {
        this.image_small_url = image_small_url;
    }

    public String getProduct_name_fr() {
        return product_name_fr;
    }

    public void setProduct_name_fr(String product_name_fr) {
        this.product_name_fr = product_name_fr;
    }

    public String getCategories() {
        return categories;
    }

    public void setCategories(String categories) {
        this.categories = categories;
    }

    public String getNutrition_grade_fr() {
        return nutrition_grade_fr;
    }

    public void setNutrition_grade_fr(String nutrition_grade_fr) {
        this.nutrition_grade_fr = nutrition_grade_fr;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(image_front_url);
        parcel.writeString(ingredients_text_fr);
        parcel.writeString(allergens);
        parcel.writeString(image_front_small_url);
        parcel.writeString(link);
        parcel.writeString(brands);
        parcel.writeString(manufacturing_places);
        parcel.writeString(image_url);
        parcel.writeString(image_ingredients_url);
        parcel.writeString(code);
        parcel.writeString(generic_name_fr);
        parcel.writeString(image_nutrition_small_url);
        parcel.writeString(image_ingredients_small_url);
        parcel.writeString(ingredients_text_with_allergens_fr);
        parcel.writeString(image_nutrition_url);
        parcel.writeString(image_small_url);
        parcel.writeString(product_name_fr);
        parcel.writeString(categories);
        parcel.writeString(nutrition_grade_fr);
        parcel.writeParcelable(nutriments, 0);
    }
}
