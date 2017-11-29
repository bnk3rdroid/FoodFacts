package foodfacts.bourgault.com.foodfacts.screens.details.content.core;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import foodfacts.bourgault.com.foodfacts.R;
import foodfacts.bourgault.com.foodfacts.base.BasePresenter;

public class FoodContentPresenter extends BasePresenter<FoodContentFragment, FoodContentModel> {

    public FoodContentPresenter(FoodContentFragment view, FoodContentModel model) {
        super(view, model);
    }

    @Override
    protected void onCreate() {
        mView.bindFood();
    }

    List<String> transformAllergens(String allergens) {
        List<String> allergensList = new LinkedList<>(Arrays.asList(allergens.split(", ")));
        List<String> tmp = new ArrayList<>();
        for (String allergen : allergensList) {
            tmp.add(allergen.substring(0, 1).toUpperCase() + allergen.substring(1).toLowerCase());
        }
        Set<String> removeDuplicate = new HashSet<>();
        removeDuplicate.addAll(tmp);
        allergensList.clear();
        allergensList.addAll(removeDuplicate);
        return allergensList;
    }

    String transformIngredients(String ingredients) {
        return ingredients
                .replace("_", "")
                .replace(" - ", "\n")
                .replace(" -", "\n")
                .replace("- ", "\n");
    }

    int getGradeColor(String grade) {
        int textColor;
        switch (grade.toUpperCase()) {
            case "A":
                textColor = R.color.colorGradeA;
                break;
            case "B":
                textColor = R.color.colorGradeB;
                break;
            case "C":
                textColor = R.color.colorGradeC;
                break;
            case "D":
                textColor = R.color.colorGradeD;
                break;
            case "E":
                textColor = R.color.colorGradeE;
                break;
            default:
                textColor = R.color.colorAccent;
                break;
        }
        return textColor;
    }
}
