package com.andra.synrgy_permission

import android.Manifest
import android.content.pm.PackageManager
import android.location.Location
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.core.app.ActivityCompat
import com.andra.synrgy_permission.databinding.ActivityMainBinding
import com.bumptech.glide.Glide
import com.google.android.gms.location.LocationServices
import com.google.android.gms.tasks.OnSuccessListener

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        val image = binding.gambar
        val button = binding.buton1
        setContentView(view)

        val imageUrl = "https://i2.wp.com/popculture.id/wp-content/uploads/2020/01/sung-jin-woo-solo-leveling.jpg?resize=770%2C513&ssl=1"

        Glide.with(this)
                .load(imageUrl)
                .centerCrop()
                .into(image)

        button.setOnClickListener {
            getLoc()
        }
    }

    private fun getLoc(){
        val fusedLocation = LocationServices.getFusedLocationProviderClient(this)

        if (ActivityCompat.checkSelfPermission(
                        this,
                        Manifest.permission.ACCESS_FINE_LOCATION
                ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                        this,
                        Manifest.permission.ACCESS_COARSE_LOCATION
                ) != PackageManager.PERMISSION_GRANTED
        ) {
            Toast.makeText(this, "Location Permission Denied", Toast.LENGTH_SHORT).show()
            Log.d("permission", "Location Permission Denied")
        }

        fusedLocation.lastLocation.addOnSuccessListener(this, object :
            OnSuccessListener<Location> {
            override fun onSuccess(location: Location?) {
                // Do it all with location
                Log.d("Location", "Lat : ${location?.latitude} Long : ${location?.longitude}")
                // Display in Toast
                Toast.makeText(this@MainActivity,
                    "Longitude dan Latitude didapatkan ${location?.latitude} , ${location?.longitude} . Silakan Cek Logcat",
                    Toast.LENGTH_LONG).show()
            }

        })
    }
}