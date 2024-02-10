package com.chavvarohan.infobyteinternassessment

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.chavvarohan.infobyteinternassessment.databinding.ActivityWatchListBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ActivityWatchList : AppCompatActivity() {

    private lateinit var binding: ActivityWatchListBinding

    private lateinit var list: ArrayList<DataItem>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWatchListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        list = ArrayList()

        val layoutManager = LinearLayoutManager(this)

        val adapter = RecyclerAdapter(list,this)

        binding.recyclerViewWatchList.layoutManager = layoutManager


        val retrofit: Retrofit = Retrofit.Builder().baseUrl("https://script.googleusercontent.com/")
            .addConverterFactory(GsonConverterFactory.create()).build()
        val api: ApiInterface = retrofit.create(ApiInterface::class.java)
        val call: Call<Data> = api.getData()

        call.enqueue(object : Callback<Data?> {
            @SuppressLint("NotifyDataSetChanged")
            override fun onResponse(call: Call<Data?>, response: Response<Data?>) {
                if (response.isSuccessful){
                    list.clear()
                    for (myData in response.body()!!){
                        list.add(myData)
                    }

                    adapter.notifyDataSetChanged()
                    binding.recyclerViewWatchList.adapter = adapter

                }
            }

            override fun onFailure(call: Call<Data?>, t: Throwable) {
                Toast.makeText(this@ActivityWatchList,"Error",Toast.LENGTH_SHORT).show()
            }

        })

    }
}