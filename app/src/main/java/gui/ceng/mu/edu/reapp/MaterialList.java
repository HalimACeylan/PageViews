package gui.ceng.mu.edu.reapp;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.io.Serializable;
import java.util.ArrayList;

public class MaterialList extends Fragment {
    private static final String ARG_PARAM1 = "listOfMaterial";
    RecyclerView dataList;
    RecyclerView.Adapter adapter;

    public MaterialList() {
        // Required empty public constructor
    }

    public static MaterialList newInstance(RecyclerView.Adapter typeOfMaterial) {
        MaterialList fragment = new MaterialList();
        Bundle args = new Bundle();
        args.putSerializable(ARG_PARAM1, (Serializable) typeOfMaterial);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        this.adapter = (RecyclerView.Adapter) getArguments().getSerializable(ARG_PARAM1);
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_material_list, container, false);
        if(view instanceof RecyclerView){
            Context context = view.getContext();
            dataList = (RecyclerView) view;
            GridLayoutManager gridLayoutManager = new GridLayoutManager(context,2, LinearLayoutManager.VERTICAL,false);
            dataList.setLayoutManager(gridLayoutManager);
            dataList.setAdapter(adapter);
        }
        return view;
    }
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }


}