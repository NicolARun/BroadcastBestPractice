package com.example.broadcastbestpractice;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends BaseActivity {
	
	private SharedPreferences preferences;
	
	private SharedPreferences.Editor editor;
	
	private CheckBox rememberPass;
	
	private EditText accountEdit;
	
	private EditText passwordEdit;
	
	private Button login;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login);
		
		preferences = PreferenceManager.getDefaultSharedPreferences(this);
		
		accountEdit = (EditText) findViewById(R.id.account);
		passwordEdit = (EditText)findViewById(R.id.password);
		login = (Button)findViewById(R.id.login);
		rememberPass = (CheckBox)findViewById(R.id.remember_pass);
		
		boolean isRemember = preferences.getBoolean("remember_password", false);
		
		if (isRemember) {
			String account = preferences.getString("account", "");
			String password = preferences.getString("password", "");
			accountEdit.setText(account);
			passwordEdit.setText(password);
			rememberPass.setChecked(isRemember);
		}
		
		login.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				String account = accountEdit.getText().toString();
				String password = passwordEdit.getText().toString();
				
				if (account.equals("admin") && password.equals("123456")) {
					
					editor = preferences.edit();
					if (rememberPass.isChecked()) {
						editor.putBoolean("remember_password", true);
						editor.putString("account", account);
						editor.putString("password", password);
					} else {
						editor.clear();
					}
					editor.commit();
					
					Intent intent = new Intent(LoginActivity.this, MainActivity.class);
					startActivity(intent);
					finish();
				} else {
					Toast.makeText(LoginActivity.this, "account or password is invalid", Toast.LENGTH_LONG).show();
				}
			}
		});
	}

}
