package gen.fs;

import gen.fs.Daywise.Displaynow;

import java.util.Locale;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

public class Grid extends Activity{
String str1[]=new String[50],str2[]=new String[50],str3[]=new String[50];
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		Bundle extras=getIntent().getExtras();
		String lname=extras.getString("login");
		String date1=extras.getString("date");
		String way=extras.getString("way");
		String mon=extras.getString("month");
	
		super.onCreate(savedInstanceState);
		//setContentView(R.layout.grid1);
		{
			if(way.endsWith("month"))
			{
/////////////////////////////////////////////////////////////////////////////////////////////////////////        
//////////		the above method will connect to database 								/////////////
/////////////////////////////////////////////////////////////////////////////////////////////////////////	 
				
				setContentView(R.layout.grid2);
				SQLiteDatabase db1;
		    	db1 = openOrCreateDatabase("Userdetails.db", SQLiteDatabase.CREATE_IF_NECESSARY, null);
	 	    	db1.setVersion(3);
		    	db1.setLocale(Locale.getDefault());
		    	db1.setLockingEnabled(true);   
		    	
///////////////////////////////////////////////////////////////////////////////////////////////////
/////////// retrieving query for retrieving some values from expenses table                //////// 		    	
///////////////////////////////////////////////////////////////////////////////////////////////////	
		    	
		    	String sq="select category,amount,date from expenses where username='"+lname+"' and month='"+mon+"'";
		    	Cursor c=db1.rawQuery(sq, null);
		    	int i=0;
		    	try
		    	{
				while(c.moveToNext())
				{
					str1[i]=c.getString(0);//////getting the values and storing into an string array
					str2[i]=c.getString(1);//////getting the values and storing into an string array
					str3[i]=c.getString(2);//////getting the values and storing into an string array
					i++;
					
				}
		    	}
		    	catch (Exception e) {
		    		e.printStackTrace();
					// TODO: handle exception
				}
		    	
///////////////////////////////////////////////////////////////////////////////////////////////////
///////////		 getting all the values into an different strings  array	            ///////////
///////////      first string array consist of 	category values							///////////
///////////      second string array consist of amount values						    ///////////		    	
///////////      third string array consist of 	date 		  						    ///////////		    	
///////////////////////////////////////////////////////////////////////////////////////////////////	
		    	
				String[] str4=new String[i*3]; 
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
				
				 GridView gridview = (GridView) findViewById(R.id.gridview);
			  
			     gridview.setAdapter(new griddis(this,str4,1));
				
			
			
			}
		
		else if(way.endsWith("day"))
			{
			setContentView(R.layout.grid1);
			SQLiteDatabase db1;
	    	db1 = openOrCreateDatabase("Userdetails.db", SQLiteDatabase.CREATE_IF_NECESSARY, null);
 	    	db1.setVersion(3);
	    	db1.setLocale(Locale.getDefault());
	    	db1.setLockingEnabled(true);   
	    	
	    	    	
	    	String sq1="select category,amount from expenses where username='"+lname+"' and date='"+date1+"'";
	    	Cursor c=db1.rawQuery(sq1, null);
	    	int i=0;
	    	try
	    	{
			while(c.moveToNext())
			{
				str1[i]=c.getString(0);
				str2[i]=c.getString(1);
				i++;
				
			}
	    	}
	    	catch (Exception e) {
	    		e.printStackTrace();
				// TODO: handle exception
			}
	    	
///////////////////////////////////////////////////////////////////////////////////////////////////
///////////		 getting all the values into an different strings  array	            ///////////
///////////      first string array consist of 	category values							///////////
///////////      second string array consist of amount values						    ///////////
///////////////////////////////////////////////////////////////////////////////////////////////////	
			    	
			String[] str3=new String[i*2]; 
			for(int j=0,k=0;k<i*2;k++)
	        {
	         if(k%2==0)
	         {

	         str3[k]=str1[j];
	         
	         
	         }
	         else if(k%2==1)
	         {
	          str3[k]=str2[j];
	          j++;
	         }
	        }
			
			 GridView gridview = (GridView) findViewById(R.id.gridview);
		  
		     gridview.setAdapter(new griddis(this,str3,2));
			
	}}
			}
		
///////////////////////////////////////////////////////////////////////////////////////////////////
///////////		 setting all the string values into an grid 2*2 or 3*3				  /////////////
///////////////////////////////////////////////////////////////////////////////////////////////////	

	
	public class griddis  extends BaseAdapter {
	     private Context mContext;
	     String[] grddispitems;
	     int v;
	   
	     public griddis(Context c,String[] displayitems,int s) {
	         
	      System.out.println("");
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

	   

	 //}


	
	}
	 
	}
		

