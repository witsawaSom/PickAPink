package com.example.myproject.di

import com.example.myproject.MainActivity
import dagger.Component
import javax.inject.Singleton


@Singleton
@Component(
    modules = [AppModule::class]
)
interface AppComponent {
    fun inject(mainFragment: MainActivity)
}