package com.example.aster.ui.messenger;

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
import com.example.aster.R;
import com.example.aster.databinding.FragmentMessengerBinding;

public class MessengerFragment extends Fragment {
    private FragmentMessengerBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentMessengerBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textMessenger;
        textView.setText("messenger mf");
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
