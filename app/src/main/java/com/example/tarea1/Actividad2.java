package com.example.tarea1;

import androidx.appcompat.app.AppCompatActivity;

import android.icu.util.Calendar;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.text.method.DateTimeKeyListener;
import android.widget.EditText;
import android.widget.TextView;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;

public class Actividad2 extends AppCompatActivity {
    TextView lblMensajeRecibido;
    Bundle b;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actividad2);

        lblMensajeRecibido = (TextView)findViewById(R.id.lblMensaje);
        b = this.getIntent().getExtras();

        String fechaNac = b.getString("FECHA_NAC");
        String[] parts = fechaNac.split("/");
        int dia = Integer.parseInt(parts[0]);
        int mes = Integer.parseInt(parts[1]);
        int año = Integer.parseInt(parts[2]);
        Period er = Period.between(LocalDate.of(año, mes, dia), LocalDate.now());

        lblMensajeRecibido.setText("Hola, "+(b.getString("SEXO").equals("Masculino")?"bienvenido ":"bienvenida "+" \n")+
        b.getString("NOMBRE")+ " de acuerdo a su fecha de nacimiento: "+ b.getString("FECHA_NAC") +". En la actualidad usted tiene " + er.getYears() + " años, su número de teléfono: "+
        b.getString("TELEFONO") + " lo registraremos para poder contactarnos con usted. Gracias.");
    }
}