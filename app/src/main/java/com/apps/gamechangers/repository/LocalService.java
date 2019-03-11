package com.apps.gamechangers.repository;

import android.content.Context;
import android.content.res.Resources;
import android.support.annotation.RawRes;

import com.apps.gamechangers.R;
import com.apps.gamechangers.model.Response;
import com.google.gson.Gson;

import java.io.InputStream;

public class LocalService implements DataSource {

    private final Context context;

    LocalService(Context context) {
        this.context = context;
    }

    @Override
    public void getUsers(String startCursor, final Callback callback) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Response response = getResponse(context, R.raw.users);
                callback.onComplete(response, response.getNextCursor());
            }
        }).start();
    }

    private Response getResponse(Context context, @RawRes int rawFile) {
        Response response = null;
        try {
            Resources res = context.getResources();
            InputStream in_s = res.openRawResource(rawFile);

            byte[] b = new byte[in_s.available()];
            in_s.read(b);
            Gson gson = new Gson();
            response = gson.fromJson(new String(b), Response.class);
        } catch (Exception e) {
        }
        return response;
    }
}
