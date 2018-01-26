package com.jansen.sander.thumper;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.jansen.sander.thumper.fragments.AlarmFragment;
import com.jansen.sander.thumper.fragments.ControlFragment;
import com.jansen.sander.thumper.fragments.NeoPixelFragment;
import com.jansen.sander.thumper.interfaces.CommunicationService;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class MainActivity extends AppCompatActivity implements NeoPixelFragment.OnFragmentInteractionListener,
                                                                ControlFragment.OnFragmentInteractionListener,
                                                                AlarmFragment.OnFragmentInteractionListener {

    private NeoPixelFragment fragmentNeoPixel = NeoPixelFragment.newInstance(1);
    private ControlFragment fragmentControl = new ControlFragment();
    private AlarmFragment fragmentAlarm = new AlarmFragment();
    private FragmentManager fragmentManager = getSupportFragmentManager();
    private Retrofit retrofit;
    private CommunicationService communicationService;

    private String nodejs_ip;
    private String nodejs_port;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            boolean returnStatus = false;
            try {
                switch (item.getItemId()) {
                    case R.id.navigation_neopixel:
                        fragmentManager.beginTransaction()
                                .replace(R.id.fragment_container, fragmentNeoPixel)
                                .commit();

                        returnStatus = true;
                        break;
                    case R.id.navigation_control:
                        fragmentManager.beginTransaction()
                                .replace(R.id.fragment_container, fragmentControl)
                                .commit();

                        returnStatus = true;
                        break;
                    case R.id.navigation_alarm:
                        fragmentManager.beginTransaction()
                                .replace(R.id.fragment_container, fragmentAlarm)
                                .commit();

                        returnStatus = true;
                        break;

                    default:
                        break;
                }
                returnStatus = false;

            } catch (Exception e){
                Toast.makeText(getApplicationContext(), "Something went wrong", Toast.LENGTH_SHORT);
            }
            return returnStatus;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

    protected void onStart() {
        super.onStart();
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        nodejs_ip  = sharedPref.getString(SettingsActivity.NODEJS_SERVER_IP, "192.168.1.100");
        nodejs_port = sharedPref.getString(SettingsActivity.NODEJS_SERVER_PORT, "3000");

        String address = "http://" + nodejs_ip + ":" + nodejs_port + "/";

        retrofit = new Retrofit.Builder()
                .baseUrl(address)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        communicationService = retrofit.create(CommunicationService.class);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.settings_menu, menu);
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
            startActivity(new Intent(this, SettingsActivity.class));
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onFragmentInteraction(Uri uri) {


    }

    public CommunicationService getRetrofitService(){
        return communicationService;
    }
}
