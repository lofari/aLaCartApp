package com.example.lorenzo.alacarta.views;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
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

public class DrawerActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private static final String TAG = "DrawerActivity";
    private List<Restaurante> restoList = new ArrayList<>();
    private ListView mListView;
    private RestoAdapter mAdapter;
    private SPManager mSPManager;
    private SearchView mSearchView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawer);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        ///////////

        mAdapter = new RestoAdapter(this, restoList);
        mSPManager = new SPManager(this);
        setViews();
        getListFromService();

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(DrawerActivity.this, DetailActivity.class);
                intent.putExtra("mi_bundle", mAdapter.getItem(i) );
                startActivity(intent);
            }
        });


    }

    private void setViews(){
        mListView = findViewById(R.id.listView);
        mListView.setAdapter( mAdapter );
        mSearchView = findViewById(R.id.action_search);
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

        public void SearchItemOnTextChange() {
        mSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                mAdapter.getFilter().filter(newText);
                return false;
            }
        });
    }

    private void logout(){
        Intent intent = new Intent(this, MainActivity.class);
        mSPManager.deleteSharedPref();
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.drawer, menu);
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

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_profile) {
            Intent profileIntent = new Intent(this, ProfileActivity.class);
            Log.d(TAG, "onNavigationItemSelected: " + "intent 1");
            startActivity(profileIntent);
        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_favourites) {
            Intent favIntent = new Intent(this, FavouriteActivity.class);
            Log.d(TAG, "onNavigationItemSelected: " + "intent 2");
            startActivity(favIntent);
        } else if (id == R.id.nav_logout) {
            logout();
        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
