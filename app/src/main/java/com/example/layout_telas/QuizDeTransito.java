package com.example.layout_telas;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
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
    Button btnProximaPergunta, btnProximoLayoutQuiz, btnVoltar;

    // Estado do Quiz
    private final List<Pergunta> perguntas = new ArrayList<>();
    private int perguntaAtual = 0;
    private int indiceSelecionado = -1; // qual botão tá selecionado na hora
    private int pontuacao = 0;
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
        btnProximoLayoutQuiz = findViewById(R.id.btnProximoLayoutQuiz);
        btnVoltar = findViewById(R.id.btnVoltar);

        for (int i = 0; i < botoesAlternativa.length; i++) {
            final int indiceClicado = i;
            botoesAlternativa[i].setOnClickListener(v -> selecionar(indiceClicado));
        }

        // Botão único: 1º clique confirma a resposta, 2º clique avança
        btnProximaPergunta.setOnClickListener(v -> {
            if (!respondida) {
                confirmarResposta();
            } else if (perguntaAtual < perguntas.size() - 1) {
                perguntaAtual++;
                mostrarPergunta();
            } else {
                mostrarPergunta();
            }
        });

        montarPergunta();
        mostrarPergunta();
    }

    private void montarPergunta() {
        // Pergunta placa de Proibido Estacionar
        perguntas.add(new Pergunta(
                new String[]{"Curva Acentuada a Esquerda", "Sentido Proibido", "Proibido Estacionar", "Velocidade Máxima"},
                2
        ));
        // Pergunta placa de Curva Acentuada a Esquerda
        perguntas.add(new Pergunta(
                new String[]{"Proibido Estacionar", "Curva Acentuada a Esquerda", "Proibido Ultrapassar", "Sentido Proibido"},
                1
        ));
        // Pergunta placa de Proibido Ultrapassar
        perguntas.add(new Pergunta(
                new String[]{"Sentido Proibido", "Velocidade Máxima", "Proibido Estacionar", "Proibido Ultrapassar"},
                3
        ));
        // Pergunta da placa de Velocidade Máxima
        perguntas.add(new Pergunta(
                new String[]{"Proibido Ultrapassar", "Proibido Estacionar", "Curva Acentuada a Esquerda", "Velocidade Máxima"},
                3
        ));
        // Pergunta da placa de Sentido Proíbido
        perguntas.add(new Pergunta(
                new String[]{"Sentido proíbido", "Proibido Estacionar", "Velocidade Máxima", "Proibido Ultrapassar"},
                0
        ));
    }

    private void mostrarPergunta() {
        respondida = false;
        indiceSelecionado = -1;
        btnProximaPergunta.setEnabled(false);
        btnProximaPergunta.setText(R.string.responder);

        switch (perguntaAtual) {
            case 0: {
                txtCurrentQuestion.setText(R.string.questao_1);
                imgPlacaTransito.setImageResource(R.drawable.placa_proibidoestacionar);
                break;
            }
            case 1: {
                txtCurrentQuestion.setText(R.string.questao_2);
                imgPlacaTransito.setImageResource(R.drawable.placa_curvaacentuadaaesquerda);
                break;
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
            botoesAlternativa[i].setBackgroundColor(ContextCompat.getColor(this, R.color.black));
        }
    }

    private void selecionar(int indice) {
        indiceSelecionado = indice;
        for (int i = 0; i < botoesAlternativa.length; i++) {
            botoesAlternativa[i].setBackgroundColor(
                    i == indice ? ContextCompat.getColor(
                            this, R.color.selected) : ContextCompat.getColor(this, R.color.black));
        }
        btnProximaPergunta.setEnabled(true);
    }

    private void confirmarResposta() {
        Pergunta p = perguntas.get(perguntaAtual);
        respondida = true;

        for (Button b : botoesAlternativa) {
            b.setEnabled(false); // Não deixa trocar após confirmar
        }

        // Destaca a alternativa correta em verde
        botoesAlternativa[p.indiceCorreta].setBackgroundColor(Color.parseColor("#4CAF50"));

        if (indiceSelecionado == p.indiceCorreta) {
            pontuacao++;
        } else if (indiceSelecionado != -1) {
            // Destaca em vermelho a errada que foi escolhida
            botoesAlternativa[indiceSelecionado].setBackgroundColor(Color.parseColor("#F44336"));
        }

        btnProximaPergunta.setText(perguntaAtual < perguntas.size() - 1
                ? "Próxima Pergunta" : "Reiniciar Quiz");
    }

    public void setBtnProximoLayoutQuiz(View view) {
        Intent it = new Intent(getApplicationContext(), TelaPizzaria.class);
        startActivity(it);
    }

    public void setBtnVoltar(View view) {
        finish();
    }
}