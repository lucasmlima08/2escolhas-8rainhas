package rainhas.ia.uepb.com.rainhas;

import android.content.Context;
import android.media.MediaPlayer;

public class Sound {

    public static Context context;

    public static MediaPlayer soundEffect;

    public static void playSoundEffect(String toque){
        try {
            soundEffect = MediaPlayer.create(context, getEffect(toque, context));
            soundEffect.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    stopSoundEffect();
                    soundEffect = null;
                }
            });
            soundEffect.start();
        } catch (Exception e) {}
    }

    // Retorna o RAW
    public static int getEffect(String toque, Context context){
        int sound = context.getResources().getIdentifier(toque, "raw", context.getPackageName());
        return sound;
    }

    public static void stopSoundEffect(){
        if ((soundEffect != null) && (soundEffect.isPlaying())){
            soundEffect.stop();
            soundEffect.release();
        }
    }
}
