package com.mazzeom.app.armstrong.libs.api

import com.mazzeom.app.armstrong.libs.api.request.*
import com.mazzeom.app.armstrong.libs.api.response.*
import retrofit2.Call
import retrofit2.http.*

interface ApiService {
    @GET("profile")
    fun getProfileRequest(): Call<GetProfileResponse>

    @POST("profile")
    fun postProfileRequest(
        @Body parameters: PostProfileRequestBody
    ): Call<PostProfileResponse>

    @PUT("profile")
    fun putProfileRequest(
        @Body parameters: PutProfileRequestBody
    ): Call<PutProfileResponse>

    @DELETE("profile")
    fun deleteProfileRequest(
        @Body parameters: DeleteProfileRequestBody
    ): Call<HashMap<String, Any>>

    @GET("pushup/{profileId}/{date}")
    fun getPushUpByProfileIdAndDateRequest(
        @Path("profileId") profileId: Int,
        @Path("date") date: String
    ): Call<GetPushUpByProfileIdAndDateResponse>

    @GET("record/{profileId}/{date}")
    fun getRecordByProfileIdAndDateRequest(
        @Path("profileId") profileId: Int,
        @Path("date") date: String
    ): Call<GetRecordByProfileIdAndDateResponse>

    @DELETE("record/{profileId}/{recordId}")
    fun deleteRecordByProfileIdAndRecordIdRequest(
       @Path("profileId") profileId: Int,
       @Path("recordId") recordId: Int
    ): Call<DeleteRecordByProfileIdAndRecordIdResponse>

    @POST("record/{profileId}")
    fun postRecordByProfileIdRequest(
        @Path("profileId") profileId: Int,
        @Body parameters: PostRecordByProfileIdRequestBody
    ): Call<PostRecordByProfileIdResponse>

    @PUT("record/{profileId}")
    fun putRecordByProfileId(
        @Path("profileId") profileId: Int,
        @Body parameters: PutRecordByProfileIdRequestBody
    ): Call<PutRecordByProfileIdResponse>
}