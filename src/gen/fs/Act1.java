package gen.fs;
import java.util.Date;
import java.util.Locale;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Debug;
import android.provider.Settings.System;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


/////////////////////////////////////////////////////////////////////////////////////////////////////////        
//////////    Main class from were our execution starts								    /////////
/////////////////////////////////////////////////////////////////////////////////////////////////////////	 



public class Act1 extends Activity {
	
	
	String Uname;
	
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        
/////////////////////////////////////////////////////////////////////////////////////////////////////////        
//////////		 setContentView(R.layout.main);  this method helps in adding the ui part which we
/////////                developed in the XML file 			                                    /////////
/////////////////////////////////////////////////////////////////////////////////////////////////////////	 
        
        
        setContentView(R.layout.main);
        
        
//////////////////////////////////////////////////////////////////////////////////////////
///////////////////      Type casting the button which  defined in xml file		  ////////
//////////////////////////////////////////////////////////////////////////////////////////
        
        Button b = (Button)findViewById(R.id.login);
        b.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View view) {
				EditText username = (EditText)findViewById(R.id.et1);
			 	EditText password = (EditText)findViewById(R.id.et2);
/////////////////////////////////////////////////////////////////////////////////////////////////////////        
//////////		 in the above two lines converting the username and password to string            ///////
/////////                and .trim()   is responsible for removing extra spaces               /////////// 			                                    /////////
/////////////////////////////////////////////////////////////////////////////////////////////////////////	 
			 	
			 	Uname = username.getEditableText().toString().trim();
			 	String UPassword = password.getEditableText().toString().trim();
			 	
/////////////////////////////////////////////////////////////////////////////////////////////////////////        
//////////		 in the above line we are checking we are providing any input in the textviews    ///////
/////////                or not if their is null input it will show dialog                        ///////			                                    /////////
/////////////////////////////////////////////////////////////////////////////////////////////////////////	 
			 	
			 	if(username.length()<1 || password.length()<1)
				{
			 		showDialog(1);
/////////////////////////////////////////////////////////////////////////////////////////////////////////        
//////////		the above method will call Dialog which is taking parameter 1 				/////////////
////////////  				 alert box will be displayed                                    /////////////
/////////////////////////////////////////////////////////////////////////////////////////////////////////	 
	 			 		
			 		
				}
			 	
			 	else{
/////////////////////////////////////////////////////////////////////////////////////////////////////////        
//////////			the above method will connect to database 								/////////////
/////////////////////////////////////////////////////////////////////////////////////////////////////////	 
			 				 	
			 	SQLiteDatabase db;
		    	db = openOrCreateDatabase("Userdetails.db", SQLiteDatabase.CREATE_IF_NECESSARY, null);
		    	db.setVersion(3);
		    	db.setLocale(Locale.getDefault());
		    	db.setLockingEnabled(true);   	    	
		    	
/////////////////////////////////////////////////////////////////////////////////////////////////////////        
//////////		the above line will select the values(username and password) from the table    //////////
/////////////////////////////////////////////////////////////////////////////////////////////////////////	 

		    	
		    	Cursor c=db.rawQuery("select Username,Password from details1 where Username='"+Uname+"' and Password='"+UPassword+"'",null);	    	
	    		
	            if(c.moveToNext())
	            {	    		
/////////////////////////////////////////////////////////////////////////////////////////////////////////        
//////////			if username and password matches it will navigate to home page             //////////
/////////////////////////////////////////////////////////////////////////////////////////////////////////	 
	          
	          // pd.setMessage("Loading Please Wait......");	
	        	ProgressDialog pd;
	        	
	       	 pd=new ProgressDialog(Act1.this);
	       	 pd.setMessage("Loading....");
	       	 pd.setProgressStyle(pd.STYLE_SPINNER);
	       	 pd.show();
		    		Intent intent=new Intent(view.getContext(), Home.class);
		    		intent.putExtra("login",Uname);
		    		startActivity(intent);
		    		
		    		Date date=new Date();
		    		int i=date.getDay();
		    		int j=date.getMonth();
		    		int k=date.getYear();
/////////////////////////////////////////////////////////////////////////////////////////////////////////        
//////////			getting the current system date,month and year into a string	           //////////
/////////////////////////////////////////////////////////////////////////////////////////////////////////	 
			    		
		    		String date1=i+"-"+j+"-"+k;
		    		
		    		Cursor c1=db.rawQuery("select notes from bills where username='"+Uname+"' and date!='"+date1+"'",null);
		    		if(c1.moveToNext())
		    		{
/////////////////////////////////////////////////////////////////////////////////////////////////////////        
//////////		if any uncleared bills are their it will prompt the user for checking the bills  ////////
//////////                  which are needed to be paid				       					/////////////		    			
/////////////////////////////////////////////////////////////////////////////////////////////////////////	 

		    			Toast t=new Toast(Act1.this);
		    			t.setDuration(10000);
		    			Toast.makeText(Act1.this, "You have got some uncleared bills go through view bills for more details",Toast.LENGTH_LONG).show();
		    			
		    			
/////////////////////////////////////////////////////////////////////////////////////////////////////////        
//////////		Toast is simple alert message which tells some action needed to be done          ////////
/////////////////////////////////////////////////////////////////////////////////////////////////////////	 
    			
		    		}
		    		
		    		
		    			    		
	            	}
	            else
	            {
	            	showDialog(2);
	            }
						
						
					
		    		
	                       
		    				    	
			 	}}
			
		});
        
        Button b1 = (Button)findViewById(R.id.register);
/////////////////////////////////////////////////////////////////////////////////////////////////////////        
//////////when the user clicks on the getregister button it will navigate to registration page   ////////
/////////////////////////////////////////////////////////////////////////////////////////////////////////	 
     
        
        b1.setOnClickListener(new View.OnClickListener() {
 		
 		public void onClick(View v) {
/////////////////////////////////////////////////////////////////////////////////////////////////////////        
//////////	Intent is like an new class which is needed to be started					         ////////
//////////	Which takes two parameters one is current class and target class			         ////////
//////////	start activity is responsible for starting the target class      			         ////////
/////////////////////////////////////////////////////////////////////////////////////////////////////////	 
 				
 			Intent intent1=new Intent(v.getContext(), Register.class);
 			startActivity(intent1);
 			
 		}
 	});
     }
    
	

    
    
/////////////////////////////////////////////////////////////////////////////////////////////////////////        
//////////   Above methods are responsible for creating a Dialog 	 					         ////////
//////////   depending on the type of parameter  i.e one or two              			         ////////
//////////   depending on the case it will create that  dialog                  		         ////////
/////////////////////////////////////////////////////////////////////////////////////////////////////////	 
   
    
    
    protected Dialog onCreateDialog(int id)
    {

switch(id)
{

case 1:
    	//Dialog d=null;
    	AlertDialog.Builder builder=new AlertDialog.Builder(this);
    	builder.setMessage("All fields are mandatory");
    	builder.setPositiveButton("Ok",new DialogInterface.OnClickListener() {
			
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				EditText et=(EditText)findViewById(R.id.et1);
				et.setText("");
				et=(EditText)findViewById(R.id.et2);
				et.setText("");
				
			}
		});
		//builder.setNegativeButton("no",null);
		AlertDialog alert =builder.create();
    	return alert;
case 2:
	//Dialog d=null;
	AlertDialog.Builder builderr=new AlertDialog.Builder(this);
	builderr.setMessage("Invalid Credentials");
	builderr.setPositiveButton("Ok",new DialogInterface.OnClickListener() {
		
		public void onClick(DialogInterface dialog, int which) {
			// TODO Auto-generated method stub
			EditText et=(EditText)findViewById(R.id.et1);
			et.setText("");
			et=(EditText)findViewById(R.id.et2);
			et.setText("");
			
		}
	});
	//builder.setNegativeButton("no",null);
	AlertDialog alertt =builderr.create();
	return alertt;  	
    	

}
return null;
    }
    
}