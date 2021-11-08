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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_settings, container, false);
        mSharedPreferencesHelper = new SharedPreferencesHelper(getActivity());
        String selectedBrowser = mSharedPreferencesHelper.getSearching();

        settingsRG = v.findViewById(R.id.settings_rg);
        settingsRG.setOnCheckedChangeListener(listener);

        googleRB = v.findViewById(R.id.google_rb);
        yandexRB = v.findViewById(R.id.yandex_rb);
        bingRB = v.findViewById(R.id.bing_rb);

        if (selectedBrowser != null) {
            if (selectedBrowser.equals(Browsers.GOOGLE)) {
                googleRB.setChecked(true);
            }
            if (selectedBrowser.equals(Browsers.YANDEX)) {
                yandexRB.setChecked(true);
            }
            if (selectedBrowser.equals(Browsers.BING)) {
                bingRB.setChecked(true);
            }
        }
        return v;
    }

    private RadioGroup.OnCheckedChangeListener listener = new RadioGroup.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(RadioGroup radioGroup, int checkId) {
            switch (checkId) {
                case R.id.google_rb:
                    mSharedPreferencesHelper.addSearching(Browsers.GOOGLE);
                    showBrowser(Browsers.GOOGLE);
                    break;
                case R.id.yandex_rb:
                    mSharedPreferencesHelper.addSearching(Browsers.YANDEX);
                    showBrowser(Browsers.YANDEX);
                    break;
                case R.id.bing_rb:
                    mSharedPreferencesHelper.addSearching(Browsers.BING);
                    showBrowser(Browsers.BING);
                    break;
            }
        }
    };

    private void showBrowser(String browser) {
        Toast.makeText(getActivity(), "Поиск по умолчанию: " + browser, Toast.LENGTH_SHORT).show();
    }
}