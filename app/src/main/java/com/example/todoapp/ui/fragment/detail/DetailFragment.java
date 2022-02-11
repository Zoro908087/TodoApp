package com.example.todoapp.ui.fragment.detail;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.todoapp.App;
import com.example.todoapp.R;
import com.example.todoapp.databinding.FragmentDashboardBinding;
import com.example.todoapp.domain.models.kimchin.Kimchin;

public class DetailFragment extends Fragment {
private FragmentDashboardBinding binding;
private NavController controller;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentDashboardBinding.inflate(inflater);
        return binding.getRoot();
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        controller = Navigation.findNavController(view);
        initListener();
        initArgumentsListener();
    }


    private void initListener() {
            binding.btnSave.setText("Save");
            binding.btnSave.setOnClickListener(v -> {
                String result = binding.etTxt.getText().toString().trim();
                if (!result.equals("")) {
                    closeFragment();
                    saveTask(result);
                } else {
                    Toast.makeText(requireContext(), "Пусто", Toast.LENGTH_LONG).show();
                }
            });
    }

    private void initArgumentsListener() {
            if (getArguments() != null) {
                Kimchin kimchin = (Kimchin) getArguments().getSerializable("model");
                binding.etTxt.setText(kimchin.getTitle());
                binding.btnSave.setText("Edit");
                binding.btnSave.setOnClickListener(v -> {
                    String result = binding.etTxt.getText().toString().trim();
                    if (!result.equals("")) {
                        kimchin.setTitle(result);
                        App.dataBase.room().update(kimchin);
                        closeFragment();
                    } else {
                        Toast.makeText(requireContext(), "Пусто", Toast.LENGTH_LONG).show();
                    }
                });
            }
    }

    private void saveTask(String result) {
        Kimchin kimchin = new Kimchin(result);
        App.dataBase.room().addTask(kimchin);
    }

    private void closeFragment() {
        controller.navigateUp();
    }
}