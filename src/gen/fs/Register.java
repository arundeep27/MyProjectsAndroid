package gen.fs;

import java.util.Locale;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/////////////////////////////////////////////////////////////////////////////////////////////////////////        
//////////		Registration class from were user can register								    /////////
/////////////////////////////////////////////////////////////////////////////////////////////////////////	 

public class Register extends Activity {

	
	
	String str1;
	 @Override
	    public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.register);/////////calling the xml file
	        
	        
	        
	        Button b1=(Button)findViewById(R.id.back);
//////////////////////////////////////////////////////////////////////////////////////////	        
///////////////////      click handler for back button in registration page///////////////
//////////////////////////////////////////////////////////////////////////////////////////	        
	        b1.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					Intent intent1=new Intent(v.getContext(), Act1.class);
		 			startActivityForResult(intent1, 0);
					
				}
			});
	         
	        
//////////////////////////////////////////////////////////////////////////////////////////
///////////////////      Type casting the button which  defined in xml file		  ////////
//////////////////////////////////////////////////////////////////////////////////////////	        
	        Button sub=(Button)findViewById(R.id.submit);
	        
//////////////////////////////////////////////////////////////////////////////////////////
///////////////////      click handler for back button in registration page///////////////
//////////////////////////////////////////////////////////////////////////////////////////
	        
	        sub.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View view) {
					// TODO Auto-generated method stub
					
						String First;
						String Last;
						String User;
						String Pass;
						String monthincome;
						String i="0";
						long l;
						
						
						EditText Firstname =(EditText)findViewById(R.id.fn);
						EditText Lastname = (EditText)findViewById(R.id.ln);
						EditText Username = (EditText)findViewById(R.id.un);
					 	EditText Password = (EditText)findViewById(R.id.pass);
					 	EditText moninc = (EditText)findViewById(R.id.monincome);
					 	
					 	
//////////////////////////////////////////////////////////////////////////////////////////
///////////////////      removing extra spaces from the input which the user gives  //////
///////////////////      .trim() method is used									    //////					 	
//////////////////////////////////////////////////////////////////////////////////////////
					 	
					 	
					 	First = Firstname.getEditableText().toString().trim();
					 	Last = Lastname.getEditableText().toString().trim();
					 	Pass = Password.getEditableText().toString().trim();
					 	User = Username.getEditableText().toString().trim();
					 	monthincome = moninc.getEditableText().toString().trim();
					 	
/////////////////////////////////////////////////////////////////////////////////////////////////////////        
//////////				the above method will connect to database 								/////////////
/////////////////////////////////////////////////////////////////////////////////////////////////////////	 
					 	
					 	SQLiteDatabase db1;
					 	db1 = openOrCreateDatabase("Userdetails.db", SQLiteDatabase.CREATE_IF_NECESSARY, null);
				        db1.setVersion(3);
				    	db1.setLocale(Locale.getDefault());
				    	db1.setLockingEnabled(true);
				    	try{
				    		
				    		
/////////////////////////////////////////////////////////////////////////////////////////////////////////        
//////////					in the above line it will only  unique usernames 				/////////////
/////////////////////////////////////////////////////////////////////////////////////////////////////////	 
  	
				    		Cursor c1=db1.rawQuery("select Username from details1 where Username='"+User+"'",null);
					    	if(c1.moveToNext())
						    {
					    		showDialog(1);
						    }
					    	else
					    	{
					    	ContentValues values1 = new ContentValues();
					    	values1.put("Firstname", First);
					    	values1.put("Lastname", Last);
					    	values1.put("Username", User);
					    	values1.put("Password", Pass);
					    	values1.put("Monthlyincome", monthincome);
					    	db1.insert("details1", null, values1);
					    	
					  			l=Long.parseLong(monthincome);	    	
					    	ContentValues values2 = new ContentValues();
					    	values2.put("Netincome", monthincome);
					    	values2.put("Total", monthincome);
					    	values2.put("Username", User);
					    	values2.put("Extraincome", i);
					    	
/////////////////////////////////////////////////////////////////////////////////////////////////////////        
//////////					inserting the new registration details into database			/////////////
/////////////////////////////////////////////////////////////////////////////////////////////////////////	 
					    	
					    	db1.insert("budget", null, values2);
					    	
					    	showDialog(2);
					    	}
					    	}
					    	catch(Exception e)
					    	{
					    	
					    	}
					    	finally
					    	{
					    	}
		 	    	}

			});
	        
	 }
	 
/////////////////////////////////////////////////////////////////////////////////////////////////////////        
//////////	Above methods are responsible for creating a Dialog 	 					         ////////
//////////	depending on the type of parameter  i.e one or two              			         ////////
//////////		depending on the case it will create that  dialog                  		         ////////
/////////////////////////////////////////////////////////////////////////////////////////////////////////	 
	 
	 protected Dialog onCreateDialog(int id)
     {
		 switch(id)
		 {
		 case 1:
			 AlertDialog.Builder builder=new AlertDialog.Builder(this);
		     	builder.setMessage("Username Already Exist");
		     	builder.setPositiveButton("Ok",new DialogInterface.OnClickListener() {
		 			
		 			public void onClick(DialogInterface dialog, int which) {
		 				// TODO Auto-generated method stub
		 				EditText Firstname =(EditText)findViewById(R.id.fn);
		 				Firstname.setText("");
						EditText Lastname = (EditText)findViewById(R.id.ln);
						Lastname.setText("");
						EditText Username = (EditText)findViewById(R.id.un);
						Username.setText("");
					 	EditText Password = (EditText)findViewById(R.id.pass);
						Password.setText("");
					 	EditText Email = (EditText)findViewById(R.id.monincome);
						Email.setText("");
		 			}
		 		});
		 		//builder.setNegativeButton("no",null);
		 		AlertDialog alert =builder.create();
		     	return alert;	 
		 
		 case 2:
			 
     	//Dialog d=null;
     	AlertDialog.Builder builderr=new AlertDialog.Builder(this);
     	builderr.setMessage("Successfully stored");
     	builderr.setPositiveButton("Ok",new DialogInterface.OnClickListener() {
 			
 			public void onClick(DialogInterface dialog, int which) {
 				// TODO Auto-generated method stub
 				EditText Firstname =(EditText)findViewById(R.id.fn);
 				Firstname.setText("");
				EditText Lastname = (EditText)findViewById(R.id.ln);
				Lastname.setText("");
				EditText Username = (EditText)findViewById(R.id.un);
				Username.setText("");
			 	EditText Password = (EditText)findViewById(R.id.pass);
				Password.setText("");
			 	EditText Email = (EditText)findViewById(R.id.monincome);
				Email.setText("");
 			}
 		});
 		//builder.setNegativeButton("no",null);
 		AlertDialog alertt =builderr.create();
     	return alertt;
		  
     	
		 }
		return null;
		 
     }      
 }

