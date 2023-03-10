package com.cieep.pmdm_c_08_retrofit_ejercicio;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.cieep.pmdm_c_08_retrofit_ejercicio.adapters.UsersAdapter;
import com.cieep.pmdm_c_08_retrofit_ejercicio.conexiones.ApiConexiones;
import com.cieep.pmdm_c_08_retrofit_ejercicio.conexiones.RetrofitObject;
import com.cieep.pmdm_c_08_retrofit_ejercicio.modelos.DataItem;
import com.cieep.pmdm_c_08_retrofit_ejercicio.modelos.Respuesta;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.net.ssl.HttpsURLConnection;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {


    private int page = 1;

    private RecyclerView contenedor;
    private UsersAdapter adapter;
    private RecyclerView.LayoutManager lm;
    private List<DataItem> users;
    // -----------
    private Retrofit retrofit;
    private ApiConexiones api;
    //
    private Button btnPage1;
    private Button btnPage2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        contenedor = findViewById(R.id.contenedor);
        btnPage1 = findViewById(R.id.btnPage1);
        btnPage2 = findViewById(R.id.btnPage2);

        users = new ArrayList<>();
        adapter = new UsersAdapter(users, R.layout.user_view_holder, this);
        lm = new LinearLayoutManager(this);
        contenedor.setAdapter(adapter);
        contenedor.setLayoutManager(lm);

        retrofit = RetrofitObject.getConexion();
        api = retrofit.create(ApiConexiones.class);

        contenedor.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                if (!recyclerView.canScrollVertically(1) && page <= 2) {
                    cargarUsers(String.valueOf("2"));
                    Toast.makeText(MainActivity.this, "Pagina cargada: "+page, Toast.LENGTH_SHORT).show();
                }
            }
        });

        cargarUsers("1");

    }

    private void cargarUsers(String page) {
        Call<Respuesta> doGetUsers = api.getUsers(page);

        doGetUsers.enqueue(new Callback<Respuesta>() {
            @Override
            public void onResponse(Call<Respuesta> call, Response<Respuesta> response) {
                if (response.code() == HttpsURLConnection.HTTP_OK && response.body() != null) {
                    // adapter.notifyItemRangeRemoved(0, users.size());
                   // users.clear();
                    int actual = users.size();
                    users.addAll(response.body().getData());
                    adapter.notifyItemRangeInserted(actual, users.size());
                    if (response.body().getPage() == 1) {
                        btnPage1.setEnabled(false);
                        btnPage2.setEnabled(true);
                    }
                    else {
                        btnPage1.setEnabled(true);
                        btnPage2.setEnabled(false);
                    }
                    MainActivity.this.page++;
                }
            }

            @Override
            public void onFailure(Call<Respuesta> call, Throwable t) {

            }
        });
    }

    public void users(View v){
        Button btn = (Button) v;
        cargarUsers(btn.getText().toString());
    }
}