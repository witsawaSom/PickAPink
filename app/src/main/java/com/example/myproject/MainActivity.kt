package com.example.myproject

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.myproject.databinding.ActivityMainBinding
import com.example.myproject.di.DaggerAppComponent
import com.example.myproject.viewmodel.SimpleViewModel
import com.example.myproject.viewmodel.SimpleViewModelFactory
import kotlinx.coroutines.*


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModelFactory: SimpleViewModelFactory
    private lateinit var viewModel: SimpleViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        DaggerAppComponent.create().inject(this)
        viewModelFactory = SimpleViewModelFactory()
        viewModel = ViewModelProvider(viewModelStore, viewModelFactory).get(SimpleViewModel::class.java)
        binding.lifecycleOwner = this
        binding.viewmodel = viewModel


        viewModel.onClick2()


    }


}
