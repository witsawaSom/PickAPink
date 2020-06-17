package com.example.myproject.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class SimpleViewModel : ViewModel() ,CoroutineScope{

    private val job = Job()


    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.Main


    private val _click =  MutableLiveData(0)
    var clickCount : LiveData<Int> = _click

    fun onClick(){
        _click.value = (_click.value ?: 0) + 1
    }

    fun count() = clickCount.value.toString()

    fun onClick2(){
        launch {
            delay(1000)
            println("Kotlin Coroutines World!")
        }
        println("Hello")
    }

}