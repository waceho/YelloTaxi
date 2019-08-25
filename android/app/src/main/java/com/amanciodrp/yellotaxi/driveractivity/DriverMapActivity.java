package com.amanciodrp.yellotaxi.driveractivity;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Build;
import android.os.Looper;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.SwitchCompat;
import androidx.core.app.ActivityCompat;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.FragmentActivity;

import android.os.Bundle;

import androidx.core.content.ContextCompat;

import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.amanciodrp.yellotaxi.CustomerRequestDialogFragment;
import com.amanciodrp.yellotaxi.HistoryActivity;
import com.amanciodrp.yellotaxi.R;
import com.amanciodrp.yellotaxi.databinding.ActivityDriverMapBinding;
import com.amanciodrp.yellotaxi.model.CustomerRequest;
import com.amanciodrp.yellotaxi.model.DefaultUseSettings;
import com.amanciodrp.yellotaxi.model.User;
import com.amanciodrp.yellotaxi.splashscreen.FullscreenActivity;
import com.amanciodrp.yellotaxi.support.CustomerRequestDetailsInterface;
import com.amanciodrp.yellotaxi.utils.GoogleServiceSingleton;
import com.amanciodrp.yellotaxi.utils.SharedPrefsObject;
import com.amanciodrp.yellotaxi.utils.UtilityKit;
import com.amanciodrp.yellotaxi.viewmodel.CustomerInfoViewModel;
import com.bumptech.glide.Glide;
import com.directions.route.AbstractRouting;
import com.directions.route.Route;
import com.directions.route.RouteException;
import com.directions.route.Routing;
import com.directions.route.RoutingListener;
import com.firebase.geofire.GeoFire;
import com.firebase.geofire.GeoLocation;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class DriverMapActivity extends FragmentActivity implements
        OnMapReadyCallback,
        RoutingListener,
        CustomerRequestDetailsInterface {

    private String TAG = DriverMapActivity.class.getSimpleName();
    private GoogleMap mMap;
    Location mLastLocation;
    LocationRequest mLocationRequest;
    private View mapView;

    private FusedLocationProviderClient mFusedLocationClient;

    private SwitchCompat mWorkingSwitch;
    private FirebaseUser currentUser;
    private int status = 0;

    private String customerId = "", destination;
    private LatLng destinationLatLng, pickupLatLng;
    private float rideDistance;

    private ActivityDriverMapBinding binding;
    private CustomerInfoViewModel customerViewModel;
    private FirebaseAuth auth;
    private GoogleServiceSingleton googleServiceSingleton = GoogleServiceSingleton.getInstance();
    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_driver_map);
        auth = FirebaseAuth.getInstance();
        mContext = getBaseContext();
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        polylines = new ArrayList<>();
        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        CustomerRequestDialogFragment.Companion.newInstance(new CustomerRequest()).setOnCustomerRequestListener(this);
        assert mapFragment != null;
        mapView = mapFragment.getView();

        assert mapFragment != null;
        mapFragment.getMapAsync(this);

        mWorkingSwitch = findViewById(R.id.workingSwitch);

        mWorkingSwitch.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked)
                connectDriver();
            else
                disconnectDriver();

        });

        binding.rideStatus.setOnClickListener(v -> {

            switch (status) {
                case 1:
                    status = 2;
                    erasePolylines();

                    if (destinationLatLng != null && destinationLatLng.latitude != 0.0 && destinationLatLng.longitude != 0.0) {
                        getRouteToMarker(destinationLatLng);
                    }
                    binding.rideStatus.setText("Course terminée");

                    break;
                case 2:
                    recordRide();
                    endRide();
                    break;
            }
        });

        binding.logout.setOnClickListener(v -> {
            disconnectDriver();
            signOut();
        });

        binding.account.setOnClickListener(v -> {
            Intent intent = new Intent(DriverMapActivity.this, DriverSettingsActivity.class);
            startActivity(intent);
        });

        binding.history.setOnClickListener(v -> {
            Intent intent = new Intent(DriverMapActivity.this, HistoryActivity.class);
            intent.putExtra("customerOrDriver", "Drivers");
            startActivity(intent);
        });

        getAssignedCustomer();
    }

    private void closeActivity() {
        auth.signOut();
        SharedPrefsObject.saveObjectToSharedPreference(getBaseContext(), DefaultUseSettings.class.getSimpleName(), DefaultUseSettings.class.getSimpleName(), null);
        UtilityKit.openActivity(getBaseContext(), FullscreenActivity.class);
        DriverMapActivity.this.finish();
    }

    private void getAssignedCustomer() {
        if (null != auth.getCurrentUser()) {
            String driverId = auth.getCurrentUser().getUid();
            DatabaseReference assignedCustomerRef = googleServiceSingleton.getDriverDatabase(driverId).child("customerRequest").child("customerRideId");
            assignedCustomerRef.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NotNull DataSnapshot dataSnapshot) {
                    if (dataSnapshot.exists()) {
                        status = 1;
                        customerId = dataSnapshot.getValue().toString();
                        getAssignedCustomerPickupLocation();
                        getAssignedCustomerDestination();
                        getAssignedCustomerInfo();

                    } else {
                        endRide();
                    }
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
        }
    }

    Marker pickupMarker;
    private DatabaseReference assignedCustomerPickupLocationRef;
    private ValueEventListener assignedCustomerPickupLocationRefListener;

    private void getAssignedCustomerPickupLocation() {
        assignedCustomerPickupLocationRef = googleServiceSingleton.getcustomerReQuest(customerId).child("location");
        assignedCustomerPickupLocationRefListener = assignedCustomerPickupLocationRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NotNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists() && !customerId.equals("")) {
                    List<Object> map = (List<Object>) dataSnapshot.getValue();
                    double locationLat = 0;
                    double locationLng = 0;
                    if (map.get(0) != null) {
                        locationLat = Double.parseDouble(map.get(0).toString());
                    }
                    if (map.get(1) != null) {
                        locationLng = Double.parseDouble(map.get(1).toString());
                    }
                    pickupLatLng = new LatLng(locationLat, locationLng);
                    pickupMarker = mMap.addMarker(new MarkerOptions().position(pickupLatLng).title("pickup location").icon(BitmapDescriptorFactory.fromResource(R.mipmap.ic_customer_location)));
                    getRouteToMarker(pickupLatLng);
                }
            }

            @Override
            public void onCancelled(@NotNull DatabaseError databaseError) {
            }
        });
    }

    /**
     * @param pickupLatLng
     */
    private void getRouteToMarker(LatLng pickupLatLng) {
        try {
            if (pickupLatLng != null && mLastLocation != null) {
                Routing routing = new Routing.Builder()
                        .travelMode(AbstractRouting.TravelMode.DRIVING)
                        .withListener(this)
                        .alternativeRoutes(false)
                        .waypoints(new LatLng(mLastLocation.getLatitude(), mLastLocation.getLongitude()), pickupLatLng)
                        .build();
                routing.execute();
            }
        } catch (Exception e){
            Log.e(TAG, e.getMessage());
        }

    }

    /**
     * get assigned destination
     **/
    private void getAssignedCustomerDestination() {
        String driverId = currentUser.getUid();
        DatabaseReference assignedCustomerRef = googleServiceSingleton.getDriverDatabase(driverId).child("customerRequest");
        assignedCustomerRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NotNull DataSnapshot dataSnapshot) {

                if (dataSnapshot.exists()) {
                    Log.d(TAG, "dataSnapshot status :" + dataSnapshot.getValue());

                    Map<String, Object> map = (Map<String, Object>) dataSnapshot.getValue();
                    if (map.get("destination") != null) {
                        destination = map.get("destination").toString();
                        binding.customerDestination.setText("Destination: " + destination);
                    } else {
                        binding.customerDestination.setText("Destination: --");
                    }

                    double destinationLat = 0.0;
                    double destinationLng = 0.0;
                    if (map.get("destinationLat") != null) {
                        destinationLat = Double.parseDouble(map.get("destinationLat").toString());
                    }
                    if (map.get("destinationLng") != null) {
                        destinationLng = Double.valueOf(map.get("destinationLng").toString());
                        destinationLatLng = new LatLng(destinationLat, destinationLng);
                    }
                }
            }

            @Override
            public void onCancelled(@NotNull DatabaseError databaseError) {
            }
        });
    }

    private void getAssignedCustomerInfo() {
        binding.customerInfo.setVisibility(View.VISIBLE);
        DatabaseReference mCustomerDatabase = googleServiceSingleton.getCustomerDatabase(customerId);
        mCustomerDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NotNull DataSnapshot dataSnapshot) {
                Log.d(TAG, "getAssignedCustomerInfo dataSnapshot status :" + dataSnapshot.exists());
                CustomerRequest customerRequest = new CustomerRequest();
                try {
                    if (dataSnapshot.exists() && dataSnapshot.getChildrenCount() > 0) {
                        Map<String, Object> map = (Map<String, Object>) dataSnapshot.getValue();
                        customerRequest.setDestination(destination);

                        if (map.get("name") != null) {
                            binding.customerName.setText(map.get("name").toString());
                            customerRequest.setName(map.get("name").toString());
                        }
                        if (map.get("phone") != null) {
                            binding.customerPhone.setText(map.get("phone").toString());
                            customerRequest.setPhone(map.get("phone").toString());
                        }
                        if (map.get("profileImageUrl") != null) {
                            Glide.with(getApplication()).load(map.get("profileImageUrl").toString()).into(binding.customerProfileImage);
                        }

                        CustomerRequestDialogFragment.Companion.newInstance(customerRequest).show(getSupportFragmentManager(), "dialog");
                    }
                } catch (Exception e){
                    Log.d(TAG, e.getMessage());
                }

            }

            @Override
            public void onCancelled(@NotNull DatabaseError databaseError) {
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

        if (!mWorkingSwitch.isChecked())
            disconnectDriver();

        final View locationButton = ((View) mapView.findViewById(Integer.parseInt("1")).getParent()).findViewById(Integer.parseInt("2"));

        binding.myLocation.setOnClickListener(v -> locationButton.callOnClick());
    }

    @Override
    protected void onDestroy() {
        disconnectDriver();
        super.onDestroy();
    }

    private void endRide() {
        binding.customerInfo.setVisibility(View.GONE);
        binding.rideStatus.setText("Accepter ce trajet");
        erasePolylines();

        String userId = currentUser.getUid();
        DatabaseReference driverRef = FirebaseDatabase.getInstance().getReference().child("Users").child("Drivers").child(userId).child("customerRequest");
        driverRef.removeValue();

        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("customerRequest");
        GeoFire geoFire = new GeoFire(ref);
        geoFire.removeLocation(customerId);
        customerId = "";
        rideDistance = 0;

        if (pickupMarker != null) {
            pickupMarker.remove();
        }
        if (assignedCustomerPickupLocationRefListener != null) {
            assignedCustomerPickupLocationRef.removeEventListener(assignedCustomerPickupLocationRefListener);
        }
        binding.customerInfo.setVisibility(View.GONE);
        binding.customerName.setText("");
        binding.customerPhone.setText("");
        binding.customerDestination.setText("Destination: --");
        binding.customerProfileImage.setImageResource(R.mipmap.ic_default_user);
    }

    /**
     * push ride data to firebase
     */
    private void recordRide() {
        String userId = currentUser.getUid();
        DatabaseReference driverRef = FirebaseDatabase.getInstance().getReference().child("Users").child("Drivers").child(userId).child("history");
        DatabaseReference customerRef = FirebaseDatabase.getInstance().getReference().child("Users").child("Customers").child(customerId).child("history");
        DatabaseReference historyRef = FirebaseDatabase.getInstance().getReference().child("history");

        try {
            String requestId = historyRef.push().getKey();
            driverRef.child(requestId).setValue(true);
            customerRef.child(requestId).setValue(true);
            HashMap map = new HashMap();
            map.put("driver", userId);
            map.put("customer", customerId);
            map.put("rating", 0);
            map.put("timestamp", getCurrentTimestamp());
            map.put("destination", destination);
            map.put("location/from/lat", 12);
            map.put("location/from/lng", 16);
            map.put("location/to/lat", 76);
            map.put("location/to/lng", 11);
            map.put("distance", rideDistance);
            historyRef.child(requestId).updateChildren(map);
            historyRef.push();
        } catch (Exception e) {
            Log.e(TAG, e.getMessage());
        }

    }

    private Long getCurrentTimestamp() {
        return System.currentTimeMillis() / 1000;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        mLocationRequest = new LocationRequest();
        mLocationRequest.setInterval(1000);
        mLocationRequest.setFastestInterval(1000);
        mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);

        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            } else {
                checkLocationPermission();
            }
        }
    }

    private void checkLocationPermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.ACCESS_FINE_LOCATION)) {
                new AlertDialog.Builder(this)
                        .setTitle("give permission")
                        .setMessage("give permission message")
                        .setPositiveButton("OK", (dialogInterface, i) -> ActivityCompat.requestPermissions(DriverMapActivity.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1))
                        .create()
                        .show();
            } else {
                ActivityCompat.requestPermissions(DriverMapActivity.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case 1: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                        mFusedLocationClient.requestLocationUpdates(mLocationRequest, mLocationCallback, Looper.myLooper());
                        mMap.setMyLocationEnabled(true);
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "Please provide the permission", Toast.LENGTH_LONG).show();
                }
                break;
            }
        }
    }

    private void connectDriver() {

        checkLocationPermission();
        mFusedLocationClient.requestLocationUpdates(mLocationRequest, mLocationCallback, Looper.myLooper());
        //mMap.setMyLocationEnabled(true);
    }

    private void disconnectDriver() {
        if (mFusedLocationClient != null) {
            mFusedLocationClient.removeLocationUpdates(mLocationCallback);
        }
        if (null != currentUser) {
            GeoFire geoFire = new GeoFire(googleServiceSingleton.getDriverAvalaible());
            geoFire.removeLocation(currentUser.getUid());
        }
    }

    private void logout(){
        User user = SharedPrefsObject.getSavedObjectFromPreference(mContext, User.class.getSimpleName(), User.class.getSimpleName(), User.class);
        if (null != user)
            disconnectFromGoogle(currentUser, user.getIdToken());
    }

    private void disconnectFromGoogle(FirebaseUser user, String token) {
        if (user != null) {

            AuthCredential credential;

            //This means you didn't have the token because user used like Facebook Sign-in method.

            //Doesn't matter if it was Facebook Sign-in or others. It will always work using GoogleAuthProvider for whatever the provider.
            credential = GoogleAuthProvider.getCredential(token, null);

            //We have to reauthenticate user because we don't know how long
            //it was the sign-in. Calling reauthenticate, will update the
            //user login and prevent FirebaseException (CREDENTIAL_TOO_OLD_LOGIN_AGAIN) on user.delete()
            user.reauthenticate(credential)
                    .addOnCompleteListener(task -> {
                        //Calling delete to remove the user and wait for a result.
                        user.delete().addOnCompleteListener(task1 -> {
                            if (task1.isSuccessful()) {
                                //Ok, user remove
                                signOut();
                            } else {
                                //Handle the exception
                                task1.getException();
                            }
                        });
                    });
        }
    }

    private void signOut() {
        googleServiceSingleton.getGoogleSigninClient(mContext).signOut()
                .addOnCompleteListener(this, task -> closeActivity());
    }

    private List<Polyline> polylines;
    private static final int[] COLORS = new int[]{R.color.primary_dark_material_light};

    @Override
    public void onRoutingFailure(RouteException e) {
        if (e != null) {
            Toast.makeText(this, "Error: " + e.getMessage(), Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(this, "Something went wrong, Try again", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onRoutingStart() {
    }

    @Override
    public void onRoutingSuccess(ArrayList<Route> route, int shortestRouteIndex) {
        if (!polylines.isEmpty()) {
            for (Polyline poly : polylines) {
                poly.remove();
            }
        }

        polylines = new ArrayList<>();
        //add route(s) to the map.
        for (int i = 0; i < route.size(); i++) {

            //In case of more than 5 alternative routes
            int colorIndex = i % COLORS.length;

            PolylineOptions polyOptions = new PolylineOptions();
            polyOptions.color(getResources().getColor(COLORS[colorIndex]));
            polyOptions.width(10 + i * 3);
            polyOptions.addAll(route.get(i).getPoints());
            Polyline polyline = mMap.addPolyline(polyOptions);
            polylines.add(polyline);

            Toast.makeText(getApplicationContext(), "Route " + (i + 1) + ": distance - " + route.get(i).getDistanceValue() + ": duration - " + route.get(i).getDurationValue(), Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public void onRoutingCancelled() {
    }

    @Override
    protected void onStart() {
        super.onStart();
        currentUser = auth.getCurrentUser();
    }

    private void erasePolylines() {
        for (Polyline line : polylines) {
            line.remove();
        }
        polylines.clear();
    }

    LocationCallback mLocationCallback = new LocationCallback() {
        @Override
        public void onLocationResult(LocationResult locationResult) {

            for (Location location : locationResult.getLocations()) {
                if (getApplicationContext() != null) {

                    if (!customerId.equals("") && mLastLocation != null && location != null) {
                        rideDistance += mLastLocation.distanceTo(location) / 1000;
                    }
                    mLastLocation = location;

                    assert location != null;
                    LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());
                    mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
                    mMap.animateCamera(CameraUpdateFactory.zoomTo(17));

                    String userId = currentUser.getUid();
                    GeoFire geoFireAvailable = new GeoFire(googleServiceSingleton.getDriverAvalaible());
                    GeoFire geoFireWorking = new GeoFire(googleServiceSingleton.getDriverWorking());

                    switch (customerId) {
                        case "":
                            geoFireWorking.removeLocation(userId);
                            geoFireAvailable.setLocation(userId, new GeoLocation(location.getLatitude(), location.getLongitude()));
                            break;

                        default:
                            geoFireAvailable.removeLocation(userId);
                            geoFireWorking.setLocation(userId, new GeoLocation(location.getLatitude(), location.getLongitude()));
                            break;
                    }
                }
            }
        }
    };

    @Override
    public void onRideStatusBtnClick() {

    }
}