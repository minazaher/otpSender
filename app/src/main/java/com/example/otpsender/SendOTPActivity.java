package com.example.otpsender;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.otpsender.databinding.ActivitySendOtpactivityBinding;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class SendOTPActivity extends AppCompatActivity {

    ActivitySendOtpactivityBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySendOtpactivityBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.btnGetOTP.setOnClickListener(v -> {
            String Mobile = binding.etInputMobile.getText().toString().trim();
            if (Mobile.isEmpty()){
                Toast.makeText(SendOTPActivity.this, "Please Enter Mobile Number", Toast.LENGTH_SHORT).show();
                return;
            }
            binding.progressBar.setVisibility(View.VISIBLE);
            binding.btnGetOTP.setVisibility(View.INVISIBLE);

            PhoneAuthProvider.getInstance().verifyPhoneNumber("+20"+Mobile,
                    60,
                    TimeUnit.SECONDS,
                    SendOTPActivity.this,
                    new PhoneAuthProvider.OnVerificationStateChangedCallbacks(){

                        @Override
                        public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
                            binding.progressBar.setVisibility(View.GONE);
                            binding.btnGetOTP.setVisibility(View.VISIBLE);
                        }

                        @Override
                        public void onVerificationFailed(@NonNull FirebaseException e) {
                            binding.progressBar.setVisibility(View.GONE);
                            binding.btnGetOTP.setVisibility(View.VISIBLE);
                            Toast.makeText(SendOTPActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onCodeSent(@NonNull String s, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                            binding.progressBar.setVisibility(View.GONE);
                            binding.btnGetOTP.setVisibility(View.VISIBLE);
                            goToVerificationWithVerificationID(Mobile, s);

                        }
                    } );
        });
    }




    private void goToVerificationWithVerificationID(String Mobile, String Id){
        Intent intent = new Intent(getApplicationContext(), VerifyOTPActivity.class);
        intent.putExtra("mobile", Mobile);
        intent.putExtra("verificationId", Id);
        startActivity(intent);
    }
}