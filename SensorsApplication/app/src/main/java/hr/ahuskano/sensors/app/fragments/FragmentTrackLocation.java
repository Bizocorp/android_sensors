package hr.ahuskano.sensors.app.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.SupportMapFragment;

import hr.ahuskano.sensors.app.R;

/**
 * Created by ahuskano on 7/29/2014.
 */
public class FragmentTrackLocation extends Fragment {

    private GoogleMap mMap;

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        mMap = ((SupportMapFragment) getActivity().getSupportFragmentManager().findFragmentById(R.id.map)).getMap();

        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_track_location, null);
    }
}
