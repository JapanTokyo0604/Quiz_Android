package com.elephant.examplequiza;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import androidx.fragment.app.Fragment;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class SelectionFragment extends Fragment {
    View rootView;
    ListView listView;
   // private static final String[] array = {"AAA","BBB"};

    private SplashSheetAPI task;

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.selection, container, false);


        setListview(getQuizSheet());

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                String urlstr = "";
                if(position==0){
                    urlstr = "https://script.google.com/macros/s/AKfycbxJXALal0EAJ-pIe08eUlBbQSgL5S0IAXrPyzSN5VmH_iIT2HY/exec";
                }else if(position==1){
                    urlstr = "https://script.google.com/macros/s/AKfycbwTKs997BtGccvgZwiqkjCxioRP4SenR-UgQ-CT1Q/exec";
                }

                task = new SplashSheetAPI(urlstr);
                task.setListener(createListener());
                task.execute();


            }
        });


        return rootView;
    }

    private String[] getQuizSheet(){
        String[] Array = {"なぞなぞ","資格試験対策"};

        return Array;
    }

    private void setListview(String[] array){
        listView = rootView.findViewById(R.id.selectionList);

        ArrayAdapter<String> arrayAdapter =
                new ArrayAdapter(rootView.getContext(),android.R.layout.simple_list_item_1,array);

        listView.setAdapter(arrayAdapter);
    }

    private SplashSheetAPI.Listener createListener() {
        return new SplashSheetAPI.Listener() {
            @Override
            public void onSuccess(String result) {
                Log.d("RESULT",result);

                ((MainActivity)getContext()).startQuiz(result);




            }
        };
    }



}