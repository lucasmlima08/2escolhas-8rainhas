package rainhas.ia.uepb.com.rainhas;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class TelaMenu extends Activity {

    ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu);

        Funcoes.context = this;
        Sound.context = this;
        //Sound.playBackgroundMusic();

        dialog = new ProgressDialog(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //Sound.stopBackgroundMusic();
    }

    @Override
    protected void onStop() {
        super.onStop();
        if(dialog != null)
            dialog.dismiss();
    }

    public void eventosMenu(View view){
        // Barra de Progresso.
        dialog.setCancelable(true);
        dialog.setMessage("Carregando...");
        dialog.show();

        Sound.playSoundEffect("beep");
        Funcoes.iniciaArvore();

        startActivity(new Intent(this, TelaEscolhaRainhas.class));
    }
}
