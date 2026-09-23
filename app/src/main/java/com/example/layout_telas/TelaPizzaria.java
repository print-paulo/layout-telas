package com.example.layout_telas;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Arrays;
import java.util.List;

public class TelaPizzaria extends AppCompatActivity {
    private EditText edtPizzaBacon, edtPizzaMucarela, edtPizzaPeperoni, edtPizzaCalabresa;
    private List<EditText> campos;
    private TextView txtTotal;
    Button btnFazerPedido, btnProximoLayoutPizzaria;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_tela_pizzaria);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            // Muda as cores das barras de status
            WindowCompat.getInsetsController(getWindow(), getWindow().getDecorView()).setAppearanceLightStatusBars(false);
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        edtPizzaBacon = findViewById(R.id.edtPizzaBacon);
        edtPizzaMucarela = findViewById(R.id.edtPizzaMucarela);
        edtPizzaPeperoni = findViewById(R.id.edtPizzaPeperoni);
        edtPizzaCalabresa = findViewById(R.id.edtPizzaCalabresa);
        txtTotal = findViewById(R.id.txtTotal);
        btnFazerPedido = findViewById(R.id.btnFazerPedido);
        btnProximoLayoutPizzaria = findViewById(R.id.btnProximoLayoutPizzaria);

        // Colocando os editTexts em uma lista
        campos = Arrays.asList(edtPizzaBacon, edtPizzaMucarela, edtPizzaPeperoni, edtPizzaCalabresa);

        // Definindo o botão fazer pedido com false
        btnFazerPedido.setEnabled(false);

        // Verificar se pelo menos um dos campos foi preenchido
        TextWatcher watcher = new TextWatcher() {
            @Override
            public void afterTextChanged(Editable editable) {

            }

            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                verificarCampos();
            }
        };

        // Aplicar o mesmo watcher em todos os campos dentro da lista
        for(EditText campo : campos) {
            campo.addTextChangedListener(watcher);
        }

        btnFazerPedido.setOnClickListener(v -> {
            float valorTotal = 0;

            for(EditText campo : campos) {
                String valor = campo.getText().toString().trim();

                if (valor.isEmpty()) {
                    continue;
                }

                int quantidade = Integer.parseInt(valor);

                if (campo == edtPizzaBacon) {
                    valorTotal += (float) (quantidade * 33.99);
                } else if (campo == edtPizzaMucarela) {
                    valorTotal += (float) (quantidade * 31.99);
                } else if (campo == edtPizzaPeperoni) {
                    valorTotal += (float) (quantidade * 32.99);
                } else if (campo == edtPizzaCalabresa) {
                    valorTotal += (float) (quantidade * 29.99);
                }
            }

            txtTotal.setText(String.format("Total R$ %.2f", valorTotal));
        });
    }

    private void verificarCampos() {
        boolean algumPreenchido = false;

        for(EditText campo : campos) {
            if (!campo.getText().toString().trim().isEmpty()) {
                algumPreenchido = true;
                break;
            }
        }

        btnFazerPedido.setEnabled(algumPreenchido);
    }

    public void setBtnProximoLayoutPizzaria(View view) {
        Intent it = new Intent(getApplicationContext(), TelaCalcularComanda.class);
        startActivity(it);
    }
}