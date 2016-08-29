package in.vaksys.generous.activities;

import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.location.Location;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatSpinner;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import butterknife.Bind;
import butterknife.ButterKnife;
import in.vaksys.generous.R;
import in.vaksys.generous.adapters.SpinnerTextAdapter;

public class LocationActivity extends AppCompatActivity implements OnMapReadyCallback, GoogleMap.OnMapLongClickListener {

    GoogleMap mMap;
    Location location;
    @Bind(R.id.tv_follow_donation_location)
    TextView tvFollowDonationLocation;
    @Bind(R.id.tv_ratecmpaign)
    TextView tvRatecmpaign;
    @Bind(R.id.tv_save_visit)
    TextView tvSaveVisit;
    private Toolbar toolbar;
    private Marker marker;
    private EditText etSearchMap;
    private View searchMap, linearFollowDonation;
    private LinearLayout linearSearch, linearMapView, tv_star_follow_donation, tv_star_donation_one;
    SharedPreferences sharedPreferences;
    public static final String MyPREFERENCES = "MyPrefs";
    public static final String individual = "individual";
    private String temp;
    private Dialog dialog;
    private TextView tv_view_map, tv_view_follow_donation;
    private Spinner spAll;
    private AppCompatSpinner sp_mark;

    String[] all = {"All", "Auditor", "Donor", "Visitor"};
    private String[] marks = {"How I made this mark?", "I was there", "Factual back to other friends", "Reports and / or pictures published"
            , "Communication and explanation of representatives of the organization", "Other (specify)"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);
        ButterKnife.bind(this);

        sharedPreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);

        temp = sharedPreferences.getString(individual, "");

        linearMapView = (LinearLayout) findViewById(R.id.linear_map_view);
        linearMapView.setVisibility(View.GONE);

        linearFollowDonation = findViewById(R.id.linearFollowDonation);
        linearFollowDonation.setVisibility(View.GONE);

        tv_star_follow_donation = (LinearLayout) findViewById(R.id.tv_star_follow_donation);
        tv_view_follow_donation = (TextView) findViewById(R.id.tv_view_follow_donation);
        tv_star_donation_one = (LinearLayout) findViewById(R.id.tv_star_donation_one);

        tv_star_donation_one.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog = new Dialog(LocationActivity.this);
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setContentView(R.layout.dialog_rate_visitor);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

                sp_mark = (AppCompatSpinner) dialog.findViewById(R.id.sp_mark);
                SpinnerTextAdapter spMark = new SpinnerTextAdapter(LocationActivity.this, marks);
                sp_mark.setAdapter(spMark);

                dialog.show();
            }
        });

        spAll = (Spinner) findViewById(R.id.sp_all);

        linearSearch = (LinearLayout) findViewById(R.id.linear_Search);
        searchMap = findViewById(R.id.linearSearchOnMap);
        tv_view_map = (TextView) findViewById(R.id.tv_view_map);
        searchMap.setVisibility(View.GONE);


        SpinnerTextAdapter spinnerTextAdapter = new SpinnerTextAdapter(LocationActivity.this, all);
        spAll.setAdapter(spinnerTextAdapter);

        if (temp.equalsIgnoreCase("individual")) {
            linearMapView.setVisibility(View.VISIBLE);
        }

        tvRatecmpaign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog = new Dialog(LocationActivity.this);
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setContentView(R.layout.dialog_rate_campaign);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });

        tvSaveVisit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog = new Dialog(LocationActivity.this);
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setContentView(R.layout.dialog_save_visit);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });

        tvFollowDonationLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                linearFollowDonation.setVisibility(View.VISIBLE);
                linearSearch.setVisibility(View.GONE);
            }
        });

        tv_view_follow_donation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                linearFollowDonation.setVisibility(View.GONE);
                linearSearch.setVisibility(View.VISIBLE);
            }
        });

        tv_star_follow_donation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog = new Dialog(LocationActivity.this);
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setContentView(R.layout.dialog_rate_campaign);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });

        etSearchMap = (EditText) findViewById(R.id.et_searchMap);

        etSearchMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                linearSearch.setVisibility(View.GONE);
                searchMap.setVisibility(View.VISIBLE);
            }
        });

        tv_view_map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                linearSearch.setVisibility(View.VISIBLE);
                searchMap.setVisibility(View.GONE);
            }
        });

//        mMap = ((SupportMapFragment) getSupportFragmentManager()
//                .findFragmentById(R.id.map)).getMap();
//
//        int status = GooglePlayServicesUtil.isGooglePlayServicesAvailable(this);
//
//        // Showing status
//        if (status == ConnectionResult.SUCCESS) {
//            Toast.makeText(this, "Available", Toast.LENGTH_SHORT).show();
//        } else {
//
//            Toast.makeText(this, "Google Play Services not Available", Toast.LENGTH_SHORT).show();
//        }
//
//        final LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
//
//        // Creating a criteria object to retrieve provider
//        Criteria criteria = new Criteria();
//
//        // Getting the name of the best provider
//        String provider = locationManager.getBestProvider(criteria, true);
//
//        //boolean gps = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
//
//
//        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
//            // TODO: Consider calling
//            //    ActivityCompat#requestPermissions
//            // here to request the missing permissions, and then overriding
//            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
//            //                                          int[] grantResults)
//            // to handle the case where the user grants the permission. See the documentation
//            // for ActivityCompat#requestPermissions for more details.
//
//            return;
//        }
//        mMap.setMyLocationEnabled(true);
//
//
//        // Getting Current Location
//        location = locationManager.getLastKnownLocation(provider);
//
//        if (location != null) {
//            onLocationChanged(location);
//        }
//        locationManager.requestLocationUpdates(provider, 20000, 0, this);
//
//        mMap.setOnMyLocationButtonClickListener(new GoogleMap.OnMyLocationButtonClickListener() {
//            @Override
//            public boolean onMyLocationButtonClick() {
//
//                boolean gps = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
//
//                if (gps) {
//                    Toast.makeText(getApplicationContext()
//                            , "setMyLocationEnabled Pressed"
//                            , Toast.LENGTH_SHORT).show();
//                } else {
//                    Toast.makeText(getApplicationContext()
//                            , "setMyLocationEnabled not working"
//                            , Toast.LENGTH_SHORT).show();
//                    startActivity(new Intent(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS));
//                }
//
//                return false;
//            }
//        });

        toolbar = (Toolbar) findViewById(R.id.tool);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setLogo(R.drawable.icon);

        toolbar.setTitle("Generous");

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        mMap = mapFragment.getMap();

        mMap.setOnMapLongClickListener(this);

        mMap.setInfoWindowAdapter(new MyInfoWindowAdapter());

    }

//    @Override
//    public void onLocationChanged(Location location) {
//        double latitude = location.getLatitude();
//
//        // Getting longitude of the current location
//        double longitude = location.getLongitude();
//
//        // Creating a LatLng object for the current location
//        LatLng latLng = new LatLng(latitude, longitude);
//
//        // Showing the current location in Google Map
//        mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
//
//        // Zoom in the Google Map
//        mMap.animateCamera(CameraUpdateFactory.zoomTo(15));
//
//        Toast.makeText(this
//                , "Latitude:" + latitude + ", Longitude:" + longitude
//                , Toast.LENGTH_SHORT).show();
//
//        Log.e("MAP: ", "Latitude:" + latitude + ", Longitude:" + longitude);
//    }
//
//    @Override
//    public void onStatusChanged(String provider, int status, Bundle extras) {
//
//    }
//
//    @Override
//    public void onProviderEnabled(String provider) {
//
//    }
//
//    @Override
//    public void onProviderDisabled(String provider) {
//
//    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;


        LatLng bhagwatiHolidays = new LatLng(23.025819, 72.5096982);
        mMap.addMarker(new MarkerOptions().position(bhagwatiHolidays).title("One").icon(BitmapDescriptorFactory.fromResource(R.drawable.map_pin)));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(bhagwatiHolidays));
        mMap.animateCamera(CameraUpdateFactory.zoomTo(14), 2000, null);

        LatLng bhagwatiHolidays1 = new LatLng(23.0258349, 72.5096782);
        mMap.addMarker(new MarkerOptions().position(bhagwatiHolidays1).title("Two").icon(BitmapDescriptorFactory.fromResource(R.drawable.map_pin)));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(bhagwatiHolidays1));
        mMap.animateCamera(CameraUpdateFactory.zoomTo(14), 2000, null);


        LatLng bhagwatiHolidays2 = new LatLng(23.0289819, 72.5096912);
        mMap.addMarker(new MarkerOptions().position(bhagwatiHolidays2).title("Three").icon(BitmapDescriptorFactory.fromResource(R.drawable.map_pin)));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(bhagwatiHolidays2));
        mMap.animateCamera(CameraUpdateFactory.zoomTo(14), 2000, null);
    }

    @Override
    public void onMapLongClick(LatLng latLng) {

        LatLng bhagwatiHolidays = new LatLng(23.025819, 72.5096982);
        mMap.addMarker(new MarkerOptions().position(bhagwatiHolidays).title("One").icon(BitmapDescriptorFactory.fromResource(R.drawable.map_pin)));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(bhagwatiHolidays));
        mMap.animateCamera(CameraUpdateFactory.zoomTo(14), 2000, null);

        LatLng bhagwatiHolidays1 = new LatLng(23.0258349, 72.5096763);
        mMap.addMarker(new MarkerOptions().position(bhagwatiHolidays1).title("Two").icon(BitmapDescriptorFactory.fromResource(R.drawable.map_pin)));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(bhagwatiHolidays1));
        mMap.animateCamera(CameraUpdateFactory.zoomTo(14), 2000, null);


        LatLng bhagwatiHolidays2 = new LatLng(23.0289819, 72.5096912);
        mMap.addMarker(new MarkerOptions().position(bhagwatiHolidays2).title("Three").icon(BitmapDescriptorFactory.fromResource(R.drawable.map_pin)));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(bhagwatiHolidays2));
        mMap.animateCamera(CameraUpdateFactory.zoomTo(14), 2000, null);
    }


    class MyInfoWindowAdapter implements GoogleMap.InfoWindowAdapter {

        private final View myContentsView;

        MyInfoWindowAdapter() {
            myContentsView = getLayoutInflater().inflate(R.layout.custom_info_contents, null);
        }

        @Override
        public View getInfoContents(Marker marker) {

//            TextView tvTitle = ((TextView) myContentsView.findViewById(R.id.title));
//            tvTitle.setText(marker.getTitle());
//            TextView tvSnippet = ((TextView) myContentsView.findViewById(R.id.snippet));
//            tvSnippet.setText(marker.getSnippet());
            myContentsView.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
            return myContentsView;
        }

        @Override
        public View getInfoWindow(Marker marker) {
            if (LocationActivity.this.marker != null
                    && LocationActivity.this.marker.isInfoWindowShown()) {
                LocationActivity.this.marker.hideInfoWindow();
                LocationActivity.this.marker.showInfoWindow();
            }
            return null;
        }

    }
}
