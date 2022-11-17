package es.joseljg.examenfutbol2223;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    public static final String EXTRA_CANTIDAD = "es.joseljg.examenfutbol2223.mainactivity.cantidad";
    private static final String EXTRA_ZONA = "es.joseljg.examenfutbol2223.mainactivity.tipozona" ;
    private EditText edt_cantidad_pantalla1 = null;
    private Spinner sp_tipo_entrada_pantalla1 = null;
    private String tipo_zona = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edt_cantidad_pantalla1 = (EditText) findViewById(R.id.edt_cantidad_pantalla1);
        sp_tipo_entrada_pantalla1 = (Spinner) findViewById(R.id.sp_tipo_entrada);
        tipo_zona = "general";
        //-------------------------------------------
        String[] tipos = {"general","anfiteatro","goles"};
        sp_tipo_entrada_pantalla1.setOnItemSelectedListener(this);
        //-------------------------------------------
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, tipos);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp_tipo_entrada_pantalla1.setAdapter(adapter);
    }


    public void siguiente(View view) {
        String texto = String.valueOf(edt_cantidad_pantalla1.getText());
        if(texto.isEmpty())
        {
            edt_cantidad_pantalla1.setError("debes de poner un número, no puede estar en blanco");
            return;
        }
        int cant = Integer.valueOf(texto);
        boolean validado = validarCantidad(cant);
        if(!validado)
        {
            edt_cantidad_pantalla1.setError("debes poner una cantidad de entradas entre 1 y 10");
            return;
        }
        Log.i("variables", String.valueOf(cant));
        Log.i("variables", tipo_zona);
        System.out.println("cantidad ->" + String.valueOf(cant));
        System.out.println("tipo ->" + tipo_zona);
        //-------------------------------------------------------------
        Intent intent = new Intent(this, Pantalla2.class);
     //   Bundle bundle = new Bundle();
     //   bundle.putString(EXTRA_ZONA, tipo_zona);
     //   bundle.putInt(EXTRA_CANTIDAD, cant);
     //   intent.putExtras(bundle);
         intent.putExtra(EXTRA_CANTIDAD, cant);
         intent.putExtra(EXTRA_ZONA, tipo_zona);
        startActivity(intent);
    }


    private boolean validarCantidad(int cant) {
        if(cant >0 && cant <= 10)
        {
            return true;
        }
        return false;
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        tipo_zona = adapterView.getItemAtPosition(i).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
        tipo_zona = "general";
    }
}