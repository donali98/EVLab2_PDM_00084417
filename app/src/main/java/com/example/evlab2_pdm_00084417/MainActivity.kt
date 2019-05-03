package com.example.evlab2_pdm_00084417

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast

class MainActivity : AppCompatActivity(),ButtonsHelper {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val photosFragments1 = PhotosFragment.newInstance(1)
        supportFragmentManager.beginTransaction().add(R.id.fl_carrete,photosFragments1).commit()


    }

    override fun onPrevClickListener() {
        Toast.makeText(this,"Hello prev",Toast.LENGTH_LONG).show()

    }

    override fun onNextClickListener() {
        Toast.makeText(this,"Hello next",Toast.LENGTH_LONG).show()
    }

}
