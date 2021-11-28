package com.example.aster.intarface.navigation.account;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import com.example.aster.databinding.FragmentAccountBinding;
import com.example.aster.entities.Post;

import java.util.ArrayList;


public class AccountFragment extends Fragment {
    private FragmentAccountBinding binding;

    ArrayList<Post> states = new ArrayList<>();

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentAccountBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        setInitialData();
        RecyclerView recyclerView = binding.accountPostList;
        // создаем адаптер
        PostAdapter postAdapter = new PostAdapter(getActivity(), states);
        // устанавливаем для списка адаптер
        recyclerView.setAdapter(postAdapter);


        final TextView textView = binding.accountName;
        textView.setText("account mf");
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private void setInitialData(){

        states.add(new Post("заголовок", "", 12, 30, 200, "", 100000, "ссылка", "ссылка на автора"));
    }

}
