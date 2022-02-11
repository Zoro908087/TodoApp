package com.example.todoapp.ui.fragment.home;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.example.todoapp.App;
import com.example.todoapp.R;
import com.example.todoapp.databinding.FragmentHomeBinding;
import com.example.todoapp.domain.models.kimchin.Kimchin;
import com.example.todoapp.ui.fragment.home.adapter.MainAdapter;

import java.util.List;

public class HomeFragment extends Fragment implements MainAdapter.Click {

    private FragmentHomeBinding binding;
    private MainAdapter adapter ;
    private NavController controller;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        adapter = new MainAdapter(this);
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        controller = Navigation.findNavController(view);
        binding.rvNote.setAdapter(adapter);
        initDefault();
        initAnimate();
        initFragmentResultListener();
        initListener();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private void initAnimate() {
        binding.btnAdd.animate().translationX(0).setDuration(1000).start();
    }

    private void initDefault() {
        binding.btnAdd.setTranslationX(200);
    }
    private void initFragmentResultListener() {
        List<Kimchin> list = App.dataBase.room().getAllTasks();
        adapter.setList(list);
    }
    private void initListener() {
        binding.btnAdd.setOnClickListener(v -> {
            controller.navigate(R.id.detailFragment);
        });
    }

    @Override
    public void update(Kimchin kimchin) {
        Bundle bundle = new Bundle();
        bundle.putSerializable("model", kimchin);
        controller.navigate(R.id.detailFragment, bundle);
    }

    @Override
    public void delete(Kimchin kimchin) {
        AlertDialog.Builder builder = new AlertDialog.Builder(requireActivity());
        builder.setTitle("Вы действительно хотите удалить эту запись?");
        builder.setPositiveButton("Да без б", (dialog, which) -> {
                    App.dataBase.room().delete(kimchin);
                    initFragmentResultListener();
                }
        );
        builder.setNegativeButton("Нет конечно", (dialog, which) -> {
            Toast.makeText(requireContext(), "Ужас", Toast.LENGTH_LONG).show();
        }).show();
    }
}