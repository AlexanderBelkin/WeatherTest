package com.dev.weathertest.ui;

import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Point;
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.dev.weathertest.R;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.Projection;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import butterknife.BindView;
import butterknife.ButterKnife;

import static android.Manifest.permission.ACCESS_COARSE_LOCATION;
import static android.Manifest.permission.ACCESS_FINE_LOCATION;

public class MapFragment extends Fragment implements GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener,
        OnMapReadyCallback,
        LocationListener, DoubleTapView.Callback {

    public static final String TAG = "MapFragment";
    private static final int RC_LOCATION_PERMISSIONS = 1;

    private static final long UPDATE_INTERVAL_IN_MILLISECONDS = 5000;
    private static final long FASTEST_UPDATE_INTERVAL_IN_MILLISECONDS =
            UPDATE_INTERVAL_IN_MILLISECONDS / 2;
    private static final float DEF_ZOOM = 18;

    @BindView(R.id.map_view)
    MapView mapView;

    @BindView(R.id.double_tap_helper_view)
    DoubleTapView doubleTapView;

    @Nullable
    private Callback callback;

    private GoogleApiClient googleApiClient;
    private GoogleMap googleMap;

    public MapFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        initGoogleApiClient();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_map, container, false);
        ButterKnife.bind(this, view);
        mapView.onCreate(savedInstanceState);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        mapView.getMapAsync(this);
        doubleTapView.setCallback(this);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof Callback) {
            callback = (Callback) context;
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        callback = null;
    }

    protected final void initGoogleApiClient() {
        googleApiClient = new GoogleApiClient.Builder(getContext())
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();
    }

    protected final void connectGoogleApiClient() {
        googleApiClient.connect();
    }

    protected final void disconnectGoogleApiClient() {
        googleApiClient.disconnect();
    }



    @Override
    public void onMapReady(GoogleMap googleMap) {
        this.googleMap = googleMap;
        googleMap.getUiSettings().setZoomGesturesEnabled(false);
        googleMap.getUiSettings().setZoomControlsEnabled(true);
        if (checkLocationPermissions()) {
            googleMap.setMyLocationEnabled(true);
            moveToLocation(getLastLocation(), DEF_ZOOM);
        } else {
            requestLocationPermissions(RC_LOCATION_PERMISSIONS);
        }
    }

    @Override
    public void onLocationChanged(Location location) {

    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        moveToLocation(getLastLocation(), DEF_ZOOM);
    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }




    @Override
    public void onStart() {
        super.onStart();
        if (mapView != null) {
            mapView.onStart();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        connectGoogleApiClient();
        if (mapView != null) {
            mapView.onResume();
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        disconnectGoogleApiClient();
        if (mapView != null) {
            mapView.onStop();
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if (mapView != null) {
            mapView.onSaveInstanceState(outState);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (mapView != null) {
            mapView.onDestroy();
        }
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        if (mapView != null) {
            mapView.onLowMemory();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == RC_LOCATION_PERMISSIONS) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED && googleMap != null) {
                googleMap.setMyLocationEnabled(true);
                moveToLocation(getLastLocation(), DEF_ZOOM);
            }
        }
    }

    public final void moveToLocation(Location location, float zoom) {
        if (location != null && googleMap != null) {
            googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(location.getLatitude(), location.getLongitude()), zoom));
        }
    }

    @Nullable
    public Location getLastLocation() {
        Location location = null;
        if (checkLocationPermissions() && googleApiClient != null && googleApiClient.isConnected()) {
            location = LocationServices.FusedLocationApi.getLastLocation(googleApiClient);
        }
        return location;
    }

    public final boolean checkLocationPermissions() {
        return (ActivityCompat.checkSelfPermission(getContext(), ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(getContext(), ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED);
    }

    public final void requestLocationPermissions(int requestCode) {
        requestPermissions(new String[]{ACCESS_FINE_LOCATION, ACCESS_COARSE_LOCATION}, requestCode);
    }

    @Override
    public void onDoubleTap(float x, float y) {
        if (googleMap != null && callback != null) {
            Projection projection = googleMap.getProjection();
            LatLng latLng = projection.fromScreenLocation(new Point((int)x, (int)y));
            callback.openDetailScreen(latLng);
        }
    }

    public interface Callback {
        void openDetailScreen(LatLng latLng);
    }
}
