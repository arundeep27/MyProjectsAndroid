package gen.fs;

import java.sql.Date;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;


import android.widget.AdapterView.OnItemClickListener;

/////////////////////////////////////////////////////////////////////////////////////////////////////////        
//////////  Home page when the user logins a list format will be displayed that list is 		/////////
//////////						invoked from this class    										/////////
/////////////////////////////////////////////////////////////////////////////////////////////////////////	 


public class Home extends Activity implements OnItemClickListener{

	String ln;
	
	ListView list1;
	String[] items = {"Budget","Expenses","View Transactions","Bills to be cleared"};
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	setContentView(R.layout.list);
	
	
/////////////////////////////////////////////////////////////////////////////////////////////////////////        
//////////               Getting the username from the login class                       ////////////////
/////////////////////////////////////////////////////////////////////////////////////////////////////////	 
	
	Bundle extras=getIntent().getExtras();
	String ln1=extras.getString("login");
	ln=ln1;
	
	
	
	
/////////////////////////////////////////////////////////////////////////////////////////////////////////        
//////////               To display a list we need to create a listview in xml           ////////////////
//////////   we are using the default list which is inbuilt in android		             ////////////////	
/////////////////////////////////////////////////////////////////////////////////////////////////////////	 

	
	list1 = (ListView)findViewById(R.id.lv1);
	list1.setOnItemClickListener(this);
	list1.setAdapter(new ArrayAdapter(this,android.R.layout.simple_list_item_1,items));
	}

	
/////////////////////////////////////////////////////////////////////////////////////////////////////////        
//////////      creating menu options through menuinflater method                               /////////
////////// which has two options one for navigating to Editincome class and forlogout class     /////////
/////////////////////////////////////////////////////////////////////////////////////////////////////////	 
	
public boolean onCreateOptionsMenu(Menu menu){
		
		MenuInflater mi=getMenuInflater();
		mi.inflate(R.menu.menu, menu);
		return super.onCreateOptionsMenu(menu);
		}
public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		switch(item.getItemId())
		{
		case R.id.menu1:
			Intent intent=new Intent(Home.this,Editincome.class);
			intent.putExtra("login",ln);
			startActivity(intent);
			break;
		case R.id.m2:
			Intent intent1=new Intent(this,Act1.class);
			startActivity(intent1);
			break;
			}
		return super.onOptionsItemSelected(item);
	}
	

/////////////////////////////////////////////////////////////////////////////////////////////////////////        
//////////         click method for the list options                                            /////////
////////// depending on the selected item it will navigate to that class     					/////////
/////////////////////////////////////////////////////////////////////////////////////////////////////////	 


@Override
public void onItemClick(AdapterView<?> arg0, View v, int position, long arg3) {
	// TODO Auto-generated method stub
	//Toast.makeText(this, "u clicked " + items[position] ,Toast.LENGTH_LONG).show();
	
	
	switch (position) {
	case 0:
		Intent intent=new Intent(v.getContext(), Budget.class);
		intent.putExtra("login",ln);//////passing the login name to Budget class
		startActivity(intent);
		break;
	case 1:
		Intent intent1=new Intent(v.getContext(), Expenses.class);
		intent1.putExtra("login",ln);//////passing the login name to Expenses class
		startActivity(intent1);
		break;
	case 2:
		Intent intent2=new Intent(v.getContext(), Transactions.class);
		intent2.putExtra("login",ln);//////passing the login name to Transactions class
		startActivity(intent2);
		break;
	case 3:
		Intent intent3=new Intent(v.getContext(),Bills.class);
		intent3.putExtra("login",ln);//////passing the login name to Bills class
		startActivity(intent3);
	}
	
}




	
	
	
}

