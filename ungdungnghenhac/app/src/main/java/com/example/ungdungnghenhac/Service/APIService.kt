package com.example.ungdungnghenhac.Service

object APIService {
    private val base_url = "https://appnhachy.000webhostapp.com/"

    fun getService(): Dataservice? {
        return APIRetrofitCilent.getClient(base_url)?.create(Dataservice::class.java)
    }
}