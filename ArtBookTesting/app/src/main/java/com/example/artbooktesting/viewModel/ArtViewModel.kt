package com.example.artbooktesting.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.artbooktesting.Util.Resource
import com.example.artbooktesting.model.ImageResponse
import com.example.artbooktesting.repo.ArtRepositoryInterface
import com.example.artbooktesting.roomdb.Art
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ArtViewModel @Inject constructor(
    private val repository: ArtRepositoryInterface
): ViewModel() {
    //Art fragment
    val artList = repository.getArt()

    //Image ApI fragment
    private val images = MutableLiveData<Resource<ImageResponse>>()
    val imageList : LiveData<Resource<ImageResponse>>
        get() = images

    private val selectedImage = MutableLiveData<String>()
    val selectedImageUrl : LiveData<String>
        get()= selectedImage


    // Art Deatils Fragment
    private var insertArtMsg = MutableLiveData<Resource<Art>>()
    val insertArtMessage : LiveData<Resource<Art>>
        get() = insertArtMessage

    fun resertInsertArtMsg(){
        insertArtMsg = MutableLiveData<Resource<Art>>()
    }

    fun setSelectedImage(url : String){
        selectedImage.postValue(url)
    }

    fun deleteArt (art : Art){
        viewModelScope.launch {
            repository.deleteArt(art)
        }

    }
    fun insertArt (art : Art){
        viewModelScope.launch {
            repository.insertArt(art)
        }

    }

    fun makeArt (name : String, artistName : String, year: String){
        if(name.isEmpty() || artistName.isEmpty() || year.isEmpty()){
            insertArtMsg.postValue(Resource.error("Enter name, artist, year",null))
        }
        var yearInt = try {
            year.toInt()

        }catch (e : Exception){
            insertArtMsg.postValue(Resource.error("Year shoukd be number",null))
            return
        }
        val art = Art(name, artistName,yearInt,selectedImage.value ?: "")
        insertArt(art)
        setSelectedImage("")
        insertArtMsg.postValue(Resource.success(art))


    }

    fun searchImage(searchString: String){
        if(searchString.isEmpty()){
            return
        }
        images.value = Resource.loading(null)
        viewModelScope.launch {
            val response = repository.searchImage(searchString)
            images.value = response
        }


    }



}