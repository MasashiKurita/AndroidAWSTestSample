package com.example.masashiakurita.androidawstestsample;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebView;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

public class MainActivity extends Activity {

    private static final String TAG = MainActivity.class.getName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getLocalIpv4Address();

        WebView myWebView = (WebView) findViewById(R.id.webview);
        myWebView.loadUrl("http://www.google.com");
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

    private void getLocalIpv4Address() {
        try {
            Enumeration<NetworkInterface> networkInterfaceEnum = NetworkInterface.getNetworkInterfaces();
            while (networkInterfaceEnum.hasMoreElements()) {
                NetworkInterface networkInterface = networkInterfaceEnum.nextElement();

                Enumeration<InetAddress> ipAddressEnum = networkInterface.getInetAddresses();
                while (ipAddressEnum.hasMoreElements()) {
                    InetAddress inetAddress = (InetAddress) ipAddressEnum.nextElement();

                    if (!inetAddress.isLoopbackAddress()) {
                        Log.d(TAG, inetAddress.toString());
                    }
                }

            }
        } catch (SocketException e) {
            Log.e(TAG, e.toString());
        }
    }
}
