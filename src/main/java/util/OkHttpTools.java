package util;

import com.google.gson.Gson;
import okhttp3.*;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * OkHttpTools.
 */
public final class OkHttpTools {

    /**
     * The constant JSON.
     */
    private static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");

    private static final OkHttpTools OK_HTTP_TOOLS = new OkHttpTools();

    private static final Gson GSON = new Gson();

    private final OkHttpClient client;

    private OkHttpTools() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.connectTimeout(10, TimeUnit.SECONDS);
        builder.readTimeout(10, TimeUnit.SECONDS);
        builder.writeTimeout(10, TimeUnit.SECONDS);
        client = builder.build();
    }

    /**
     * Gets instance.
     *
     * @return the instance
     */
    public static OkHttpTools getInstance() {
        return OK_HTTP_TOOLS;
    }

    /**
     * Post string.
     *
     * @param url  the url
     * @param json the json
     * @return the string
     * @throws IOException the io exception
     */
    public String post(final String url, final String json) throws IOException {
        RequestBody body = RequestBody.create(JSON, json);
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();
        return client.newCall(request).execute().body().string();

    }

    /**
     * Post t.
     *
     * @param <T>      the type parameter
     * @param url      the url
     * @param json     the json
     * @param classOfT the class of t
     * @return the t
     * @throws IOException the io exception
     */
    public <T> T post(final String url, final String json, final Class<T> classOfT) throws IOException {
        RequestBody body = RequestBody.create(JSON, json);
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();
        Response response = client.newCall(request).execute();
        assert response.body() != null;
        final String result = response.body().string();
        return GSON.fromJson(result, classOfT);

    }

    /**
     * Post string.
     *
     * @param url    the url
     * @param params the params
     * @return the string
     * @throws IOException the io exception
     */
    public String post(final String url, final Map<String, String> params) throws IOException {
        String json = GSON.toJson(params);
        RequestBody body = RequestBody.create(JSON, json);
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();
        return client.newCall(request).execute().body().string();
    }

    /**
     * Gets gson.
     *
     * @return the gson
     */
    public Gson getGson() {
        return GSON;
    }

}
