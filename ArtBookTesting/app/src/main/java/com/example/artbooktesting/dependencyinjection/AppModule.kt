package com.example.artbooktesting.dependencyinjection

import android.content.Context
import androidx.core.content.PermissionChecker.PermissionResult
import androidx.room.Room
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.bumptech.glide.request.RequestOptions
import com.example.artbooktesting.API.RetrofitAPI
import com.example.artbooktesting.R
import com.example.artbooktesting.Util.Util.BASE_URL
import com.example.artbooktesting.repo.ArtRepository
import com.example.artbooktesting.repo.ArtRepositoryInterface
import com.example.artbooktesting.roomdb.ArtDao
import com.example.artbooktesting.roomdb.ArtDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun injectRoomDatabese(@ApplicationContext context : Context) =
        Room.databaseBuilder(context,ArtDatabase::class.java,"ArtBookDB").build()

    @Singleton
    @Provides
    fun injectDao (database : ArtDatabase) = database.artDao()

    @Singleton
    @Provides
    fun injectRetrofitAPI() : RetrofitAPI =
        Retrofit.Builder().addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
            .create(RetrofitAPI::class.java)

    @Singleton
    @Provides
    fun InjectNormalRepo(dao : ArtDao, api : RetrofitAPI) = ArtRepository(dao,api) as ArtRepositoryInterface


    @Singleton
    @Provides
    fun InjectGlide (@ApplicationContext context: Context): RequestManager {
        return Glide.with(context).setDefaultRequestOptions(
            RequestOptions().placeholder(R.drawable.ic_launcher_foreground)
                .error(R.drawable.ic_launcher_foreground)
        )
    }


}