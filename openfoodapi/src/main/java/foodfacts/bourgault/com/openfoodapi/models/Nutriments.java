package foodfacts.bourgault.com.openfoodapi.models;

import android.os.Parcel;
import android.os.Parcelable;

public class Nutriments implements Parcelable {
    private String sugars_serving;

    private String proteins_serving;

    private String salt_serving;

    private String fat_value;

    private String fiber;

    private String energy_100g;

    private String carbohydrates_unit;

    private String sugars;

    private String fat_serving;

    private String fat_unit;

    private String sodium_100g;

    private String sugars_unit;

    private String proteins_unit;

    private String salt_unit;

    private String carbohydrates;

    private String carbohydrates_serving;

    private String salt_value;

    private String fiber_value;

    private String sodium_serving;

    private String energy_unit;

    private String fiber_serving;

    private String proteins_100g;

    private String fat;

    private String carbohydrates_value;

    private String energy_value;

    private String carbohydrates_100g;

    private String fiber_modifier;

    private String fat_100g;

    private String sugars_value;

    private String energy_serving;

    private String sodium;

    private String sodium_value;

    private String fiber_unit;

    private String sodium_unit;

    private String salt_100g;

    private String sugars_100g;

    private String fiber_100g;

    private String proteins_value;

    private String proteins;

    private String energy;

    private String salt;

    protected Nutriments(Parcel in) {
        sugars_serving = in.readString();
        proteins_serving = in.readString();
        salt_serving = in.readString();
        fat_value = in.readString();
        fiber = in.readString();
        energy_100g = in.readString();
        carbohydrates_unit = in.readString();
        sugars = in.readString();
        fat_serving = in.readString();
        fat_unit = in.readString();
        sodium_100g = in.readString();
        sugars_unit = in.readString();
        proteins_unit = in.readString();
        salt_unit = in.readString();
        carbohydrates = in.readString();
        carbohydrates_serving = in.readString();
        salt_value = in.readString();
        fiber_value = in.readString();
        sodium_serving = in.readString();
        energy_unit = in.readString();
        fiber_serving = in.readString();
        proteins_100g = in.readString();
        fat = in.readString();
        carbohydrates_value = in.readString();
        energy_value = in.readString();
        carbohydrates_100g = in.readString();
        fiber_modifier = in.readString();
        fat_100g = in.readString();
        sugars_value = in.readString();
        energy_serving = in.readString();
        sodium = in.readString();
        sodium_value = in.readString();
        fiber_unit = in.readString();
        sodium_unit = in.readString();
        salt_100g = in.readString();
        sugars_100g = in.readString();
        fiber_100g = in.readString();
        proteins_value = in.readString();
        proteins = in.readString();
        energy = in.readString();
        salt = in.readString();
    }

    public static final Creator<Nutriments> CREATOR = new Creator<Nutriments>() {
        @Override
        public Nutriments createFromParcel(Parcel in) {
            return new Nutriments(in);
        }

        @Override
        public Nutriments[] newArray(int size) {
            return new Nutriments[size];
        }
    };

    public String getSugars_serving() {
        return sugars_serving;
    }

    public void setSugars_serving(String sugars_serving) {
        this.sugars_serving = sugars_serving;
    }

    public String getProteins_serving() {
        return proteins_serving;
    }

    public void setProteins_serving(String proteins_serving) {
        this.proteins_serving = proteins_serving;
    }

    public String getSalt_serving() {
        return salt_serving;
    }

    public void setSalt_serving(String salt_serving) {
        this.salt_serving = salt_serving;
    }

    public String getFat_value() {
        return fat_value;
    }

    public void setFat_value(String fat_value) {
        this.fat_value = fat_value;
    }

    public String getFiber() {
        return fiber;
    }

    public void setFiber(String fiber) {
        this.fiber = fiber;
    }

    public String getEnergy_100g() {
        return energy_100g;
    }

    public void setEnergy_100g(String energy_100g) {
        this.energy_100g = energy_100g;
    }

    public String getCarbohydrates_unit() {
        return carbohydrates_unit;
    }

    public void setCarbohydrates_unit(String carbohydrates_unit) {
        this.carbohydrates_unit = carbohydrates_unit;
    }

    public String getSugars() {
        return sugars;
    }

    public void setSugars(String sugars) {
        this.sugars = sugars;
    }

    public String getFat_serving() {
        return fat_serving;
    }

    public void setFat_serving(String fat_serving) {
        this.fat_serving = fat_serving;
    }

    public String getFat_unit() {
        return fat_unit;
    }

    public void setFat_unit(String fat_unit) {
        this.fat_unit = fat_unit;
    }

    public String getSodium_100g() {
        return sodium_100g;
    }

    public void setSodium_100g(String sodium_100g) {
        this.sodium_100g = sodium_100g;
    }

    public String getSugars_unit() {
        return sugars_unit;
    }

    public void setSugars_unit(String sugars_unit) {
        this.sugars_unit = sugars_unit;
    }

    public String getProteins_unit() {
        return proteins_unit;
    }

    public void setProteins_unit(String proteins_unit) {
        this.proteins_unit = proteins_unit;
    }

    public String getSalt_unit() {
        return salt_unit;
    }

    public void setSalt_unit(String salt_unit) {
        this.salt_unit = salt_unit;
    }

    public String getCarbohydrates() {
        return carbohydrates;
    }

    public void setCarbohydrates(String carbohydrates) {
        this.carbohydrates = carbohydrates;
    }

    public String getCarbohydrates_serving() {
        return carbohydrates_serving;
    }

    public void setCarbohydrates_serving(String carbohydrates_serving) {
        this.carbohydrates_serving = carbohydrates_serving;
    }

    public String getSalt_value() {
        return salt_value;
    }

    public void setSalt_value(String salt_value) {
        this.salt_value = salt_value;
    }

    public String getFiber_value() {
        return fiber_value;
    }

    public void setFiber_value(String fiber_value) {
        this.fiber_value = fiber_value;
    }

    public String getSodium_serving() {
        return sodium_serving;
    }

    public void setSodium_serving(String sodium_serving) {
        this.sodium_serving = sodium_serving;
    }

    public String getEnergy_unit() {
        return energy_unit;
    }

    public void setEnergy_unit(String energy_unit) {
        this.energy_unit = energy_unit;
    }

    public String getFiber_serving() {
        return fiber_serving;
    }

    public void setFiber_serving(String fiber_serving) {
        this.fiber_serving = fiber_serving;
    }

    public String getProteins_100g() {
        return proteins_100g;
    }

    public void setProteins_100g(String proteins_100g) {
        this.proteins_100g = proteins_100g;
    }

    public String getFat() {
        return fat;
    }

    public void setFat(String fat) {
        this.fat = fat;
    }

    public String getCarbohydrates_value() {
        return carbohydrates_value;
    }

    public void setCarbohydrates_value(String carbohydrates_value) {
        this.carbohydrates_value = carbohydrates_value;
    }

    public String getEnergy_value() {
        return energy_value;
    }

    public void setEnergy_value(String energy_value) {
        this.energy_value = energy_value;
    }

    public String getCarbohydrates_100g() {
        return carbohydrates_100g;
    }

    public void setCarbohydrates_100g(String carbohydrates_100g) {
        this.carbohydrates_100g = carbohydrates_100g;
    }

    public String getFiber_modifier() {
        return fiber_modifier;
    }

    public void setFiber_modifier(String fiber_modifier) {
        this.fiber_modifier = fiber_modifier;
    }

    public String getFat_100g() {
        return fat_100g;
    }

    public void setFat_100g(String fat_100g) {
        this.fat_100g = fat_100g;
    }

    public String getSugars_value() {
        return sugars_value;
    }

    public void setSugars_value(String sugars_value) {
        this.sugars_value = sugars_value;
    }

    public String getEnergy_serving() {
        return energy_serving;
    }

    public void setEnergy_serving(String energy_serving) {
        this.energy_serving = energy_serving;
    }

    public String getSodium() {
        return sodium;
    }

    public void setSodium(String sodium) {
        this.sodium = sodium;
    }

    public String getSodium_value() {
        return sodium_value;
    }

    public void setSodium_value(String sodium_value) {
        this.sodium_value = sodium_value;
    }

    public String getFiber_unit() {
        return fiber_unit;
    }

    public void setFiber_unit(String fiber_unit) {
        this.fiber_unit = fiber_unit;
    }

    public String getSodium_unit() {
        return sodium_unit;
    }

    public void setSodium_unit(String sodium_unit) {
        this.sodium_unit = sodium_unit;
    }

    public String getSalt_100g() {
        return salt_100g;
    }

    public void setSalt_100g(String salt_100g) {
        this.salt_100g = salt_100g;
    }

    public String getSugars_100g() {
        return sugars_100g;
    }

    public void setSugars_100g(String sugars_100g) {
        this.sugars_100g = sugars_100g;
    }

    public String getFiber_100g() {
        return fiber_100g;
    }

    public void setFiber_100g(String fiber_100g) {
        this.fiber_100g = fiber_100g;
    }

    public String getProteins_value() {
        return proteins_value;
    }

    public void setProteins_value(String proteins_value) {
        this.proteins_value = proteins_value;
    }

    public String getProteins() {
        return proteins;
    }

    public void setProteins(String proteins) {
        this.proteins = proteins;
    }

    public String getEnergy() {
        return energy;
    }

    public void setEnergy(String energy) {
        this.energy = energy;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(sugars_serving);
        parcel.writeString(proteins_serving);
        parcel.writeString(salt_serving);
        parcel.writeString(fat_value);
        parcel.writeString(fiber);
        parcel.writeString(energy_100g);
        parcel.writeString(carbohydrates_unit);
        parcel.writeString(sugars);
        parcel.writeString(fat_serving);
        parcel.writeString(fat_unit);
        parcel.writeString(sodium_100g);
        parcel.writeString(sugars_unit);
        parcel.writeString(proteins_unit);
        parcel.writeString(salt_unit);
        parcel.writeString(carbohydrates);
        parcel.writeString(carbohydrates_serving);
        parcel.writeString(salt_value);
        parcel.writeString(fiber_value);
        parcel.writeString(sodium_serving);
        parcel.writeString(energy_unit);
        parcel.writeString(fiber_serving);
        parcel.writeString(proteins_100g);
        parcel.writeString(fat);
        parcel.writeString(carbohydrates_value);
        parcel.writeString(energy_value);
        parcel.writeString(carbohydrates_100g);
        parcel.writeString(fiber_modifier);
        parcel.writeString(fat_100g);
        parcel.writeString(sugars_value);
        parcel.writeString(energy_serving);
        parcel.writeString(sodium);
        parcel.writeString(sodium_value);
        parcel.writeString(fiber_unit);
        parcel.writeString(sodium_unit);
        parcel.writeString(salt_100g);
        parcel.writeString(sugars_100g);
        parcel.writeString(fiber_100g);
        parcel.writeString(proteins_value);
        parcel.writeString(proteins);
        parcel.writeString(energy);
        parcel.writeString(salt);
    }
}