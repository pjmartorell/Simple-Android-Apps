package pj.martorell;

import java.io.File;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Environment;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;


public class MyService extends Service {
	
    private static final String TAG = "SERVEI";
    MediaPlayer mediaPlayer;
    
	/** Called when the activity is first created. */
    @Override
    public void onCreate() {
    	mediaPlayer = new MediaPlayer();
    	Toast.makeText(this, "Creant servei", Toast.LENGTH_SHORT).show();
    	Log.v(TAG, "Creant servei");
    	
    	File sdCard = Environment.getExternalStorageDirectory();
        File song = new File(sdCard.getAbsolutePath() + "/Music/QD! - 08 Es fantasma funky.mp3");
        try{
        	mediaPlayer.setDataSource(song.getAbsolutePath());
        	mediaPlayer.prepare();
        }catch (Exception e) {
			// TODO: handle exception
		}
    }

	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return null;
	}
    
    public int onStartCommand(Intent intent, int flags, int startId){
    	Toast.makeText(this, "Arrancant servei", Toast.LENGTH_SHORT).show();
    	Log.v(TAG, "Arrancant servei");
		
		if(mediaPlayer.isPlaying()) mediaPlayer.pause();
		else mediaPlayer.start();
        return START_STICKY;
    }
    
    public void onDestroy(){
    	Toast.makeText(this, "Finalitzant servei", Toast.LENGTH_SHORT).show();
    	Log.v(TAG, "Finalitzant servei");
    	mediaPlayer.stop();
    	mediaPlayer.release();
    }
}