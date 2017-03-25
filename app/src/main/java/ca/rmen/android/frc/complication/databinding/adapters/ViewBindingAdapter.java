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

package ca.rmen.android.frc.complication.databinding.adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.databinding.BindingAdapter;
import android.net.Uri;
import android.support.wearable.view.ConfirmationOverlay;
import android.view.View;

import com.google.android.wearable.intent.RemoteIntent;

import ca.rmen.android.frc.complication.R;

public class ViewBindingAdapter {
    @BindingAdapter("link")
    public static void setLink(final View view, final String link) {
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RemoteIntent.startRemoteActivity(view.getContext(),
                        new Intent(Intent.ACTION_VIEW)
                                .setData(Uri.parse(link))
                                .addCategory(Intent.CATEGORY_BROWSABLE),
                        null /*resultReceiver*/);
                Context context = view.getContext();
                if (context instanceof Activity) {
                    // 'Open on phone' confirmation overlay
                    new ConfirmationOverlay()
                            .setType(ConfirmationOverlay.OPEN_ON_PHONE_ANIMATION)
                            .setMessage(view.getContext().getString(R.string.about_link_opened_on_phone))
                            .showOn((Activity) context);
                }
            }
        });
    }
}
