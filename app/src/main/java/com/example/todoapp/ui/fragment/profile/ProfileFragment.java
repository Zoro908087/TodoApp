package com.example.todoapp.ui.fragment.profile;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;

import com.bumptech.glide.Glide;
import com.example.todoapp.R;
import com.example.todoapp.databinding.FragmentProfileBinding;
import com.example.todoapp.utils.Pref;


public class ProfileFragment extends Fragment {
    private FragmentProfileBinding binding;
    private Uri uri;
    private ActivityResultLauncher<Intent> resultContracts;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentProfileBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initDefault();
        iniImageListener();
        initTxtListener();
        initBtn();
        initListener();
        initAnim();
    }

    private void initAnim() {
        binding.imageScreen.animate().translationY(0).setDuration(1000).start();
        binding.txtFirstName.animate().translationX(0).setDuration(1000).start();
        binding.txtLastName.animate().translationX(0).setDuration(1000).start();
        binding.btnSave.animate().translationY(0).setDuration(1000).start();
        binding.editFirstName.animate().alpha(1).setDuration(1000).start();
        binding.editLastName.animate().alpha(1).setDuration(1000).start();
    }

    private void initDefault() {
        binding.imageScreen.setTranslationY(-200);
        binding.txtFirstName.setTranslationX(-200);
        binding.txtLastName.setTranslationX(200);
        binding.btnSave.setTranslationY(500);
    }

    private void initListener() {
        binding.imageScreen.setOnClickListener(v -> {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if (requireActivity().checkSelfPermission(
                        Manifest.permission.READ_EXTERNAL_STORAGE) !=
                        PackageManager.PERMISSION_GRANTED) {
                    requireActivity().requestPermissions(new
                                    String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                            1);
                } else {
                    getGallery();
                }
            }
        });
        resultContracts = registerForActivityResult(new
                        ActivityResultContracts.StartActivityForResult(),
                result -> {
                    Intent intent = result.getData();
                    if (intent != null) {
                        uri = intent.getData();
                        Glide.with(binding.imageScreen).load(uri).circleCrop().into(binding.imageScreen);
                    }
                });
    }

    private void getGallery() {
        Intent intent = new Intent(Intent.ACTION_PICK,
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        resultContracts.launch(intent);
    }

    private void initBtn() {
        binding.btnSave.setOnClickListener(v -> {
            saveDate();
            initTxtListener();
            iniImageListener();
            clear();
        });
    }

    private void clear() {
        binding.editFirstName.setText("");
        binding.editLastName.setText("");
        InputMethodManager imm = (InputMethodManager)
                requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(binding.getRoot().getWindowToken(), 0);
    }

    private void saveDate() {
        String firstName = binding.editFirstName.getText().toString().trim();
        String lastName = binding.editLastName.getText().toString().trim();
        if (!firstName.equals("")) {
            Pref.getPrefs().saveFirstName(firstName);
        }
        if (!lastName.equals("")) {
            Pref.getPrefs().saveLastName(lastName);
        }
        if (uri != null) {
            Pref.getPrefs().saveImage(uri);
        }
    }

    private void initTxtListener() {
        binding.txtFirstName.setText(Pref.getPrefs().firstName());
        binding.txtLastName.setText(Pref.getPrefs().lastName());
    }

    private void iniImageListener() {
        if (!Pref.getPrefs().getImage().equals("")) {
            uri = Uri.parse(Pref.getPrefs().getImage());
            Glide.with(binding.imageScreen).load(uri).circleCrop().into(binding.imageScreen);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode == 1){
            getGallery();
        }
    }
}