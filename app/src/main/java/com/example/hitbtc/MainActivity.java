package com.example.hitbtc;

import android.content.Context;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.example.hitbtc.fragments.CurrencyListFragment;

public class MainActivity extends AppCompatActivity {

    private CurrencyListFragment currencyListFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (getSupportFragmentManager().getBackStackEntryCount() == 0) {
            currencyListFragment = new CurrencyListFragment();
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, currencyListFragment).commit();
        }
    }

    public boolean isOnline() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        if (cm.getActiveNetworkInfo() != null && cm.getActiveNetworkInfo().isConnectedOrConnecting()) {
            return true;
        } else {
            Toast.makeText(this.getApplicationContext(), R.string.no_internet_connection, Toast.LENGTH_SHORT).show();
            return false;
        }
    }
}
