package com.example.bitpet_mobile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.vishnusivadas.advanced_httpurlconnection.PutData;

public class Login extends AppCompatActivity {

    TextInputEditText textInputEditTextUsername, textInputEditTextPassword;
    Button btnLogin;
    TextView textViewLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        textInputEditTextUsername = findViewById(R.id.username);
        textInputEditTextPassword = findViewById(R.id.password);
        btnLogin = findViewById(R.id.btnLogin);
        textViewLogin = findViewById(R.id.textLogin);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Handler handler = new Handler(Looper.getMainLooper());
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        String username, password;
                        username = String.valueOf(textInputEditTextUsername.getText());
                        password = String.valueOf(textInputEditTextPassword.getText());
                        if(!username.equals("") && !password.equals("")) {

                            String[] field = new String[2];
                            field[0] = "username";
                            field[1] = "password";
                            //Creo arreglo para los datos
                            String[] data = new String[2];
                            data[0] = username;
                            data[1] = password;
                            PutData putData = new PutData("http://bitcodepet.000webhostapp.com/validaUsuario.php", "POST", field, data);
                            if (putData.startPut()) {
                                if (putData.onComplete()) {
                                    String result = putData.getResult();
                                    if(result.equals("OK")){
                                        Intent intent = new Intent(getApplicationContext(), PanelPrincipal.class);
                                        startActivity(intent);
                                        finish();
                                    }
                                    else{
                                        Toast.makeText(getApplicationContext(), result, Toast.LENGTH_SHORT).show();
                                    }
                                }
                            }
                        } else {
                            Toast.makeText(getApplicationContext(), "Todos los campos son requeridos.", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
    }
}