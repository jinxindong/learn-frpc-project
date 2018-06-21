package fzu.edu.frpc.core.until;

import com.google.gson.Gson;
import okhttp3.*;

import java.io.IOException;
import java.util.Map;

/**
 * @author jinxindong
 * @create 2018-06-11 19:15
 */

public class OkHttpRemoteImpl implements Remote {
    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");

    @Override
    public boolean getRemoteInfo(String url) {
        Request request = new Request.Builder()
                .url(url)
                .build();

        return doCallHttp(request);
    }

    @Override
    public boolean postRemoteInfo(String url, Map<String, String> params) {
        String json = new Gson().toJson(params);
        RequestBody body = RequestBody.create(JSON, json);
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();
        return doCallHttp(request);
    }

    private boolean doCallHttp(Request request) {
        OkHttpClient client = new OkHttpClient();
        try {
            Response response = client.newCall(request).execute();
            if (response.isSuccessful()) {
                return true;
            }
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return false;
    }

}
