package api

import retrofit2.Call
import retrofit2.http.GET

interface SparkApi {

    @GET("/")
    fun getSample(): Call<String>

    @GET("/s/cjhhio7a7z76cpo/profile.json")
    fun getProfile(): Call<ProfileResponse>

    @GET("/s/rz6jsqx53jhwbtc/grades.json")
    fun getGrades(): Call<List<GradeResponse>>

}