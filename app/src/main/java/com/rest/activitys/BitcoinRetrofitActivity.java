package com.rest.activitys;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.rest.R;
import com.rest.modelo.Cotacao;
import com.rest.ws.RetrofitConfig;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BitcoinRetrofitActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bitcoin_retrofit);

        Spinner spinner = (Spinner) findViewById(R.id.sp_coins);
        final TextView resultado = (TextView) findViewById(R.id.resultado);

        final List<String> coins = new ArrayList<String>();
        coins.add("");
        coins.add("BTC");
        coins.add("LTC");
        coins.add("BCH");

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_dropdown_item, coins);

        spinner.setAdapter(adapter);


        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                if (position > 0) {

                    String coinSelecionado = coins.get(position);

                    Call<Cotacao> acao = RetrofitConfig.
                            getServiceBitcoin().buscarCotacao(coinSelecionado);


                    final ProgressDialog dialog = new ProgressDialog(BitcoinRetrofitActivity.this);
                    dialog.setMessage("Aguarde");
                    dialog.show();

                    acao.enqueue(new Callback<Cotacao>() {
                        @Override
                        public void onResponse(Call<Cotacao> call, Response<Cotacao> response) {
                            Cotacao cotacao = response.body();
                            resultado.setText(cotacao.toString());
                            dialog.dismiss();
                        }

                        @Override
                        public void onFailure(Call<Cotacao> call, Throwable t) {
                            dialog.dismiss();
                            Toast.makeText(BitcoinRetrofitActivity.this,
                                    "Erro " + t.getMessage(),
                                    Toast.LENGTH_SHORT).show();
                        }
                    });


                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }

}
