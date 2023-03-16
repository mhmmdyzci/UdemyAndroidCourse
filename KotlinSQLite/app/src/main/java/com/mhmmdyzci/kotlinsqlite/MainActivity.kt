package com.mhmmdyzci.kotlinsqlite

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        try{
           //Database oluşturma  burdaki name database in adı
            var myDatabase = this.openOrCreateDatabase("Musicians", MODE_PRIVATE,null)
           // database tablo oluşturma
            myDatabase.execSQL("CREATE TABLE IF NOT EXISTS musicians (id INTEGER PRIMARY KEY,name VARCHAR, age INT)")
            // tabloya değer atama
            myDatabase.execSQL("INSERT INTO musicians(name,age) VALUES ('James',50)")
            myDatabase.execSQL("INSERT INTO musicians(name,age) VALUES ('Kirk',60)")

            // Tablodan güncelleme yapma
            myDatabase.execSQL("UPDATE musicians SET age = 61 WHERE name= 'Lars'")

            //Tabloda silme işlemi yapma
            myDatabase.execSQL("DELETE FROM musicians WHERE name ='Lars'")

            // Tablodan değer çekme (where filtreleme yapıyo )
            val cursor = myDatabase.rawQuery("SELECT * FROM musicians WHERE name = 'James' ",null)
            val idIx = cursor.getColumnIndex("id")
            val nameIx = cursor.getColumnIndex("name")
            val ageIx = cursor.getColumnIndex("age")

            //while ın içine koyduğumuz sart: imleç ekranda gezebildiği kadar gezsin şartı
            while (cursor.moveToNext()){
                println("Name: " + cursor.getString(nameIx))
                println("Age: " + cursor.getInt(ageIx))
                println("id: " + cursor.getInt(idIx))
            }
            cursor.close()

        }catch (e:Exception){
            e.printStackTrace()
        }
    }
}