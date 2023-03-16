package com.mhmmdyzci.artbookkotlin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.recyclerview.widget.LinearLayoutManager
import com.mhmmdyzci.artbookkotlin.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var artList : ArrayList<Art>
    private lateinit var artAdapter: ArtAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        artList = ArrayList<Art>()
        artAdapter = ArtAdapter(artList)

        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = artAdapter

        try {
            val database = this.openOrCreateDatabase("Arts", MODE_PRIVATE,null)
            val cursor = database.rawQuery("SELECT * FROM arts ",null)
            val artNameIx = cursor.getColumnIndex("artname")
            var idIx = cursor.getColumnIndex("id")
            while (cursor.moveToNext()){
                var artName = cursor.getString(artNameIx)
                var id = cursor.getInt(idIx)
                var art = Art(artName,id)
                artList.add(art)

            }
            artAdapter.notifyDataSetChanged()
            cursor.close()
        }catch (e:Exception){
            e.printStackTrace()
        }

        var artAdapter = ArtAdapter(artList)
        binding.recyclerView.adapter = artAdapter

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        //Menüyü bağlama işlemi
        val menuInflater = menuInflater
        // R.menu.art_menu art_menu menü dosyasının adı
        menuInflater.inflate(R.menu.art_menu,menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Menüye tıklanırsa ne olcak
        //R.id.add_art_item add_art_item menüdeki tıklayacağımız yerin id si
        if(item.itemId == R.id.add_art_item){
            val intent = Intent(this@MainActivity,DetailsActivity::class.java)
            intent.putExtra("info","new")
            startActivity(intent)
        }
        return super.onOptionsItemSelected(item)
    }
}