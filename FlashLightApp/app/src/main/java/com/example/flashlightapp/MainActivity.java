package com.example.flashlightapp;

import androidx.appcompat.app.AppCompatActivity;

import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;

import com.example.flashlightapp.databinding.ActivityMainBinding;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        Objects.requireNonNull(getSupportActionBar()).hide();
        binding.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(binding.button.getText().toString().equals("Turn on")) {
                    binding.button.setText(R.string.turn_off);
                    binding.imageViewFlash.setImageResource(R.drawable.torch_on);
                    chageFlashState(true);
                }
                else{
                    binding.button.setText(R.string.turn_on);
                    binding.imageViewFlash.setImageResource(R.drawable.torch_off);
                    chageFlashState(false);
                }
            }
        });
    }

    private void chageFlashState(boolean val) {
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.M){
            CameraManager cameraManager = (CameraManager) getSystemService(CAMERA_SERVICE);
            String cameraId = null;
            try {
                cameraId = cameraManager.getCameraIdList()[0];
                cameraManager.setTorchMode(cameraId,val);
            } catch (CameraAccessException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        binding.button.setText(R.string.turn_on);
    }
}