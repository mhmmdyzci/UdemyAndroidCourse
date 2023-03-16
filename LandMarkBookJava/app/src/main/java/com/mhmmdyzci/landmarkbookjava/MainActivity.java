package com.mhmmdyzci.landmarkbookjava;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.mhmmdyzci.landmarkbookjava.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    ArrayList<Landmark> landmarkArrayList;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        landmarkArrayList = new ArrayList<>();
        Landmark trabzon = new Landmark("Edanur","Trabzon (Toplam nüfüs IQ su 100 falan)", R.drawable.trabzon );
        Landmark erzincan = new Landmark("MRX YUSUF", "Erzincan(Yobazlar Şehri)", R.drawable.erzincan);
        Landmark mardin = new Landmark("Cemil", "Mardin (Suriye denebilir)", R.drawable.mardin);
        Landmark hatay = new Landmark( "MRX.Jr Mami", "Hatay(Medeniyetler Şehri)", R.drawable.hatay);
        Landmark yozgat = new Landmark("BatuHAN", "Yozgat(Kayseri)", R.drawable.yzogat);
        Landmark bayburt = new Landmark("Murat","Bayburt(Ora nere amk)", R.drawable.bayburt);
        Landmark bursa = new Landmark("Oğuz&Görkem","Bursa(Elif)",R.drawable.bursa);
        Landmark aa = new Landmark("Şerife","Eskişehir(İtalya)",R.drawable.eski);



        landmarkArrayList.add(trabzon);
        landmarkArrayList.add(erzincan);
        landmarkArrayList.add(mardin);
        landmarkArrayList.add(hatay);
        landmarkArrayList.add(yozgat);
        landmarkArrayList.add(bayburt);
        landmarkArrayList.add(bursa);
        landmarkArrayList.add(aa);


        /*binding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        LandMarkAdapter landMarkAdapter = new LandMarkAdapter(landmarkArrayList);
        binding.recyclerView.setAdapter(landMarkAdapter) ; */
        //Adapter ListView Mapping Binding


        ArrayAdapter arrayAdapter;
        arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1,
                landmarkArrayList.stream().map(landmark -> landmark.name).collect(Collectors.toList()));
        binding.listView.setAdapter(arrayAdapter);
        binding.listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //Toast.makeText(MainActivity.this, landmarkArrayList.get(i).name, Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MainActivity.this, DetailsActivity.class);
                intent.putExtra("Landmark", landmarkArrayList.get(i));
                startActivity(intent);
            }
        });



    }
}