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
import android.webkit.WebChromeClient.CustomViewCallback;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;


/////////////////////////////////////////////////////////////////////////////////////////////////////////        
//////////   This class will executed when the user clicks on Budget option from the list format ////////
//////////                                from home page                                 		/////////
/////////////////////////////////////////////////////////////////////////////////////////////////////////	 

public class Budget extends Activity implements OnItemClickListener {
	 
	 String lname;
	 String str,str1;

	 
	   ListView list1;
		String[] items = {"View Budget","Add Budget"};/// displaying the list with two options
		/** Called when the activity is first created. */
		@Override
		public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.lay);
		
/////////////////////////////////////////////////////////////////////////////////////////////////////////        
//////////				Getting the username from the Budget class                       ////////////////
/////////////////////////////////////////////////////////////////////////////////////////////////////////	 
		
		Bundle extras=getIntent().getExtras();
		String ln1=extras.getString("login");
		lname=ln1;
		
/////////////////////////////////////////////////////////////////////////////////////////////////////////        
//////////To display a list we need to create a listview in xml           ////////////////
//////////we are using the default list which is inbuilt in android		             ////////////////	
/////////////////////////////////////////////////////////////////////////////////////////////////////////	 
		
		list1 = (ListView)findViewById(R.id.lv1);
		list1.setOnItemClickListener(this);
		list1.setAdapter(new ArrayAdapter(this,android.R.layout.simple_list_item_1,items));
		}
		
/////////////////////////////////////////////////////////////////////////////////////////////////////////        
//////////								click method for the list options                        ////////
//////////     			depending on the selected item it will navigate to that class  			/////////
/////////////////////////////////////////////////////////////////////////////////////////////////////////	 

	@Override
	public void onItemClick(AdapterView<?> arg0, View v, int position, long arg3) {
		// TODO Auto-generated method stub
			
		
		switch (position) {
		case 0:
			
/////////////////////////////////////////////////////////////////////////////////////////////////////////        
//////////	the above method will connect to database 								/////////////
/////////////////////////////////////////////////////////////////////////////////////////////////////////	 
			
		SQLiteDatabase db2;
			db2=openOrCreateDatabase("Userdetails.db",SQLiteDatabase.CREATE_IF_NECESSARY, null);
			db2.setVersion(3);
			db2.setLocale(Locale.getDefault());
			db2.setLockingEnabled(true);
			
			try
			{

				
/////////////////////////////////////////////////////////////////////////////////////////////////////////        
////	the above line will select the Monthlyincome from the details table  n store in a string   //////
////     it will take name of a user who has logged in                                              /////
/////////////////////////////////////////////////////////////////////////////////////////////////////////	 
				
				Cursor c=db2.rawQuery("select Monthlyincome from details1 where Username='"+lname+"'",null);
		    	if(c.moveToNext())
		    	{
				 str=c.getString(0);
				 				
		    	}
/////////////////////////////////////////////////////////////////////////////////////////////////////////        
//////////		the above line will select the Total from the budgettable  n store in a string    ///////
////		     it will take name of a user who has logged in                                      /////
/////////////////////////////////////////////////////////////////////////////////////////////////////////
		    	Cursor c1=db2.rawQuery("select Total from budget where Username='"+lname+"'",null);
		    	if(c1.moveToNext())
		    	{
				 str1=c1.getString(0);
				 			
		    	}		  
			}
			catch (Exception e) {
				e.printStackTrace();
				// TODO: handle exception
				
				
				
			}
			Intent intent=new Intent(v.getContext(), Viewbudget.class);
			intent.putExtra("net",str);////passsing the netincome to viewbudget class
			intent.putExtra("avail",str1);////passsing the available balance to viewbudget class
			intent.putExtra("login",lname);////passsing the login name to viewbudget class
			startActivity(intent);/// starting the activity
			break;
		case 1:
			Intent intent1=new Intent(v.getContext(),Addbudget.class);
			intent1.putExtra("login1",lname);////passsing the login name to viewbudget class
			startActivity(intent1);/// starting the activity
			break;
		
		
		}
	}
	}

	 

