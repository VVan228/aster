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
import com.example.aster.entities.PostSearch;
import com.example.aster.events.Event;
import com.example.aster.events.Observer;
import com.example.aster.intarface.MainActivity;
import com.example.aster.models.Data;
import com.example.aster.models.Search;

import java.util.ArrayList;
import java.util.List;

import it.moondroid.coverflow.components.ui.containers.FeatureCoverFlow;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;

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
        MovieAdapter movieAdapter = new MovieAdapter(movieList, getActivity());
        FeatureCoverFlow coverFlow = binding.homeCoverflow;
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
        movieList.add(new CoverMovie("Что такое инвестиции", "https://mulino58.ru/wp-content/uploads/5/9/2/59249b47fed1b6aa04b14b15fbcaf79c.png"));
        movieList.add(new CoverMovie("Когда и как покупать валюту", "https://i.ytimg.com/vi/2AR9hl-CM5I/maxresdefault.jpg"));
        movieList.add(new CoverMovie("3 ways to make money", "https://i.ytimg.com/vi/FCi0x5suBUg/maxresdefault.jpg"));

        movieList.add(new CoverMovie("Биткоин", "https://avatars.mds.yandex.net/get-zen_doc/58826/pub_5b154dc65a104ffc19653770_5b154f5d77d0e6b3a92abbc7/scale_1200"));
        movieList.add(new CoverMovie("Высокорисковые инвестиции", "https://investblog.biz/wp-content/uploads/2020/08/High_risk.png"));
        movieList.add(new CoverMovie("Как собрать инвестиционный портфель", "https://avatars.mds.yandex.net/get-zen-pub-og/1520034/pub_5c72bf4cb40ff300b344dc55_5c72c5b2694ebb00ba19af78/orig"));


    }
}