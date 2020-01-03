package com.example.notascompartidas;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.FirebaseException;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthSettings;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class Login extends AppCompatActivity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);
        Button btnOk = findViewById(R.id.btnOk);
        final EditText editText = findViewById(R.id.et01);
        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                testPhoneVerify("57", editText.getText().toString());

            }
        });
    }


    public void testPhoneAutoRetrieve(String indice, String numero) {
        StringBuilder celNumero = new StringBuilder();
        celNumero.append("+");
        celNumero.append(indice);
        celNumero.append(numero);

        String smsCode;

        smsCode = String.valueOf((int) (Math.random() * 878879) + 100000);


        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        FirebaseAuthSettings firebaseAuthSettings = firebaseAuth.getFirebaseAuthSettings();

        firebaseAuthSettings.setAutoRetrievedSmsCodeForPhoneNumber(celNumero.toString(), smsCode);

        PhoneAuthProvider phoneAuthProvider = PhoneAuthProvider.getInstance();
        phoneAuthProvider.verifyPhoneNumber(
                celNumero.toString(),
                60L,
                TimeUnit.SECONDS,
                this,
                new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                    @Override
                    public void onVerificationCompleted(PhoneAuthCredential credential) {
                        startActivity(new Intent(Login.this, MainActivity.class));
                    }

                    @Override
                    public void onVerificationFailed(FirebaseException e) {
                        Toast.makeText(Login.this, "Fallo, reIntente", Toast.LENGTH_LONG);
                    }

                });

    }

    public void testPhoneVerify(String indice, String numero) {
        StringBuilder celNumero = new StringBuilder();
        celNumero.append("+");
        celNumero.append(indice);
        celNumero.append(numero);

        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                celNumero.toString(), 30L , TimeUnit.SECONDS,
                this, new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

                    @Override
                    public void onCodeSent(String verificationId,
                                           PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                        startActivity(new Intent(Login.this, MainActivity.class));
                    }

                    @Override
                    public void onVerificationCompleted(PhoneAuthCredential phoneAuthCredential) {

                    }

                    @Override
                    public void onVerificationFailed(FirebaseException e) {

                    }

                });

    }
}
