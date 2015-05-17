package rainhas.ia.uepb.com.rainhas;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class TelaTabuleiro extends Activity {

    private LinearLayout tab0, tab1, tab2, tab3, tab4, tab5, tab6, tab7;
    private Button botoes[];
    private Button anterior, proximo;
    private TextView strTabuleiro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tabuleiro);

        Funcoes.context = this;
        Sound.context = this;

        strTabuleiro = (TextView) findViewById(R.id.strTabuleiro);

        anterior = (Button) findViewById(R.id.anterior);
        proximo = (Button) findViewById(R.id.proximo);

        tab0 = (LinearLayout) findViewById(R.id.tabb0);
        tab1 = (LinearLayout) findViewById(R.id.tabb1);
        tab2 = (LinearLayout) findViewById(R.id.tabb2);
        tab3 = (LinearLayout) findViewById(R.id.tabb3);
        tab4 = (LinearLayout) findViewById(R.id.tabb4);
        tab5 = (LinearLayout) findViewById(R.id.tabb5);
        tab6 = (LinearLayout) findViewById(R.id.tabb6);
        tab7 = (LinearLayout) findViewById(R.id.tabb7);

        iniciarTabuleiro();

        if (Funcoes.numSolucoes > 0) {
            strTabuleiro.setText("Tabuleiro "+(Funcoes.solAtual+1)+" de "+Funcoes.numSolucoes);
            rainhasArray(Funcoes.busca.profundidade.get(Funcoes.solAtual));
            anterior.setEnabled(false);
            proximo.setEnabled(true);
        } else {
            strTabuleiro.setText("Não Encontrou Soluções!");
            anterior.setEnabled(false);
            proximo.setEnabled(false);
        }
    }

    private void organizarInterface(){
        for (int i=0; i<botoes.length; i++)
            botoes[i].setBackground(this.getResources().getDrawable(R.drawable.tab0));
        strTabuleiro.setText("Tabuleiro "+(Funcoes.solAtual+1)+" de "+Funcoes.numSolucoes);
        rainhasArray(Funcoes.busca.profundidade.get(Funcoes.solAtual));
    }

    private void iniciarTabuleiro(){
        botoes = new Button[64];
        LinearLayout.LayoutParams parametros = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.MATCH_PARENT);
        parametros.weight = 1.0f;
        for (int i=0; i<botoes.length; i++){
            botoes[i] = new Button(this);
            botoes[i].setId(i);
            botoes[i].setBackground(this.getResources().getDrawable(R.drawable.tab0));
            botoes[i].setLayoutParams(parametros);
            if (i<8)
                tab0.addView(botoes[i]);
            else if (i<16)
                tab1.addView(botoes[i]);
            else if (i<24)
                tab2.addView(botoes[i]);
            else if (i<32)
                tab3.addView(botoes[i]);
            else if (i<40)
                tab4.addView(botoes[i]);
            else if (i<48)
                tab5.addView(botoes[i]);
            else if (i<56)
                tab6.addView(botoes[i]);
            else
                tab7.addView(botoes[i]);
        }
    }

    private void rainhasArray(int[] array){
        for (int i=0; i<array.length; i++)
            incluirRainhasNoTabuleiro(array[i]-1,i);
    }

    private void incluirRainhasNoTabuleiro(int x, int y){
        int aux = 0;
        if (x == 0){
            botoes[y].setBackground(this.getResources().getDrawable(R.drawable.tab_rainha));
        } else if (x == 01) {
            aux = 8*1;
            botoes[aux+y].setBackground(this.getResources().getDrawable(R.drawable.tab_rainha));
        } else if (x == 2) {
            aux = 8*2;
            botoes[aux+y].setBackground(this.getResources().getDrawable(R.drawable.tab_rainha));
        } else if (x == 3) {
            aux = 8*3;
            botoes[aux+y].setBackground(this.getResources().getDrawable(R.drawable.tab_rainha));
        } else if (x == 4) {
            aux = 8*4;
            botoes[aux+y].setBackground(this.getResources().getDrawable(R.drawable.tab_rainha));
        } else if (x == 5) {
            aux = 8*5;
            botoes[aux+y].setBackground(this.getResources().getDrawable(R.drawable.tab_rainha));
        } else if (x == 6) {
            aux = 8*6;
            botoes[aux+y].setBackground(this.getResources().getDrawable(R.drawable.tab_rainha));
        } else if (x == 7) {
            aux = 8*7;
            botoes[aux+y].setBackground(this.getResources().getDrawable(R.drawable.tab_rainha));
        }
    }

    public void eventosTabuleiro(View view){
        Sound.playSoundEffect("beep");
        // Botões
        anterior.setEnabled(true);
        proximo.setEnabled(true);
        // Tabuleiros
        if (view.getId() == R.id.anterior){
            if (Funcoes.solAtual > 0){
                Funcoes.solAtual--;
                organizarInterface();
            }
        } else if (view.getId() == R.id.proximo){
            if (Funcoes.solAtual < Funcoes.numSolucoes-1){
                Funcoes.solAtual++;
                organizarInterface();
            }
        }
        // botoes.
        if (Funcoes.solAtual == 0)
            anterior.setEnabled(false);
        if (Funcoes.solAtual == Funcoes.numSolucoes-1)
            proximo.setEnabled(false);
    }
}
