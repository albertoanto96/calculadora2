package dsa.eetac.upc.edu.calculadora2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Debug;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Alberto on 04/12/2016.
 */

public class Ui2Activity extends AppCompatActivity {
    String tag = "Events";
    String res;
    StringBuffer sb;
    List<String> lst = new ArrayList<String>();
    int contador = 0;
    String text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);//llamada al metodo original
        setContentView(R.layout.ui2);
        Bundle extra = getIntent().getExtras();
        sb = new StringBuffer();
        res = extra.getString("texto");
        TextView textBox = (TextView) findViewById(R.id.textView);
        text = extra.getString("texto");

        String[] li = text.split("//");
        for (int i = 0; i < li.length; i++) {
            lst.add(li[i]);
            sb.append(contador + ":" + li[i] + "\r\n");
            contador++;
        }
        textBox.setText(sb);
        Log.d(tag, "event onCreate()");

    }

    @Override
    public void onStart() {
        super.onStart();
        Log.d(tag, "event onStart()");

    }

    @Override
    public void onResume() {
        super.onResume();  // Always call the superclass method first
        Log.d(tag, "event onResume()");
    }

    @Override
    public void onPause() {
        super.onPause();  // Always call the superclass method first
        Log.d(tag, "event onPause()");
    }

    @Override
    public void onStop() {
        super.onStop();  // Always call the superclass method first
        Log.d(tag, "event onStop()");

    }

    @Override
    protected void onRestart() {
        super.onRestart();  // Always call the superclass method first
        Log.d(tag, "event onRestart()");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();  // Always call the superclass
        // Stop method tracing that the activity started during onCreate()
        Log.d(tag, "event onDestroy()");
        Debug.stopMethodTracing();
    }

    public void retorna(View w) {
        Intent Activity1 = getIntent();
        EditText num = (EditText) findViewById(R.id.num);
        try {
            String sel = num.getText().toString();
            Activity1.putExtra("result", lst.get(Integer.parseInt(sel)).toString());
            setResult(RESULT_OK, Activity1);
            finish();
        } catch (Exception e) {

            setResult(RESULT_CANCELED, Activity1);
            if (lst.size() == 0) {
                Toast.makeText(getApplicationContext(), "Historial vacio",
                        Toast.LENGTH_LONG).show();
                finish();
            } else {
                if (num.getText().toString().equals("")) {
                    Toast.makeText(getApplicationContext(), "Ningun valor elegido",
                            Toast.LENGTH_SHORT).show();

                } else {
                    Toast.makeText(getApplicationContext(), "Fuera del margen del historial",
                            Toast.LENGTH_LONG).show();
                }
            }
        }

    }

    public void borrar(View v) {
        Intent Activity3 = new Intent(getApplicationContext(), Ui3Activity.class);
        startActivityForResult(Activity3, 200);

    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if ((requestCode == 200) && (resultCode == Activity.RESULT_OK)) {
            Intent Activity1 = new Intent(getApplicationContext(), Ui1Activity.class);
            Activity1.putExtra("borra","ok");
            startActivity(Activity1);
        }
    }
}
