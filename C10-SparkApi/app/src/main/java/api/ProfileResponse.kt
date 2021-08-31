package api

import com.google.gson.annotations.SerializedName

data class ProfileResponse(
    var id: Long = 0,
    var name: String = "",
    var gpa: Double = 0.0,
    var credit: Int = 0,
    @SerializedName("faculty") var facultyName: String = ""
)
