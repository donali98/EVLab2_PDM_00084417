package com.example.evlab2_pdm_00084417

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.evlab2_pdm_00084417.fragments.PhotosFragment
import com.example.evlab2_pdm_00084417.helpers.ButtonsHelper

class MainActivity : AppCompatActivity(), ButtonsHelper {

    var currentPosition = 0
    var photosFragments:ArrayList<PhotosFragment> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val photosFragments1 = PhotosFragment.newInstance(R.drawable.ic_launcher_background,0)
        val photosFragments2 = PhotosFragment.newInstance(R.drawable.ic_launcher_foreground,1)
        val photosFragments3 = PhotosFragment.newInstance(R.drawable.abc_ic_go_search_api_material,2)
        photosFragments.apply {
            add(photosFragments1)
            add(photosFragments2)
            add(photosFragments3)
        }
        supportFragmentManager.beginTransaction().add(R.id.fl_carrete,photosFragments.get(0)).commit()

    }

    override fun onPrevClickListener() {
        if(currentPosition!=0){
            currentPosition--
            supportFragmentManager.beginTransaction().replace(R.id.fl_carrete, photosFragments.get(currentPosition)).commit()
        }
        else{
            supportFragmentManager.beginTransaction().replace(R.id.fl_carrete, photosFragments.get(2)).commit()
            currentPosition = 2
        }
    }

    override fun onNextClickListener() {
        if(currentPosition!=2){
            currentPosition++
            supportFragmentManager.beginTransaction().replace(R.id.fl_carrete, photosFragments.get(currentPosition)).commit()
        }
        else{
            currentPosition= 0
            supportFragmentManager.beginTransaction().replace(R.id.fl_carrete, photosFragments.get(0)).commit()
        }
    }

}
