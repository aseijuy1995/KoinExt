package com.example.websockerext

import androidx.lifecycle.*
import com.example.websockerext.conn.IApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.java.KoinJavaComponent.inject

class MainViewModel(private val repo: MainRepository) : ViewModel() {
    private val mAppResult = MutableLiveData<AppResult>()
//    val appResult: LiveData<AppResult> = mAppResult


    fun postAppResult():LiveData<AppResult> {
        viewModelScope.launch(Dispatchers.IO) {
            mAppResult.postValue(repo.postAppResult())
        }
        return mAppResult
    }
}