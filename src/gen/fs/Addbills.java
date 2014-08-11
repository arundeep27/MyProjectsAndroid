package gen.fs;

import java.util.Calendar;
import java.util.Locale;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;

public class Addbills extends Activity {
	private int myYear;
    private int myMonth;
    private int myDay;
    String lname,date1,first,month,str;
	static final int ID_DATEPICKER=0;
	static final int DATE_DIALOG_ID =0;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.addbills);
	
		Bundle extras=getIntent().getExtras();
		String ln1=extras.getString("login");//// fetching the username 
		lname=ln1;
		
		Button save=(Button)findViewById(R.id.store);
		
		Button datePickerButton = (Button)findViewById(R.id.pickDate);
        datePickerButton.setOnClickListener(datePickerButtonOnClickListener);
		  
		  save.setOnClickListener(new View.OnClickListener() {///// click method 
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
			
				
				EditText s =(EditText)findViewById(R.id.amount);
				String money=s.getEditableText().toString();/////// converting the editabletext into string
				
				EditText description=(EditText)findViewById(R.id.desc);
				String notes=description.getEditableText().toString();/////// converting the editabletext into string	
				
				Button bt=(Button)findViewById(R.id.pickDate);
				String bt1=bt.getText().toString();/////// converting the editabletext into string
				
				if((money.length()<1)||(bt1.equals("Select Date"))||(notes.length()<1))
				{
					showDialog(2);//// displays alert if null input is their in three edittexts
				}
				else
				{
					
/////////////////////////////////////////////////////////////////////////////////////////////////////////        
//////////			the above method will connect to database 								/////////////
/////////////////////////////////////////////////////////////////////////////////////////////////////////	 
					
					SQLiteDatabase db1;
			    	db1 = openOrCreateDatabase("Userdetails.db", SQLiteDatabase.CREATE_IF_NECESSARY, null);
			    	db1.setVersion(3);
			    	db1.setLocale(Locale.getDefault());
			    	db1.setLockingEnabled(true);   
		    	try{
		    		ContentValues values = new ContentValues();
		    		values.put("amount",money);
		    		values.put("username",lname);
		    		values.put("notes",notes);
		    		values.put("date",date1);
		    		db1.insert("bills", null, values);//// inserting the values into the database table
		    		   
		    		showDialog(1);/// displays dialog on successfully storing the values
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
//////////	Above methods are responsible for creating a Dialog 	 					         ////////
//////////	depending on the type of parameter  i.e one or two              			         ////////
//////////		depending on the case it will create that  dialog                  		         ////////
/////////////////////////////////////////////////////////////////////////////////////////////////////////	 
	
	protected Dialog onCreateDialog(int id) 
	{
	    // TODO Auto-generated method stub
	  switch(id)
	  {
	    case ID_DATEPICKER:
	   //Toast.makeText(AddFood.this, 
	  // "- Select Date -", 
	  // Toast.LENGTH_LONG).show();
	   return new DatePickerDialog(this,myDateSetListener,myYear, myMonth, myDay);
	   default:
	   return null;
	   
	   case 1: 
		  		   
	AlertDialog.Builder builder=new AlertDialog.Builder(this);
		builder.setMessage("Successfully stored");
	 //	builder.show();
	 	builder.setPositiveButton("Ok",new DialogInterface.OnClickListener() {
	 	public void onClick(DialogInterface dialog, int which) {
					// TODO Auto-generated method stub
					EditText amt=(EditText)findViewById(R.id.amount);
					amt.setText("");
					EditText description=(EditText)findViewById(R.id.desc);
					description.setText("");
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

/////////////////////////////////////////////////////////////////////////////////////////////////////////        
//////////	Above method are responsible for creating a Dialog 	 which helps in selecting the date   ////
//////////		it is needed for selecting the date 			              			         ////////
/////////////////////////////////////////////////////////////////////////////////////////////////////////	 


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
	     /*
	         Toast.makeText(WritingPrescription.this, date, 
	         Toast.LENGTH_LONG).show();
	     */
	           Button datePickerButton = (Button)findViewById(R.id.pickDate);
	           datePickerButton.setText(date1);
	} 
	 };
}
