package com.frsummit.http_request;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends Activity {
    TextView textView;
    Button button;
    RequestQueue requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.textView);
        button = findViewById(R.id.btnParse);

        requestQueue = Volley.newRequestQueue(this);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                jsonParse();
            }
        });
    }

    private void jsonParse() {
        System.out.println("Working 1");
        String url = "https://raw.githubusercontent.com/SaiNitesh/REST-Web-services/master/RESTfulWS/json_file.json";
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        System.out.println("Working 2");
                        try {
                            System.out.println("Working 3");
                            JSONArray jsonArray = response.getJSONArray("countryItems");
                            System.out.println(jsonArray);
                            for(int i=0; i<jsonArray.length(); i++) {
                                JSONObject jsonObject = jsonArray.getJSONObject(i);

//                                int userId = jsonObject.getInt("userId");
//                                int id = jsonObject.getInt("id");

                                String userId = jsonObject.getString("nm");
                                String id = jsonObject.getString("cty");
                                String title = jsonObject.getString("hse");
                                String body = jsonObject.getString("yrs");

                                textView.append(userId + ", " + id + ", " + ", " + title + ", " + body);
                            }
                        } catch (JSONException e) {
                            System.out.println("Working 4");
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println("Working 5");
                error.printStackTrace();
            }
        });

        requestQueue.add(request);

        System.out.println("request");
        System.out.println(request);
    }
}

/**
 * https://raw.githubusercontent.com/SaiNitesh/REST-Web-services/master/RESTfulWS/json_file.json
 *
     {
         "countryItems": [
             {
                 "nm": "Edmund lronside",
                 "cty": "United Kingdom",
                 "hse": "House of Wessex",
                 "yrs": "1016"
             },
             {
                 "nm": "Cnut",
                 "cty": "United Kingdom",
                 "hse": "House of Denmark",
                 "yrs": "1016"
            },
            {
                 "nm": "Harold I Harefoot",
                 "cty": "United Kingdom",
                 "hse": "House of Denmark",
                 "yrs": "1035"
            },
            {
                 "nm": "Harthacanut",
                 "cty": "United Kingdom",
                 "hse": "House of Denmark",
                 "yrs": "1040"
            },
            {
                 "nm": "Edward the Confessor",
                 "cty": "United Kingdom",
                 "hse": "House of Wessex",
                 "yrs": "1042"
             }
         ]
     }

 * */
