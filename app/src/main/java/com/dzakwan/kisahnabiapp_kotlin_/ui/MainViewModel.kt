package com.dzakwan.kisahnabiapp_kotlin_.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dzakwan.kisahnabiapp_kotlin_.data.KisahResponse
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import retrofit2.Response

// ViewModel disebut juga sebagai datalayer, sebagai jembatan dari data ke ui
class MainViewModel : ViewModel() {

    val kisahResponse = MutableLiveData<List<KisahResponse>>()
    val ifLoading = MutableLiveData<Boolean>()
    val ifError = MutableLiveData<Throwable>()

    fun getKisahNabi(responHandler: (List<KisahResponse>) -> Unit, errorHandler: (Throwable) -> Unit ) {
        ApiClient.getApiService().getKisahNabi()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                responHandler(it)
            }, {
                errorHandler(it)
            })
    }

    fun getData() {
        ifLoading.value = true

        getKisahNabi({
            ifLoading.value = false
            kisahResponse.value = it
            // another choice
//            kisahResponse.postValue(it)
        }, {
            ifLoading.value = false
            ifError.value = it
        })
    }
}