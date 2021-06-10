package com.mazzeom.app.armstrong.libs.api

import com.mazzeom.app.armstrong.libs.api.ApiService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Api {
    companion object {
        var service: ApiService? = null

        @JvmStatic
        fun create(): ApiService {
            if (service == null) {
                val retrofit = Retrofit.Builder()
                    .baseUrl("https://undi.mazzeom.com/armstrong/api/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()

                service = retrofit.create(ApiService::class.java)
            }
            return service!!
        }
    }
}