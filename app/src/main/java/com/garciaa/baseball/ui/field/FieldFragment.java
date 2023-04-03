package com.garciaa.baseball.ui.field;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.garciaa.baseball.BaseballFieldView;
import com.garciaa.baseball.R;
import com.garciaa.baseball.databinding.FragmentFieldBinding;

public class FieldFragment extends Fragment {
    BaseballFieldView baseballFieldView;
    FrameLayout layout;

    private FragmentFieldBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        FieldViewModel fieldViewModel = new ViewModelProvider(this).get(FieldViewModel.class);

        binding = FragmentFieldBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        // TextView textView = binding.textDashboard;
        //dashboardViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);

        layout = root.findViewById(R.id.layout);
        baseballFieldView = new BaseballFieldView(requireActivity());
        layout.addView(baseballFieldView);
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}