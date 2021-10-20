package com.example.newsapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response



class MainActivity : AppCompatActivity() {
    private val TAG: String = "MainActivity"

    private lateinit var newsAdapter: NewsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupRecyclerView()
        getDataFromApi()
    }

    private fun setupRecyclerView(){
        newsAdapter = NewsAdapter(arrayListOf())
        recyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = newsAdapter
        }
    }

    private fun getDataFromApi(){
        showLoading(true)
        val serviceGenerator = ServiceGenerator.buildService(ApiService::class.java)
        serviceGenerator.getNews("keyword", "4fb5d5505596460fba24aa2de7c560ec")
            .enqueue(object : Callback<NewsModel> {
                override fun onFailure(call: Call<NewsModel>, t: Throwable) {
                    printLog( t.toString() )
                    showLoading(false)
                }
                override fun onResponse(
                    call: Call<NewsModel>,
                    response: Response<NewsModel>
                ) {
                    printLog( response.toString() )

                    showLoading(false)
                    if (response.isSuccessful) {
                        showResult( response.body()!! )
                    }
                }
            })
    }

    private fun printLog(message: String) {
        Log.d(TAG, message)
    }

    private fun showLoading(loading: Boolean) {
        when(loading) {
            true -> progressBar.visibility = View.VISIBLE
            false -> progressBar.visibility = View.GONE
        }
    }

    private fun showResult(results: NewsModel) {
        val result = results
        for (result in results.articles) printLog( "title: ${result.title}" )
        newsAdapter.setData( results.articles )
    }
}
