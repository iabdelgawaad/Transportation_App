package com.FBLoginSample.activity;

/**
 * Created by rokia on 5/16/2015.
 */
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.FBLoginSample.R;

/**
 * Created by hp1 on 21-01-2015.
 */
public class Tab1 extends Fragment {

    MyAdapter mAdapter;
    RecyclerView recyclerView;
    ItemData itemsData[] = new ItemData[]{};

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.tab_1, container, false);

        return v;
    }

    public Tab1()
    {

    }



    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        // recyclerView.addItemDecoration(new HorizontalDividerItemDecoration.Builder(this));
        // this is data fro recycler view
//        Bundle extras = getActivity().getIntent().getExtras();
//        String stationarray = extras.getString("stations");
//
//        Log.d("statiiions:",stationarray);




        // 2. set layoutManger
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        // 3. create an adapter
        mAdapter = new MyAdapter(itemsData);
        // 4. set adapter
        recyclerView.setAdapter(mAdapter);
        // 5. set item animator to DefaultAnimator
        recyclerView.setItemAnimator(new DefaultItemAnimator());

//        recyclerView.addOnItemTouchListener(
//
////                new RecyclerItemClickListener(this, new RecyclerItemClickListener.OnItemClickListener() {
////                    @Override public void onItemClick(View view, int position) {
////                        Intent i = new Intent(getActivity().getApplication().getBaseContext(),MyActivity.class);
////                        startActivity(i);
////                    }
////                })
////        );
//
//    }
    }

    public void setDataFromHomeFragment(String[] station)
    {
        itemsData = new ItemData[station.length];
        for (int i = 0 ; i < station.length ; i++ )
        {
            itemsData[i] =  new ItemData(station[i]);
        }
        mAdapter = new MyAdapter(itemsData);

        recyclerView.setAdapter(mAdapter);

        mAdapter.notifyDataSetChanged();

    }

}
