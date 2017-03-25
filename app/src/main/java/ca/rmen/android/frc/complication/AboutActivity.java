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
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.StringRes;
import android.support.wearable.view.ConfirmationOverlay;

import com.google.android.wearable.intent.RemoteIntent;

public class AboutActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        findViewById(R.id.about_license).setOnClickListener(v -> openLink(R.string.about_link_license));
        findViewById(R.id.about_source).setOnClickListener(v -> openLink(R.string.about_link_source));
        findViewById(R.id.about_wiki).setOnClickListener(v -> openLink(R.string.about_link_wiki));
        findViewById(R.id.about_credits_equinox_tool).setOnClickListener(v -> openLink(R.string.about_credits_link_equinox_tool));
        findViewById(R.id.about_credits_roman_numeral_algorithm).setOnClickListener(v -> openLink(R.string.about_credits_link_roman_numeral_algorithm));
    }

    private void openLink(@StringRes int linkResId) {
        RemoteIntent.startRemoteActivity(this,
                new Intent(Intent.ACTION_VIEW)
                        .setData(Uri.parse(getString(linkResId)))
                        .addCategory(Intent.CATEGORY_BROWSABLE),
                null /*resultReceiver*/);
        // 'Open on phone' confirmation overlay
        new ConfirmationOverlay()
                .setType(ConfirmationOverlay.OPEN_ON_PHONE_ANIMATION)
                .setMessage(getString(R.string.about_link_opened_on_phone))
                .showOn(this);
    }
}
