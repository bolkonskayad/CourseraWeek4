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
import android.widget.Switch;

public class SearchFragment extends Fragment {

    private EditText searchEt;
    private Button searchBtn;
    private SharedPreferencesHelper mSharedPreferencesHelper;
    private String url;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search, container, false);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mSharedPreferencesHelper = new SharedPreferencesHelper(getActivity());

        searchBtn = view.findViewById(R.id.search_btn);
        searchEt = view.findViewById(R.id.browser_et);

        searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.addCategory(Intent.CATEGORY_BROWSABLE);
                String searchSystem = mSharedPreferencesHelper.getSearching();
                Browsers browsers = Browsers.valueOf(searchSystem);

                if (searchEt != null) {
                    switch (browsers) {
                        case GOOGLE:
                            url = "https://www.google.ru/search?q=";
                            break;
                        case YANDEX:
                            url = "https://yandex.kz/search/?text=";
                            break;
                        case BING:
                            url = "https://www.bing.com/search?q=";
                            break;
                    }
                    intent.setData(Uri.parse(url + searchEt.getText().toString()));
                    startActivity(intent);
                }
            }
        });
    }
}