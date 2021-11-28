package com.example.aster.intarface.navigation.account;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.aster.R;
import com.example.aster.entities.Post;

public class ViewHolder extends RecyclerView.ViewHolder {
    TextView username;
    TextView dislikes;
    TextView likes;
    TextView title;
    ImageView img;
    public ViewHolder(@NonNull View itemView) {
        super(itemView);
        title = itemView.findViewById(R.id.post_title);
        username = itemView.findViewById(R.id.post_username);
        dislikes = itemView.findViewById(R.id.post_dislikesCounter);
        likes = itemView.findViewById(R.id.post_likesCounter);
        img = itemView.findViewById(R.id.post_image);
    }
}
