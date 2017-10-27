package com.example.sistemas.mapas;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import com.example.sistemas.mapas.api.DatosApi;
import com.example.sistemas.mapas.models.Cai;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private Retrofit retrofit;

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        retrofit= new Retrofit.Builder().baseUrl("https://www.datos.gov.co/resource/").addConverterFactory(GsonConverterFactory.create()).build();

    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {

        mMap = googleMap;

        DatosApi service = retrofit.create(DatosApi.class);
        Call<List<Cai>> caiCall=service.obtenerListaCai();

        caiCall.enqueue(new Callback<List<Cai>>() {
            @Override
            public void onResponse(Call<List<Cai>> call, Response<List<Cai>> response) {

                if(response.isSuccessful()){

                    List listaCai= response.body();
                    for (int i=0;i<listaCai.size();i++){

                        Cai m= (Cai) listaCai.get(i);

                        //Agregar los marcadores en los puntos de la lista llamada
                        LatLng marcador = new LatLng(m.getLatitud(),m.getLongitud());
                        mMap.addMarker(new MarkerOptions()
                                .position(marcador).title(m.getNombre())
                                .snippet(m.getUbicacion())
                                .icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_action_name)));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(marcador,12));


                        //Agregar botones de Zoom
                        mMap.getUiSettings().setZoomControlsEnabled(true);


                    }

                }else{

                    //Log.e(TAG,"onResponse" + response.errorBody());
                }

            }

            @Override
            public void onFailure(Call<List<Cai>> call, Throwable t) {

                //Log.e(TAG,"onFailure" + t.getMessage());

            }
        });



    }
}
