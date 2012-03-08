package pj.martorell;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class CalcActivity extends Activity {

	protected static final String TAG = "CALC";
	private static final String PREFS_NAME = "CALC";
	MyListener l1;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.calc_layout);

		EditText op1 = (EditText) findViewById(R.id.op1);
		SharedPreferences prefs = getSharedPreferences(PREFS_NAME,
				Context.MODE_PRIVATE);
		float resultat = prefs.getFloat("resultat", 0);
		op1.setText(String.valueOf(resultat));

		l1 = new MyListener();
		l1.resolt = prefs.getBoolean("resolt", true);
		l1.operacio = prefs.getInt("operacio", 0);
		l1.acumulat = prefs.getFloat("acumulat", 0);
		
		Button bt0 = (Button) findViewById(R.id.num0);
		Button bt1 = (Button) findViewById(R.id.num1);
		Button bt2 = (Button) findViewById(R.id.num2);
		Button bt3 = (Button) findViewById(R.id.num3);
		Button bt4 = (Button) findViewById(R.id.num4);
		Button bt5 = (Button) findViewById(R.id.num5);
		Button bt6 = (Button) findViewById(R.id.num6);
		Button bt7 = (Button) findViewById(R.id.num7);
		Button bt8 = (Button) findViewById(R.id.num8);
		Button bt9 = (Button) findViewById(R.id.num9);
		Button bts = (Button) findViewById(R.id.suma);
		Button btr = (Button) findViewById(R.id.resta);
		Button btd = (Button) findViewById(R.id.div);
		Button btm = (Button) findViewById(R.id.mul);
		Button btD = (Button) findViewById(R.id.dot);
		Button btC = (Button) findViewById(R.id.esborrar);
		Button btequal = (Button) findViewById(R.id.igual);

		bt0.setOnClickListener(l1);
		bt1.setOnClickListener(l1);
		bt2.setOnClickListener(l1);
		bt3.setOnClickListener(l1);
		bt4.setOnClickListener(l1);
		bt5.setOnClickListener(l1);
		bt6.setOnClickListener(l1);
		bt7.setOnClickListener(l1);
		bt8.setOnClickListener(l1);
		bt9.setOnClickListener(l1);
		bts.setOnClickListener(l1);
		btr.setOnClickListener(l1);
		btd.setOnClickListener(l1);
		btm.setOnClickListener(l1);
		btC.setOnClickListener(l1);
		btD.setOnClickListener(l1);
		btequal.setOnClickListener(l1);
	}

	@Override
	public void onPause() {
		super.onPause();
		EditText op1 = (EditText) findViewById(R.id.op1);
		float _op1 = Float.parseFloat(op1.getText().toString());
		float _acumulat = Float.parseFloat(String.valueOf(l1.acumulat));
		SharedPreferences prefs = getSharedPreferences(PREFS_NAME,
				Context.MODE_PRIVATE);
		SharedPreferences.Editor editor = prefs.edit();
		editor.putFloat("resultat", _op1);
		editor.putInt("operacio", l1.operacio);
		editor.putBoolean("resolt", l1.resolt);
		editor.putInt("operacio", 0);
		editor.putFloat("acumulat", _acumulat);
		editor.commit();
	}

	public class MyListener implements OnClickListener {
		EditText op1 = (EditText) findViewById(R.id.op1);
		double operador1;
		int operacio = 0;
		double acumulat;
		boolean resolt = false;

		@Override
		public void onClick(View v) {
			operador1 = Double.parseDouble(op1.getText().toString());
			switch (v.getId()) {
			case R.id.suma:
				Log.v(TAG, "Sumant");
				operacio = 1;
				acumulat = operador1;
				op1.setText("0");
				break;
			case R.id.resta:
				operacio = 2;
				acumulat = operador1;
				Log.v(TAG, "Restant");
				op1.setText("0");
				break;
			case R.id.mul:
				operacio = 3;
				acumulat = operador1;
				Log.v(TAG, "Multiplicant");
				op1.setText("0");
				break;
			case R.id.div:
				operacio = 4;
				acumulat = operador1;
				op1.setText("0");
				Log.v(TAG, "Dividint");
				break;
			case R.id.igual:
				resoldre();
				Log.v("CALC", "Igual");
				break;
			case R.id.esborrar:
				op1.setText("0");
				acumulat = 0;
				Log.v("CALC", "Esborrar");
				break;
			case R.id.dot:
				String aux = op1.getText().toString();
				if (!aux.contains(".")) {
					if (resolt)
						op1.setText("0.");
					else
						op1.setText(op1.getText() + ".");
				} else if (aux.isEmpty())
					writeText("0.");
				break;
			case R.id.num1:
				writeText("1");
				break;
			case R.id.num2:
				writeText("2");
				break;
			case R.id.num3:
				writeText("3");
				break;
			case R.id.num4:
				writeText("4");
				break;
			case R.id.num5:
				writeText("5");
				break;
			case R.id.num6:
				writeText("6");
				break;
			case R.id.num7:
				writeText("7");
				break;
			case R.id.num8:
				writeText("8");
				break;
			case R.id.num9:
				writeText("9");
				break;
			case R.id.num0:
				if (Double.parseDouble(op1.getText().toString()) != 0)
					writeText("0");
				break;
			default:
			}
		}

		public void writeText(String text) {
			if (resolt) {
				op1.setText(text);
				resolt = false;
			} else {
				double _op1 = Double.parseDouble(op1.getText().toString());
				if (_op1 == 0)
					op1.setText(text);
				else
					op1.setText(op1.getText() + text);
			}
		}

		public void resoldre() {
			Double result = 0.0;
			switch (operacio) {
			case 0:
				break;
			case 1:
				result = acumulat + operador1;
				break;
			case 2:
				result = acumulat - operador1;
				break;
			case 3:
				result = acumulat * operador1;
				break;
			case 4:
				result = acumulat / operador1;
				break;
			default:
			}
			if (Double.isInfinite(result)) {
				Toast.makeText(getApplicationContext(), "Divisió per zero!",
						Toast.LENGTH_SHORT).show();
				op1.setText("0");
				acumulat = 0;
			} else if (Double.isNaN(result)) {
				Toast.makeText(getApplicationContext(), "Not a Number!",
						Toast.LENGTH_SHORT).show();
				op1.setText("0");
				acumulat = 0;
			} else {
				op1.setText(result.toString());
				operacio = 0;
				resolt = true;
			}

		}

	};

}