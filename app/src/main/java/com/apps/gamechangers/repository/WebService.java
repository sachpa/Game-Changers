package com.apps.gamechangers.repository;

import android.content.Context;
import android.net.Uri;
import android.text.TextUtils;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.BasicNetwork;
import com.android.volley.toolbox.DiskBasedCache;
import com.android.volley.toolbox.HurlStack;
import com.android.volley.toolbox.JsonObjectRequest;
import com.apps.gamechangers.model.Response;
import com.google.gson.Gson;

import org.json.JSONObject;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class WebService implements DataSource {

    private static final String VOLLEY_CACHE_DIRECTORY = "volley";
    private static final int DEFAULT_DISK_USAGE_BYTES = 1024;
    private static final String BASE_SERVER_URL = "https://slack.com/api/users.list";
    private static final String AUTH_TOKEN_HEADER = "Authorization";
    private static final String AUTH_TOKEN_VALUE = "Bearer xoxp-5048173296-5048487710-19045732087-b5427e3b46";
    private static final String PARAM_LIMIT_KEY = "limit";
    private static final String PARAM_LIMIT_VALUE = "7";
    private static final String PARAM_CURSOR_KEY = "limit";

    private Context context;

    WebService(Context context) {
        this.context = context.getApplicationContext();
    }

    @Override
    public void getUsers(String nextCursor, final Callback callback) {

        final Uri.Builder uriBuilder = Uri.parse(BASE_SERVER_URL)
                .buildUpon()
                .appendQueryParameter(PARAM_LIMIT_KEY, PARAM_LIMIT_VALUE);

        if (!TextUtils.isEmpty(nextCursor)) {
            uriBuilder.appendQueryParameter(PARAM_CURSOR_KEY, nextCursor);
        }

        final RequestQueue requestQueue = getQueue();
        final JsonObjectRequest jsonObjectRequest =
                new JsonObjectRequest(Request.Method.GET,
                        uriBuilder.toString(),
                        null,
                        new com.android.volley.Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                requestQueue.stop();
                                final Response responseObject = new Gson().fromJson(response.toString(), Response.class);
                                callback.onComplete(responseObject, responseObject.getNextCursor());
                            }
                        },
                        new com.android.volley.Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                requestQueue.stop();
                                callback.onError();
                            }
                        }) {
                    @Override
                    public Map<String, String> getHeaders() throws AuthFailureError {
                        final Map<String, String> headers = new HashMap<>();
                        headers.put(AUTH_TOKEN_HEADER, AUTH_TOKEN_VALUE);
                        return headers;
                    }
                };

        requestQueue.start();
        requestQueue.add(jsonObjectRequest);
    };


    /**
     * Return optimized request queue.
     * Default implementation of Volley returns queue with 4 threads started, we don't need it here.
     * @return request queue.
     */
    private RequestQueue getQueue() {
        final File file = new File(context.getCacheDir(), VOLLEY_CACHE_DIRECTORY);
        return new RequestQueue(new DiskBasedCache(file, DEFAULT_DISK_USAGE_BYTES), new BasicNetwork(new HurlStack()), 1);
    }
}
