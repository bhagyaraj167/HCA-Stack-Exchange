package com.example.hca_stackexchange.service

import com.example.hca_stackexchange.model.StackExchangeX
import retrofit2.Call
import retrofit2.http.GET

interface StackExchangeService {

 @GET("2.2/questions?order=desc&sort=activity&site=stackoverflow")
fun getStackExchangeResponse():Call<StackExchangeX>
}