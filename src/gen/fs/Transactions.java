package gen.fs;

import java.util.Calendar;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

public class Transactions extends Activity implements OnItemClickListener {
	
	String lname,month;
	
	        ListView list1;
			String[] items = {"Monthly","Day-to-Day"};///////list format
			/** Called when the activity is first created. */
			
			@Override
			public void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
			setContentView(R.layout.transactions);
			
			Bundle extras=getIntent().getExtras();
			String ln1=extras.getString("login");///////getting the values
			lname=ln1;
			
			
/////////////////////////////////////////////////////////////////////////////////////////////////////////        
//////////  			 To display a list we need to create a listview in xml           ////////////////
//////////	we are using the default list which is inbuilt in android		             ////////////////	
/////////////////////////////////////////////////////////////////////////////////////////////////////////	 
			
			list1 = (ListView)findViewById(R.id.lv1);
			list1.setOnItemClickListener(this);
			list1.setAdapter(new ArrayAdapter(this,android.R.layout.simple_list_item_1,items));
			}

			
/////////////////////////////////////////////////////////////////////////////////////////////////////////        
////////// 			click method for the list options                                            ////////
//////////		depending on the selected item it will navigate to that class     				/////////
/////////////////////////////////////////////////////////////////////////////////////////////////////////	 
			
			public void onItemClick(AdapterView<?> arg0, View v, int position, long arg3) {
			switch(position){
			case 0:
				Intent intent=new Intent(Transactions.this,Monthly.class);///////target class
				intent.putExtra("login",lname);/////passing username
				startActivity(intent);/////starting the activity
			break;
			case 1:
				Intent intent1=new Intent(Transactions.this,Daywise.class);///////target class
				intent1.putExtra("login",lname);/////passing the username
				startActivity(intent1);////////////starting the activity
			
			}
			}
			}
	 
