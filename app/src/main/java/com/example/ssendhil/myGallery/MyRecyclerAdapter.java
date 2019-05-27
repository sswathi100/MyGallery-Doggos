package com.example.ssendhil.myGallery;
import android.content.Context;
import android.content.Intent;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;

import java.util.ArrayList;
public class MyRecyclerAdapter extends RecyclerView.Adapter<MyRecyclerAdapter.MyViewHolder> {
    private ArrayList<MyIcon> icons;
    private Context context;
    Model model;
    public MyRecyclerAdapter(ArrayList<MyIcon> ic, Context c, Model m){
        icons = ic;
        context = c;
        model = m;
    }
    public static class MyViewHolder extends RecyclerView.ViewHolder {
        private RatingBar ratingBar;
        private ImageView imageView;
        public MyViewHolder(View view) {
            super(view);
            ratingBar = view.findViewById(R.id.ratings);
            imageView = view.findViewById(R.id.images);
        }
    }
    @Override
    public MyRecyclerAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.icon_layout, parent, false);
        MyViewHolder mvh = new MyViewHolder(view);
        return mvh;
    }
    @Override
    public void onBindViewHolder(final MyViewHolder h, int pos){
        h.ratingBar.setRating(icons.get(pos).getMyRating());
        new LoadPic(h.imageView).execute(context.getString(icons.get(pos).getMyId()));
        h.imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        h.imageView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent = new Intent(context, FullscreenActivity.class);
                intent.putExtra("myImg", icons.get(h.getAdapterPosition()).getMyId());
                intent.putExtra("rating", (int) icons.get(h.getAdapterPosition()).getMyRating());
                intent.putExtra("position", h.getAdapterPosition());
                context.startActivity(intent);
            }
        });
        h.ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                model.setIconRating(h.getAdapterPosition(),(int) rating);
            }
        });
    }
    public void changeList(ArrayList<MyIcon> l) {
        icons.clear();
        icons.addAll(l);
    }
    public void updateList(ArrayList<MyIcon> ic) {
        final MyDiffUtilCallBack d = new MyDiffUtilCallBack(icons, ic);
        final DiffUtil.DiffResult r = DiffUtil.calculateDiff(d);
        icons.clear();
        icons.addAll(ic);
        r.dispatchUpdatesTo(this);
    }
    @Override
    public int getItemCount(){
        return icons.size();
    }
}