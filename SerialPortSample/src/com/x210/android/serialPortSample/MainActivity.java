package com.x210.android.serialPortSample;

import java.io.IOException;

import com.x210.android.serialPortSample.R;

import android.app.AlertDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;

public class MainActivity extends SerialPortActivity {
	EditText mReception;
	EditText Emission;
	final static int CONTEXT_ITEM_ID1 = 1000;
	final static int CONTEXT_ITEM_ID2 = 1001;
	final static int CONTEXT_ITEM_ID3 = 1010;
	public static final String TAG = "MainActivity";
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
		mReception = (EditText) findViewById(R.id.reception);

		Emission = (EditText) findViewById(R.id.emission);
		Emission.setOnEditorActionListener(new OnEditorActionListener() {
			public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
				
				int i;
				CharSequence t = v.getText();
				char[] text = new char[t.length()];
				for (i=0; i<t.length(); i++) {
					text[i] = t.charAt(i);
				}
				try {
					System.out.println("____执行了命令发送___________");
					byte[] x = new byte[]{(byte)0xAA,0x11};
					mOutputStream.write(x);
					//mOutputStream.write('\n');
					//mOutputStream.write('\r');
					//Log.e(TAG, "Emission-mO" + mOutputStream);
					
				} catch (IOException e) {
					e.printStackTrace();
				}
				return false;
			}
		});
		
		
		Button clear1 = (Button)findViewById(R.id.button1);
		clear1.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				mReception.setText("");
			}
			
		});
		
		Button clear2 = (Button)findViewById(R.id.button2);
		clear2.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Emission.setText("");
				
			}
		});
	
	}  
 
	@Override
	protected void onDataReceived(final byte[] buffer, final int size) {
		runOnUiThread(new Runnable() {
			public void run() {
				if (mReception != null) {
					for(int i=0;i<size;i++){
						System.out.print((buffer[i] & 0xff)+" ");
					}
					System.out.println();
					//mReception.append(new String(buffer, 0, size));
					//mReception.getInputExtras(isFinishing());
					Log.e(TAG, "mReception" + mReception);
				}
			}
		});
	}   
    
	@Override
	public boolean onCreateOptionsMenu(Menu menu){
		// TODO Auto-generated method stub
		super.onCreateOptionsMenu(menu);
		menu.add(0, CONTEXT_ITEM_ID1, 0, "ABOUT").setIcon(R.drawable.ic_launcher);
		menu.add(0, CONTEXT_ITEM_ID2, 0, "EXIT").setIcon(R.drawable.ic_launcher);
		menu.add(0, CONTEXT_ITEM_ID3, 0, "SETTING").setIcon(R.drawable.ic_launcher);
		return true;
	}



	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub

    	switch (item.getItemId()) {
		case CONTEXT_ITEM_ID1:
			AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
			builder.setTitle("About");
			builder.setMessage(R.string.about_msg);
			builder.show();
			break;
		case CONTEXT_ITEM_ID2:
			MainActivity.this.finish();
			break;
		case CONTEXT_ITEM_ID3:
			//设置
			MainActivity.this.finish();
			break;
		default:
			break;	
    	}
		return false;
	}
}
