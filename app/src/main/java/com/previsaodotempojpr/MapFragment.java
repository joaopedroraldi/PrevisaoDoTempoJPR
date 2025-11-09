package com.previsaodotempojpr;

import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class MapFragment extends Fragment implements OnMapReadyCallback {

    private GoogleMap mMap;
    private String currentCity;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_map, container, false);

        MainActivity mainActivity = (MainActivity) getActivity();
        if (mainActivity != null) {
            this.currentCity = mainActivity.getCurrentCity();
        }

        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        if (mapFragment != null) {
            mapFragment.getMapAsync(this);
        }

        return view;
    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        mMap = googleMap;
        updateMapLocation(this.currentCity);
    }

    public void updateCity(String newCity) {
        if (isAdded() && mMap != null) {
            this.currentCity = newCity;
            updateMapLocation(newCity);
        }
    }

    private void updateMapLocation(String city) {
        if (city == null || city.isEmpty() || getContext() == null) {
            return;
        }

        mMap.clear(); // Limpa marcadores antigos
        Geocoder geocoder = new Geocoder(getContext(), Locale.getDefault());

        try {
            List<Address> addresses = geocoder.getFromLocationName(city, 1);
            if (addresses != null && !addresses.isEmpty()) {
                Address address = addresses.get(0);
                LatLng cityLocation = new LatLng(address.getLatitude(), address.getLongitude());
                mMap.addMarker(new MarkerOptions().position(cityLocation).title("Localização: " + city));
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(cityLocation, 12f));
            } else {
                // Fallback se o geocoder não encontrar a cidade
                setFallbackLocation();
            }
        } catch (IOException e) {
            e.printStackTrace();
            // Fallback em caso de erro de rede/IO
            setFallbackLocation();
        }
    }

    private void setFallbackLocation() {
        LatLng joinville = new LatLng(-26.3031, -48.8456);
        mMap.addMarker(new MarkerOptions().position(joinville).title("Localização: Joinville (Padrão)"));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(joinville, 12f));
    }
}