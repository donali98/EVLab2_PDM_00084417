package com.example.evlab2_pdm_00084417

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val photosFragment = PhotosFragment()
        supportFragmentManager.beginTransaction().add(R.id.fl_carrete,photosFragment).commit()

    }
}
/*
package com.example.pokeapi


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView


class PokemonInfoFragment : Fragment() {

    lateinit var tvPokemonName:TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_pokemon_info, container, false)
        tvPokemonName = view.findViewById(R.id.tv_poke_name)
        tvPokemonName.text = arguments!!.getString("name","none")
        return view
    }

    companion object {

        fun newInstance(param1: String) =
            PokemonInfoFragment ().apply {
                arguments = Bundle().apply {
                    putString("name", param1)
                }
            }
    }


}
//////////////////////////////////////////////////////////////////////////////////////////

package com.example.pokeapi

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.FrameLayout
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.pokeapi.Pokemon.Pokemon
import com.example.pokeapi.Utilities.Helper
import com.example.pokeapi.Utilities.LogNames
import com.example.pokeapi.Utilities.NetworkUtils
import com.google.gson.Gson
import com.squareup.picasso.Picasso

class MainActivity : AppCompatActivity(),Helper {


    var framePokemonInfo: FrameLayout? = null
    lateinit var fragmentRecyclerView:RecyclerView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        framePokemonInfo = findViewById(R.id.pokeInfoContainer)
        fragmentRecyclerView = findViewById(R.id.pokemon_list_rv)


    }

    override fun getIsContainerVisible(): Boolean? {
        return framePokemonInfo!=null && framePokemonInfo?.visibility == View.VISIBLE

    }

    override fun getPokemonListRecyclerView(): RecyclerView? {
        return fragmentRecyclerView
    }

    override fun getLayoutManager(): RecyclerView.LayoutManager? {
        return LinearLayoutManager(this)
    }

    override fun getFragmentTransaction(): FragmentTransaction? {
        return supportFragmentManager.beginTransaction()
    }
    override fun getAcIntent(): Intent? {
        return Intent(this, PokemonViewer::class.java)
    }

    override fun getRequestQueue(): RequestQueue? {
        return Volley.newRequestQueue(this)
    }


}
/////////////////////////////////////////////////////////////////////////////////
package com.example.pokeapi


import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.pokeapi.Pokemon.Pokemon
import com.example.pokeapi.Utilities.Helper
import com.example.pokeapi.Utilities.LogNames
import com.example.pokeapi.Utilities.NetworkUtils
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_main.*


class PokemonListFragment : Fragment() {

    var pokemons: ArrayList<Pokemon> = ArrayList()
    lateinit var helper: Helper
    lateinit var pokemonListRv: RecyclerView
    lateinit var customLayoutManager: RecyclerView.LayoutManager
    lateinit var pokemonAdapter: PokemonAdapter
    var currentPosition = 0
    var isLandscape = false
    val networkUtils: NetworkUtils = NetworkUtils()



    override fun onAttach(context: Context?) {
        super.onAttach(context)
        helper = context as Helper
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_pokemon_list, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        configureRecyclerView()

        isLandscape = helper.getIsContainerVisible()!!

        if (savedInstanceState != null) {
            currentPosition = savedInstanceState.getInt("INDEX", 0)
        }
        /*if (isLandscape) {
            clickItemListener(currentPosition)
        }*/
    }

    val clickItemListener = fun(itemIndex: Int) {
        val item = pokemons.get(itemIndex)

        if (!isLandscape) {
            val i = helper.getAcIntent()!!
            i.putExtra("name", item.name)
            startActivity(i)
        } else {
            val pokemonInfoFragment = PokemonInfoFragment.newInstance(item.name)

            val fragmentTransaction = helper.getFragmentTransaction()!!
            fragmentTransaction.replace(R.id.pokeInfoContainer,pokemonInfoFragment)
            fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
            fragmentTransaction.commit()
        }
    }

    fun configureRecyclerView() {
        //Haciendo la peticion
        networkUtils.getResponse(null) {
            val queue = helper.getRequestQueue()
            val request = StringRequest(
                Request.Method.GET, it.toString(),
                Response.Listener { response ->
                    run {
                        val pokeInfo = networkUtils.getPokemonInformation(response)
                        for (i in 0 until pokeInfo.results.size) {
                            //Log.d(LogNames.customLog,pokeInfo.results[i].name)
                            val pokeRequest = StringRequest(
                                Request.Method.GET, pokeInfo.results[i].url,
                                Response.Listener { response ->
                                    run {
                                        //Log.d(LogNames.customLog,response.toString())
                                        try {
                                            val gson = Gson()
                                            val pokemon = gson.fromJson(
                                                response.toString(),
                                                Pokemon::class.java
                                            )

                                            pokemons.add(pokemon)
                                            if (i == pokeInfo.results.size - 1) {

                                                //Log.i(LogNames.customLog,"yes")
                                                pokemonListRv = helper.getPokemonListRecyclerView()!!
                                                customLayoutManager = helper.getLayoutManager()!!

                                                pokemonAdapter = PokemonAdapter(pokemons,clickItemListener)
                                                pokemonListRv.apply {
                                                    setHasFixedSize(true)
                                                    layoutManager = customLayoutManager
                                                    adapter = pokemonAdapter
                                                }

                                            } else {
                                            }
                                            //Log.d(LogNames.customLog,pokemons[0].types[0].type.name)
                                        } catch (e: Exception) {
                                            Log.e(LogNames.customError, e.toString())
                                        }


                                    }
                                },
                                Response.ErrorListener {
                                    Log.e(LogNames.customError, it.toString())

                                })
                            queue!!.add(pokeRequest)
                        }

                    }

                },
                Response.ErrorListener {
                    Log.e(LogNames.customError, it.toString())
                })
            queue!!.add(request)

        }

    }


}*/