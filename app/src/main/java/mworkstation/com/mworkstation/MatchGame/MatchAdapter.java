package mworkstation.com.mworkstation.MatchGame;


import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import androidx.recyclerview.widget.RecyclerView;
import mworkstation.com.mworkstation.R;

public class MatchAdapter extends RecyclerView.Adapter<MatchAdapter.CategoryViewHolder> {

    List<Match> storyList;
    Activity context;

    private MatchOnclickListener onItemClickListener;


    public MatchAdapter(List<Match> storyList, Activity context) {
        this.storyList = storyList;
        this.context = context;

    }

    @Override
    public CategoryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext())
                .inflate(R.layout.box_of_match, parent, false);


        return new CategoryViewHolder(v);

    }

    @Override
    public void onBindViewHolder(final CategoryViewHolder holder, int position) {
        final Match story = storyList.get(position);

        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClickListener.onItemClick(story, v);
            }
        };

       holder.card.setOnClickListener(listener);

        if(story.answer){
            holder.word_tv.setText(story.word);
        }else {
            holder.word_tv.setVisibility(View.GONE);
            holder.word_view_img.setVisibility(View.VISIBLE);
            holder.word_view_img.setBackgroundResource(story.res);
        }





    }

    @Override
    public int getItemCount() {
        return storyList.size();
    }

    public class CategoryViewHolder extends RecyclerView.ViewHolder {

        public TextView word_tv;
        public ImageView word_view_img;
        public LinearLayout card,hide_info;


        public CategoryViewHolder(View view) {
            super(view);
            card=view.findViewById(R.id.card);
            word_tv=view.findViewById(R.id.word_tv);
            word_view_img=view.findViewById(R.id.word_view_img);

            hide_info=view.findViewById(R.id.hide_info);

        }



    }

    public MatchOnclickListener getOnItemClickListener() {
        return onItemClickListener;
    }

    public void setOnItemClickListener(MatchOnclickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }


}