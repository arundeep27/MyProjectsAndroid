package gen.fs;


import java.util.Calendar;
import java.util.Locale;

import android.R.integer;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import android.widget.AdapterView.OnItemSelectedListener;

public class Expenses extends Activity{
	
	String lname,date1,first,month,str;
	private int myYear;
    private int myMonth;
    private int myDay;
	static final int ID_DATEPICKER=0;
	static final int DATE_DIALOG_ID =0;
	//Spinner s;
	
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.expense);
      
        Bundle extras=getIntent().getExtras();
		String ln1=extras.getString("login");
		lname=ln1;
        
       // Spinner s =(Spinner)findViewById(R.id.spiner);
		Spinner s =(Spinner)findViewById(R.id.spiner);
         
        Button datePickerButton = (Button)findViewById(R.id.pickDate);
        datePickerButton.setOnClickListener(datePickerButtonOnClickListener);
        
 final String[] barr=getResources().getStringArray(R.array.brancharray);
        
       
/////////////////////////////////////////////////////////////////////////////////////////////////////////        
//////////				To display a dropdownlist we need to create a listview in xml         ///////////
//////////	we are using the default list which is inbuilt in android		             ////////////////	
/////////////////////////////////////////////////////////////////////////////////////////////////////////	 
        
        ArrayAdapter<CharSequence> aas= ArrayAdapter.createFromResource(this,R.array.brancharray,android.R.layout.simple_spinner_item);
        aas.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
       
       s.setAdapter(aas);
  s.setOnItemSelectedListener(new OnItemSelectedListener() {

	@Override
	public void onItemSelected(AdapterView<?> arg0, View v, int position,
			long arg3) {
		// TODO Auto-generated method stub
	
	
	}

	@Override
	public void onNothingSelected(AdapterView<?> arg0) {
		// TODO Auto-generated method stub
		
	}
	  
});
 
  
  Button save=(Button)findViewById(R.id.savecontent);
  
  save.setOnClickListener(new View.OnClickListener() {
	
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
	
		
		Spinner s =(Spinner)findViewById(R.id.spiner);
		String second=s.getSelectedItem().toString();
		
		EditText amt=(EditText)findViewById(R.id.eamount);
		String fourth=amt.getEditableText().toString();//taking input	
		
		Button bt=(Button)findViewById(R.id.pickDate);
		String bt1=bt.getText().toString();
		
		if((fourth.length()<1)||(bt1.equals("Select Date"))||(second.equals("Choose From the Dropdown Menu")))
		{
			showDialog(2);
		}
		else
		{
			
/////////////////////////////////////////////////////////////////////////////////////////////////////////        
//////////	the above method will connect to database 								/////////////
/////////////////////////////////////////////////////////////////////////////////////////////////////////	 
			
			SQLiteDatabase db1;
	    	db1 = openOrCreateDatabase("Userdetails.db", SQLiteDatabase.CREATE_IF_NECESSARY, null);
	    	db1.setVersion(3);
	    	db1.setLocale(Locale.getDefault());
	    	db1.setLockingEnabled(true);   
    	try{
    		ContentValues values = new ContentValues();
    		values.put("month",month);
    		values.put("username",lname);
    		values.put("category",second);
    		values.put("amount",fourth);
    		values.put("date",date1);
    		db1.insert("expenses", null, values);
   		
    		String up="UPDATE budget SET Total=Total-'"+fourth+"' WHERE Username='"+lname+"'";
    		db1.execSQL(up);

    			showDialog(1);
	    	}
	    	catch(Exception e){
	    	e.printStackTrace();
		    	//showDialog(1);
    	}
		}
		}
});
  
  
    }
  private Button.OnClickListener datePickerButtonOnClickListener
  = new Button.OnClickListener()
{

   @Override
       public void onClick(View v) 
        {
             // TODO Auto-generated method stub
              final Calendar c = Calendar.getInstance();
              myYear = c.get(Calendar.YEAR);
              myMonth = c.get(Calendar.MONTH);
              myDay = c.get(Calendar.DAY_OF_MONTH);
              showDialog(ID_DATEPICKER);
         }
};
    
 //@Override
/* Method for alert boxes
 * 
 */

/////////////////////////////////////////////////////////////////////////////////////////////////////////        
//////////Above method are responsible for creating a Dialog 	 which helps in selecting the date   ////
//////////		it is needed for selecting the date 			              			         ////////
////////// 		as well as for displaying alert boxes			            			         ////////
/////////////////////////////////////////////////////////////////////////////////////////////////////////	 


protected Dialog onCreateDialog(int id) 
{
    // TODO Auto-generated method stub
  switch(id)
  {
    case ID_DATEPICKER:

   return new DatePickerDialog(this,myDateSetListener,myYear, myMonth, myDay);
   default:
   return null;
   
   case 1: 
AlertDialog.Builder builder=new AlertDialog.Builder(this);
	builder.setMessage("Successfully stored");////////////displays message
 //	builder.show();
 	builder.setPositiveButton("Ok",new DialogInterface.OnClickListener() {
 	public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				EditText amt=(EditText)findViewById(R.id.eamount);
				amt.setText("");
				Button bt=(Button)findViewById(R.id.pickDate);
				bt.setText("Select Date");
			}
		});
		//builder.setNegativeButton("no",null);
		AlertDialog alert =builder.create();
		return  alert ;
		
   case 2:
	   AlertDialog.Builder builderr=new AlertDialog.Builder(this);
		builderr.setMessage("Please fill all the fields");
	 	builderr.setPositiveButton("Ok",new DialogInterface.OnClickListener() {
	 		
				
				public void onClick(DialogInterface dialog, int which) {
					// TODO Auto-generated method stub
					
				}
			});
			//builder.setNegativeButton("no",null);
			AlertDialog alertt =builderr.create();
			
	 	return  alertt ;
	   
 }
}

//////////it is needed for selecting the date 			/////

private DatePickerDialog.OnDateSetListener myDateSetListener
              = new DatePickerDialog.OnDateSetListener()
  {
      @Override
public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) 
{
            // TODO Auto-generated method stub
      String date = "Year: " + String.valueOf(year) + "\n"
      + "Month: " + String.valueOf(monthOfYear+1) + "\n"
      + "Day: " + String.valueOf(dayOfMonth);
      
      month=String.valueOf(monthOfYear+1);
      
       date1 = String.valueOf(dayOfMonth)+ 
      "-" + String.valueOf(monthOfYear+1) + 
      "-" + String.valueOf(year);

           Button datePickerButton = (Button)findViewById(R.id.pickDate);
           datePickerButton.setText(date1);
} 
 };
    
    
}