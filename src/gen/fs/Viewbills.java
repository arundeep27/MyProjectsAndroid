package gen.fs;

import gen.fs.Grid.griddis;

import java.util.Locale;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class Viewbills extends Activity implements OnItemClickListener{
	
	String str6,lname,str7,str8;
	String str1[]=new String[50],str2[]=new String[50],str3[]=new String[50];

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.grid3);
		
		Bundle extras=getIntent().getExtras();
		lname=extras.getString("login");////getting the username

		
/////////////////////////////////////////////////////////////////////////////////////////////////////////        
//////////the above method will connect to database 								/////////////
/////////////////////////////////////////////////////////////////////////////////////////////////////////	 
		
		
		SQLiteDatabase db;
    	db = openOrCreateDatabase("Userdetails.db", SQLiteDatabase.CREATE_IF_NECESSARY, null);
    	db.setVersion(3);
    	db.setLocale(Locale.getDefault());
    	db.setLockingEnabled(true);   	    	
    	
/////////////////////////////////////////////////////////////////////////////////////////////////////////        
//////////the above line will select the values(notes,amount,date) from the bills              //////////
////////// 					by considering the username                                       ///////////
/////////////////////////////////////////////////////////////////////////////////////////////////////////	 
	    	
    	String sq="select notes,amount,date from bills where Username='"+lname+"'";
    	Cursor c=db.rawQuery(sq, null);
    	int i=0;
    	try
    	{
		while(c.moveToNext())
		{
			str1[i]=c.getString(0);/////storing the retrieved value into a string 
			str2[i]=c.getString(1);/////storing the retrieved value into a string
			str3[i]=c.getString(2);/////storing the retrieved value into a string
			i++;
			
		}
    	}
    	catch (Exception e) {
    		e.printStackTrace();
			// TODO: handle exception
		}
/////////////////////////////////////////////////////////////////////////////////////////////////////
////////		storing the values which are retrieved from the database into an string array  //////
/////////////////////////////////////////////////////////////////////////////////////////////////////
    	
    	
		final String[] str4=new String[i*3]; 
		for(int j=0,k=0;k<i*3;k++)
        {
         if(k%3==0)
         {
         str4[k]=str1[j];
         }
         else if(k%3==1)
         {
          str4[k]=str2[j];        
         }
         else if(k%3==2)
         {
          str4[k]=str3[j];
          j++;
         }
        }

		
/////////////////////////////////////////////////////////////////////////////////////////////////////
//////// 	  arranging the values which are retrieved from the database into an grid	 	   //////
/////////////////////////////////////////////////////////////////////////////////////////////////////
		
		 GridView gridview = (GridView) findViewById(R.id.gridview);
	  
	     gridview.setAdapter(new griddis(this,str4,1));
	     
	     gridview.setOnItemClickListener(new OnItemClickListener() {
	         public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
	        	 if(position%3==2)
	        		{
	        			str7=str4[position];
		        		str8=str4[position-2];
		        			
/////////////////////////////////////////////////////////////////////////////////////////////////////////        
//////////			the above method will connect to database 								/////////////
/////////////////////////////////////////////////////////////////////////////////////////////////////////	 
	        		
	        	    SQLiteDatabase db1;
			    	db1 = openOrCreateDatabase("Userdetails.db", SQLiteDatabase.CREATE_IF_NECESSARY, null);
			    	db1.setVersion(3);
			    	db1.setLocale(Locale.getDefault());
			    	db1.setLockingEnabled(true);   
			    	
/////////////////////////////////////////////////////////////////////////////////////////////////////
////////			deleting the values which are selected by the user from 					/////
////////						the database 		deleting the cleared bills				   //////
/////////////////////////////////////////////////////////////////////////////////////////////////////
	  			   
		    String str112="DELETE FROM bills WHERE username='"+lname+"' and date='"+str7+"'and notes='"+str8+"' ";
		    		db1.execSQL(str112);
	        	 
		    		Toast.makeText(Viewbills.this, "Your bills to be paid has been removed", Toast.LENGTH_SHORT).show();
	        	 
		    		Intent intent1=new Intent(Viewbills.this,Home.class);
					intent1.putExtra("login",lname);
					startActivity(intent1);
	        		}
	        		else
	        		{
	        			Toast.makeText(Viewbills.this, "Please Select Date", Toast.LENGTH_SHORT).show();	
	        		}
	        		
	         }
	     });
	     
	}
	

	     public class griddis  extends BaseAdapter {
		     private Context mContext;
		     String[] grddispitems;
		     int v;
		   
		     public griddis(Context c,String[] displayitems,int s) {
		         
//		      System.out.println("");
		      mContext = c;

		      grddispitems=displayitems;
		      v=s;
		               
		     }

		     public int getCount() {
		         return grddispitems.length;
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
		             if(v==1)
		             {
		             tv.setLayoutParams(new GridView.LayoutParams(70, 30));
		             }
		             else if(v==2)
		             {
		            	 tv.setLayoutParams(new GridView.LayoutParams(100, 30));
		             }
		         }
		         else {
		             tv = (TextView) convertView;
		         }

		         tv.setText(grddispitems[position]);
		         
		         return tv;

		     }
	     }


		@Override
		public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
				long arg3) {
			// TODO Auto-generated method stub
			
		}  }