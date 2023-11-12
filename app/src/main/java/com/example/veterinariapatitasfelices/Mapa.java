package com.example.veterinariapatitasfelices;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class Mapa extends AppCompatActivity implements OnMapReadyCallback, GoogleMap.OnMapClickListener{

    GoogleMap mMap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mapa);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.maps);
        mapFragment.getMapAsync(this);
    }
    @Override
    public void onMapReady(@NonNull GoogleMap googleMap){
        mMap = googleMap;
        this.mMap.setOnMapClickListener(this);
        LatLng Veterinaria = new LatLng(-38.739393954111975, -72.62381583107683);
        mMap.addMarker(new MarkerOptions().position(Veterinaria).title("Veterinaria Patitas Felices"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(Veterinaria));
    }
    @Override
    public void onMapClick(@NonNull LatLng latLng){
    }

    public void Atras(View view){
        Intent Atras = new Intent(this, MainActivity.class);
        startActivity(Atras);
    }



}