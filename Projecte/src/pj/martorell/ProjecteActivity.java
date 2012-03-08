package pj.martorell;

import android.app.Activity;
import android.app.TabActivity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;

public class ProjecteActivity extends TabActivity {

	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.main);

	    Resources res = getResources(); // Resource object to get Drawables
	    TabHost tabHost = getTabHost();  // The activity TabHost
	    TabHost.TabSpec spec;  // Resusable TabSpec for each tab
	    Intent intent;  // Reusable Intent for each tab

	    // Create an Intent to launch an Activity for the tab (to be reused)
	    intent = new Intent().setClass(this, RankingActivity.class);

	    // Initialize a TabSpec for each tab and add it to the TabHost
	    spec = tabHost.newTabSpec("Ranking").setIndicator("Ranking",
	                      res.getDrawable(R.drawable.ic_tab_ranking))
	                  .setContent(intent);
	    tabHost.addTab(spec);

	    // Do the same for the other tabs
	    intent = new Intent().setClass(this, MemoryActivity.class);
	    spec = tabHost.newTabSpec("Memory").setIndicator("Memory",
	                      res.getDrawable(R.drawable.ic_tab_memory))
	                  .setContent(intent);
	    tabHost.addTab(spec);
	    
	    intent = new Intent().setClass(this, CalcActivity.class);
	    spec = tabHost.newTabSpec("Calc").setIndicator("Calc",
	                      res.getDrawable(R.drawable.ic_tab_calc))
	                  .setContent(intent);
	    tabHost.addTab(spec);

	    intent = new Intent().setClass(this, PlayerActivity.class);
	    spec = tabHost.newTabSpec("Music").setIndicator("Music",
	                      res.getDrawable(R.drawable.ic_tab_player))
	                  .setContent(intent);
	    tabHost.addTab(spec);
	    
	    intent = new Intent().setClass(this, ProfileActivity.class);
	    spec = tabHost.newTabSpec("Perfil").setIndicator("Perfil",
	                      res.getDrawable(R.drawable.ic_tab_profile))
	                  .setContent(intent);
	    tabHost.addTab(spec);

	    tabHost.setCurrentTab(2);
	}
}