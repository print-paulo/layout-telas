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

        montarPergunta();
        mostrarPergunta();
    }

    private void montarPergunta() {
        // Pergunta placa de Proibido Estacionar
        perguntas.add(new Pergunta(
                new String[]{"" +
                        "Curva Acentuada a Esquerda", "Sentido Proibido", "Proibido Estacionar","Velocidade Máxima"},
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

    private void mostrarPergunta() {
        respondida = false;
        indiceSelecionado = -1;
        btnProximaPergunta.setEnabled(false);
        switch (perguntaAtual) {
            case 0: {
                txtCurrentQuestion.setText(R.string.questao_1);
                imgPlacaTransito.setImageResource(R.drawable.placa_proibidoestacionar);
                break;
            }
            case 1: {
                txtCurrentQuestion.setText(R.string.questao_2);
                imgPlacaTransito.setImageResource(R.drawable.placa_curvaacentuadaaesquerda);
            }
            case 2: {
                txtCurrentQuestion.setText(R.string.questao_3);
                imgPlacaTransito.setImageResource(R.drawable.placa_proibidoultrapassar);
                break;
            }
            case 3: {
                txtCurrentQuestion.setText(R.string.questao_4);
                imgPlacaTransito.setImageResource(R.drawable.placa_velocidademax);
                break;
            }
            case 4: {
                txtCurrentQuestion.setText(R.string.questao_5);
                imgPlacaTransito.setImageResource(R.drawable.placa_sentidoproibido);
                break;
            }
            default: {
                break;
            }
        }

        Pergunta p = perguntas.get(perguntaAtual);

        for (int i = 0; i < botoesAlternativa.length; i++) {
            botoesAlternativa[i].setText(p.alternativas[i]);
            botoesAlternativa[i].setEnabled(true);
        }
    }

    public void setBtnProximoLayout(View view) {
        // a fazer
    }

    public void setBtnVoltar(View view) {
        finish();
    }
}