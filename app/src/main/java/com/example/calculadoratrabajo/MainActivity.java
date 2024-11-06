package com.example.calculadoratrabajo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;

public class MainActivity extends AppCompatActivity {

    //variables globales
    //traemos los controles para poder manipularlos
    EditText editNumero1, editOperacion,editNumero2;
    RadioButton radioSumar, radioRestar, radioMultiplicar, radioDividir;
    Button botonCalcular, botonBorrar, botonGuardar, botonMostrar;
    TextView textResultado;
    int operacionElegida = 0; //cambiará en función de la operacion que elijamos. 1 suma. 2 Resta. 3 Multiplicacion. 4 Division.
    //los numeros que introduce el usuario
    float numero1, numero2;
    //al principio los valores introducidos no son validos puesto que no hay ningunos
    boolean valor1Valido = false;
    boolean valor2Valido = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editNumero1 = findViewById(R.id.editNumero1);
        editOperacion = findViewById(R.id.editOperacion);
        editNumero2 = findViewById(R.id.editNumero2);
        radioSumar = findViewById(R.id.radioSumar);
        radioRestar = findViewById(R.id.radioRestar);
        radioMultiplicar = findViewById(R.id.radioMultiplicar);
        radioDividir = findViewById(R.id.radioDividir);
        botonCalcular = findViewById(R.id.botonCalcular);
        botonBorrar = findViewById(R.id.botonBorrar);
        botonGuardar = findViewById(R.id.botonGuardar);
        botonMostrar = findViewById(R.id.botonMostrar);
        textResultado = findViewById(R.id.textResultado);

        //esto hace que el edit text de operación no sea accesible desde la interfaz de usuario
        editOperacion.setEnabled(false);

        //Eventos de los botones----------------------------------------------------------------
        //--------------------------------------------------------------------------------------

        //evento del boton calcular
        botonCalcular.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {

                ocultarTeclado();
                //sacamos los numeros introducidos por el usuario
                numero1 = devuelveElPrimerNumero();
                numero2 = devuelveElSegundoNumero();

                if (radioSumar.isChecked()){
                    operacionElegida = 1;
                    if (valor1Valido && valor2Valido){
                        calcularSuma(numero1,numero2);
                        resetearValoresValidos();
                    }else{
                        Toast.makeText(MainActivity.this,"Introduce valores válidos", Toast.LENGTH_LONG).show();
                    }
                }else if (radioRestar.isChecked()){
                    operacionElegida = 2;
                    if (valor1Valido && valor2Valido){
                        calcularResta(numero1, numero2);
                        resetearValoresValidos();
                    }else{
                        Toast.makeText(MainActivity.this,"Introduce valores válidos", Toast.LENGTH_LONG).show();
                    }

                }else if (radioMultiplicar.isChecked()){
                    operacionElegida = 3;
                    if(valor1Valido && valor2Valido){
                        calcularMultiplicacion(numero1, numero2);
                        resetearValoresValidos();
                    }else {
                        Toast.makeText(MainActivity.this, "Introduce valores válidos", Toast.LENGTH_LONG).show();
                    }
                }else if (radioDividir.isChecked()){
                    operacionElegida = 4;
                    if((valor1Valido && valor2Valido) && (numero2 != 0)){
                        calcularDivision(numero1, numero2);
                        resetearValoresValidos();
                    }else{
                        Toast.makeText(MainActivity.this, "Introduce valores válidos", Toast.LENGTH_LONG).show();
                    }
                }else {operacionElegida = 0;

                    //si no se ha elegido ninguna opcion se muestra una advertencia
                    Toast.makeText(MainActivity.this, "Debes elegir una opcion para realizar una operacion", Toast.LENGTH_LONG).show();

                } //si no hay nada seleccionado la operacion elegida es 0;
            }
        });//final evento boton calcular
        
        //evento del boton borrar
        botonBorrar.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                borrarDatos();
            }
        });

        //evento del boton guardar
        botonGuardar.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {

                float numero1 = devuelveElPrimerNumero();
                String operando = "";
                float numero2 = devuelveElSegundoNumero();
                float resultado = devuelveResultado();

                //todo tiene que ser diferente a 0 que es el valor que devuelven los metodos si hay error
                if ((operacionElegida != 0) && ((resultado !=0) && (numero1 !=0) && (numero2 !=0))){

                    switch (operacionElegida){

                        case 0:
                            Toast.makeText(MainActivity.this, "Todavia no has hecho ninguna operacion", Toast.LENGTH_LONG).show();
                            break;
                        case 1:
                            operando = "+";
                            break;
                        case 2:
                            operando = "-";
                            break;
                        case 3:
                            operando = "*";
                            break;
                        case 4:
                           operando = "/";
                           break;
                    }//final switch
                    //manda los datos a guardar
                    guardarDatos(numero1, numero2, operando, resultado);

                }else{
                    Toast.makeText(MainActivity.this, "Todavia no has hecho ninguna operacion", Toast.LENGTH_LONG).show();
                    //si la operacion elegida es 0 no tiene sentido seguir
                }
            }
        });

        botonMostrar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                mostrarUltimaOperacion();
            }
        });

        //evento del radioBoton Sumar
        radioSumar.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                editOperacion.setText("+");
                operacionElegida = 1;
            }
        });

        //Evento del radioboton Restar
        radioRestar.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                editOperacion.setText("-");
                operacionElegida = 2;
            }
        });

        //evento del radioBoton Multiplicar
        radioMultiplicar.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                editOperacion.setText("*");
                operacionElegida = 3;
            }
        });

        //evento del radioBoton Dividir
        radioDividir.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                editOperacion.setText("/");
                operacionElegida = 4;
            }
        });
    }//final onCreate

    //este metodo recupera los datos de la ultima operacion y los carga de nuevo en las vistas
    private void mostrarUltimaOperacion() {

        //este procedimiento recupera los datos guardados
        Toast.makeText(this, "Recuperando los datos", Toast.LENGTH_LONG).show();
        SharedPreferences prefs = getSharedPreferences("MisDatos", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();

        String primerNumero = String.valueOf(prefs.getFloat("Primer Numero", 0));
        String segundoNumero = String.valueOf(prefs.getFloat("Segundo Numero", 0));
        String operando = prefs.getString("Operando","operando");
        String resultado = String.valueOf(prefs.getFloat("resultado", 0));

        editNumero1.setText(primerNumero);
        editNumero2.setText(segundoNumero);
        editOperacion.setText(operando);
        textResultado.setText(resultado);
    }

    private void guardarDatos(float numero1, float numero2, String operando, float resultado) {

        //este procedimiento guarda los datos de la aplicación en un archivo en la memoria del dispositivo
        Toast.makeText(this, "Guardando los datos",Toast.LENGTH_LONG).show();
        SharedPreferences prefs = getSharedPreferences("MisDatos", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putFloat("Primer Numero", numero1);
        editor.putFloat("Segundo Numero", numero2);
        editor.putString("Operando", operando);
        editor.putFloat("resultado", resultado);
        editor.commit();

    }//final guardar datos

    private float devuelveResultado() {
        //este metodo devuelve el resultado de la ultima operacion
        float resultado;
        try{
            resultado = Float.parseFloat(textResultado.getText().toString());
        }catch(Exception e){
            return 0;
        }

        return resultado;
    }

    //este metodo borra los datos  de los edittext cuando se pulsa el boton borrar
    private void borrarDatos() {
        editNumero1.setText("");
        editNumero2.setText("");
        editOperacion.setText("");
        textResultado.setText("Resultado");
        ocultarTeclado();
    }

    private void ocultarTeclado() {
        // Ocultar el teclado virtual
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager)getSystemService(MainActivity.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    //este metodo resetea los valores para que se comprueben nuevamente
    private void resetearValoresValidos() {
        valor1Valido = false;
        valor1Valido = false;
    }

    //Devuelve el primer numero introducido por el usuario
    private float devuelveElPrimerNumero() {

        try{
            Float numero = Float.parseFloat(editNumero1.getText().toString());
            valor1Valido = true;
            return numero;
        }catch(Exception e){
            return 0;
        }


    }

    //Devuelve el segundo numero introducido por el usuario
    private float devuelveElSegundoNumero() {
        try{
            Float numero = Float.parseFloat(editNumero2.getText().toString());
            valor2Valido = true;
            return numero;
        }catch(Exception e){
            return 0;
        }


    }

    //este metodo se usa para coger los numeros introducidos por el usuario y que se puedan utilizar

    private void calcularDivision(float numero1, float numero2) {

        //se calcula la division
        float division = numero1 / numero2;
        //se muestra en pantalla
        textResultado.setText(String.valueOf(division));

    }

    private void calcularMultiplicacion(float numero1, float numero2) {

        //se calcula la division
        float multiplicacion = numero1 * numero2;
        //se muestra en pantalla
        textResultado.setText(String.valueOf(multiplicacion));

    }

    private void calcularResta(float numero1, float numero2) {

        //se calcula la resta
        float resta = numero1 - numero2;
        //se muestra en pantalla
        textResultado.setText(String.valueOf(resta));

    }

    private void calcularSuma(float numero1, float numero2) {

        //se calcula la suma
        float suma = numero1 + numero2;
        //se muestra en pantalla
        textResultado.setText(String.valueOf(suma));

    }
}