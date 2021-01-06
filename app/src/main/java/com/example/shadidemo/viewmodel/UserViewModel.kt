package com.example.shadidemo.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shadidemo.database.RoomDBApplication
import com.example.shadidemo.database.entity.UserEntity
import com.example.shadidemo.model.UserResponse
import com.example.shadidemo.network.ApiClient
import com.example.shadidemo.network.ApiInterface
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserViewModel : ViewModel() {

    private val isLoading = MutableLiveData<Boolean>()
    private val userDao = RoomDBApplication.database?.userDao()

    init {
        isLoading.postValue(true)
        val apiService = ApiClient.getClient()?.create(ApiInterface::class.java)
        apiService?.getUserResponse()?.enqueue(object : Callback<UserResponse> {
            override fun onResponse(call: Call<UserResponse>, response: Response<UserResponse>) {
                response.apply {
                    if (isSuccessful) {
                        isLoading.postValue(false)
                        body()?.results?.let {
                            val usersList = it.map { UserEntity(it.name.first, it.name.last, it.dob.age, it.location.state, it.location.city, it.location.country, it.picture.medium.toByteArray(Charsets.UTF_8)) }
                            insertUserDataToDB(usersList)
                        }
                    }
                }
            }
            override fun onFailure(call: Call<UserResponse>, t: Throwable) {
                isLoading.postValue(false)
            }
        })
    }

    private fun insertUserDataToDB(usersList: List<UserEntity>) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                userDao?.apply {
                    deleteAllUsers()
                    insertAllUsers(usersList)
                }
            }
        }
    }

    fun updateStatus(status: Int, userEntity: UserEntity) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                userDao?.apply {
                    updateUser(status, userEntity.id)
                }
            }
        }
    }

    fun displayUsers(): LiveData<List<UserEntity>>? {
        return userDao?.getAllUsers()
    }

    fun isLoading(): MutableLiveData<Boolean> {
        return isLoading
    }
}