package rainhas.ia.uepb.com.rainhas;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

public class TelaEscolhaRainhas extends Activity implements View.OnClickListener {

    LinearLayout tab0, tab1, tab2, tab3, tab4, tab5, tab6, tab7;
    Button botoes[];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tabuleiro_escolha_rainhas);

        Funcoes.context = this;
        Sound.context = this;

        tab0 = (LinearLayout) findViewById(R.id.tab0);
        tab1 = (LinearLayout) findViewById(R.id.tab1);
        tab2 = (LinearLayout) findViewById(R.id.tab2);
        tab3 = (LinearLayout) findViewById(R.id.tab3);
        tab4 = (LinearLayout) findViewById(R.id.tab4);
        tab5 = (LinearLayout) findViewById(R.id.tab5);
        tab6 = (LinearLayout) findViewById(R.id.tab6);
        tab7 = (LinearLayout) findViewById(R.id.tab7);

        iniciarBotoes();
    }

    private void iniciarBotoes(){
        botoes = new Button[64];
        LinearLayout.LayoutParams parametros = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.MATCH_PARENT);
        parametros.weight = 1.0f;
        for (int i=0; i<botoes.length; i++){
            botoes[i] = new Button(this);
            botoes[i].setId(i);
            botoes[i].setBackground(this.getResources().getDrawable(R.drawable.tab0));
            botoes[i].setOnClickListener(this);
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

    private int[] escolha = {-1,-1};
    private int auxPos = 0;
    public void onClick(View view) {
        // Inclui apenas 2 rainhas.
        escolha[auxPos] = view.getId();
        view.setBackground(this.getResources().getDrawable(R.drawable.tab_rainha));
        if (auxPos == 0)
            auxPos = 1;
        else
            auxPos = 0;
        // Reseta as outras casas do tabuleiro.
        for (int i = 0; i < botoes.length; i++)
            if (botoes[i].getId() != escolha[0] && botoes[i].getId() != escolha[1])
                botoes[i].setBackground(this.getResources().getDrawable(R.drawable.tab0));
    }

    public void eventosEscolhaRainhas(View view){
        if (escolha[0] != -1 && escolha[1] != -1 && escolha[0] != escolha[1]){
            processarEscolha();
            startActivity(new Intent(Funcoes.context,TelaTabuleiro.class));
        } else {
            Toast.makeText(this,"Escolha duas Rainhas!",Toast.LENGTH_SHORT).show();
        }
        Sound.playSoundEffect("beep");
    }

    private void processarEscolha() {
        int r1_x = 0, r1_y = 0;
        int r2_x = 0, r2_y = 0;
        // Posição da primeira rainha.
        if (escolha[0]<8) {
            r1_y = 0;
            r1_x = escolha[0];
        } else if (escolha[0]<16) {
            r1_y = 1;
            r1_x = escolha[0]%8;
        } else if (escolha[0]<24) {
            r1_y = 2;
            r1_x = escolha[0]%16;
        } else if (escolha[0]<32){
            r1_y = 3;
            r1_x = escolha[0]%24;
        } else if (escolha[0]<40) {
            r1_y = 4;
            r1_x = escolha[0]%32;
        } else if (escolha[0]<48) {
            r1_y = 5;
            r1_x = escolha[0]%40;
        } else if (escolha[0]<56) {
            r1_y = 6;
            r1_x = escolha[0]%48;
        } else {
            r1_y = 7;
            r1_x = escolha[0]%56;
        }
        // Posição da segunda rainha.
        if (escolha[1]<8) {
            r2_y = 0;
            r2_x = escolha[1];
        } else if (escolha[1]<16) {
            r2_y = 1;
            r2_x = escolha[1]%8;
        } else if (escolha[1]<24) {
            r2_y = 2;
            r2_x = escolha[1]%16;
        } else if (escolha[1]<32){
            r2_y = 3;
            r2_x = escolha[1]%24;
        } else if (escolha[1]<40) {
            r2_y = 4;
            r2_x = escolha[1]%32;
        } else if (escolha[1]<48) {
            r2_y = 5;
            r2_x = escolha[1]%40;
        } else if (escolha[1]<56) {
            r2_y = 6;
            r2_x = escolha[1]%48;
        } else {
            r2_y = 7;
            r2_x = escolha[1]%56;
        }
        Funcoes.solAtual = 0;
        Funcoes.rainha1[0] = r1_x;
        Funcoes.rainha1[1] = r1_y;
        Funcoes.rainha2[0] = r2_x;
        Funcoes.rainha2[1] = r2_y;
        Funcoes.gerarBuscas();
    }
}
