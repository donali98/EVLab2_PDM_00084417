package com.example.evlab2_pdm_00084417

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast

class MainActivity : AppCompatActivity(),ButtonsHelper {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val photosFragment = PhotosFragment()
        supportFragmentManager.beginTransaction().add(R.id.fl_carrete,photosFragment).commit()

    }

    override fun onPrevClickListener() {

        Toast.makeText(this,"Hello",Toast.LENGTH_LONG).show()
    }

}
