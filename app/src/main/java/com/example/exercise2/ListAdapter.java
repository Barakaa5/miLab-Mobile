package com.example.exercise2;

import android.content.Context;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ListAdapter extends RecyclerView.Adapter{

    String[] names_list;
    int[] images_list;
    Context context;

    public ListAdapter(Context ct, String[] data, int[] images) {
        names_list = data;
        images_list = images;
        context = ct;
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView rowText;
        private ImageView rowImage;


        public MyViewHolder(View itemView) {
            super(itemView);
            rowText = itemView.findViewById(R.id.textView1);
            rowImage = itemView.findViewById(R.id.imageView1);
        }
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.recycler_view_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        String textForDisplay = names_list[position];
        ((MyViewHolder)holder).rowText.setText(textForDisplay);
        int imageForDisplay = images_list[position];
        ((MyViewHolder)holder).rowImage.setImageResource(imageForDisplay);
    }

    @Override
    public int getItemCount() {
        return names_list.length;
    }
}
