package com.mhmmdyzci.navigationjava;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


public class firstFragment extends Fragment {




    public firstFragment() {
        // Required empty public constructor
    }





    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_first, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        // Bu metod  fragment içersindeki görünümler oluşturduktan sonra çağıralan metodtur
        Button button = view.findViewById(R.id.button);
        // onclil eventine ayrı bir metod tanımlayacağımıza burda dinleyici tanımıyoruz
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToSecond(view);

            }
        });
    }

    public void goToSecond(View view){
       // NavDirections action =  firstFragmentDirections.actionFirstFragmentToSecondFragment();
        firstFragmentDirections.ActionFirstFragmentToSecondFragment action = firstFragmentDirections.actionFirstFragmentToSecondFragment();
        action.setAge(50);
        Navigation.findNavController(view).navigate(action);

    }
}