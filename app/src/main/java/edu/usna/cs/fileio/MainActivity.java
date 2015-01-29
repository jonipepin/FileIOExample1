package edu.usna.cs.fileio;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		final String fileName = "fileIoExample.txt";

		final TextView tv = (TextView) findViewById(R.id.textView);
		final EditText et = (EditText) findViewById(R.id.editText);
		Button saveButton = (Button) findViewById(R.id.saveButton);
		Button readButton = (Button) findViewById(R.id.readButton);

		saveButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				String s = et.getText().toString();
				saveStringToFile(fileName, s);
				
				Toast.makeText(getBaseContext(), "String Saved", Toast.LENGTH_SHORT).show();
				
				// clear EditText
				et.setText("");
			}

		});

		readButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				String s = readFromFile(fileName);
				tv.setText(s);
			}

		});
	}

	private void saveStringToFile(String fileName, String stringToBeSaved) {
		FileOutputStream fos;
		try {
			fos = openFileOutput(fileName, Context.MODE_PRIVATE);
			fos.write(stringToBeSaved.getBytes());
			fos.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private String readFromFile(String fileName) {

		String retrievedString = "";

		try {
			InputStream inputStream = openFileInput(fileName);

			if (inputStream != null) {
				InputStreamReader inputStreamReader = new InputStreamReader(
						inputStream);
				BufferedReader bufferedReader = new BufferedReader(
						inputStreamReader);
				String tempString = "";
				StringBuilder stringBuilder = new StringBuilder();

				while ((tempString = bufferedReader.readLine()) != null) {
					stringBuilder.append(tempString);
				}

				inputStream.close();
				retrievedString = stringBuilder.toString();
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return retrievedString;
	}

}
