package jp.co.cyberagent.dojo2019.data.network

import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET("users/{account}")
    suspend fun fetchGithubUser(@Path("account") account: String): GithubResponse
}