package com.example.ms.ui

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.example.ms.R
import com.example.ms.adapter.DishAdapter
import com.example.ms.data.DishItem
import com.example.ms.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    // This is the amount user will enter and has to be passed
    val amount = binding.etBudget

    lateinit var adapter: DishAdapter
    private var dishItems = mutableListOf<DishItem>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        val myLM = LinearLayoutManager(this)
//        val rvDishes = binding.rvUserInfo
//        rvDishes.adapter = adapter
//        rvDishes.layoutManager = myLM

        val recyclerView = binding.rvUserInfo
        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        fetchDishes()
        adapter = DishAdapter(this, dishItems)
        recyclerView.adapter = adapter

    }


    private fun fetchDishes() {

        val url = ""
        // val queue = Volley.newRequestQueue(this)
        // Request a string response from the provided URL.
        val dishObjectRequest = JsonObjectRequest(Request.Method.GET, url, null,

            {
                val dishJsonArray = it.getJSONArray("DishItem")
                val dishArray = ArrayList<DishItem>()
                for (i in 0 until dishJsonArray.length()) {
                    val dishJsonObject = dishJsonArray.getJSONObject(i)
                    val dishes = DishItem(
                        dishJsonObject.getString("name"),
                        dishJsonObject.getString("id"),
                        dishJsonObject.getInt("price"),
                        dishJsonObject.getInt("score")
                    )
                    dishArray.add(dishes)
                }
                adapter.updateDishes(dishArray)
            },
            {
                Log.d("SR", it.toString())
            }
        )
        MySingleton.getInstance(this).addToRequestQueue(dishObjectRequest)
    }
}
//queue.add(dishObjectRequest)
//        { response ->
//
//            Log.d("SR",response.toString())     },
//        { error ->
//            Log.d("SR",error.toString())
//        }