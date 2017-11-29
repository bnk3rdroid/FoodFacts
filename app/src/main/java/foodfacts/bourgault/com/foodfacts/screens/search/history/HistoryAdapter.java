package foodfacts.bourgault.com.foodfacts.screens.search.history;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import foodfacts.bourgault.com.foodfacts.R;
import foodfacts.bourgault.com.foodfacts.screens.details.core.FoodActivity;
import foodfacts.bourgault.com.openfoodapi.models.Food;
import foodfacts.bourgault.com.openfoodapi.models.Product;

import static foodfacts.bourgault.com.foodfacts.screens.details.core.FoodActivity.EXTRA_FOOD_OBJECT;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.HistoryVH> {

    private List<Product> mHistory;
    private Context mContext;

    public HistoryAdapter(@NonNull List<Product> history) {
        mHistory = history;
    }

    @Override
    public HistoryVH onCreateViewHolder(ViewGroup parent, int viewType) {
        mContext = parent.getContext();
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_history, parent, false);
        return new HistoryVH(view);
    }

    @Override
    public void onBindViewHolder(HistoryVH holder, int position) {
        Product current = mHistory.get(position);
        Glide.with(mContext).load(current.getImage_front_small_url()).into(holder.mImage);
        holder.mTitle.setText(current.getProduct_name_fr());

        holder.mLayout.setOnClickListener(view -> {
            Intent intent = new Intent(mContext, FoodActivity.class);
            Food food = new Food();
            food.setStatus(Food.STATUS_OK);
            food.setCode("1");
            food.setProduct(current);
            intent.putExtra(EXTRA_FOOD_OBJECT, food);
            mContext.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return mHistory.size();
    }

    public void update(@NonNull List<Product> history) {
        mHistory = history;
        notifyDataSetChanged();
    }

    class HistoryVH extends RecyclerView.ViewHolder {

        @BindView(R.id.item_history)
        ConstraintLayout mLayout;

        @BindView(R.id.item_history_image)
        ImageView mImage;

        @BindView(R.id.item_history_title)
        TextView mTitle;

        HistoryVH(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
