package com.example.jbt.placeofzeze.frags;

import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;
import android.preference.SwitchPreference;
import android.support.annotation.Nullable;

import com.example.jbt.placeofzeze.R;

public class SettingsFrag extends PreferenceFragment {
    // implements Preference.OnPreferenceChangeListener
    private SwitchPreference switch1 , switch2;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        addPreferencesFromResource(R.xml.settingsxml);
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(getActivity());

            //2 switches in setting act - switches key and summary
        switch1 = (SwitchPreference) findPreference("Dtype");
        switch2 = (SwitchPreference) findPreference("Radius");

        if (switch1.isChecked()&&!switch2.isChecked()){

            switch2.setSummary("4Km's");

        }else if (switch1.isChecked()&&switch2.isChecked()){

            switch2.setSummary("2Km's");

        }else if (!switch1.isChecked()&&!switch2.isChecked()){

            switch2.setSummary("2.4 Mille's");

        }else if (!switch1.isChecked()&&switch2.isChecked()){

            switch2.setSummary("1.2 Mille's");
        }



    }


}
