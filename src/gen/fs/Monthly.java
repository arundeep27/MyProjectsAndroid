package gen.fs;

import java.util.Calendar;
import java.util.Locale;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Toast;


import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;

public class Monthly extends Activity implements TextWatcher,OnItemClickListener{
	
	String lname,month,date1,str1,str2,str3;

	
/////////////////////////////////////////////////////////////////////////////////////////////////////////        
//////////	 	 		AutoCompleteTextView  helps in predicting the text			             ////////
/////////////////////////////////////////////////////////////////////////////////////////////////////////	 
	
	
	AutoCompleteTextView myAutoComplete;
	String item[]={
	  "January", "February", "March", "April",
	  "May", "June", "July", "August",
	  "September", "October", "November", "December"
	};
	
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.monthly);
		
		Bundle extras=getIntent().getExtras();
		String ln1=extras.getString("login");///getting the username
		lname=ln1;
	

		Button viewtra=(Button)findViewById(R.id.viewtrans);

		  	   myAutoComplete = (AutoCompleteTextView)findViewById(R.id.myautocomplete);
		       myAutoComplete.addTextChangedListener(this);
		       myAutoComplete.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, item));
		       

		       
//////////////////////////////////////////////////////////////////////////////////////////////
/////// click handler for view transactions button in xml file                       /////////		       
//////////////////////////////////////////////////////////////////////////////////////////////		       
	viewtra.setOnClickListener(new View.OnClickListener() {

		
		
		@Override
		public void onClick(View v) {
			
			String textinauto=myAutoComplete.getEditableText().toString();/////// converting to string
			
//////////////////////////////////////////////////////////////////////////////////////////////
/////// the month format in database is stored as like 1,2,3,4,.........12          //////////
///////  but user gives in the format as January,February.........December          //////////
///////                so converting through if loop to 1,2,3...12 values			//////////		
//////////////////////////////////////////////////////////////////////////////////////////////				       
			
			if(textinauto.equals(""))
			{
				Toast.makeText(Monthly.this, "You need to enter the month",Toast.LENGTH_LONG).show();		
			}
			
			if(textinauto.equals("January"))
			{
				str3="1";
				dataGrid();
			}
			else if(textinauto.equals("February"))
			{
				str3="2";
				dataGrid();
			}
			else if(textinauto.equals("March"))
			{
				str3="3";
				dataGrid();
			}
			else if(textinauto.equals("April"))
			{
				str3="4";
				dataGrid();
			}
			else if(textinauto.equals("May"))
			{
				str3="5";
				dataGrid();
			}
			else if(textinauto.equals("June"))
			{
				str3="6";
				dataGrid();
			}
			
			else if(textinauto.equals("July"))
			{
				str3="7";
				dataGrid();
			}
			else if(textinauto.equals("August"))
			{
				str3="8";
				dataGrid();
			}
			else if(textinauto.equals("September"))
			{
				str3="9";
				dataGrid();
			}
			else if(textinauto.equals("October"))
			{
				str3="10";
				dataGrid();
			}
			else if(textinauto.equals("November"))
			{
				str3="11";
				dataGrid();
			}
			else if(textinauto.equals("December"))
			{
				str3="12";
				dataGrid();
			}
		}
	
		
			
		}
	);

	}
	
	
	
		@Override
		public void afterTextChanged(Editable arg0) {
		 // TODO Auto-generated method stub

		}

		@Override
		public void beforeTextChanged(CharSequence s, int start, int count,
		  int after) {
		 // TODO Auto-generated method stub

		}

		@Override
		public void onTextChanged(CharSequence s, int start, int before, int count) {
		 // TODO Auto-generated method stub

		}

		public void dataGrid()
		{
			
			Intent intent=new Intent(Monthly.this,Grid.class);
			intent.putExtra("login",lname);//////passing the username
			intent.putExtra("month",str3);/////////passing the month
			intent.putExtra("way","month");////////////passing a string name as month
			startActivity(intent);/////////starting the intent
	
		}



		@Override
		public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
				long arg3) {
			// TODO Auto-generated method stub
			
		}	
}

	
	
	


