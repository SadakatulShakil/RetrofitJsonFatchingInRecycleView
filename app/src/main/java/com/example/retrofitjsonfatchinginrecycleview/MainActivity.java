package com.example.retrofitjsonfatchinginrecycleview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;
import android.widget.Toolbar;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.example.retrofitjsonfatchinginrecycleview.RetrofitClint.getRetrofitInstance;

public class MainActivity extends AppCompatActivity {

    //public static final String URL_DATA ="https://simplifiedcoding.net/demos/marvel/";
    private RecyclerView rvMarvel;
    private ArrayList<Hero>myHeroList;
    private CustomAdapter myAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       /* androidx.appcompat.widget.Toolbar toolbar = findViewById(R.id.toolBar);
        setSupportActionBar(toolbar);
        if(getSupportActionBar()!=null)
        {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
*/
        rvMarvel = findViewById(R.id.marvelItemsRecView);
        myHeroList = new ArrayList<>();

        rvMarvel.setLayoutManager(new LinearLayoutManager(this));
        myAdapter = new CustomAdapter(MainActivity.this, myHeroList);
        rvMarvel.setAdapter(myAdapter);

        loadingMarvelData();
    }


    private void loadingMarvelData(){
        Retrofit retrofit = RetrofitClint.getRetrofitInstance();
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Hero Is Coming...");
        progressDialog.show();

        Api api = retrofit.create(Api.class);
        Call<List<Hero>> call = api.getHeroes();
        call.enqueue(new Callback<List<Hero>>() {
            @Override
            public void onResponse(Call<List<Hero>> call, Response<List<Hero>> response) {


                if (response.code() == 200){
                    List<Hero> heroes = response.body();
                    progressDialog.dismiss();
                    // Here is the All retrofit data in heroes//
                    ///////Do now what ever do to the list/////////
                    myHeroList.addAll(heroes);
                    myAdapter.notifyDataSetChanged();
                }


            }
            @Override
            public void onFailure(Call<List<Hero>> call, Throwable t) {

                Toast.makeText(MainActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
    /*private void setSupportActionBar(Toolbar toolbar) {
    }*/
}
