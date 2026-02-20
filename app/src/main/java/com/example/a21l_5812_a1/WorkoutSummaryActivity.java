package com.example.a21l_5812_a1;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import java.util.ArrayList;
import java.util.Objects;

public class WorkoutSummaryActivity extends AppCompatActivity {
    
    private TextView txtWorkoutName, txtSelectedSlots, txtNutritionTotal;
    private Button btnSendPlan, btnSendSMS;
    private String workoutName;
    private ArrayList<String> selectedSlots;
    private double nutritionTotal;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout_summary);
        
        // Get data from intent
        workoutName = getIntent().getStringExtra("workout_name");
        selectedSlots = getIntent().getStringArrayListExtra("selected_slots");
        nutritionTotal = getIntent().getDoubleExtra("nutrition_total", 0.0);
        
        initViews();
        displaySummary();
        
        btnSendPlan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sharePlan();
            }
        });
        
        btnSendSMS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkSMSPermissionAndSend();
            }
        });
    }
    
    private void initViews() {
        txtWorkoutName = findViewById(R.id.txtWorkoutName);
        txtSelectedSlots = findViewById(R.id.txtSelectedSlots);
        txtNutritionTotal = findViewById(R.id.txtNutritionTotal);
        btnSendPlan = findViewById(R.id.btnSendPlan);
        btnSendSMS = findViewById(R.id.btnSendSMS);
    }
    
    @SuppressLint({"DefaultLocale", "SetTextI18n"})
    private void displaySummary() {
        txtWorkoutName.setText(Objects.requireNonNullElse(workoutName, "Not selected"));
        
        if (selectedSlots != null && !selectedSlots.isEmpty()) {
            StringBuilder slots = new StringBuilder();
            for (int i = 0; i < selectedSlots.size(); i++) {
                slots.append(selectedSlots.get(i));
                if (i < selectedSlots.size() - 1) {
                    slots.append(", ");
                }
            }
            txtSelectedSlots.setText(slots.toString());
        } else {
            txtSelectedSlots.setText("No slots selected");
        }
        
        txtNutritionTotal.setText(String.format("$%.2f", nutritionTotal));
    }
    
    private void sharePlan() {
        @SuppressLint("DefaultLocale") String message = "🏋️‍♂️ FASTFit Workout Summary 🏋️‍♂️\n\n" +
                        "📋 Workout: " + workoutName + "\n" +
                        "⏰ Slots: " + getSelectedSlotsString() + "\n" +
                        "💰 Nutrition Total: $" + String.format("%.2f", nutritionTotal) + "\n\n" +
                        "Stay fit, stay strong! 💪";
        
        Intent shareIntent = new Intent(Intent.ACTION_SEND);
        shareIntent.setType("text/plain");
        shareIntent.putExtra(Intent.EXTRA_TEXT, message);
        shareIntent.putExtra(Intent.EXTRA_SUBJECT, "My FASTFit Workout Plan");
        startActivity(Intent.createChooser(shareIntent, "Share Workout Plan via"));
    }
    
    private String getSelectedSlotsString() {
        if (selectedSlots == null || selectedSlots.isEmpty()) {
            return "None";
        }
        return TextUtils.join(", ", selectedSlots);
    }
    
    private void checkSMSPermissionAndSend() {
        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.SEND_SMS)
                != PackageManager.PERMISSION_GRANTED) {
            // Request permission
            ActivityCompat.requestPermissions(this,
                    new String[]{android.Manifest.permission.SEND_SMS},
                    100);
        } else {
            // Permission already granted
            sendSMS();
        }
    }
    
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 100) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                sendSMS();
            } else {
                Toast.makeText(this, "SMS permission denied. Cannot send SMS.",
                        Toast.LENGTH_SHORT).show();
            }
        }
    }
    
    private void sendSMS() {
        // Validate data
        if (workoutName == null || workoutName.isEmpty()) {
            Toast.makeText(this, "Missing workout data. Cannot send SMS.", 
                    Toast.LENGTH_SHORT).show();
            return;
        }
        
        if (selectedSlots == null || selectedSlots.isEmpty()) {
            Toast.makeText(this, "No slots selected. Cannot send SMS.", 
                    Toast.LENGTH_SHORT).show();
            return;
        }
        
        // Create message
        @SuppressLint("DefaultLocale") String message = "FASTFit Confirmation\n\n" +
                        "Workout: " + workoutName + "\n" +
                        "Slots: " + getSelectedSlotsString() + "\n" +
                        "Nutrition: $" + String.format("%.2f", nutritionTotal) + "\n\n" +
                        "Thank you for choosing FASTFit!";
        
        try {
            SmsManager smsManager = SmsManager.getDefault();
            
            // For long messages, divide into parts
            if (message.length() > 160) {
                ArrayList<String> messageParts = smsManager.divideMessage(message);
                smsManager.sendMultipartTextMessage("+1234567890", null, 
                        messageParts, null, null);
            } else {
                smsManager.sendTextMessage("+1234567890", null, message, null, null);
            }
            
            Toast.makeText(this, "SMS sent successfully!", Toast.LENGTH_SHORT).show();
            
        } catch (Exception e) {
            Toast.makeText(this, "Failed to send SMS: " + e.getMessage(), 
                    Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }
}
