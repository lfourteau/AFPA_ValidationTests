package com.example.afpa.garageapp;

import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.TextView;

import com.example.afpa.garageapp.database.FindGarages;
import com.example.afpa.garageapp.model.Garage;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.maps.android.clustering.ClusterManager;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment
        extends Fragment
        implements OnMapReadyCallback,
        GoogleMap.OnMarkerClickListener {

    private List<Garage> garages;
    int garage_id;
    private GoogleMap mMap;
    private View rootView;
    private MyItem clickedClusterItem;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        //Appelle la class FindGarage qui lance la connection à l'API pour réccupérer tous les garages
        FindGarages data = new FindGarages();
        data.execute();
        try {
            //Rempli la liste garages avec ce qui est reçu
            garages = data.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        //Créer la vue / fond google map
        rootView = inflater.inflate(R.layout.fragment_main, container, false);
        final MapFragment mapFragment = (MapFragment) getActivity().getFragmentManager().findFragmentById(R.id.map);

        mapFragment.getMapAsync(this);

        return rootView;
    }


    //Génère les markers sur la carte en fonction des données reçues dans la list de garages via l'API
    @Override
    public void onMapReady(GoogleMap googleMap) {
//      Ajout d'un listener au clic sur un marqueur
        googleMap.setOnMarkerClickListener(this);
        this.mMap = googleMap;
        mClusterManager = new ClusterManager(this.getContext(), mMap);
        mMap.setOnCameraIdleListener(mClusterManager);
        mMap.setOnMarkerClickListener(mClusterManager);
        mMap.setInfoWindowAdapter(mClusterManager.getMarkerManager());
        mMap.setOnInfoWindowClickListener(mClusterManager); //added
        mClusterManager.setOnClusterItemInfoWindowClickListener(new ClusterManager.OnClusterItemInfoWindowClickListener<MyItem>() {
            @Override
            public void onClusterItemInfoWindowClick(MyItem myItem) {
                //Cluster item InfoWindow clicked, set title as action
                Intent i = new Intent(getActivity(), ReviewsPage.class);
                i.putExtra("garage_id", "" + myItem.getGarage_id());
                i.putExtra("garage_name", myItem.getTitle());
                startActivity(i);
            }
        });
        mClusterManager
                .setOnClusterItemClickListener(new ClusterManager.OnClusterItemClickListener<MyItem>() {
                    @Override
                    public boolean onClusterItemClick(MyItem item) {
                        clickedClusterItem = item;
                        return false;
                    }
                });

        addItems();

        mClusterManager.getMarkerCollection().setOnInfoWindowAdapter(
                new MyCustomAdapterForItems());


    }
    public class MyCustomAdapterForItems implements GoogleMap.InfoWindowAdapter {

        private final View myContentsView;

        MyCustomAdapterForItems() {
            myContentsView = getActivity().getLayoutInflater().inflate(
                    R.layout.info_window, null);
        }


        @Override
        public View getInfoWindow(Marker marker) {

            TextView tvTitle = ((TextView) myContentsView.findViewById(R.id.txtTitle));
            TextView tvSnippet = ((TextView) myContentsView.findViewById(R.id.txtSnippet));

            tvTitle.setText(clickedClusterItem.getTitle());
            tvSnippet.setText(clickedClusterItem.getSnippet());

            return myContentsView;
        }

        @Override
        public View getInfoContents(Marker marker) {
            return null;
        }
    }
    @Override
    public boolean onMarkerClick(Marker marker) {
        return false;
    }

    // Declare a variable for the cluster manager.
    private ClusterManager<MyItem> mClusterManager;


    private void addItems() {

        //Parcourt la liste des garages reçus
        for (Garage gar : garages) {
            //Reccupère le nom du concessionnaire
            String concName = gar.getConcessionnaire();
            //S'il n'y en un pas, indique "aucun"
            if (concName.equals("")) {
                concName = "Ancun";
            }

            double lat = gar.getLatitude();
            double lng = gar.getLongitude();
            String nomGarage = gar.getNom();
            String Concessionnaire = "Concessionnaire " + gar.getConcessionnaire();
            int garage_id = gar.getId();

            MyItem offsetItem = new MyItem(lat, lng, nomGarage, Concessionnaire, garage_id);
            mClusterManager.addItem(offsetItem);


        }
    }


}
