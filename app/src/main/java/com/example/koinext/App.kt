package com.example.koinext

import android.app.Application
import com.example.websockerext.MainRepository
import com.example.websockerext.MainRepositoryImpl
import com.example.websockerext.MainViewModel
import com.example.websockerext.conn.IApiService
import com.example.websockerext.const.BASE_URL
import com.example.websockerext.const.createOkHttp
import com.example.websockerext.const.createRetrofit
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.KoinComponent
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import org.koin.dsl.module

class App : Application(), KoinComponent {
    private val appModule = module {
        single<IApiService> {
            createRetrofit(
                okHttpClient = createOkHttp(),
                baseUrl = BASE_URL
//                ,
//                factory = CoroutineCallAdapterFactory()
            )
        }
        factory<MainRepository> { MainRepositoryImpl(iApiService = get()) }
        viewModel { MainViewModel(repo = get()) }
    }

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(Level.NONE)
            androidContext(this@App)
            modules(appModule)
        }
    }
}