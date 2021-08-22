package com.example.moviesapp.ui.splash;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;

import androidx.appcompat.app.AlertDialog;

import com.example.moviesapp.R;

public class SplashPresenter {

    private Activity activity;

    public SplashPresenter(Activity activity) {
        this.activity = activity;
    }

    public boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager = ((ConnectivityManager) activity.getSystemService(Context.CONNECTIVITY_SERVICE));
        return connectivityManager.getActiveNetworkInfo() != null && connectivityManager.getActiveNetworkInfo().isConnected();
    }

    public void showNetworkInformationDialog() {
        new AlertDialog.Builder(activity)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setTitle(activity.getString(R.string.internet_connection_alert_title))
                .setMessage(activity.getString(R.string.internet_connection_alert_desc))
                .setPositiveButton(activity.getString(R.string.alert_dialog_close_button), (dialogInterface, i) -> activity.finish()).show();
    }
}
