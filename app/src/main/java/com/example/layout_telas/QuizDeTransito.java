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

import java.util.ArrayList;
import java.util.List;

public class QuizDeTransito extends AppCompatActivity {
    // Modelo de pergunta
    private static class Pergunta {
        String[] alternativas;
        int indiceCorreta; // 0 a 3

        Pergunta(String[] alternativas, int indiceCorreta) {
            this.alternativas = alternativas;
            this.indiceCorreta = indiceCorreta;
        }
    }

    // Views
    TextView txtCurrentQuestion;
    ImageView imgPlacaTransito;
    Button[] botoesAlternativa;
    Button btnProximaPergunta, btnProximoLayout, btnVoltar;

    // Estado do Quiz
    private List<Pergunta> perguntas = new ArrayList<>();
    private int perguntaAtual = 0;
    private int indiceSelecionado = -1; // qual botão tá selecionado na hora
    private boolean respondida = false;

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
        imgPlacaTransito = findViewById(R.id.imgPlacaTransito);
        botoesAlternativa = new Button[]{
                findViewById(R.id.btnOption1),
                findViewById(R.id.btnOption2),
                findViewById(R.id.btnOption3),
                findViewById(R.id.btnOption4)
        };
        btnProximaPergunta = findViewById(R.id.btnProximaPergunta);
        btnProximoLayout = findViewById(R.id.btnProximoLayout);
        btnVoltar = findViewById(R.id.btnVoltar);
    }

    private void montarPergunta() {
        // Pergunta placa de Proibido Estacionar
        perguntas.add(new Pergunta(
                new String[]{"PCurva Acentuada a Esquerda", "Sentido Proibido", "Proibido Estacionar","Velocidade Máxima"},
                2
        ));
        // Pergunta placa de Curva Acentuada a Esquerda
        perguntas.add(new Pergunta(
                new String[]{"Proibido Estacionar", "Curva Acentuada a Esquerda", "Proibido Ultrapassar","Sentido Proibid"},
                1
        ));
        // Pergunta placa de Proibido Ultrapassar
        perguntas.add(new Pergunta(
                new String[]{"Proibido Estacionar", "Sentido Proibido", "Proibido Estacionar","Proibido Ultrapassar"},
                3
        ));
        // Pergunta da placa de Velocidade Máxima
        perguntas.add(new Pergunta(
                new String[]{"Proibido Ultrapassar", "Proibido Estacionar", "Curva Acentuada a Esquerda","Velocidade Máxima"},
                3
        ));
        // Pergunta da placa de Sentido Proíbido
        perguntas.add(new Pergunta(
                new String[]{"Sentido proíbido", "Sentido Estacionar", "Velocidade Máxima","Proibido Ultrapassar"},
                0
        ));
    }

    public void setBtnProximoLayout(View view) {
        // a fazer
    }

    public void setBtnVoltar(View view) {
        finish();
    }
}