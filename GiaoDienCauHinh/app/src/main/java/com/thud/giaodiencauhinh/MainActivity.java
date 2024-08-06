package com.thud.giaodiencauhinh;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.preference.Preference;
import androidx.preference.ListPreference;
import static androidx.preference.PreferenceManager.getDefaultSharedPreferences;
import androidx.preference.PreferenceFragmentCompat;
import androidx.fragment.app.FragmentTransaction;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
        FragmentTransaction transaction =
                getSupportFragmentManager().beginTransaction();
        transaction.replace(android.R.id.content,
                new MySettingsFragment());
        transaction.commit();
    }
    private static Preference.OnPreferenceChangeListener CapNhat =
            new Preference.OnPreferenceChangeListener() {
                @Override
                public boolean onPreferenceChange(
                        Preference preference, Object newValue) {
                    String strValue = newValue.toString();
                    if (preference instanceof ListPreference) {
                        ListPreference myList=(ListPreference) preference;
                        int i = myList.findIndexOfValue(strValue);
                        preference.setSummary(myList.getEntries()[i]);
                    } else {
                        preference.setSummary(strValue);
                    }
                    return true;
                }
            };
    private static void CapNhatGiaTri(Preference myPref) {
        myPref.setOnPreferenceChangeListener(CapNhat);
        SharedPreferences myFile =
                getDefaultSharedPreferences(myPref.getContext());
        String strNew = myFile.getString(myPref.getKey(), "");
        CapNhat.onPreferenceChange(myPref, strNew);
    }

    public static class MySettingsFragment extends
            PreferenceFragmentCompat {

        @Override
        public void onCreatePreferences(@Nullable Bundle
                                                savedInstanceState, @Nullable String rootKey) {
            addPreferencesFromResource(R.xml.settings_layout);
            CapNhatGiaTri(findPreference("hoten"));
            CapNhatGiaTri(findPreference("trinhduyet"));
        }
    }
}