package com.previsaodotempojpr;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class WeatherListFragment extends Fragment {

    private RecyclerView recyclerView;
    private WeatherAdapter adapter;
    private List<Forecast> forecastList;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_weather_list, container, false);

        recyclerView = view.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        forecastList = new ArrayList<>();
        adapter = new WeatherAdapter(forecastList);
        recyclerView.setAdapter(adapter);

        fetchWeatherData();

        return view;
    }

    private void fetchWeatherData() {
        String city = "Joinville";
        String apiKey = "SUA_CHAVE_API"; // SUBSTITUA PELA SUA CHAVE
        String url = "https://api.hgbrasil.com/weather?key=" + apiKey + "&city_name=" + city;

        RequestQueue queue = Volley.newRequestQueue(getContext());

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONObject results = response.getJSONObject("results");
                            JSONArray forecastArray = results.getJSONArray("forecast");

                            for (int i = 0; i < forecastArray.length(); i++) {
                                JSONObject forecastObject = forecastArray.getJSONObject(i);
                                String date = forecastObject.getString("date");
                                String description = forecastObject.getString("description");
                                String max = forecastObject.getString("max");
                                String min = forecastObject.getString("min");
                                forecastList.add(new Forecast(date, description, max, min));
                            }
                            adapter.notifyDataSetChanged();

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // TODO: Tratar erro
                    }
                });

        queue.add(jsonObjectRequest);
    }
}