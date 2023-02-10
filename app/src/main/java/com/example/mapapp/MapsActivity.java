package com.example.mapapp;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.mapapp.databinding.ActivityMapsBinding;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.Vector;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private ActivityMapsBinding binding;

    Vector<MarkerOptions> markerOptions;
    LatLng centerlocation;

    //need to change to your ip address
    private String URL = "http://192.168.0.12/Project_Hazard/allhazard.php";
    String perms[] = {"android.permission.ACCESS_FINE_LOCATION"};
    RequestQueue requestQueue;
    Gson gson;
    Project_Hazard [] project_hazards;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_maps);

        gson = new GsonBuilder().create();

        binding = ActivityMapsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        centerlocation = new LatLng(3.0, 101);

        markerOptions = new Vector<>();

        markerOptions.add(new MarkerOptions().title("Cawangan Alor Setar")
                .position(new LatLng(6.12, 100.37))
                .snippet("Open during MCO : 8am - 6pm"));

        markerOptions.add(new MarkerOptions().title("Cawangan Bayan Lepas")
                .position(new LatLng(5.29, 100.259))
                .snippet("Open during MCO : 8am - 6am"));

        markerOptions.add(new MarkerOptions().title("Cawangan Ipoh")
                .position(new LatLng(4.61, 101.08))
                .snippet("Open during MCO : 8am - 6am"));

        markerOptions.add(new MarkerOptions().title("Cawangan Gua Musang")
                .position(new LatLng(4.8721, 100.96))
                .snippet("Open during MCO : 8am - 6am"));

        markerOptions.add(new MarkerOptions().title("Cawangan Arau")
                .position(new LatLng(6.45, 100.29))
                .snippet("Open during MCO : 8am - 6am"));



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

        // Add a marker in Sydney and move the camera
        //LatLng sydney = new LatLng(-34, 151);
        // mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));

        for (MarkerOptions mark : markerOptions) {
            mMap.addMarker(mark);
        }

        enableMyLocation();
        //mMap.addMarker(markerOptions);

        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(centerlocation,8));
        //enableMyLocation();
        sendRequest();

        //mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
        //@Override
        //public boolean onMarkerClick(@NonNull Marker marker) {
        //    String markertitle = marker.getTitle();
        //    return false;
        //}
    };

    /**
     * Enables the My Location layer if the fine location permission has been granted.
     */
    private void enableMyLocation() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            //if (mMap != null) {
            mMap.setMyLocationEnabled(true);
        } else {
            //String perms[] = {"android.permission.ACCESS_FINE_LOCATION"};
            // Permission to access the location is missing. Show rationale and request permission
            ActivityCompat.requestPermissions(this,perms,200);
        }
    }

    public void sendRequest(){
        requestQueue = Volley.newRequestQueue(getApplicationContext());
        StringRequest stringRequest = new StringRequest(Request.Method.GET,URL,onSuccess,onError);
        requestQueue.add(stringRequest);
    }

    public Response.Listener<String> onSuccess = new Response.Listener<String>(){
        @Override
        public void onResponse(String response) {

            project_hazards = gson.fromJson(response, Project_Hazard[].class);
            Log.d("Project_Hazard", "Number of Maklumat Data Point : " +project_hazards.length);

            if(project_hazards.length <1){
                Toast.makeText(getApplicationContext(),"Problem Retrieving JSON data", Toast.LENGTH_LONG).show();
                return;
            }

            for(Project_Hazard info:project_hazards){

                Double lat = Double.parseDouble(info.hzLtd);
                Double lng = Double.parseDouble(info.hzLng);
                String title = info.hzLocation;
                String snippet = info.hzDesc;

                MarkerOptions marker = new MarkerOptions().position(new LatLng(lat, lng))
                        .title(title)
                        .snippet(snippet)
                  .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_YELLOW));

                mMap.addMarker(marker);
            }

        }
    };

    public Response.ErrorListener onError = new Response.ErrorListener() {
        @Override
        public void onErrorResponse(VolleyError error) {
            Toast.makeText(getApplicationContext(),error.getMessage(),Toast.LENGTH_LONG).show();
        }
    };
}
