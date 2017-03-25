/*
 * French Revolutionary Calendar Android Wear Complications
 * Copyright (C) 2017 Carmen Alvarez
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 3
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, see <http://www.gnu.org/licenses/>.
 */
package ca.rmen.android.frc.complication;

import android.app.Activity;
import android.content.ComponentName;
import android.os.Bundle;
import android.preference.PreferenceFragment;
import android.support.annotation.Nullable;
import android.support.wearable.complications.ComplicationProviderService;

public class SettingsActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        setResult(RESULT_OK);
    }

    @Override
    protected void onDestroy() {
        ComplicationUtils.updateAllComplications(this);
        super.onDestroy();
    }

    public static class SettingsFragment extends PreferenceFragment {
        @Override
        public void onCreate(@Nullable Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            addPreferencesFromResource(R.xml.settings);
            ComponentName providerService = getActivity().getIntent().getParcelableExtra(ComplicationProviderService.EXTRA_CONFIG_PROVIDER_COMPONENT);
            if (providerService != null) {
                String providerClassName = providerService.getClassName();
                if (!DateComplication.class.getName().equals(providerClassName)) {
                    getPreferenceScreen().removePreference(findPreference(getString(R.string.setting_key_roman_numeral)));
                }
            }
        }
    }
}
