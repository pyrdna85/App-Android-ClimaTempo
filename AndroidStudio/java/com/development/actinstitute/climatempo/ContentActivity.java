package com.development.actinstitute.climatempo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.development.actinstitute.climatempo.R;

public class ContentActivity extends AppCompatActivity {
    TextView mensagem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content);
        mensagem = findViewById(R.id.mensagem);
        Intent i = this.getIntent();
        String email = i.getStringExtra("email");
        mensagem.setText(mensagem.getText()+" "+email+"");
    }
}
