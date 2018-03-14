package com.rest.activitys;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.rest.R;
import com.rest.tasks.BitcoinAsyncTask;

import java.util.ArrayList;
import java.util.List;

public class BitcoinActivity extends AppCompatActivity {

    private String moedaSelecionada;
    private TextView edResultado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bitcoin);
        edResultado = (TextView) findViewById(R.id.resultado);

        Spinner spinner = (Spinner) findViewById(R.id.sp_coins);

        final List<String> coins = new ArrayList<String>();
        coins.add("");
        coins.add("BCH");
        coins.add("BTC");
        coins.add("LTC");

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item,
                coins );

        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                moedaSelecionada = coins.get(i);
                edResultado.setText("");
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }

    public void buscar(View view) {

        if (!TextUtils.isEmpty(moedaSelecionada)) {

            try {
                BitcoinAsyncTask rest = new BitcoinAsyncTask();
                String resultado = rest.execute("https://www.mercadobitcoin.net/api/"+moedaSelecionada+"/ticker/").get();

                edResultado.setText(resultado);

            } catch (Exception e) {
                Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
            }

        }
    }
}
