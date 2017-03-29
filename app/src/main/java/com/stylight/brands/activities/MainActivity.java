package com.stylight.brands.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.stylight.brands.R;
import com.stylight.brands.adapters.ListAdapterBrands;
import com.stylight.brands.adapters.ListAdapterSelected;
import com.stylight.brands.model.BrandResponse;
import com.stylight.brands.model.Brands;
import com.stylight.brands.model.Names;
import com.stylight.brands.model.SelectedNames;
import com.stylight.brands.rest.ApiClient;
import com.stylight.brands.rest.ApiInterface;
import com.stylight.brands.utils.Utils;

import java.util.ArrayList;
import java.util.List;
import java.util.jar.Attributes;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.stylight.brands.Constants.API_KEY;

public class MainActivity extends AppCompatActivity {


    private static final String TAG = MainActivity.class.getSimpleName();

    ListView listView;
    ListView listViewSelected;
    ArrayList<Names> brands = new ArrayList<>();
    ArrayList<SelectedNames> selected = new ArrayList<>();
    ListAdapterBrands adapter;
    ListAdapterSelected adapterSelected;
    ProgressBar pg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        toolbar.setTitle("Brands");

        listView = (ListView) findViewById(R.id.listview);
        listViewSelected = (ListView) findViewById(R.id.listview_selected);

        pg = (ProgressBar) findViewById(R.id.progressBar);

        adapterSelected = new ListAdapterSelected(MainActivity.this, selected);
        listViewSelected.setAdapter(adapterSelected);

        fetchSearch();
    }

    public void addToList(Integer pos) {

        SelectedNames obj = new SelectedNames(brands.get(pos).getId(), brands.get(pos).getName(), true);
        selected.add(obj);
        brands.get(pos).setChecked(true);

        Utils.setListViewHeightBasedOnChildren(listViewSelected);
        adapterSelected.notifyDataSetChanged();

    }


    public void removeFromList(Integer id) {


        for (int i = 0; i < selected.size(); i++) {
            int item = selected.get(i).getId();
            if (item == id)
                selected.remove(i);
        }


        Utils.setListViewHeightBasedOnChildren(listViewSelected);
        adapterSelected.notifyDataSetChanged();
    }

    public void removeFromTopList(Integer id) {


        for (int i = 0; i < selected.size(); i++) {
            int item = selected.get(i).getId();
            System.out.println(item);
            if (item == id)
                selected.remove(i);
        }


        Utils.setListViewHeightBasedOnChildren(listViewSelected);
        adapterSelected.notifyDataSetChanged();

        for (int i = 0; i < brands.size(); i++) {
            int item = brands.get(i).getId();
            if (item == id)
                System.out.println(i);
            brands.get(i).setChecked(false);
        }


        Utils.setListViewHeightBasedOnChildren(listView);
        adapter.notifyDataSetChanged();
    }


    private void fetchSearch() {

        pg.setVisibility(View.VISIBLE);
        ApiInterface apiService =
                ApiClient.getClient().create(ApiInterface.class);


        Call<BrandResponse> call = apiService.getBrands(API_KEY);


        call.enqueue(new Callback<BrandResponse>() {
            @Override
            public void onResponse(Call<BrandResponse> call, Response<BrandResponse> response) {
                int statusCode = response.code();

                pg.setVisibility(View.GONE);

                List<Brands> results = response.body().getBrands();

                for (int i = 0; i < results.size(); i++) {

                    Names obj = new Names(results.get(i).getId(), results.get(i).getName(), false);
                    brands.add(obj);
                }

                adapter = new ListAdapterBrands(MainActivity.this, brands);
                listView.setAdapter(adapter);
                Utils.setListViewHeightBasedOnChildren(listView);


            }

            @Override
            public void onFailure(Call<BrandResponse> call, Throwable t) {
                // Log error here since request failed
                Log.e(TAG, t.toString());
            }
        });


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
