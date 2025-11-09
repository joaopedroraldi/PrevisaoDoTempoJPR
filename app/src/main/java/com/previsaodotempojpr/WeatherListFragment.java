package com.previsaodotempojpr;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
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

        MainActivity mainActivity = (MainActivity) getActivity();
        if (mainActivity != null) {
            fetchWeatherData(mainActivity.getCurrentCity());
        }

        return view;
    }

    public void updateCity(String newCity) {
        if (isAdded() && getContext() != null) {
            fetchWeatherData(newCity);
        }
    }

    private void fetchWeatherData(String city) {
        String apiKey = "715d2612";
        String url = "https://api.hgbrasil.com/weather?key=" + apiKey + "&city_name=" + city;

        RequestQueue queue = Volley.newRequestQueue(requireContext());

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, response -> {
                    try {
                        // Limpa a lista antes de adicionar novos itens para evitar duplicatas
                        forecastList.clear();
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
                        Toast.makeText(getContext(), "Erro ao processar os dados da previsão.", Toast.LENGTH_SHORT).show();
                    }
                }, error -> {
                    // Limpa a lista para que o usuário não veja dados antigos e incorretos
                    forecastList.clear();
                    adapter.notifyDataSetChanged();
                    Toast.makeText(getContext(), "Erro: Cidade não encontrada ou problema de conexão.", Toast.LENGTH_LONG).show();
                });

        queue.add(jsonObjectRequest);
    }
}