package com.example.swapusingmenu;

import android.app.Activity;
import android.os.Bundle;
import android.view.*;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

public class MainActivity extends Activity {
	AutoCompleteTextView editor1,editor2;
	String temp1,temp2,selectedField;
	
	String[] androidBooks =
		   {"Hello, Android - Ed Burnette",
		    "Professional Android 2 App Dev - Reto Meier",
		    "Unlocking Android - Frank Ableson",
		    "Android App Development - Blake Meike",
		    "Pro Android 2 - Dave MacLean",
		    "Beginning Android 2 - Mark Murphy",
		    "Android Programming Tutorials - Mark Murphy",
		    "Android Wireless App Development - Lauren Darcey",
		    "Pro Android Games - Vladimir Silva",};
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        initializeFields();
        registerForContextMenu(editor1);
        registerForContextMenu(editor2);
       
        editor1.setOnTouchListener(new View.OnTouchListener()
        {
            public boolean onTouch(View arg0, MotionEvent arg1)
            {
            	//If EditText1 is selected then selectedfield will be set to "field1"
            	selectedField="field1"; 
                return false;
            }
        });
        editor2.setOnTouchListener(new View.OnTouchListener()
        {
            public boolean onTouch(View arg0, MotionEvent arg1)
            {
            	//If EditText1 is selected then selectedfield will be set to "field2"
            	selectedField="field2";
                return false;
            }
        });
    }
    
    @Override //Option menu/Action menu
    public boolean onCreateOptionsMenu(Menu menu)
    {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.main, menu);
        return true;
    }
    @Override//Option menu selection listener
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch (item.getItemId())
        {
        case R.id.swap_item:
        	//initializeFields();
            temp1 = editor1.getText().toString();
            temp2 = editor2.getText().toString();
            
            editor1.setText(temp2);
            editor2.setText(temp1);
        	return true;
        case R.id.remove_item:
        	removeSelectedField();
        	return true;
        default:
            return super.onOptionsItemSelected(item);
        }
    }
    @Override
    public boolean onPrepareOptionsMenu(Menu menu)
    {
    	initializeFields();
    	super.onPrepareOptionsMenu(menu);
    	if(selectedField.equals("field1") && editor1.getText().toString().equals(""))
    	{
    		menu.getItem(1).setEnabled(false);
    	}
    	else if(selectedField.equals("field2") && editor2.getText().toString().equals(""))
    	{
    		menu.getItem(1).setEnabled(false);
    	}
    	else
    	{
    		menu.getItem(1).setEnabled(true);
    	}
    	return true;    	
    }
   
    
    @Override
    public void onCreateContextMenu(ContextMenu menu,View v,ContextMenu.ContextMenuInfo menuInfo)
    {
    	super.onCreateContextMenu(menu,v,menuInfo);
        
    	menu.setHeaderTitle("Context Menu");
    	menu.add(0,Menu.FIRST,Menu.NONE,"Clear Text");
    }
    public boolean onContextItemSelected(MenuItem item)
    {
    	if(item.getItemId() == Menu.FIRST)
    	{
    		removeSelectedField();
    		return true;
    	}
		return super.onContextItemSelected(item);
    }
    
    public void initializeFields()
    {
    	ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_dropdown_item_1line,androidBooks);
        
        editor1 = (AutoCompleteTextView)findViewById(R.id.EditText1);
        editor2 = (AutoCompleteTextView)findViewById(R.id.EditText2);
        
        editor1.setThreshold(3);
        editor1.setAdapter(adapter);
        editor2.setThreshold(3);
        editor2.setAdapter(adapter);
        
    }
    public void removeSelectedField()
    {
    	if(selectedField.equals("field1") )
    	{
    		editor1.setText("");
    	}
    	else if(selectedField.equals("field2"))
    	{
    		editor2.setText("");
    	}
    }
}
