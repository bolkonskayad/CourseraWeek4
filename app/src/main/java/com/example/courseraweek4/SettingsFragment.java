package com.example.courseraweek4;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class SettingsFragment extends Fragment {

    private RadioButton googleRB;
    private RadioButton yandexRB;
    private RadioButton bingRB;
    private RadioGroup settingsRG;

    private SharedPreferencesHelper mSharedPreferencesHelper;
    String url;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_settings, container, false);
        return view;
    }

    public void onViewCreated(View view, Bundle savedInstanceState) {

        mSharedPreferencesHelper = new SharedPreferencesHelper(getActivity());
        String selectedBrowser = mSharedPreferencesHelper.getSearching();

        settingsRG = view.findViewById(R.id.settings_rg);
        settingsRG.setOnCheckedChangeListener(listener);

        googleRB = view.findViewById(R.id.google_rb);
        yandexRB = view.findViewById(R.id.yandex_rb);
        bingRB = view.findViewById(R.id.bing_rb);

        Browsers browsers = Browsers.valueOf(selectedBrowser);

        switch (browsers) {
            case GOOGLE:
                googleRB.setChecked(true);
                break;
            case YANDEX:
                yandexRB.setChecked(true);
                break;
            case BING:
                bingRB.setChecked(true);
                break;
        }
    }

    private RadioGroup.OnCheckedChangeListener listener = new RadioGroup.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(RadioGroup radioGroup, int checkId) {

            switch (checkId) {
                case R.id.google_rb:
                    url = Browsers.GOOGLE.toString();
                    break;
                case R.id.yandex_rb:
                    url = Browsers.YANDEX.toString();
                    break;
                case R.id.bing_rb:
                    url = Browsers.BING.toString();
                    break;
            }
            mSharedPreferencesHelper.addSearching(url);
            showBrowser(url);
        }
    };

    private void showBrowser(String browser) {
        Toast.makeText(getActivity(), "Поиск по умолчанию: " + browser, Toast.LENGTH_SHORT).show();
    }
}