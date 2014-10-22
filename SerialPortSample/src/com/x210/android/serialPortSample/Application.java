/*
 * Copyright 2009 Cedric Priscal
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License. 
 */

package com.x210.android.serialPortSample;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.security.InvalidParameterException;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;

import android.content.Context;
import android.util.Log;

import com.x210.android.serialPortSample.SerialPort;
import com.x210.android.serialPortSample.SerialPortFinder;

public class Application extends android.app.Application {
//public class Application extends PreferenceActivity {
 
	public SerialPortFinder mSerialPortFinder = new SerialPortFinder();
	private SerialPort mSerialPort = null;
	public static final String SETTING_INFOS = "com.x210.android.serialPortData_preferences";
	public static final int MODE = Context.MODE_WORLD_READABLE + MODE_WORLD_WRITEABLE;
	public static final String DEVICE = "DEVICE";
	public static final String BAUDRATE = "BAUDRATE";
	public static final String TAG = "Application";
//	private ListPreference devices;
//	private ListPreference baudrates;
	
    DocumentBuilder builder=null;
    Document document=null;
    InputStream inputStream=null;
    private String path = "/dev/s3c2410_serial1";//串口文件
    private int baudrate = 19200;//波特率

	public SerialPort getSerialPort() throws SecurityException, IOException,
			InvalidParameterException {
		Log.e(TAG, "mSerialPort:" + mSerialPort);

		if (mSerialPort == null) {
			/** Read serial port parameters */
			 //String path = "/dev/ttyS0";//The serail port can not be opened for an unknown reason.
			 Log.e(TAG,"path: " + path);
			 Log.e(TAG,"baudrate: " + baudrate);
			 mSerialPort = new SerialPort(new File(path), baudrate);			
		}// if
		Log.e(TAG, "mSerialPort:" + mSerialPort);
		return mSerialPort;
	}

	public void closeSerialPort() {
		if (mSerialPort != null) {
			mSerialPort.close();
			mSerialPort = null;
		}
	}
	
	public void setmSerialPort(SerialPort mSerialPort) {
		this.mSerialPort = mSerialPort;
	}

	public void setPath(String path) {
		this.path = path;
	}
}
