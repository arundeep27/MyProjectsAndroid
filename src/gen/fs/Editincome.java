package gen.fs;

import java.util.Calendar;
import java.util.Locale;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Editincome extends Activity{
	 	
	    EditText amt;
	    String ln,date1;
	    String first,str5,str6,str7,str8;
		String second,month;
		String third;
		static final int DATE_DIALOG_ID =0;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.newincome);
		
		
		Bundle extras=getIntent().getExtras();
		String ln1=extras.getString("login");
		ln=ln1;
		
		 amt=(EditText)findViewById(R.id.incomenew);
		

	        
	        
        Button b1=(Button)findViewById(R.id.save);

        b1.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
					
								
				first=amt.getEditableText().toString();
				
				SQLiteDatabase db1;
		        db1 = openOrCreateDatabase("Userdetails.db", SQLiteDatabase.CREATE_IF_NECESSARY, null);
		        db1.setVersion(3);
		    	db1.setLocale(Locale.getDefault());
		    	db1.setLockingEnabled(true);
		    	try{
		    		ContentValues values = new ContentValues();
		    		values.put("Monthlyincome",first);
		    		
		    		int i=db1.update("details1", values, "Username='"+ln+"'", null);
		    		
//		    		Cursor c3=db1.rawQuery("select Netincome from budget where Username='"+ln+"'",null);
//			    	if(c3.moveToNext())
//			    			{
//			    		str5=c3.getString(0);
//			    			}
//			    	long l1=Long.parseLong(str5);
//			    	long l2=Long.parseLong(first);
//			    	long l3=l1+l2;
//			    	String str6=""+l3;
			    	
			    	ContentValues value1=new ContentValues();
			    	value1.put("Netincome",first);
			    	
			    	int j=db1.update("budget", value1, "Username='"+ln+"'", null);
			    	
			    	Cursor c3=db1.rawQuery("select Total from budget where Username='"+ln+"'",null);
			    	if(c3.moveToNext())
			    			{
			    		str8=c3.getString(0);
			    			}
			    	long l4=Long.parseLong(str8);
			    	long l5=Long.parseLong(first);
			    	long l6=l4+l5;
			    	str7=""+l6;
			    	
			    	ContentValues value2=new ContentValues();
			    	value2.put("Total",str7);
			    	
			    	int k=db1.update("budget", value2, "Username='"+ln+"'", null);
			    	
			    	
			    	showDialog(1);
			    	}
			    	catch(Exception e){
			    		e.printStackTrace();
			    	
				    	
		    	}
 	    	}

	});
	}
	


    //@Override
   /* Method for alert boxes
    * 
    */
protected Dialog onCreateDialog(int id) 
 {
       // TODO Auto-generated method stub
     switch(id)
     {
      
      case 1: 
AlertDialog.Builder builder=new AlertDialog.Builder(this);
   	builder.setMessage("Income Updated");
    //	builder.show();
    	builder.setPositiveButton("Ok",new DialogInterface.OnClickListener() {
    		
			
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				EditText amt=(EditText)findViewById(R.id.incomenew);
				amt.setText("");
				
			}
		});
		//builder.setNegativeButton("no",null);
		AlertDialog alert =builder.create();
		
    	return  alert ;
    }
     return null;
 }
}





		
        



             
        

   



        
        
	    



