package xyz.mattjashworth.android.canvasapi;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;


/**
 * Created by mattjashworth on 07/05/2018.
 * For University of Hull Tour Guide Manager
 * Originally CanvasAPI.
 */

public class LocateFragment extends Fragment implements OnMapReadyCallback {

    GoogleMap mMap;
    View rootView;
    Boolean inSession = null;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_locate, container, false);

        SupportMapFragment mapFragment = (SupportMapFragment) this.getChildFragmentManager()
                .findFragmentById(R.id.mapLocate);
        mapFragment.getMapAsync(this);

        if(inSession != null) {
            //Init Tour Finder
        } else {
            showNotInSession();
        }


        return rootView;
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        LatLng staffHouse = new LatLng(53.771772, -0.368111);
        mMap.moveCamera(CameraUpdateFactory.newLatLng(staffHouse));
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(53.771772, -0.368111), 15.0f));

    }

    private void showNotInSession() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Locate Tour Groups");
        builder.setMessage("There are currenly no active Open Day Tour Groups");
        builder.setPositiveButton("Dismiss", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                //Do nothing
            }
        });

        builder.show();
    }

}
