package gen.fs;

import java.util.Calendar;
import java.util.Locale;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class Daywise extends Activity implements OnItemClickListener{
	
	String lname,month,date1,str2[],datepick;
	private int myYear;
    private int myMonth;
    private int myDay;
	static final int ID_DATEPICKER=0;
	Button datePickerButton;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.day);
		
		Bundle extras=getIntent().getExtras();
		String ln1=extras.getString("login");//getting the username
		lname=ln1;
		
		
		///method called when the show transactions button is clicked in day_to-day list///
		Button trans=(Button)findViewById(R.id.showtrans);
			
	        trans.setOnClickListener(new View.OnClickListener() {
				
				public void onClick(View view) {	

					try
					{
						Intent intent=new Intent(Daywise.this,Grid.class);
						intent.putExtra("login",lname);									
					    intent.putExtra("date",date1);	
					    intent.putExtra("way","day");
						startActivity(intent);
					}
					catch (Exception e) {
						// TODO: handle exception
						e.printStackTrace();
					}
					}

	        });
	
	        datePickerButton = (Button)findViewById(R.id.pickDate);
	        
	        
        datePickerButton.setOnClickListener(datePickerButtonOnClickListener);
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
	 }
	}

	
	
/////////////////////////////////////////////////////////////////////////////////////////////////////////        
//////////Above method are responsible for creating a Dialog 	 which helps in selecting the date   ////
//////////it is needed for selecting the date 			              			         ////////
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
	 public class Displaynow  extends BaseAdapter {
	     private Context mContext;
	     String[] itemsdis;
	   
	     public Displaynow(Context c,String[] displayitems) {
	         
	      System.out.println("");
	      mContext = c;

	         itemsdis=displayitems;
	               
	     }

	     public int getCount() {
	         return itemsdis.length;
	     }

	     public Object getItem(int position) {
	         return null;
	     }

	     public long getItemId(int position) {
	         return 0;
	     }

	     // create a new ImageView for each item referenced by the Adapter
	     public View getView(int position, View convertView, ViewGroup parent) {
	         TextView tv;
//	        
	         if (convertView == null) {
	             tv = new TextView(mContext);
	             tv.setLayoutParams(new GridView.LayoutParams(100, 40));
	         }
	         else {
	             tv = (TextView) convertView;
	         }

	         tv.setText(itemsdis[position]);
	         return tv;


	     }

	

}
	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) 
	{
		// TODO Auto-generated method stub
		
	}
	
	}
