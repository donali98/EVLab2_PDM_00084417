package com.example.evlab2_pdm_00084417


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView


class PhotosFragment : Fragment() {

    lateinit var imgPhoto: ImageView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_photos, container, false)
        imgPhoto = view.findViewById(R.id.img_foto)
        imgPhoto.setImageResource(R.drawable.ic_launcher_background)
        return view
    }

    companion object {

        fun newInstance(param1: Int) =
                PhotosFragment().apply {
                    arguments = Bundle().apply {
                        putInt("resource", param1)
                }
        }
    }


}
