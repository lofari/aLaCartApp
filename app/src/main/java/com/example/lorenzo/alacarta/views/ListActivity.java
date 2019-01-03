package com.example.lorenzo.alacarta.views;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;

import com.example.lorenzo.alacarta.CallListener;
import com.example.lorenzo.alacarta.R;
import com.example.lorenzo.alacarta.Restaurante;
import com.example.lorenzo.alacarta.RestoAdapter;
import com.example.lorenzo.alacarta.SPManager;
import com.example.lorenzo.alacarta.WebServiceRepository;

import java.util.ArrayList;
import java.util.List;

public class ListActivity extends AppCompatActivity {
    private static final String TAG = "ListActivity";
    private List<Restaurante> restoList = new ArrayList<>();
    private ListView mListView;
    private RestoAdapter mAdapter;
    private SPManager mSPManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        mAdapter = new RestoAdapter(this, restoList);
        mSPManager = new SPManager(this);
        setViews();
        getListFromService();

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(ListActivity.this, DetailActivity.class);
                Bundle bundle = new Bundle();
                Restaurante selectedRestorant = mAdapter.getItem(i);
                bundle.putSerializable("restaurant", selectedRestorant );
                intent.putExtra("mi_bundle", bundle);
                startActivity(intent);
            }
        });
    }

    private void setViews(){
        mListView = findViewById(R.id.listView);

        //setADAPTER
        //mAdapter = new ArrayAdapter<String>(ListActivity.this,R.layout.item_list, restoList);
        mListView.setAdapter( mAdapter );
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.navigation_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (id) {
            case R.id.menuProfile:
                Intent profileIntent = new Intent(this, ProfileActivity.class);
                startActivity(profileIntent);
                break;
            case R.id.menuFavourite:
                Intent favIntent = new Intent(this, FavouriteActivity.class);
                startActivity(favIntent);
                break;
            case R.id.menuLogout:
                logout();
                break;

            default:
                return super.onOptionsItemSelected(item);

        }

        return super.onOptionsItemSelected(item);
    }

    public void getListFromService () {
        new WebServiceRepository()
                .getRestaurant(new CallListener<List<Restaurante>>() {
                    @Override
                    public void onSucess(List<Restaurante> restaurantes) {
                        restoList = restaurantes;
                        mAdapter.setRestoList(restoList);
                        mAdapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onError(Throwable error) {
                        error.printStackTrace();
                    }
                });
    }

//    public void SearchItemOnTextChange() {
//        mSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
//            @Override
//            public boolean onQueryTextSubmit(String query) {
//                return false;
//            }
//
//            @Override
//            public boolean onQueryTextChange(String newText) {
//                mAdapter.getFilter().filter(newText);
//                return false;
//            }
//        });
//    }

    private void logout(){
//        SharedPreferences preferences = getSharedPreferences("bla",Context.MODE_PRIVATE);
//        SharedPreferences.Editor editor = preferences.edit();
//        editor.clear();
//        editor.commit();
        mSPManager.deleteSharedPref();
        finish();
    }
}
