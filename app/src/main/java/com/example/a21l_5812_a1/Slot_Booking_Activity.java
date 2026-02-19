package com.example.a21l_5812_a1;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class Slot_Booking_Activity extends AppCompatActivity {


    private TextView txtWorkoutName, txtSelectedSlots;
    private CheckBox chkMorning, chkNight, chkEvening;
    private Button btnProceedNutrition, btnConfirmWorkout;
    private String workoutName;
    private int selectedSlotCount = 0;
    private ArrayList<String> selectedSlots = new ArrayList<>();

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_slot_booking);

        workoutName=getIntent().getStringExtra("workout_name");

        Get_Ids();
        txtWorkoutName.setText("Workout: " + workoutName);

        setupListsners();




    }
    private   void Get_Ids()
    {
        txtWorkoutName = findViewById(R.id.txtWorkoutName);
        txtSelectedSlots = findViewById(R.id.txtSelectedSlots);
        chkMorning = findViewById(R.id.chkMorning);
        chkNight = findViewById(R.id.chkNight);
        chkEvening = findViewById(R.id.chkEvening);
        btnProceedNutrition = findViewById(R.id.btnProceedNutrition);
        btnConfirmWorkout = findViewById(R.id.btnConfirmWorkout);
    }

    private void setupListsners()
    {
        View.OnClickListener slotClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateSlotSelection();
            }
        };

        chkMorning.setOnClickListener(slotClickListener);
        chkNight.setOnClickListener(slotClickListener);
        chkEvening.setOnClickListener(slotClickListener);

        btnProceedNutrition.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Slot_Booking_Activity.this, Nutrition_Activity.class);
                intent.putExtra("workout_name", workoutName);
                intent.putStringArrayListExtra("selected_slots", selectedSlots);
                startActivity(intent);
            }
        });

        btnConfirmWorkout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Slot_Booking_Activity.this, Workout_Activity.class);
                intent.putExtra("workout_name", workoutName);
                intent.putStringArrayListExtra("selected_slots", selectedSlots);
                intent.putExtra("nutrition_total", 0.0);
                startActivity(intent);
            }
        });
    }

    @SuppressLint("SetTextI18n")
    private void updateSlotSelection() {
        selectedSlots.clear();
        selectedSlotCount = 0;

        if (chkMorning.isChecked()) {
            selectedSlots.add("Morning");
            selectedSlotCount++;
        }
        if (chkNight.isChecked()) {
            selectedSlots.add("Afternoon");
            selectedSlotCount++;
        }
        if (chkEvening.isChecked()) {
            selectedSlots.add("Evening");
            selectedSlotCount++;
        }

        txtSelectedSlots.setText("Selected Slots: " + selectedSlotCount);
        btnProceedNutrition.setEnabled(selectedSlotCount > 0);
    }


    }


