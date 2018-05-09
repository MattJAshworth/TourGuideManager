package xyz.mattjashworth.android.canvasapi;


import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;


/**
 * A simple {@link Fragment} subclass.
 */
public class MapsFragment extends Fragment implements OnMapReadyCallback {

    View rootView;
    GoogleMap mMap;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_maps, container, false);

        SupportMapFragment mapFragment = (SupportMapFragment) this.getChildFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        return rootView;
    }



    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        LatLng staffHouse = new LatLng(53.771772, -0.368111);
        LatLng fenner = new LatLng(53.771845, -0.369635);
        LatLng rbb = new LatLng(53.771481, -0.368720);
        LatLng library = new LatLng(53.771051, -0.369191);
        LatLng larkin = new LatLng(53.770300, -0.368671);
        LatLng venn = new LatLng(53.769848, -0.368605);
        LatLng middleton = new LatLng(53.770037, -0.367916);
        LatLng business = new LatLng(53.769983, -0.371046);
        LatLng allamMedical = new LatLng(53.770955, -0.370580);
        LatLng chemisty = new LatLng(53.770922, -0.367668);
        LatLng ferens = new LatLng(53.770647, -0.366990);
        LatLng wilberforce = new LatLng(53.770785, -0.366281);
        LatLng union = new LatLng(53.771859, -0.366904);
        LatLng courtyard = new LatLng(53.773083, -0.366185);
        LatLng sportsCentre = new LatLng(53.774028, -0.369030);


        mMap.addMarker(new MarkerOptions().position(staffHouse)
                .title("Staff House"));
        mMap.addMarker(new MarkerOptions().position(fenner)
                .title("Fenner Building"));
        mMap.addMarker(new MarkerOptions().position(rbb)
                .title("Robert Blackburn Building"));
        mMap.addMarker(new MarkerOptions().position(library)
                .title("Brynmor Jones Library"));
        mMap.addMarker(new MarkerOptions().position(larkin)
                .title("Larkin"));
        mMap.addMarker(new MarkerOptions().position(venn)
                .title("Venn (Reception)"));
        mMap.addMarker(new MarkerOptions().position(middleton)
                .title("Middleton Hall"));
        mMap.addMarker(new MarkerOptions().position(business)
                .title("Business School"));
        mMap.addMarker(new MarkerOptions().position(allamMedical)
                .title("Allam Medical Building"));
        mMap.addMarker(new MarkerOptions().position(chemisty)
                .title("Department of Chemisty"));
        mMap.addMarker(new MarkerOptions().position(ferens)
                .title("Ferens"));
        mMap.addMarker(new MarkerOptions().position(wilberforce)
                .title("Wilberforce"));
        mMap.addMarker(new MarkerOptions().position(union)
                .title("University Union"));
        mMap.addMarker(new MarkerOptions().position(courtyard)
                .title("The Courtyard"));
        mMap.addMarker(new MarkerOptions().position(sportsCentre)
                .title("Sports & Fitness Centre"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(staffHouse));
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(53.771772, -0.368111), 15.0f));

    }

}
