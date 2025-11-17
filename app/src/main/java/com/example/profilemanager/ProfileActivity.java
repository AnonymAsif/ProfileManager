package com.example.profilemanager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.HashMap;

public class ProfileActivity extends AppCompatActivity {

    private HashMap<Integer, Integer> drawables;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_profile);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        // Initializes onClick Listeners of image icons
        int[] imageIDs = {
                R.id.attentive_loaf, R.id.awestruck, R.id.crashout, R.id.grumpy, R.id.sleepy2, R.id.mad,
                R.id.peaceful_loaf, R.id.sad, R.id.sitting, R.id.hungry, R.id.polite, R.id.sleepy };

        int[] drawableIDs = {
                R.drawable.attentive_loaf, R.drawable.awestruck, R.drawable.crashout, R.drawable.grumpy,
                R.drawable.sleepy2, R.drawable.mad, R.drawable.peaceful_loaf, R.drawable.sad,
                R.drawable.sitting, R.drawable.hungry, R.drawable.polite, R.drawable.sleepy
        };

        // Saves the corresponding drawable name to each ID in a map
        drawables = new HashMap<>();
        for (int i = 0; i < imageIDs.length; i++) {
            findViewById(imageIDs[i]).setOnClickListener(this::setCatIcon);
            drawables.put(imageIDs[i], drawableIDs[i]);
        }
    }

    public void setCatIcon(View view) {
        // Only allows images to call method
        if (!(view instanceof ImageView selectedImage)) return;

        Integer drawableID = drawables.get(selectedImage.getId());
        if (drawableID == null) return;

        // Adds drawableName to extra and finishes activity
        Intent returnIntent = new Intent();
        returnIntent.putExtra("drawableID", drawableID);

        setResult(RESULT_OK, returnIntent);
        finish();
    }

}