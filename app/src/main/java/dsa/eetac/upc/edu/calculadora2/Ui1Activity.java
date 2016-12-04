package dsa.eetac.upc.edu.calculadora2;

import android.app.Activity;
import android.content.Intent;
import android.os.Debug;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class Ui1Activity extends AppCompatActivity {
    String tag = "Events";
    StringBuffer sb=new StringBuffer();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);//llamada al metodo original
        setContentView(R.layout.ui1);

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
        try {
            System.out.println("Entra");
            Bundle extra = getIntent().getExtras();
            String ex=extra.getString("borra");
            if (ex == "ok") {
                sb = null;
            }
        }
        catch (Exception e){}
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

    public void Resultado(View v){//metodo para obtener el resultado al clickar en resultat

        TextView result = (TextView) findViewById(R.id.result);//Obtenemos el valor del textView result
        EditText text1 = (EditText) findViewById(R.id.text1);//Obtenemos los valores de los campos donde hemos introducido los valores
        EditText text2 = (EditText) findViewById(R.id.text2);
        int n1=0;
        int n2=0;
        try {//probamos de obtener valores para detectar si los campos están vacíos
            n2 = Integer.parseInt(text1.getText().toString());
            n1 = Integer.parseInt(text2.getText().toString());
            RadioGroup radio=(RadioGroup) findViewById(R.id.radioGroup);//obtenemos la operacion
            int seleccion=radio.getCheckedRadioButtonId();//obtenemos el id del radiobutton seleccionado
            RadioButton rdio=(RadioButton) findViewById(seleccion);//creamos un radio button nuevo a partir de esa id
            String op=rdio.getText().toString();//obtenemos el texto de ese radiobutton
            StringBuilder sol=new StringBuilder();//creamos el StringBuilder que contendrá la solución
            int res;
            if (op.equals("+")){//buscamos la solución y realizamos la operacion correspondiente

                res=n1+n2;
                sb.append(n1+"+"+n2+"="+res+"//");
                sol.append(n1+n2);
            }
            if (op.equals("-")){

                res=n1-n2;
                sb.append(n1+"-"+n2+"="+res+"//");
                sol.append(n1-n2);
            }
            if (op.equals("*")){

                res=n1*n2;
                sb.append(n1+"*"+n2+"="+res+"//");
                sol.append(n2*n1);
            }
            if (op.equals("/")){
                int i=n2%n1;
                try {
                    sol.append(n1 / n2);
                }
                catch(Exception e){//detectamos si la solucion es infinita
                    Toast.makeText(getApplicationContext(),"Inifinit",Toast.LENGTH_LONG).show();
                }
                if (i!=0){Toast.makeText(getApplicationContext(),"El resultat dona decimals",Toast.LENGTH_LONG).show();}
                res=n1/n2;
                sb.append(n1+"-"+n2+"="+res+"//");

            }
            result.setText(sol);
        }
        catch(Exception e){
            Toast.makeText(getApplicationContext(),"Camps buits",Toast.LENGTH_LONG).show();
        }

    }
    public void esborrar(View v){//ponemos a 0 los valores cuando el usuario clicka esborrar
        EditText text1 = (EditText) findViewById(R.id.text1);
        EditText text2 = (EditText) findViewById(R.id.text2);
        TextView result = (TextView) findViewById(R.id.result);
        text1.setText("0");
        text2.setText("0");
        result.setText("0");

    }
    public void historial (View v){
        Intent Activity2 = new Intent(getApplicationContext(), Ui2Activity.class);
        Activity2.putExtra("texto",sb.toString());
        startActivityForResult(Activity2,100);
    }
    protected void onActivityResult(int requestCode,int resultCode,Intent data){
        super.onActivityResult(requestCode,resultCode,data);
        EditText editText1= (EditText) findViewById(R.id.text1);
        EditText editText2= (EditText) findViewById(R.id.text2);
        RadioGroup radioGroup= (RadioGroup)findViewById(R.id.radioGroup);
        TextView textBox= (TextView)findViewById(R.id.result);
        //Si el codigo se compara y el resutlado es OK
        if((requestCode==100)&&(resultCode== Activity.RESULT_OK)){
            //Coge los extras
            String[] result=null;
            Bundle resultats=data.getExtras();
            //De los extras coge el string identificado por "valor12"
            String[] stresult1= resultats.getString("result").split("=");
            if(stresult1[0].contains("+")){
                result= stresult1[0].split("\\+");
                radioGroup.check(R.id.suma);

            }
            if(stresult1[0].contains("-")){
                result= stresult1[0].split("-");
                radioGroup.check(R.id.resta);

            }
            if(stresult1[0].contains("*")){
                result= stresult1[0].split("\\*");
                radioGroup.check(R.id.multi);

            }
            if(stresult1[0].contains("/")){
                result= stresult1[0].split("/");
                radioGroup.check(R.id.divi);

            }
            editText2.setText(result[0]);
            editText1.setText(result[1]);
            textBox.setText("0");

        }

    }
}
