package com.example.a21l_5812_a1;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;

public class NutritionActivity extends AppCompatActivity {
    
    private TextView txtProteinQty, txtBarQty, txtSaladQty, txtJuiceQty;
    private TextView txtProteinPrice, txtBarPrice, txtSaladPrice, txtJuicePrice;
    private TextView txtTotalPrice;
    private Button btnConfirm;
    private TextView btnShare;
    private Button btnProteinMinus, btnProteinPlus, btnBarMinus, btnBarPlus;
    private Button btnSaladMinus, btnSaladPlus, btnJuiceMinus, btnJuicePlus;
    
    private String workoutName;
    private ArrayList<String> selectedSlots;
    private double totalPrice = 0.0;
    
    // Nutrition items with prices
    private final double PROTEIN_PRICE = 8.99;
    private final double BAR_PRICE = 3.49;
    private final double SALAD_PRICE = 6.99;
    private final double JUICE_PRICE = 4.99;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nutrition);
        
        // Get data from intent
        workoutName = getIntent().getStringExtra("workout_name");
        selectedSlots = getIntent().getStringArrayListExtra("selected_slots");
        
        initViews();
        setupQuantityControls();
        updateTotalPrice();
        
        btnShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shareNutritionPlan();
            }
        });
        
        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(NutritionActivity.this, WorkoutSummaryActivity.class);
                intent.putExtra("workout_name", workoutName);
                intent.putStringArrayListExtra("selected_slots", selectedSlots);
                intent.putExtra("nutrition_total", totalPrice);
                startActivity(intent);
            }
        });
    }
    
    @SuppressLint("DefaultLocale")
    private void initViews() {
        // Quantity TextViews
        txtProteinQty = findViewById(R.id.txtProteinQty);
        txtBarQty = findViewById(R.id.txtBarQty);
        txtSaladQty = findViewById(R.id.txtSaladQty);
        txtJuiceQty = findViewById(R.id.txtJuiceQty);
        
        // Price TextViews
        txtProteinPrice = findViewById(R.id.txtProteinPrice);
        txtBarPrice = findViewById(R.id.txtBarPrice);
        txtSaladPrice = findViewById(R.id.txtSaladPrice);
        txtJuicePrice = findViewById(R.id.txtJuicePrice);
        
        // Set initial prices
        txtProteinPrice.setText(String.format("$%.2f", PROTEIN_PRICE));
        txtBarPrice.setText(String.format("$%.2f", BAR_PRICE));
        txtSaladPrice.setText(String.format("$%.2f", SALAD_PRICE));
        txtJuicePrice.setText(String.format("$%.2f", JUICE_PRICE));
        
        // Total price TextView
        txtTotalPrice = findViewById(R.id.txtTotalPrice);
        
        // Buttons
        btnConfirm = findViewById(R.id.btnConfirm);
        btnShare = findViewById(R.id.btnShare);
        
        // Minus buttons
        btnProteinMinus = findViewById(R.id.btnProteinMinus);
        btnBarMinus = findViewById(R.id.btnBarMinus);
        btnSaladMinus = findViewById(R.id.btnSaladMinus);
        btnJuiceMinus = findViewById(R.id.btnJuiceMinus);
        
        // Plus buttons
        btnProteinPlus = findViewById(R.id.btnProteinPlus);
        btnBarPlus = findViewById(R.id.btnBarPlus);
        btnSaladPlus = findViewById(R.id.btnSaladPlus);
        btnJuicePlus = findViewById(R.id.btnJuicePlus);
    }
    
    private void setupQuantityControls() {
        // Protein Shake controls
        btnProteinMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int qty = Integer.parseInt(txtProteinQty.getText().toString());
                if (qty > 0) {
                    qty--;
                    txtProteinQty.setText(String.valueOf(qty));
                    updateTotalPrice();
                }
            }
        });
        
        btnProteinPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int qty = Integer.parseInt(txtProteinQty.getText().toString());
                qty++;
                txtProteinQty.setText(String.valueOf(qty));
                updateTotalPrice();
            }
        });
        
        // Energy Bar controls
        btnBarMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int qty = Integer.parseInt(txtBarQty.getText().toString());
                if (qty > 0) {
                    qty--;
                    txtBarQty.setText(String.valueOf(qty));
                    updateTotalPrice();
                }
            }
        });
        
        btnBarPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int qty = Integer.parseInt(txtBarQty.getText().toString());
                qty++;
                txtBarQty.setText(String.valueOf(qty));
                updateTotalPrice();
            }
        });
        
        // Salad controls
        btnSaladMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int qty = Integer.parseInt(txtSaladQty.getText().toString());
                if (qty > 0) {
                    qty--;
                    txtSaladQty.setText(String.valueOf(qty));
                    updateTotalPrice();
                }
            }
        });
        
        btnSaladPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int qty = Integer.parseInt(txtSaladQty.getText().toString());
                qty++;
                txtSaladQty.setText(String.valueOf(qty));
                updateTotalPrice();
            }
        });
        
        // Juice controls
        btnJuiceMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int qty = Integer.parseInt(txtJuiceQty.getText().toString());
                if (qty > 0) {
                    qty--;
                    txtJuiceQty.setText(String.valueOf(qty));
                    updateTotalPrice();
                }
            }
        });
        
        btnJuicePlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int qty = Integer.parseInt(txtJuiceQty.getText().toString());
                qty++;
                txtJuiceQty.setText(String.valueOf(qty));
                updateTotalPrice();
            }
        });
    }
    
    private void updateTotalPrice() {
        int proteinQty = Integer.parseInt(txtProteinQty.getText().toString());
        int barQty = Integer.parseInt(txtBarQty.getText().toString());
        int saladQty = Integer.parseInt(txtSaladQty.getText().toString());
        int juiceQty = Integer.parseInt(txtJuiceQty.getText().toString());
        
        totalPrice = (proteinQty * PROTEIN_PRICE) + (barQty * BAR_PRICE) + 
                     (saladQty * SALAD_PRICE) + (juiceQty * JUICE_PRICE);
        
        txtTotalPrice.setText(String.format("$%.2f", totalPrice));
    }
    
    private void shareNutritionPlan() {
        @SuppressLint("DefaultLocale") String message = "FASTFit Nutrition Plan\n\n" +
                        "Items Selected:\n" +
                        "Protein Shake: " + txtProteinQty.getText().toString() + " x $" + PROTEIN_PRICE + "\n" +
                        "Energy Bar: " + txtBarQty.getText().toString() + " x $" + BAR_PRICE + "\n" +
                        "Salad: " + txtSaladQty.getText().toString() + " x $" + SALAD_PRICE + "\n" +
                        "Juice: " + txtJuiceQty.getText().toString() + " x $" + JUICE_PRICE + "\n\n" +
                        String.format("Total: $%.2f", totalPrice);
        
        Intent shareIntent = new Intent(Intent.ACTION_SEND);
        shareIntent.setType("text/plain");
        shareIntent.putExtra(Intent.EXTRA_TEXT, message);
        startActivity(Intent.createChooser(shareIntent, "Share Nutrition Plan"));
    }
}
