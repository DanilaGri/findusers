package com.master.findusers.util;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AlertDialog;

import retrofit2.HttpException;

public class DialogUtil {
    private static volatile DialogUtil instance;
    private AlertDialog alertDialog;

    private DialogUtil() {
    }

    public static DialogUtil getInstance() {
        if (instance == null) {
            synchronized (DialogUtil.class) {
                if (instance == null) {
                    instance = new DialogUtil();
                }
            }
        }
        return instance;
    }

    public void showErrorDialog(Activity activity,
                                final String msg) {

        if (alertDialog != null) {
            alertDialog.dismiss();
        }

        AlertDialog.Builder builder = new AlertDialog.Builder(activity);

        builder.setTitle("Error");
        builder.setMessage(msg);
        builder.setPositiveButton("OK", (dialog, which) -> dialog.dismiss());
        // Showing Alert Message
        alertDialog = builder.create();
        alertDialog.setCancelable(false);
        alertDialog.show();

    }

    public void showErrorDialog(Activity activity,
                                final Throwable e) {

        if (alertDialog != null) {
            alertDialog.dismiss();
        }

        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setTitle("Error");
        builder.setPositiveButton("OK", (dialog, which) -> {
            dialog.dismiss();
        });
        String msg = "";
        if (!isInternetAvailable(activity)) {
            msg = "Error network connection!";
        } else {
            if (e instanceof HttpException) {
                int code = ((HttpException) e).code();
                // error case
                switch (code) {
                    case 404:
                        msg = "not found";
                        break;
                    case 500:
                        msg = "server broken";
                        break;
                }
//                if(msg.equals("")){
//                    JsonParser parser = new JsonParser();
//                    JsonElement mJson = null;
//                    try {
//                        mJson = parser.parse(((HttpException) e).response().errorBody().string());
//                        Gson gson = new Gson();
//                ErrorDataResponse errorResponse = gson.fromJson(mJson, ErrorDataResponse.class);
//                msg = errorResponse.getData().getMessage();
//                    } catch (IOException ex) {
//                        ex.printStackTrace();
//                    }
//                }
            }
            if (msg.equals("")) {
                msg = e.getMessage();
            }

        }
        builder.setMessage(msg);

        // Showing Alert Message
        alertDialog = builder.create();
        alertDialog.setCancelable(false);
        alertDialog.show();

    }

    private boolean isInternetAvailable(Activity activity) {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) activity.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    public void destroyDialog() {
        if (alertDialog != null) {
            alertDialog.dismiss();
        }
    }
}
