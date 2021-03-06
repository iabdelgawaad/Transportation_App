package com.FBLoginSample.activity;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.FBLoginSample.R;


public class MainActivity extends ActionBarActivity implements FragmentDrawer.FragmentDrawerListener,Communicator {

    private static String TAG = MainActivity.class.getSimpleName();

    private Toolbar mToolbar;
    private FragmentDrawer drawerFragment;
    SharedPreferences sharedPref;
    FragmentManager manager;
    Tab1 tab1;
    Tab2 tab2;
    Tab3 tab3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        mToolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        drawerFragment = (FragmentDrawer)
                getSupportFragmentManager().findFragmentById(R.id.fragment_navigation_drawer);
        drawerFragment.setUp(R.id.fragment_navigation_drawer, (DrawerLayout) findViewById(R.id.drawer_layout), mToolbar);
        drawerFragment.setDrawerListener(this);

        //


        //

        // display the first navigation drawer view on app launch
        displayView(0);
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

        if(id == R.id.action_search){
            Toast.makeText(getApplicationContext(), "Search action is selected!", Toast.LENGTH_SHORT).show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onDrawerItemSelected(View view, int position) {
        displayView(position);
    }

    private void displayView(int position) {
        Fragment fragment = null;
        String title = getString(R.string.app_name);
        switch (position) {
            case 0:
                fragment = new HomeFragment();
                title = getString(R.string.title_home);
                break;
            case 1:
                fragment = new FriendsFragment();
                title = getString(R.string.title_busStations);
                break;
            case 2:
                fragment = new MessagesFragment();
                title = getString(R.string.title_metroStations);
                break;
            case 3:
                fragment = new MessagesFragment();
                title = getString(R.string.title_nearby);
                break;
            case 4:
                fragment = new MessagesFragment();
                title = getString(R.string.title_navigation);
                break;
            case 5:
                logOut();
                title = getString(R.string.title_logout);
                break;
            default:
                break;
        }

        if (fragment != null) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.container_body, fragment);
            fragmentTransaction.commit();

            // set the toolbar title
            getSupportActionBar().setTitle(title);
        }

    }

    public void logOut ()
    {
        //checking sharedPrefs to see if we had signed in already - if we have, skip this activity
        sharedPref = getSharedPreferences("transportation", getApplicationContext().MODE_PRIVATE);
        boolean signedIn = sharedPref.getBoolean("signedIn", false);

        if (signedIn)
        {
            signedIn = false;
            //
            SharedPreferences preferences = getSharedPreferences("transportation", getApplicationContext().MODE_PRIVATE);
            SharedPreferences.Editor editor = preferences.edit();
            editor.putBoolean("signedIn",  signedIn);
            editor.commit();
            //
            Intent i = new Intent(MainActivity.this, com.FBLoginSample.SplashScreen.class);
            startActivity(i);

            // close this activity
            finish();
        }


    }

    @Override
    public void
    respond(String[] stations)
    {
        //manager = getSupportFragmentManager();
        tab1=  (Tab1) HomeFragment.adapter.getRegisteredFragment(0);
        //  tab1 = (Tab1) manager.findFragmentById(R.id.tab1);
        tab1.setDataFromHomeFragment(stations);
    }
}
