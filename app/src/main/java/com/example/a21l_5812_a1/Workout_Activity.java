package com.example.a21l_5812_a1;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class Workout_Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_workout);

        Button btnLegPressBook = findViewById(R.id.btnLegPressBook);
        Button btnLegPressTutorial = findViewById(R.id.btnLegPressTutorial);

        Button btnLatPulldownBook = findViewById(R.id.btnLatPulldownBook);
        Button btnLatPulldownTutorial = findViewById(R.id.btnLatPulldownTutorial);

        Button btnChestPressBook = findViewById(R.id.btnChestPressBook);
        Button btnChestPressTutorial = findViewById(R.id.btnChestPressTutorial);



        btnLegPressBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Workout_Activity.this, Slot_Booking_Activity.class);
                intent.putExtra("workout_name", "Leg Press");
                startActivity(intent);
            }
        });

        btnLatPulldownBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Workout_Activity.this, Slot_Booking_Activity.class);
                intent.putExtra("workout_name", "Lat Pulldown");
                startActivity(intent);
            }
        });

        btnChestPressBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Workout_Activity.this, Slot_Booking_Activity.class);
                intent.putExtra("workout_name", "Chest Press");
                startActivity(intent);
            }
        });

        // Tutorial click listeners (Implicit Intents to YouTube)
        btnLegPressTutorial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openYouTubeTutorial("Leg Press tutorial");
            }
        });

        btnLatPulldownTutorial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openYouTubeTutorial("Lat Pulldown tutorial");
            }
        });

        btnChestPressTutorial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openYouTubeTutorial("Chest Press tutorial");
            }
        });
    }

    private void openYouTubeTutorial(String query) {
        Intent intent = new Intent(Intent.ACTION_VIEW,
                Uri.parse("https://www.youtube.com/results?search_query=" + query));
        startActivity(intent);
    }
    }
