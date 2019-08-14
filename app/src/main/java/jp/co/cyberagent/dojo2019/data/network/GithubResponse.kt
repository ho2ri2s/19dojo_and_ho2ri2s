package jp.co.cyberagent.dojo2019.data.network


import com.google.gson.annotations.SerializedName

data class GithubResponse(
    @SerializedName("avatar_url")
    val avatarUrl: String
)