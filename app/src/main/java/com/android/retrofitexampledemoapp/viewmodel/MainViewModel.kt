package com.android.retrofitexampledemoapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.retrofitexampledemoapp.model.Post
import com.android.retrofitexampledemoapp.repository.Repository
import kotlinx.coroutines.launch
import retrofit2.Response

class MainViewModel(private val repository: Repository): ViewModel() {

    val response: MutableLiveData<Response<Post>> = MutableLiveData()
    val response2: MutableLiveData<Response<Post>> = MutableLiveData()
    val response3: MutableLiveData<Response<List<Post>>> = MutableLiveData()
    val response4: MutableLiveData<Response<List<Post>>> = MutableLiveData()
    val responsePost: MutableLiveData<Response<Post>> = MutableLiveData()

    fun getPost() {
        viewModelScope.launch {
            val myResponse = repository.getPost()
            response.value = myResponse
        }
    }

    fun getPost2(number: Int) {
        viewModelScope.launch {
            val myResponse2 = repository.getPost2(number)
            response2.value = myResponse2
        }
    }

    fun getCustomPosts(userId: Int, sort: String, order: String) {
        viewModelScope.launch {
            val myResponse3 = repository.getCustomPosts(userId, sort, order)
            response3.value = myResponse3
        }
    }

    fun getCustomPosts2(userId: Int, options: Map<String, String>) {
        viewModelScope.launch {
            val myResponse4 = repository.getCustomPosts2(userId, options)
            response4.value = myResponse4
        }
    }

    fun pushCustomPost(post: Post) {
        viewModelScope.launch {
            val myResponsePost = repository.pushPost(post)
            responsePost.value = myResponsePost
        }
    }
}
