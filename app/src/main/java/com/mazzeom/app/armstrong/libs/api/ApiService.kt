package com.mazzeom.app.armstrong.libs.api

import com.mazzeom.app.armstrong.libs.api.request.DeleteProfileRequestBody
import com.mazzeom.app.armstrong.libs.api.request.PostProfileRequestBody
import com.mazzeom.app.armstrong.libs.api.response.GetProfileResponse
import com.mazzeom.app.armstrong.libs.api.response.PostProfileResponse
import retrofit2.Call
import retrofit2.http.*

interface ApiService {
    @Headers("Content-Type: application/json")
    @GET("profile")
    fun getProfileRequest(): Call<GetProfileResponse>

    @Headers("Content-Type: application/json")
    @POST("profile")
    fun postProfileRequest(
        @Body parameters: PostProfileRequestBody
    ): Call<PostProfileResponse>

    @Headers("Content-Type: application/json")
    @DELETE("profile")
    fun deleteProfileRequest(
        @Body parameters: DeleteProfileRequestBody
    ): Call<HashMap<String, Any>>
}