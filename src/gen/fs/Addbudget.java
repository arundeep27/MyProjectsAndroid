package gen.fs;

import java.util.Locale;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Addbudget extends Activity {

String ln,str,str3,str1,str5;
	
	
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.addbudget);
		
		Bundle extras=getIntent().getExtras();
		String ln1=extras.getString("login1").trim();/////////getting username from budget class
		ln=ln1;


Button but=(Button)findViewById(R.id.save);

but.setOnClickListener(new View.OnClickListener() {////////click handler for save button
	
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
	
		String inc;
		
		EditText ed=(EditText)findViewById(R.id.income);
		
		inc = ed.getEditableText().toString();/////// converting the editabletext into string	
		
/////////////////////////////////////////////////////////////////////////////////////////////////////////        
//////////			the above method will connect to database 								/////////////
/////////////////////////////////////////////////////////////////////////////////////////////////////////	 
		
		SQLiteDatabase db1;
		db1 = openOrCreateDatabase("Userdetails.db", SQLiteDatabase.CREATE_IF_NECESSARY, null);
	    db1.setVersion(3);
	    db1.setLocale(Locale.getDefault());
	    db1.setLockingEnabled(true);
		
	    try{
	    	
/////////////////////////////////////////////////////////////////////////////////////////////////////////        
////		the above line will select the Extraincome from the budget table  n store in a string   /////
////	     it will take name of a user who has logged in                                          /////
/////////////////////////////////////////////////////////////////////////////////////////////////////////	 
	    	
	    	Cursor c1=db1.rawQuery("select Extraincome from budget where Username='"+ln+"'",null);
	    	if(c1.moveToNext())
		    {
		    	 str1=c1.getString(0);
		    
		    }
	    	long l1=0;
	    	String in=inc;
	    	l1=Long.parseLong(str1);
	    	long l2=Long.parseLong(in);
	    	long l3=l1+l2;
	    	String str3=""+l3;
	    	
	    	
/////////////////////////////////////////////////////////////////////////////////////////////////////////        
////		the above line will select the Monthlyincome from the details1 table  n store in a string   /////
////	     it will take name of a user who has logged in                                          /////
/////////////////////////////////////////////////////////////////////////////////////////////////////////
	    	
	    	Cursor c3=db1.rawQuery("select Monthlyincome from details1 where Username='"+ln+"'",null);
	    	if(c3.moveToNext())
	    			{
	    		str5=c3.getString(0);
	    			}
	    	
	    	long l7=Long.parseLong(str5);
	    	long l8=l7+l3;
	    	String str8=""+l8;
	    	
	    	ContentValues values = new ContentValues();
	    	values.put("Extraincome",str3);
	    	values.put("Total",str8);   	
	    	
	    	
/////////////////////////////////////////////////////////////////////////////////////////////////////////        
////		Update query for updating the totalincome and extraincome in budget table			    /////
////	     it will take name of a user who will be logged in                                      /////
/////////////////////////////////////////////////////////////////////////////////////////////////////////
	    	
	    	int i=db1.update("budget", values, "Username='"+ln+"'", null);

	    	if(i==1)
	    	{
	    	showDialog(1);
	    	}
	    	else
	    	{
	    		
	    	}
	    }
    	catch(Exception e){ 
    		e.printStackTrace();
	    	
	}
	}
	
	    });
 }
	
/////////////////////////////////////////////////////////////////////////////////////////////////////////        
//////////					   Above methods are responsible for creating a Dialog 	 	         ////////
/////////////////////////////////////////////////////////////////////////////////////////////////////////	 
	
	
 protected Dialog onCreateDialog(int id)
 {
 	//Dialog d=null;
 	AlertDialog.Builder builder=new AlertDialog.Builder(this);
 	builder.setMessage("Successfully stored");
 	builder.setPositiveButton("Ok",new DialogInterface.OnClickListener() {
			
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				
				EditText ed=(EditText)findViewById(R.id.income);
				ed.setText("");
			}
		});
		//builder.setNegativeButton("no",null);
		AlertDialog alert =builder.create();//// creates or displays alert dialog
 	return alert;
 }      
 }
	
	
	
	
	
	
	

