package edu.vt.emartin7.dreamcatcher.view;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.List;

import edu.vt.emartin7.dreamcatcher.model.Dream;

/**
 * Created by ericmartin on 2/10/18.
 */

public class DreamAdapter extends RecyclerView.Adapter<DreamHolder> {

    private List<Dream> mDreams;
    private Context mContext;

    public DreamAdapter(Context context, List<Dream> dreams) {
        super();
        mDreams = dreams;
        mContext = context;
    }

    @Override
    public DreamHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(mContext);

        return new DreamHolder(layoutInflater, parent);
    }

    @Override
    public void onBindViewHolder(DreamHolder holder, int position) {
        Dream dream = mDreams.get(position);
        holder.bind(dream);
    }

    @Override
    public int getItemCount() {
        return mDreams.size();
    }
}
