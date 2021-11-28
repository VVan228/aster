package com.example.aster.intarface.navigation.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextSwitcher;
import android.widget.TextView;
import android.widget.ViewSwitcher;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.aster.CoverMovie;
import com.example.aster.MovieAdapter;
import com.example.aster.R;
import com.example.aster.databinding.FragmentHomeBinding;
import com.example.aster.intarface.MainActivity;

import java.util.ArrayList;
import java.util.List;

import it.moondroid.coverflow.components.ui.containers.FeatureCoverFlow;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;

    private FeatureCoverFlow coverFlow;
    private MovieAdapter movieAdapter;
    private List<CoverMovie> movieList = new ArrayList<>();
    private TextSwitcher mTitle;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        /*final TextView textView = binding.textHome;
        textView.setText("home mf");*/

        //cover
        initData();
        mTitle = binding.homeTitle;
        mTitle.setFactory(new ViewSwitcher.ViewFactory() {
            @Override
            public View makeView() {
                LayoutInflater inflater = LayoutInflater.from(getActivity());
                TextView txt = (TextView) inflater.inflate(R.layout.home_layout_title, null);

                return txt;
            }
        });

        Animation in = AnimationUtils.loadAnimation(getActivity(), R.anim.slide_in_top);
        Animation out = AnimationUtils.loadAnimation(getActivity(), R.anim.slide_out_bottom);

        mTitle.setInAnimation(in);
        mTitle.setOutAnimation(out);

        //set adapter for cover flow
        movieAdapter = new MovieAdapter(movieList, getActivity());
        coverFlow = binding.homeCoverflow;
        coverFlow.setAdapter(movieAdapter);

        coverFlow.setOnScrollPositionListener(new FeatureCoverFlow.OnScrollPositionListener() {
            @Override
            public void onScrolledToPosition(int position) {
                // changing name? then image is changing
                mTitle.setText(movieList.get(position).getName());
            }

            @Override
            public void onScrolling() {

            }
        });

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    /**
     * Add name and image to movie
     */
    private void initData() {

        movieList.add(new CoverMovie("Когда выгоднее покупать валюту", "https://avatars.mds.yandex.net/get-zen-pub-og/1641076/pub_5c5f10fd64276e00ae3e03be_5c64773265c9a700ba7aa408/orig"));
        movieList.add(new CoverMovie("Как заработать на инвестициях", "http://tv-bis.ru/wp-content/uploads/2017/08/672.jpg"));
        movieList.add(new CoverMovie("Когда выгоднее покупать валюту", "https://avatars.mds.yandex.net/get-zen-pub-og/1641076/pub_5c5f10fd64276e00ae3e03be_5c64773265c9a700ba7aa408/orig"));
        movieList.add(new CoverMovie("Как заработать на инвестициях", "http://tv-bis.ru/wp-content/uploads/2017/08/672.jpg"));

    }
}