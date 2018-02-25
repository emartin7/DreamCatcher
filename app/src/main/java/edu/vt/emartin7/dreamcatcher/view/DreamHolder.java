package edu.vt.emartin7.dreamcatcher.view;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import edu.vt.emartin7.dreamcatcher.R;
import edu.vt.emartin7.dreamcatcher.controller.DreamActivity;
import edu.vt.emartin7.dreamcatcher.model.Dream;

/**
 * Created by ericmartin on 2/10/18.
 */

public class DreamHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    //model field
    private Dream mDream;

    private TextView mTitleTextView;
    private TextView mDateTextView;
    private ImageView mDreamRealizedView;
    private ImageView mDreamDeferredView;

    public DreamHolder(LayoutInflater inflater, ViewGroup viewGroup) {
        super(inflater.inflate(R.layout.list_item_dream, viewGroup, false));

        mTitleTextView = itemView.findViewById(R.id.dream_title);
        mDateTextView = itemView.findViewById(R.id.dream_date);
        mDreamRealizedView = itemView.findViewById(R.id.dream_realized_image);
        mDreamDeferredView = itemView.findViewById(R.id.dream_deferred_image);

        itemView.setOnClickListener(this);
    }

    public void bind(Dream dream) {
        mDream = dream;
        mTitleTextView.setText(mDream.getTitle());
        mDateTextView.setText(mDream.getFormattedDate());
        mDreamDeferredView.setVisibility(dream.isDeferred() ? View.VISIBLE : View.GONE);
        mDreamRealizedView.setVisibility(dream.isRealized() ? View.VISIBLE : View.GONE);
    }

    @Override
    public void onClick(View view) {
        Intent intent = DreamActivity.newIntent(view.getContext(), mDream.getID());
        view.getContext().startActivity(intent);
    }
}
