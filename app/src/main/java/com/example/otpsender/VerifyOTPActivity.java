package com.example.otpsender;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.otpsender.databinding.ActivityVerifyOtpactivityBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.Firebase;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

public class VerifyOTPActivity extends AppCompatActivity {
    ActivityVerifyOtpactivityBinding binding;
    String mobile, verificationId, code ="";
    EditText[] digitInputs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityVerifyOtpactivityBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        
        setupOTPInputs();
        
        mobile = getIntent().getStringExtra("mobile");
        verificationId = getIntent().getStringExtra("verificationId");
        
        binding.tvMobile.setText(String.format("+20 %s", mobile));
        binding.btnVerifyOTP.setOnClickListener(v -> {

            for (EditText digitInput : digitInputs) {
                String currentDigit= digitInput.getText().toString().trim();
                if (currentDigit.isEmpty()) {
                    Toast.makeText(VerifyOTPActivity.this, "Please Enter Valid Code", Toast.LENGTH_SHORT).show();
                    code = "";
                    return;
                }
                else
                    code = code.concat(currentDigit);

            }

            if (verificationId != null){
                binding.progressBar.setVisibility(View.VISIBLE);
                binding.btnVerifyOTP.setVisibility(View.INVISIBLE);
                PhoneAuthCredential phoneAuthCredential = PhoneAuthProvider.getCredential(verificationId,code);
                FirebaseAuth.getInstance()
                        .signInWithCredential(phoneAuthCredential)
                        .addOnCompleteListener(task -> Toast.makeText(VerifyOTPActivity.this, "Verified!", Toast.LENGTH_SHORT).show())
                        .addOnFailureListener(e -> Toast.makeText(VerifyOTPActivity.this, "The Code You Entered is Not Valid", Toast.LENGTH_SHORT).show());
            }
        });

        
    }

    private void setupOTPInputs(){
        digitInputs = new EditText[]{
                binding.etInputCode1,
                binding.etInputCode2,
                binding.etInputCode3,
                binding.etInputCode4,
                binding.etInputCode5,
                binding.etInputCode6
        };
        
        for (int i = 0; i < digitInputs.length-1; i++){
            int finalI = i;
            digitInputs[i].addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    if (!s.toString().trim().isEmpty())
                        digitInputs[finalI +1].requestFocus();
                }

                @Override
                public void afterTextChanged(Editable s) {

                }
            });
        }
    }
    
    
}