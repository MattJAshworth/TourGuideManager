package xyz.mattjashworth.android.canvasapi;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * Created by mattjashworth on 07/05/2018.
 * For University of Hull Tour Guide Manager
 * Originally CanvasAPI.
 */

public class OrganiserHomeFragment extends Fragment {


    public OrganiserHomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_organiser_home, container, false);
    }

}
