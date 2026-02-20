package com.example.a21l_5812_a1;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class Slot_Booking_Activity extends AppCompatActivity {


    private TextView txtWorkoutName, txtSelectedSlots;
    private Button btnMorning, btnAfternoon, btnEvening;
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
        btnMorning = findViewById(R.id.btnMorning);
        btnAfternoon = findViewById(R.id.btnAfternoon);
        btnEvening = findViewById(R.id.btnEvening);
        btnProceedNutrition = findViewById(R.id.btnProceedNutrition);
        btnConfirmWorkout = findViewById(R.id.btnConfirmWorkout);
    }

    private void setupListsners()
    {
        View.OnClickListener slotClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Button clickedButton = (Button) v;
                String slot = clickedButton.getText().toString();

                if (selectedSlots.contains(slot)) {
                    selectedSlots.remove(slot);
                    clickedButton.setBackgroundResource(R.drawable.button_rounded_white);
                } else {
                    selectedSlots.add(slot);
                    clickedButton.setBackgroundResource(R.drawable.button_rounded_red);
                    clickedButton.setTextColor(getResources().getColor(android.R.color.white));
                }

                updateSlotSelection();
            }
        };

        btnMorning.setOnClickListener(slotClickListener);
        btnAfternoon.setOnClickListener(slotClickListener);
        btnEvening.setOnClickListener(slotClickListener);

        btnProceedNutrition.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Slot_Booking_Activity.this, NutritionActivity.class);
                intent.putExtra("workout_name", workoutName);
                intent.putStringArrayListExtra("selected_slots", selectedSlots);
                startActivity(intent);
            }
        });

        btnConfirmWorkout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Slot_Booking_Activity.this, WorkoutSummaryActivity.class);
                intent.putExtra("workout_name", workoutName);
                intent.putStringArrayListExtra("selected_slots", selectedSlots);
                intent.putExtra("nutrition_total", 0.0);
                startActivity(intent);
            }
        });
    }

    @SuppressLint("SetTextI18n")
    private void updateSlotSelection() {
        selectedSlotCount = selectedSlots.size();
        txtSelectedSlots.setText("Selected Slots: " + selectedSlotCount);
        
        if (selectedSlotCount > 0) {
            btnProceedNutrition.setEnabled(true);
            btnProceedNutrition.setBackgroundResource(R.drawable.button_rounded_red);
        } else {
            btnProceedNutrition.setEnabled(false);
            btnProceedNutrition.setBackgroundResource(R.drawable.button_rounded_gray);
        }
    }


    }


