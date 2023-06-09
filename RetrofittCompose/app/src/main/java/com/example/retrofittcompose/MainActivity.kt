package com.example.retrofittcompose

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.retrofittcompose.model.CryptoModel
import com.example.retrofittcompose.service.CryptoAPI
import com.example.retrofittcompose.ui.theme.RetrofittComposeTheme
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : ComponentActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RetrofittComposeTheme {
                MainScreen()
            }
        }
    }
}


@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable

fun MainScreen() {
    val BASE_URL = "https://raw.githubusercontent.com/"
    var cryptoModels = remember { mutableStateListOf<CryptoModel>() }

    val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(CryptoAPI::class.java)

    val call = retrofit.getData()

    call.enqueue(object: Callback<List<CryptoModel>> {
        override fun onFailure(call: Call<List<CryptoModel>>, t: Throwable) {
            t.printStackTrace()
        }
        override fun onResponse(
            call: Call<List<CryptoModel>>,
            response: Response<List<CryptoModel>>
        ) {
            if (response.isSuccessful) {
                response.body()?.let {
                    cryptoModels.addAll(it)
                }
            }
        }
    })

    Scaffold(topBar = { AppBar() }) {
        Surface(color = MaterialTheme.colors.surface) {
            CryptoList(cryptos = cryptoModels)
        }
    }
}


@Composable
fun AppBar() {
    TopAppBar(elevation = 10.dp,contentPadding = PaddingValues(15.dp)) {
        Text(text = "Retro Compose",fontSize = 26.sp)
    }
}

@Composable
fun CryptoList(cryptos: List<CryptoModel>) {
    LazyColumn(contentPadding = PaddingValues(5.dp)) {
        items(cryptos) { crypto ->
            CryptoRow(crypto = crypto)
        }
    }
}

@Composable
fun CryptoRow(crypto: CryptoModel) {
    Column(modifier = Modifier.fillMaxWidth().background(color = MaterialTheme.colors.surface).clickable {
        println(crypto)
    }) {
        Text(text = crypto.currency,
            style = MaterialTheme.typography.h4,
            modifier = Modifier.padding(2.dp),
            fontWeight = FontWeight.Bold
        )
        Text(text = crypto.price,
            style = MaterialTheme.typography.h5,
            modifier = Modifier.padding(2.dp))
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    RetrofittComposeTheme {
        CryptoRow(CryptoModel("BTC","50000"))
    }
}