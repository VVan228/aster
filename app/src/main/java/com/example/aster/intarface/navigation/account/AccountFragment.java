package com.example.aster.intarface.navigation.account;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.aster.intarface.postCreation.PostCreationActivity;
import com.example.aster.databinding.FragmentAccountBinding;
import com.example.aster.entities.Post;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;


public class AccountFragment extends Fragment implements InterfaceAccountView{
    private FragmentAccountBinding binding;

    ArrayList<Post> states = new ArrayList<>();
    AccountPresenter presenter;
    PostAdapter postAdapter;
    String uid;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentAccountBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        presenter = new AccountPresenter(this);

        RecyclerView recyclerView = binding.accountPostList;
        // создаем адаптер
        postAdapter = new PostAdapter(getActivity(), states);
        // устанавливаем для списка адаптер
        recyclerView.setAdapter(postAdapter);
        recyclerView.setHasFixedSize(false);

        FloatingActionButton add = binding.accountAddArticle;
        add.setOnClickListener(v -> {
            presenter.onFabClick();

        });

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }


    @Override
    public void openPostCreationActivity() {
        Intent intent = new Intent(getActivity(), PostCreationActivity.class);
        startActivity(intent);
    }

    @Override
    public void addPost(Post post) {
        states.add(states.size(),post);
        postAdapter.notifyItemInserted(states.size()-1);
    }

    @Override
    public void changeUsername(String title) {
        final TextView textView = binding.accountName;
        textView.setText(title);
    }
}
