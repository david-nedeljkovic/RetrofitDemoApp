package com.android.retrofitexampledemoapp

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.retrofitexampledemoapp.adapter.RecyclerViewAdapter
import com.android.retrofitexampledemoapp.databinding.ActivityMainBinding
import com.android.retrofitexampledemoapp.model.Post
import com.android.retrofitexampledemoapp.repository.Repository
import com.android.retrofitexampledemoapp.viewmodel.MainViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel
    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: RecyclerViewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        adapter = RecyclerViewAdapter()
        binding.rvListPosts.layoutManager = LinearLayoutManager(this)
        binding.rvListPosts.adapter = adapter

        val repository = Repository()
        viewModel = MainViewModel(repository)


        val customPost = Post(23,5,"Android developer","David Nedeljkovic, Junior Android developer.")
        viewModel.pushCustomPost(customPost)
        viewModel.responsePost.observe(this, Observer { response ->
            if(response.isSuccessful) {
                Log.i(TAG, "POST code: ${response.code()}")
                Log.i(TAG, "POST body: ${response.body()}")
                Log.i(TAG, "POST message: ${response.message()}")
            } else {
                Toast.makeText(this, "${response.errorBody()}", Toast.LENGTH_SHORT).show()
            }
        })

        viewModel.getCustomPosts(5, "id", "desc")
        viewModel.response3.observe(this, Observer { response ->
            if(response.isSuccessful) {
                response.body()?.let { adapter.setListPosts(it) }
            } else {
                Toast.makeText(this, "${response.errorBody()}", Toast.LENGTH_SHORT).show()
            }
        })
/**
        viewModel.getPost()
        viewModel.response.observe(this, Observer { it ->
            if (it.isSuccessful) {
                binding.tvResponse.text = it.body()?.title ?: "Title not exist"
            } else {
                Log.i(TAG, "errorBody: ${it.errorBody()}")
                binding.tvResponse.text = it.code().toString()
            }
        })

        binding.btnGet.setOnClickListener {
            val number = binding.editTextNumber.text.toString()
            viewModel.getPost2(number = number.toInt())
            viewModel.response2.observe(this, Observer { it ->
                if (it.isSuccessful) {
                    binding.tvResponse.text = it.body().toString()
                } else {
                    binding.tvResponse.text = it.code().toString()
                }
            })
        }

        val options: HashMap<String, String> = HashMap()
        options["_sort"] = "id"
        options["_order"] = "desc"

        binding.btnGet.setOnClickListener {
            val userId = binding.editTextNumber.text.toString()
            viewModel.getCustomPosts2(userId.toInt(), options)
            viewModel.response4.observe(this, Observer { listPosts ->
                if (listPosts.isSuccessful) {
                    binding.tvResponse.text = listPosts.body().toString()
                    listPosts.body()?.forEach { it ->
                        Log.i(TAG, "userId: ${it.userId}")
                        Log.i(TAG, "id: ${it.id}")
                        Log.i(TAG, "title: ${it.title}")
                        Log.i(TAG, "body: ${it.body}")
                    }
                } else {
                    binding.tvResponse.text = listPosts.code().toString()
                }
            })
        }
 **/
    }

    companion object {
        private val TAG: String = MainActivity::class.java.name
    }
}
