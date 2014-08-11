package gen.fs;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class Viewbudget extends Activity {
	
	String ln;
	String netincome;
	String available="";
	
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.viewbudget);/// setting the xml or calling the UI
		
		Bundle extras=getIntent().getExtras();
		String ln1=extras.getString("login");///// getting the login name from the Budget class
		String netinc=extras.getString("net");///// getting the netincome from the Budget class
		String availble=extras.getString("avail");///// getting the available balance from the Budget class
		ln=ln1;
		netincome=netinc;
		if(availble==null)
		available="";
		else
			available=availble;
		
		
		TextView tv1=(TextView)findViewById(R.id.net);
		TextView tv2=(TextView)findViewById(R.id.avail);
		
		
		try
		{
			
		tv1.setText(netincome);////////setting the netincome
		tv2.setText(available);////////setting the available income
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
}
}