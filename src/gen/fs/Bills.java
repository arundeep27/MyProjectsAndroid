package gen.fs;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

public class Bills extends Activity implements OnItemClickListener {

String ln;
	
	ListView list1;
	String[] items = {"View Bills to be cleared","Add Bills to be cleared"};// list format with two options
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	setContentView(R.layout.listbills);
	
	Bundle extras=getIntent().getExtras();
	String ln1=extras.getString("login");
	ln=ln1;
	
/////////////////////////////////////////////////////////////////////////////////////////////////////////        
//////////To display a list we need to create a listview in xml           ////////////////
//////////we are using the default list which is inbuilt in android		             ////////////////	
/////////////////////////////////////////////////////////////////////////////////////////////////////////	 
	
	list1 = (ListView)findViewById(R.id.lv1);
	list1.setOnItemClickListener(this);
	list1.setAdapter(new ArrayAdapter(this,android.R.layout.simple_list_item_1,items));
	}
	

/////////////////////////////////////////////////////////////////////////////////////////////////////////        
//////////			click method for the list options                                            ////////
//////////		depending on the selected item it will navigate to that class     				 ////////
/////////////////////////////////////////////////////////////////////////////////////////////////////////	 
	
	
	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
		// TODO Auto-generated method stub
		switch(position){
		case 0:
			Intent intent1=new Intent(arg1.getContext(),Viewbills.class);
			intent1.putExtra("login",ln);///passsing the username
			startActivity(intent1);///starting the activity
		break;
		case 1:
			Intent intent2=new Intent(arg1.getContext(),Addbills.class);
			intent2.putExtra("login",ln);////passing the username
			startActivity(intent2);//// starting the activity
		break;	
		}
		
		
		
		
	}
	}
	
	
	
	
	


