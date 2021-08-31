package vms.android.sampleapi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import api.GradeResponse
import api.ProfileResponse
import api.SparkApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory

class MainActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl("https://dl.dropboxusercontent.com")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val sparkApi: SparkApi = retrofit.create(SparkApi::class.java)

        // Get Profile
        val getProfileRequest: Call<ProfileResponse> = sparkApi.getProfile()
        getProfileRequest.enqueue(object: Callback<ProfileResponse> {
            override fun onResponse(call: Call<ProfileResponse>, response: Response<ProfileResponse>) {
                val profileResponse = response.body()
                if (profileResponse != null) {
                    Log.d("SPARK-API", profileResponse.facultyName)
                }
            }

            override fun onFailure(call: Call<ProfileResponse>, t: Throwable) {
                Log.e("SPARK_API", "Failed to request!")
            }
        })

        // Get Grades
        val getGradeRequest: Call<List<GradeResponse>> = sparkApi.getGrades()
        getGradeRequest.enqueue(object: Callback<List<GradeResponse>> {
            override fun onResponse(call: Call<List<GradeResponse>>, response: Response<List<GradeResponse>>) {
                var gradeResponse = response.body()
                if (gradeResponse != null) {
                    Log.d("SPARK-API", gradeResponse[0].toString())
                }
            }

            override fun onFailure(call: Call<List<GradeResponse>>, t: Throwable) {
                Log.e("SPARK_API", "Failed to request!")
            }
        })
    }
}