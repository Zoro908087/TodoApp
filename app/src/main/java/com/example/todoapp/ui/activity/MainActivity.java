package com.example.todoapp.ui.activity;

import android.os.Bundle;
import android.view.View;

import com.example.todoapp.R;
import com.example.todoapp.utils.Pref;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.todoapp.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        NavController navController = Navigation.findNavController(this,
                R.id.nav_host_fragment_activity_main);
        NavigationUI.setupWithNavController(binding.navView, navController);
            if(!Pref.getPrefs().isBoardShow()){
                navController.navigate(R.id.boardFragment);
            }
        navController.addOnDestinationChangedListener((controller, destination, arguments) -> {
            switch (destination.getId()) {
                case R.id.boardFragment:
                    binding.navView.setVisibility(View.GONE);
                    break;
                default:
                    binding.navView.setVisibility(View.VISIBLE);
            }

        });
    }

}