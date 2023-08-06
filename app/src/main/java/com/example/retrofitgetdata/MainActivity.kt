package com.example.retrofitgetdata

/*
* Used to get web data
* Web service provider
* ******************** */

/*
* add gradle dependency
* use permission for internet in manifest
* * create layout file : TEXTVIEW (one data at a time)
* ***** to show all data, use recycler view...
        * create custom layout
        * create adapter class
        * use progress bar as data comes from web & might take time to load
            * linearlayout -> constraint layout
            * add progress bar
            * make recycler view invisible while loading
* create modal class(data) to modal the data - optional
* create interface */

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.retrofitgetdata.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

class MainActivity : AppCompatActivity() {

    // base url- before posts
    private val baseUrl = "https://jsonplaceholder.typicode.com/"

    // enable view binding
    // class automatically created when imported view binding in project
    lateinit var mainBinding: ActivityMainBinding

    // arraylist
    var postsList = ArrayList<Posts>()

    // object of adapter class
    // mention in onCreate method, how data will be displayed
    lateinit var adapter: PostsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //inflating binding
        mainBinding = ActivityMainBinding.inflate(layoutInflater)
        // to access elements directly
        val view = mainBinding.root

        // to directly access view binding
        // replace layout file with view variable
        // inside content view parenthesis
        setContentView(view)

        // how to display adapter class data in recycler view
        // continue inside onResponse fun
        mainBinding.recyclerView.layoutManager = LinearLayoutManager(this)

        // calling the fun in oncreate method
        showData()
    }

    // to show data
    fun showData(){
        val retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val retrofitAPI: RetrofitAPI = retrofit.create(RetrofitAPI::class.java)

        // fun getting data in interface used call, hence using call
        // to get retrofit in main activity
        val call: Call<List<Posts>> = retrofitAPI.getAllPosts()

        call.enqueue(object: Callback<List<Posts>>{
            override fun onResponse(call: Call<List<Posts>>, response: Response<List<Posts>>) {

                if (response.isSuccessful){

                    // when response successful, progressbar invisible and recycler view visible
                    mainBinding.progressBar.isVisible = false
                    mainBinding.recyclerView.isVisible = true

                    // add response to arraylist from rest api
                    postsList = response.body() as ArrayList<Posts>
                    adapter = PostsAdapter(postsList)
                    mainBinding.recyclerView.adapter = adapter
                }

            /**************************************************************************************
             * for  displaying single data item in textview
             *
             *
                // if no response, data to avoid app crash
                if(!response.isSuccessful){

                    mainBinding.textViewUserId.text = "error"
                    mainBinding.textViewId.text = "error"
                    mainBinding.textViewTitle.text = "error"
                    mainBinding.textViewBody.text = "error"

                }

                // if import successful, get data in arraylist
                // data from restAPI gets transferred into arrayList as kotlin object
                postsList = response.body() as ArrayList<Posts>

                // 0- specifies index no
                // .toString converts int to string datatype
                mainBinding.textViewUserId.text = postsList[1].userId.toString()
                mainBinding.textViewId.text = postsList[1].id.toString()
                mainBinding.textViewTitle.text = postsList[1].title
                mainBinding.textViewBody.text = postsList[1].subtitle
                *********************************************************** */
            }

            // if error occurs, determine error
            override fun onFailure(call: Call<List<Posts>>, t: Throwable) {
                Toast.makeText(applicationContext
                ,t.localizedMessage
                ,Toast.LENGTH_LONG).show()
            }

        })
    }
}