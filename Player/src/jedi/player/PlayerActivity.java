package jedi.player;

import java.io.File;

import android.app.Activity;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Environment;


// Simple unique song player
public class PlayerActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        MediaPlayer mediaPlayer = new MediaPlayer();
        File sdCard = Environment.getExternalStorageDirectory();
        File song = new File(sdCard.getAbsolutePath() + "/Music/QD! - 08 Es fantasma funky.mp3");
        try{
        	mediaPlayer.setDataSource(song.getAbsolutePath());
        	mediaPlayer.prepare();
        }catch (Exception e) {
			// TODO: handle exception
		}
        
        mediaPlayer.start();
//        mediaPlayer.pause();
//        mediaPlayer.stop();
//        mediaPlayer.release();
    }
}