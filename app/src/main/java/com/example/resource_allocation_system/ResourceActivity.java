package com.example.resource_allocation_system;

import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.IOException;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class ResourceActivity extends AppCompatActivity {
    private LinearLayout resourcesLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.resources);

        // Initialize views
//        resourceNameTextView = findViewById(R.id.resourceNameTextView);
        resourcesLayout = findViewById(R.id.resourcesLayout); // Reference to a LinearLayout

        // Call the method to fetch data from the API
        fetchDataFromAPI();
    }

    private void fetchDataFromAPI() {
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url("https://mercyjac-res.onrender.com/api/resources/all")
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    String responseData = response.body().string();

                    // Process the JSON response
                    try {
                        JSONArray jsonArray = new JSONArray(responseData);

                        // Loop through all resources in the JSON array
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject resource = jsonArray.getJSONObject(i);
                            final String resourceName = resource.getString("resourceName");
                            final String resourceDescription = resource.getString("description");

                            // Update UI on the main thread
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    // Create TextViews dynamically for each resource
                                    TextView resourceTextView = new TextView(ResourceActivity.this);
                                    resourceTextView.setTextColor(getResources().getColor(R.color.white));
                                    resourceTextView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 18);
                                    resourceTextView.setText(resourceName + ": " + resourceDescription);

                                    // Add the TextView to the LinearLayout
                                    resourcesLayout.addView(resourceTextView);
                                }
                            });
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                } else {
                    Log.e("API Error", "Failed to fetch data");
                }
            }
        });
    }
}
