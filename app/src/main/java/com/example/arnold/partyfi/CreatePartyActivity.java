package com.example.arnold.partyfi;

import android.app.Fragment;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.location.Geocoder;
import android.graphics.drawable.Drawable;
import android.location.Address;
import android.widget.Toast;

import com.google.android.gms.maps.MapView;


import java.io.IOException;
import java.util.List;


public class CreatePartyActivity extends MapActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_party);
        if (savedInstanceState == null) {
            getFragmentManager().beginTransaction()
                    .add(R.id.container, new PlaceholderFragment())
                    .commit();
        }
        // Getting reference to btn_find of the layout activity_main
        Button btn_find = (Button) findViewById(R.id.btn_find);

        // Defining button click event listener for the find button
        View.OnClickListener findClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Getting reference to EditText to get the user input location
                EditText etLocation = (EditText) findViewById(R.id.et_location);

                // Getting user input location
                String location = etLocation.getText().toString();

                if(location!=null && !location.equals("")){
                    new GeocoderTask().execute(location);
                }
            }
        };
        // Setting button click event listener for the find button
        //btn_find.setOnClickListener(findClickListener);
    }


    public void showTimePickerDialog(View v) {
        DialogFragment newFragment = new TimePickerFragment();
        newFragment.show(getSupportFragmentManager(), "timePicker");
    }

    public void showDatePickerDialog(View v) {
        DialogFragment newFragment = new DatePickerFragment();
        newFragment.show(getSupportFragmentManager(), "DatePicker");
    }
    public void onFinishTimePickerDialog(DatePicker view, int year, int month, int day)
    {

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_create_party, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private class GeocoderTask extends AsyncTask<String, Void, List<Address>> {

        @Override
        protected List<Address> doInBackground(String... locationName) {
            // Creating an instance of Geocoder class
            Geocoder geocoder = new Geocoder(getBaseContext());
            List<Address> addresses = null;

            try {
                // Getting a maximum of 3 Address that matches the input text
                addresses = geocoder.getFromLocationName(locationName[0], 3);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return addresses;
        }
//        @Override
//        protected void onPostExecute(List<Address> addresses) {
//
//            // Getting Reference to MapView of the layout activity_main
//            MapView mapView = (MapView) findViewById(R.id.map_view);
//
//            // Setting ZoomControls
//
//            mapView.setBuiltInZoomControls(true);
//
//
//            // Getting MapController for the MapView
//            MapController mc = mapView.getController();
//
//            // Getting Drawable object corresponding to a resource image
//            Drawable drawable = getResources().getDrawable(R.drawable.marker);
//
//            // Getting Overlays of the map
//            List<Overlay> overlays = mapView.getOverlays();
//
//            // Creating an ItemizedOverlay
//            LocationOverlay locationOverlay = new LocationOverlay(drawable,getBaseContext());
//
//            // Clearing the overlays
//            overlays.clear();
//
//            if(addresses==null || addresses.size()==0){
//                Toast.makeText(getBaseContext(), "No Location found", Toast.LENGTH_SHORT).show();
//
//                // Redraws the map to clear the overlays
//                mapView.invalidate();
//            }
//
//            // Adding Markers on Google Map for each matching address
//            for(int i=0;i<addresses.size();i++){
//
//                Address address = (Address) addresses.get(i);
//
//                // Creating an instance of GeoPoint, to display in Google Map
//                GeoPoint p = new GeoPoint(
//                        (int)(address.getLatitude()*1E6),
//                        (int)(address.getLongitude()*1E6)
//                );
//
//                String addressText = String.format("%s, %s",
//                        address.getMaxAddressLineIndex() > 0 ? address.getAddressLine(0) : "",
//                        address.getCountryName());
//
//                // Creating an OverlayItem to mark the point
//                OverlayItem overlayItem = new OverlayItem(p, "Location",addressText);
//
//                // Adding the OverlayItem in the LocationOverlay
//                locationOverlay.addOverlay(overlayItem);
//
//                // Adding locationOverlay to the overlay
//                overlays.add(locationOverlay);
//
//                // Locate the first location
//                if(i==0)
//                    mc.animateTo(p);
//            }
//
//            // Redraws the map
//            mapView.invalidate();
//        }
//    }
    }
    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {

        public PlaceholderFragment() {
        }


        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_create_party, container, false);
            return rootView;
        }

    }

}
