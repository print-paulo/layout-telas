package com.example.layout_telas;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Locale;

public class TelaCalcularComanda extends AppCompatActivity {

    private EditText edtConsumoTotal, edtCouvertArtistico, edtDividirContaPor, edtTaxaServico;
    private EditText edtContaTotal, edtValorPorPessoa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_tela_calcular_comanda);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            // Muda as cores das barras de status
            WindowCompat.getInsetsController(getWindow(), getWindow().getDecorView()).setAppearanceLightStatusBars(false);
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        edtConsumoTotal = findViewById(R.id.edtConsumoTotal);
        edtCouvertArtistico = findViewById(R.id.edtCouvertArtistico);
        edtDividirContaPor = findViewById(R.id.edtDividirContaPor);
        edtTaxaServico = findViewById(R.id.edtTaxaServico);
        edtContaTotal = findViewById(R.id.edtContaTotal);
        edtValorPorPessoa = findViewById(R.id.edtValorPorPessoa);

        findViewById(R.id.btnCalcularContaFinal).setOnClickListener(v -> calcularContaFinal());
    }

    private void calcularContaFinal() {
        try {
            double consumoTotal = Double.parseDouble(edtConsumoTotal.getText().toString().trim());
            double couvertArtistico = Double.parseDouble(edtCouvertArtistico.getText().toString().trim());
            int dividirContaPor = Integer.parseInt(edtDividirContaPor.getText().toString().trim());
            double taxaServico = Double.parseDouble(edtTaxaServico.getText().toString().trim());

            if (dividirContaPor <= 0) {
                Toast.makeText(this, "O número de pessoas deve ser maior que zero", Toast.LENGTH_SHORT).show();
                return;
            }

            double subtotal = consumoTotal + couvertArtistico;
            double valorTaxa = subtotal * (taxaServico / 100.0);
            double contaTotal = subtotal + valorTaxa;
            double valorPorPessoa = contaTotal / dividirContaPor;

            edtContaTotal.setText(String.format(new Locale("pt", "BR"), "%.2f", contaTotal));
            edtValorPorPessoa.setText(String.format(new Locale("pt", "BR"), "%.2f", valorPorPessoa));

        } catch (NumberFormatException e) {
            Toast.makeText(this, "Preencha todos os campos corretamente", Toast.LENGTH_SHORT).show();
        }
    }
}