package com.example.courseraweek4;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

public class SearchFragment extends Fragment {

    private EditText searchEt;
    private Button searchBtn;
    private SharedPreferencesHelper mSharedPreferencesHelper;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_search, container, false);

        mSharedPreferencesHelper = new SharedPreferencesHelper(getActivity());
        searchBtn = v.findViewById(R.id.search_btn);
        searchEt = v.findViewById(R.id.browser_et);

        searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url = null;
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.addCategory(Intent.CATEGORY_BROWSABLE);
                if (searchEt != null) {
                    String searchSystem = mSharedPreferencesHelper.getSearching();
                    if (searchSystem.equals(Browsers.GOOGLE)) {
                        url = "https://www.google.ru/search?q=";
                    } else if (searchSystem.equals(Browsers.BING)) {
                        url = "https://www.bing.com/search?q=";
                    } else if (searchSystem.equals(Browsers.YANDEX)) {
                        url = "https://yandex.kz/search/?text=";
                    }
                    if (!url.equals("")) {
                        intent.setData(Uri.parse(url + searchEt.getText().toString()));
                        startActivity(intent);
                    }
                }
            }
        });
        return v;
    }

}