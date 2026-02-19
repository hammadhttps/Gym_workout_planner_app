package com.example.a21l_5812_a1;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Workout_Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_workout);

        Button btnChestBook = findViewById(R.id.btnChestBook);
        Button btnChestTutorial = findViewById(R.id.btnChestTutorial);

        Button btnBicep_Work = findViewById(R.id.btnbicep);
        Button Bicep_Tutorial = findViewById(R.id.btnbicep_tut);

        Button Arms_Workout = findViewById(R.id.btnArmsBook);
        Button btnArms_Tutorial = findViewById(R.id.btnArmsTutorial);



        btnChestBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Workout_Activity.this, Slot_Booking_Activity.class);
                intent.putExtra("workout_name", "Chest Workout");
                startActivity(intent);
            }
        });

        btnBicep_Work.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Workout_Activity.this, Slot_Booking_Activity.class);
                intent.putExtra("workout_name", "Biceps Workout");
                startActivity(intent);
            }
        });

        Arms_Workout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Workout_Activity.this, Slot_Booking_Activity.class);
                intent.putExtra("workout_name", "Arms Complete Workout");
                startActivity(intent);
            }
        });

        // Tutorial click listeners (Implicit Intents to YouTube)
        btnChestTutorial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openYouTubeTutorial("Back Workout");
            }
        });

        btnArms_Tutorial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openYouTubeTutorial("Arms workout");
            }
        });

        Bicep_Tutorial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openYouTubeTutorial("Bicep workout");
            }
        });
    }

    private void openYouTubeTutorial(String query) {
        Intent intent = new Intent(Intent.ACTION_VIEW,
                Uri.parse("https://www.youtube.com/results?search_query=" + query));
        startActivity(intent);
    }
    }
