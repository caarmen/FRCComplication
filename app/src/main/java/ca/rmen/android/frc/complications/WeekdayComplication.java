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

package ca.rmen.android.frc.complications;

import android.support.wearable.complications.ComplicationData;
import android.support.wearable.complications.ComplicationManager;
import android.support.wearable.complications.ComplicationProviderService;
import android.support.wearable.complications.ComplicationText;

public class WeekdayComplication extends ComplicationProviderService {
    @Override
    public void onComplicationUpdate(int complicationId, int type, ComplicationManager complicationManager) {
        ComplicationData data = new ComplicationData.Builder(type)
                .setShortText(ComplicationText.plainText(ComplicationUtils.getNow(this).getWeekdayName()))
                .build();
        complicationManager.updateComplicationData(complicationId, data);
    }
}
