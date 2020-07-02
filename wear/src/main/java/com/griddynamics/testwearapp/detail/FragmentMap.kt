package com.griddynamics.testwearapp.detail

import android.Manifest.permission.*
import android.annotation.SuppressLint
import android.app.Fragment
import android.content.Context.LOCATION_SERVICE
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.app.ActivityCompat
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.MapView
import com.google.android.gms.maps.MapsInitializer
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.griddynamics.testwearapp.R

class FragmentMap : Fragment(), LocationListener {
    private var mMapView: MapView? = null
    private var googleMap: GoogleMap? = null
    private var positionCounter = 1;

    private val MIN_DISTANCE_CHANGE_FOR_UPDATES = 10f

    private val MIN_TIME_BW_UPDATES = 1000 * 3 * 1.toLong()

    override fun onCreateView(
        inflater: LayoutInflater?,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater!!.inflate(R.layout.fr_map, null, false)
        mMapView = view as MapView
        mMapView!!.onCreate(savedInstanceState)
        mMapView!!.onResume()
        try {
            MapsInitializer.initialize(activity.applicationContext)
        } catch (e: Exception) {
            e.printStackTrace()
        }

        if (ActivityCompat.checkSelfPermission(
                context,
                ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                context,
                ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            Log.e("###", "NO permissions")
            activity.requestPermissions(arrayOf(ACCESS_FINE_LOCATION),  1)
        } else {

            setLocation()
        }
        return view
    }

    @SuppressLint("MissingPermission")
    private fun setLocation() {
        val locationManager = activity
            .getSystemService(LOCATION_SERVICE) as LocationManager

        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, MIN_TIME_BW_UPDATES,
            MIN_DISTANCE_CHANGE_FOR_UPDATES, this)

        mMapView!!.getMapAsync { mMap ->
            googleMap = mMap
            val location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER)
            val sydney = LatLng(location.longitude, location.longitude)
            googleMap!!.addMarker(
                MarkerOptions().position(sydney).title("Position")
                    .snippet("${location.latitude.format(8)}  ${location.longitude.format(8)}")
            ).showInfoWindow()

            val cameraPosition =
                CameraPosition.Builder().target(sydney).zoom(12f).build()
            googleMap!!.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition))
        }
    }

    override fun onResume() {
        super.onResume()
        mMapView!!.onResume()
    }

    override fun onPause() {
        super.onPause()
        mMapView!!.onPause()
    }

    override fun onLocationChanged(position: Location?) {
        positionCounter = positionCounter.inc()
        val sss = LatLng(position!!.latitude, position.longitude)

        googleMap!!.addMarker(
            MarkerOptions()
                .position(sss)
                .title("Position")
                .snippet("${position.latitude.format(8)}  ${position.longitude.format(8)}")
        ).showInfoWindow()

        val cameraPosition =
            CameraPosition.Builder().target(sss).zoom(12f).build()

        googleMap!!.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition))
    }

    fun Double.format(digits: Int) = "%.${digits}f".format(this)

    override fun onStatusChanged(p0: String?, p1: Int, p2: Bundle?) {}

    override fun onProviderEnabled(p0: String?) {}

    override fun onProviderDisabled(p0: String?) {}

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        when (requestCode) {
            1 -> {
                if (grantResults[0] === PackageManager.PERMISSION_GRANTED) {
                    setLocation()
                } else {
                    Toast.makeText(
                        context,
                        "Permission denied to read your External storage",
                        Toast.LENGTH_SHORT
                    ).show()
                }
                return
            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }
}