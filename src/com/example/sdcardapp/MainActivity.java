package com.example.sdcardapp;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends Activity {
	
	EditText inputEditText;
	Button addButton;
	Button viewButton;
	TextView outputTextView;
	File data_file;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        inputEditText = (EditText) findViewById(R.id.input);
        addButton = (Button) findViewById(R.id.add_data);
        viewButton = (Button) findViewById(R.id.view_data);
        outputTextView = (TextView) findViewById(R.id.output);
        File external_sd_card_directiry = Environment.getExternalStorageDirectory();
        data_file = new File(external_sd_card_directiry,"data_source.txt");
        
        viewButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				

		        BufferedReader br = null;
				try {
					br = new BufferedReader(new FileReader(data_file));
				} catch (FileNotFoundException e1) {
					e1.printStackTrace();
				}
				String data = "";
				String currentData = " ";
				try {
					 while(currentData!=null){
						 
						 data = data + currentData + "\n";
						 currentData = br.readLine();
					 }
					 
					 outputTextView.setText(data);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				try {
					br.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		});
        
        addButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
			BufferedWriter bw = null;
			try {
				bw = new BufferedWriter(new FileWriter(data_file));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
				try {
					bw.append(inputEditText.getText().toString() + "\n");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			try {
				bw.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			}
			
		});
        
       
        		
    }
    
    public boolean isExternalStorageWritable() {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state)) {
            return true;
        }
        return false;
    }

    /* Checks if external storage is available to at least read */
    public boolean isExternalStorageReadable() {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state) ||
            Environment.MEDIA_MOUNTED_READ_ONLY.equals(state)) {
            return true;
        }
        return false;
    }
}
