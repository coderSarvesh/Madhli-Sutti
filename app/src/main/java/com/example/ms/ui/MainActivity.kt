package com.example.ms.ui

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.Request
import com.android.volley.toolbox.JsonArrayRequest
import com.example.ms.adapter.DishAdapter
import com.example.ms.data.DishItem
import com.example.ms.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding


    private lateinit var adapter: DishAdapter
    private val dishItems = mutableListOf<DishItem>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.btnSuggestDishes.setOnClickListener {

            var amount = binding.etBudget.text.toString()
            if (amount.isNotBlank() && amount.isNotEmpty()){
                amount = binding.etBudget.text.toString()
            }
            else{
                Toast.makeText(this, "Enter A Valid Amount", Toast.LENGTH_SHORT).show()
            }
            println(amount)
            fetchDishes(amount)
        }

        val recyclerView = binding.rvUserInfo
        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        adapter = DishAdapter(this, dishItems)
        recyclerView.adapter = adapter

    }


    private fun fetchDishes(amount: String) {

        val url = "https://6342d5c482fe0e2127e6db00--warm-sawine-0692cd.netlify.app/api/dish/$amount"
        // val queue = Volley.newRequestQueue(this)
        // Request a string response from the provided URL.
        val dishObjectRequest = JsonArrayRequest(Request.Method.GET, url, null,

            { list ->
                println(list)
                val dishJsonArray = list
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
