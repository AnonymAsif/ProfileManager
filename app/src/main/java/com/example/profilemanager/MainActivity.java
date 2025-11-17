package com.example.profilemanager;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import com.google.android.material.snackbar.Snackbar;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.view.View;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.profilemanager.databinding.ActivityMainBinding;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    Button mapButton;
    Button clearButton;
    ImageView catImage;
    ActivityResultLauncher<Intent> profileActivityResultLauncher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mapButton = findViewById(R.id.mapButton);
        mapButton.setOnClickListener(this::OnOpenInGoogleMaps);

        clearButton = findViewById(R.id.clearButton);
        clearButton.setOnClickListener(v -> catImage.setImageResource(R.drawable.default_cat));

        profileActivityResultLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(), result -> {
            if (result.getResultCode() == RESULT_OK) {
                Intent data = result.getData();
                assert data != null;

                String drawableName = data.getStringExtra("drawableName");
                int resID = getResources().getIdentifier(drawableName, "drawable", getPackageName());
                catImage.setImageResource(resID);
            }
        });

        catImage = findViewById(R.id.catImage);
        catImage.setOnClickListener(v ->
                profileActivityResultLauncher.launch(new Intent(this, ProfileActivity.class)));
    }

    public void OnOpenInGoogleMaps(View v) {
        EditText address = findViewById(R.id.addressInput);

        Uri gmmIntentUri = Uri.parse("https://maps.google.ca/maps?q=" + address.getText());
        Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
        mapIntent.setPackage("com.google.android.apps.maps");

        startActivity(mapIntent);
    }

}