package rr.uicommunitim;

import androidx.annotation.Nullable;

import com.android.volley.NetworkResponse;
import com.android.volley.Response;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONObject;

import java.io.UnsupportedEncodingException;

public class JsonEmptyRequest extends JsonObjectRequest {
    public JsonEmptyRequest(String url, Response.Listener<JSONObject> listener, @Nullable Response.ErrorListener errorListener) {
        super(url, listener, errorListener);
    }

    public JsonEmptyRequest(int post, String url, JSONObject js, Response.Listener<JSONObject> jsonObjectListener, Response.ErrorListener errorListener) {
        super(post,url,js,jsonObjectListener,errorListener);
    }

    @Override
    protected Response<JSONObject> parseNetworkResponse(NetworkResponse response) {
        try {
            if (response.data.length == 0) {
                byte[] responseData = "{}".getBytes("UTF8");
                response = new NetworkResponse(response.statusCode, responseData, response.notModified, response.networkTimeMs, response.allHeaders);
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return super.parseNetworkResponse(response);
    }
}
