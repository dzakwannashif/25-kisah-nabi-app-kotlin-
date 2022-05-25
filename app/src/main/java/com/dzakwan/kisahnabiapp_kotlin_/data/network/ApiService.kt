package com.dzakwan.kisahnabiapp_kotlin_.data.network

import com.dzakwan.kisahnabiapp_kotlin_.data.KisahResponse
import io.reactivex.rxjava3.core.Flowable
import retrofit2.http.GET

// pengganti dao kalo di localedatabase
interface ApiService {

    @GET("kisahnabi")
    fun getKisahNabi() : Flowable<List<KisahResponse>>
}