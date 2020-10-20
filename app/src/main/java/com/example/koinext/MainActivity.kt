package com.example.koinext

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.websockerext.MainViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {
    private val viewModel: MainViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel.postAppResult().observe(this) { appresult ->
            println("appresult:applestoreurl:${appresult.applestoreurl}, result:${appresult.result}")
        }
    }
}