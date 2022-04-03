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
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class LoginActivity extends AppCompatActivity {

    ImageView backButton;
    EditText Email, Pass;
    TextView registerButton;
    Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

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


        //Botão voltar da tela de login
        backButton = findViewById(R.id.backBtn);

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();

            }
        });
        //Fim

        //Botão registrar tela login
        registerButton = findViewById(R.id.registerBtn);

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent registro = new Intent(LoginActivity.this, RegisterActivity.class);
                LoginActivity.this.startActivity(registro);
                finish();
            }
        });
        //Fim


        btnLogin = findViewById(R.id.loginBtn);
        Email = findViewById(R.id.loginEmail);
        Pass = findViewById(R.id.loginPass);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String email = Email.getText().toString();
                final String pass = Pass.getText().toString();
                Response.Listener<String> resposta = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try{
                            JSONObject jsonResposta = new JSONObject(response);
                            boolean ok = jsonResposta.getBoolean("success");
                            if(ok){
                                String email = jsonResposta.getString("email");
                                String pass = jsonResposta.getString("pass");
                                Intent bemvindo = new Intent(LoginActivity.this, WebviewActivity.class);
                                bemvindo.putExtra("email", email);


                                LoginActivity.this.startActivity(bemvindo);
                                LoginActivity.this.finish();

                            }else{
                                AlertDialog.Builder alerta = new AlertDialog.Builder(LoginActivity.this);
                                alerta.setMessage("Erro no login: Verifique os dados e digite novamente.")
                                        .setNegativeButton("Refazer", null)
                                        .create()
                                        .show();
                            }

                        }catch(JSONException e){
                            e.getMessage();
                        }
                    }
                };

                LoginRequest r = new LoginRequest(email,pass, resposta);
                RequestQueue cola = Volley.newRequestQueue(LoginActivity.this);
                cola.add(r);

            }
        });

    }


}
