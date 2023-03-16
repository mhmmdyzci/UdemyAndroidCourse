package com.example.hiltkotlin

import android.app.Activity
import android.app.Application
import com.google.gson.Gson
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.scopes.ActivityScoped
import dagger.hilt.components.SingletonComponent
import javax.inject.Qualifier
import javax.inject.Singleton

interface MyInterface {
    fun myPrintFuction () : String
}
/*
@InstallIn(ActivityComponent::class)
@Module
abstract class MyModule{
    @ActivityScoped
    @Binds
    abstract fun bindingFuction(myImplementor: InterfaceImplementor) : MyInterface


}*/
@InstallIn(SingletonComponent::class)
@Module
class MyModule{
    @FirstImplementor
    @Singleton
    @Provides
    fun providerFunction() : MyInterface{
        return InterfaceImplementor()
    }

    @SecondImplementor
    @Singleton
    @Provides
    fun secondProviderFunction() : MyInterface{
        return  SecondInterfaceImplementor()
    }

    @Singleton
    @Provides
    fun gSonProvider() : Gson {
        return  Gson()
    }

}

//Annotation Oluşturmak
@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class FirstImplementor
@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class SecondImplementor