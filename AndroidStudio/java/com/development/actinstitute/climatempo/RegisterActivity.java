package com.development.actinstitute.climatempo;

import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class RegisterActivity extends AppCompatActivity {

    ImageView backButton;
    EditText Email, Pass;
    Button btnRegistro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        View decorView = getWindow().getDecorView();
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            decorView.setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_IMMERSIVE
                            // Set the content to appear under the system bars so that the
                            // content doesn't resize when the system bars hide and show.
                            | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                            | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                            // Hide the nav bar and status bar
                            | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
        }

        backButton = findViewById(R.id.backBtn);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btnRegistro  = findViewById(R.id.registerBtn);
        Email = findViewById(R.id.registerEmail);
        Pass = findViewById(R.id.registerPass);

        btnRegistro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = Email.getText().toString();
                String pass  = Pass.getText().toString();
                Response.Listener<String> resposta = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try{
                            JSONObject jsonResposta = new JSONObject(response);
                            boolean ok    = jsonResposta.getBoolean("success");

                            if(ok){
                                Intent i = new Intent(RegisterActivity.this, LoginActivity.class);
                                RegisterActivity.this.startActivity(i);
                                RegisterActivity.this.finish();
                            }else{
                                AlertDialog.Builder alerta = new AlertDialog.Builder(RegisterActivity.this);
                                alerta.setMessage("Erro: O nome de usuário que você digitou, já está em uso. ")
                                        .setNegativeButton("Refazer", null)
                                        .create()
                                        .show();
                            }


                        }catch (JSONException e){
                            e.getMessage();
                        }
                    }
                };

                RegisterRequest r = new RegisterRequest(email,pass,resposta);
                RequestQueue cola = Volley.newRequestQueue(RegisterActivity.this);
                cola.add(r);
            }
        });

    }
}
