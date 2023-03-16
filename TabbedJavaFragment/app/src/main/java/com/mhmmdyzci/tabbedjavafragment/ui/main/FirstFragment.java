package com.mhmmdyzci.tabbedjavafragment.ui.main;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.mhmmdyzci.tabbedjavafragment.R;


public class FirstFragment extends Fragment {
    PageViewModel pageViewModel;
    // FirsFragment.newInstance metodu kullanıldığında yeni bir FırstFragment oluştur

    public static FirstFragment newInstance(){

        return new FirstFragment();
    }
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
      pageViewModel =  new ViewModelProvider(requireActivity()).get(PageViewModel.class);

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_first,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        EditText editText = view.findViewById(R.id.editText);

        // metin değişince bana call back veren fonk.
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            //metin değişmeden önce
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            // metin değiştiği anda
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                pageViewModel.setName(charSequence.toString());

            }

            @Override
            // metin değiştikten sonra
            public void afterTextChanged(Editable editable) {

            }
        });

    }
}