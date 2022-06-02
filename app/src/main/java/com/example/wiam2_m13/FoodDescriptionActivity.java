package com.example.wiam2_m13;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class FoodDescriptionActivity extends AppCompatActivity implements View.OnClickListener {

    ArrayList<Plato> carrito;
    RecyclerView recycleViewopiniones;
    List<Coment> listaComentarios = new ArrayList<>();
    Adapter adapter;
    TextView txtV_namePlatos;
    RatingBar ratingBar;
    Plato platoRecivido;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_desc);

        txtV_namePlatos = findViewById(R.id.textV_NombrePlato);
        ratingBar = findViewById(R.id.ratingDESC);

        carrito = new ArrayList<Plato>();
        getDataComent();
        getData();
        setData();

        //añadir a carrito
        findViewById(R.id.button3).setOnClickListener(this);
        //apartado opiniones
        recycleViewopiniones = findViewById(R.id.rvOpiniones);
        adapter = new Adapter(this,listaComentarios,listaComentarios.size());
        recycleViewopiniones.setAdapter(adapter);


    }

    private void setData() {
        if(platoRecivido !=null) {
            txtV_namePlatos.setText(platoRecivido.nombre);
            ratingBar.setRating(platoRecivido.rating);
        }else{

        }
    }
    private void getDataComent() {

        listaComentarios.add(new Coment("Fulano",getDateTime(),"",R.drawable.callos));
        listaComentarios.add(new Coment("Ruben",getDateTime(),"",R.drawable.bravas));
        listaComentarios.add(new Coment("Miguel",getDateTime(),"",R.drawable.hamburguesa));
        listaComentarios.add(new Coment("Raul",getDateTime(),"",R.drawable.croquetas));

    }

    private String getDateTime() {
        Date c = Calendar.getInstance().getTime();
        SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy", Locale.getDefault());
        String formattedDate = df.format(c);
        return formattedDate;
    }


    private void getData() {
        if (getIntent().hasExtra("envioObjetoPrueba")) {
            platoRecivido = (Plato) getIntent().getSerializableExtra("envioObjetoPrueba");
        } else {
            Toast.makeText(this, "No data found", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onClick(View view) {
        if(view.getId()==R.id.button3){
            carrito.add(platoRecivido);
            Toast.makeText(this, "se añadio a la nota.", Toast.LENGTH_SHORT).show();
        }
        Log.d("CARRITO", "onClick: carrito.size "+carrito.size());
        for (Plato plato:carrito) {
            Log.d("CARRITO", "foreach:carrito "+plato.nombre);
        }
    }
}