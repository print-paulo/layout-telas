package com.example.layout_telas;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowCompat;
import androidx.core.view.WindowInsetsCompat;

public class QuizDeTransito extends AppCompatActivity {
    TextView txtCurrentQuestion, txtQuestion;
    ImageView imgPlacaTransito;
    Button btnOption1, btnOption2, btnOption3, btnOption4, btnProximaPergunta, btnProximoLayout, btnVoltar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_quiz_de_transito);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            // Muda as cores das barras de status
            WindowCompat.getInsetsController(getWindow(), getWindow().getDecorView()).setAppearanceLightStatusBars(false);
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        // Definição de variáveis do layout
        txtCurrentQuestion = findViewById(R.id.txtCurrentQuestion);
        txtQuestion = findViewById(R.id.txtQuestion);
        imgPlacaTransito = findViewById(R.id.imgPlacaTransito);
        btnOption1 = findViewById(R.id.btnOption1);
        btnOption2 = findViewById(R.id.btnOption2);
        btnOption3 = findViewById(R.id.btnOption3);
        btnOption4 = findViewById(R.id.btnOption4);
        btnProximaPergunta = findViewById(R.id.btnProximaPergunta);
        btnProximoLayout = findViewById(R.id.btnProximoLayout);
        btnVoltar = findViewById(R.id.btnVoltar);
    }

    public void setBtnProximoLayout(View view) {
        // a fazer
    }

    public void setBtnVoltar(View view) {
        finish();
    }
}