package rainhas.ia.uepb.com.rainhas;

import android.content.Context;

public class Funcoes {

    public static Context context;

    public static int numSolucoes;
    public static int solAtual = 0;

    public static int[] rainha1 = {0,0};
    public static int[] rainha2 = {0,0};

    public static Tabuleiro tabuleiro;
    public static Busca busca;

    public static void iniciaArvore(){
        tabuleiro = new Tabuleiro();
        tabuleiro.gerarRamificacoes();
    }

    public static void gerarBuscas(){
        busca = new Busca();

        busca.rainhasEscolhidas[0][0] = rainha1[0];
        busca.rainhasEscolhidas[0][1] = rainha1[1];
        busca.rainhasEscolhidas[1][0] = rainha2[0];
        busca.rainhasEscolhidas[1][1] = rainha2[1];

        busca.profundidade(tabuleiro);
        //busca.limitada(tabuleiro,3);
        //busca.largura(tabuleiro);
        //busca.iterativa(tabuleiro);

        numSolucoes = busca.profundidade.size();
    }
}
