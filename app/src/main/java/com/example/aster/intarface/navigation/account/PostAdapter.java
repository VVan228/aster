package com.example.aster.intarface.navigation.account;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.aster.R;
import com.example.aster.entities.Post;

import java.util.List;

public class PostAdapter extends RecyclerView.Adapter<ViewHolder>{

    private final LayoutInflater inflater;
    private final List<Post> states;

    PostAdapter(Context context, List<Post> states) {
        this.states = states;
        this.inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.post, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Post state = states.get(position);
        //holder.flagView.setImageResource(state.getFlagResource());
        holder.username.setText(state.getAuthor());
        holder.title.setText(state.getTitle());
        holder.dislikes.setText(String.valueOf(state.getDislikes()));
        holder.likes.setText(String.valueOf(state.getLikes()));
    }

    @Override
    public int getItemCount() {
        return states.size();
    }
}
