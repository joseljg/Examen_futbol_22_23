package es.joseljg.examenfutbol2223;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;

public class Pantalla2 extends AppCompatActivity {

    private int cant;
    private String tipo_zona;
    private double precioEntradas;
    private double precioDescuento;
    private double precioTotal;
    private boolean es_socio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantalla2);
        es_socio = false;
        precioDescuento = 0;
        precioEntradas = 0;
        //-------------------------------------------------
        Intent intent = getIntent();
        if(intent != null)
        {
            cant = intent.getIntExtra(MainActivity.EXTRA_CANTIDAD,0);
            tipo_zona = intent.getStringExtra(MainActivity.EXTRA_ZONA);
        }
        else{
            cant = 0;
            tipo_zona = "general";
        }
        actualizarPrecios();

    }

    private void actualizarPrecios() {
        precioEntradas = calcularPrecioEntradas(cant,tipo_zona);
        precioDescuento = calcularDescuentoSocio(es_socio);
        precioTotal = precioEntradas - precioDescuento;
    }

    private double calcularDescuentoSocio(boolean es_socio) {
        if(es_socio) {
            double descuento = calcularPrecioEntradas(cant, tipo_zona) * 0.1;
            return descuento;
        }
        else{
            return 0;
        }
    }

    private double calcularPrecioEntradas(int cant, String tipo_zona) {
        double precioEntrada = 0;
        switch (tipo_zona)
        {
            case "general":
                precioEntrada = 3;
                break;
            case "goles":
                precioEntrada = 2;
                break;
            case "anfiteatro":
                precioEntrada = 2.5;
                break;
        }
        double precioEntradas = precioEntrada * cant;
        return precioEntradas;
    }

    public void cambiar_opcion_socio(View view) {
        RadioButton boton = (RadioButton) view;
        if(boton.isChecked()) {
            switch (boton.getId()) {
                case R.id.rb_socio_si:
                    es_socio = true;
                    break;
                case R.id.rb_socio_no:
                    es_socio = false;
                    break;
            }
        }
    }
}