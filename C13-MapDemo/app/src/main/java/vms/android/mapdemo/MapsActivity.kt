package vms.android.mapdemo

import android.location.Location
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import vms.android.mapdemo.databinding.ActivityMapsBinding

class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    private lateinit var binding: ActivityMapsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMapsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

        loadAUPlace()
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        // Add a marker in Sydney and move the camera
        val auCenter = Location("AU Center")
        auCenter.latitude = 13.612320
        auCenter.longitude = 100.836808
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(LatLng(auCenter.latitude, auCenter.longitude), 17f))

        for (place in auPlaceList) {
            val placeLocation = Location(place.facultyName)
            placeLocation.latitude = place.latitude
            placeLocation.longitude = place.longtitude

            val d = auCenter.distanceTo(placeLocation)
            mMap.addMarker(MarkerOptions().position(LatLng(placeLocation.latitude, placeLocation.longitude)).title(place.facultyName).snippet(place.abbreviation + " (${d} meters)"))
        }
    }

    var auPlaceList = ArrayList<AUPlace>()
    fun loadAUPlace() {
        auPlaceList.add(AUPlace("Vincent Mary School of Science and Technology", "VMS", R.drawable.vms_logo, 13.613239, 100.835531))
        auPlaceList.add(AUPlace("Albert Laurence School of Communication Arts", "CA", R.drawable.ca_logo, 13.612227, 100.835039))
        auPlaceList.add(AUPlace("Montfort Del Rosario School of Architecture and Design", "AR", R.drawable.ar_logo, 13.612125, 100.835315))
        auPlaceList.add(AUPlace("School of Law", "LAW", R.drawable.law_logo, 13.611869, 100.837477))
        auPlaceList.add(AUPlace("School of Music", "MS", R.drawable.ms_logo, 13.612264, 100.837577))
        auPlaceList.add(AUPlace("Martin De Tours School of Management and Economics", "MSME", R.drawable.msme_logo, 13.612958, 100.836499))
        auPlaceList.add(AUPlace("Faculty of Nursing Science", "NS", R.drawable.ns_logo, 13.612219, 100.838038))
        auPlaceList.add(AUPlace("Theodore Maria School of Arts", "ARTS", R.drawable.arts_logo, 13.611520, 100.837211))
    }
}