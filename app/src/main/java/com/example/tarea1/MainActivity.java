package com.example.tarea1;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.Notification;
import android.content.Intent;
import android.content.ServiceConnection;
import android.icu.util.Calendar;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import java.time.LocalDate;

public class MainActivity extends AppCompatActivity {
    Intent intent;
    Bundle b;
    private  int dia,mes,ano;
    EditText fecha, nombre, telefono;
    RadioButton radGenero;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        radGenero = (RadioButton)findViewById(R.id.radMasc);
        radGenero.setChecked(true);

        fecha =(EditText)findViewById(R.id.dtFechaNac);
    }
    public void VerCalendario(View view){
        final Calendar c= Calendar.getInstance();
        dia=c.get(Calendar.DAY_OF_MONTH);
        mes=c.get(Calendar.MONTH);
        ano=c.get(Calendar.YEAR);
        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                fecha.setText(dayOfMonth+"/"+(month+1)+"/"+year);
            }
        },dia,mes,ano);
        datePickerDialog.show();
    }
    public void EnviarDatos(View view){
        intent = new Intent(MainActivity.this, Actividad2.class);

        nombre = (EditText) findViewById(R.id.txtNombre);
        radGenero = (RadioButton)findViewById(R.id.radMasc);
        if(!radGenero.isChecked()){
            radGenero = (RadioButton)findViewById(R.id.radFemenino);
        }
        fecha =(EditText)findViewById(R.id.dtFechaNac);
        telefono = (EditText) findViewById(R.id.txtTelefono);

        if(nombre.getText().toString().length() > 0){
            if(fecha.getText().toString().length() > 0){
                String[] parts = fecha.getText().toString().split("/");
                int año = Integer.parseInt(parts[2]);
                if(año < LocalDate.now().getYear()){
                    if(telefono.getText().toString().length() > 0){
                        if(telefono.getText().toString().length() == 10){
                            b= new Bundle();
                            b.putString("NOMBRE", nombre.getText().toString());
                            b.putString("SEXO", radGenero.getText().toString());
                            b.putString("FECHA_NAC", fecha.getText().toString());
                            b.putString("TELEFONO", telefono.getText().toString());

                            intent.putExtras(b);
                            startActivity(intent);
                        }
                        else{
                            Toast.makeText(getBaseContext(), "El número de teléfono debe contener los diez dígitos", Toast.LENGTH_LONG).show();
                        }
                    }
                    else{
                        Toast.makeText(getBaseContext(), "Es obligatorio ingresar el número de teléfono", Toast.LENGTH_LONG).show();
                    }
                }
                else{
                    Toast.makeText(getBaseContext(), "El año de nacimiento tiene que ser menor al año actual", Toast.LENGTH_LONG).show();
                }
            }
            else{
                Toast.makeText(getBaseContext(), "Es obligatorio ingresar una fecha de nacimiento", Toast.LENGTH_LONG).show();
            }
        }
        else{
            Toast.makeText(getBaseContext(), "Es obligatorio ingresar su nombre", Toast.LENGTH_SHORT).show();
        }
    }
}