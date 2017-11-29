package foodfacts.bourgault.com.foodfacts.screens.details.content.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import foodfacts.bourgault.com.foodfacts.R;

public class TagsAdapter extends RecyclerView.Adapter<TagsAdapter.TagsVH> {

    private List<String> mTags;

    public TagsAdapter(@NonNull List<String> tags) {
        mTags = tags;
    }

    @Override
    public TagsVH onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_tag, parent, false);
        return new TagsVH(view);
    }

    @Override
    public void onBindViewHolder(TagsVH holder, int position) {
        holder.mTag.setText(mTags.get(position));
    }

    @Override
    public int getItemCount() {
        return mTags.size();
    }

    public void update(List<String> tags) {
        mTags = tags;
        notifyDataSetChanged();
    }

    class TagsVH extends RecyclerView.ViewHolder {

        @BindView(R.id.tag)
        TextView mTag;

        TagsVH(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
