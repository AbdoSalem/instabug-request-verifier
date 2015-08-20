package com.example.requestverifier;

import com.example.requestverifier.R;
import com.example.requestverifier.RequestManager.Result;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends Activity implements OnClickListener {
	EditText et_name, et_mail, et_phone, et_special, et_projects;
	Button bSubmit;
	Spinner spin_url;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
		initComponents();
	}

	private void initComponents() {
		et_mail = (EditText) findViewById(R.id.et_mail);
		et_name = (EditText) findViewById(R.id.et_name);
		et_phone = (EditText) findViewById(R.id.et_phone);
		et_projects = (EditText) findViewById(R.id.et_projects);
		et_special = (EditText) findViewById(R.id.et_special);
		bSubmit = (Button) findViewById(R.id.b_submit);
		spin_url = (Spinner) findViewById(R.id.spinner1);
		bSubmit.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		if (et_mail.getText().toString().matches("")) {
			Toast.makeText(this, "Check email", Toast.LENGTH_LONG).show();
			et_mail.requestFocus();
			return;
		}
		if (et_name.getText().toString().matches("")) {
			Toast.makeText(this, "Check name", Toast.LENGTH_LONG).show();
			et_name.requestFocus();
			return;
		}
		if (et_phone.getText().toString().matches("")) {
			Toast.makeText(this, "Check phone", Toast.LENGTH_LONG).show();
			et_phone.requestFocus();
			return;
		}
		if (et_projects.getText().toString().matches("")) {
			Toast.makeText(this, "Check projects", Toast.LENGTH_LONG).show();
			et_projects.requestFocus();
			return;
		}
		if (et_special.getText().toString().matches("")) {
			Toast.makeText(this, "Check specials", Toast.LENGTH_LONG).show();
			et_special.requestFocus();
			return;
		}

		SubmitRequest req = new SubmitRequest(et_name.getText().toString(),
				et_mail.getText().toString(), et_phone.getText().toString(),
				et_special.getText().toString(), et_projects.getText()
						.toString());
		RequestManager reqm = new RequestManager(this);
		reqm.execute(req);
	}

	class SubmitRequest extends Request {

		public SubmitRequest(String name, String mail, String phone,
				String special, String projects) {
			addParameter("name", name);
			addParameter("email", mail);
			addParameter("phone", phone);
			addParameter("special", special);
			addParameter("projects", projects);
			Log.e("sbmission", "ading params");
		}

		@Override
		public String getUrl() {

			return spin_url.getSelectedItem().toString();
		}

		@Override
		public void handleResponse(String response, Result res) {
			if (res == Result.OK) {
				Toast.makeText(MainActivity.this, response, Toast.LENGTH_LONG)
						.show();
			} else
				Toast.makeText(MainActivity.this, response, Toast.LENGTH_LONG)
						.show();

		}

	}
}
