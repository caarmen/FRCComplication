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


import static ca.rmen.android.frc.complications.ComplicationUtils.getShortTextComplicationData;

import android.os.RemoteException;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.wear.watchface.complications.data.ComplicationData;
import androidx.wear.watchface.complications.data.ComplicationType;
import androidx.wear.watchface.complications.datasource.ComplicationDataSourceService;
import androidx.wear.watchface.complications.datasource.ComplicationRequest;

import java.util.Calendar;
import java.util.Locale;

import ca.rmen.lfrc.FrenchRevolutionaryCalendarDate;

public class TimeComplication extends ComplicationDataSourceService {
    @Nullable
    @Override
    public ComplicationData getPreviewData(@NonNull ComplicationType complicationType) {
        return getShortTextComplicationData(getText());
    }

    @Override
    public void onComplicationRequest(@NonNull ComplicationRequest complicationRequest, @NonNull ComplicationRequestListener complicationRequestListener) {
        try {
            complicationRequestListener.onComplicationData(getShortTextComplicationData(getText()));
        } catch (RemoteException e) {
            throw new RuntimeException("Should migrate to kotlin", e);
        }
    }

    private String getText() {
        FrenchRevolutionaryCalendarDate frcDate = ComplicationUtils.getNow(this);
        Calendar endTime = Calendar.getInstance();
        endTime.add(Calendar.MILLISECOND, 86400);
        return String.format(Locale.getDefault(), "%d:%02d", frcDate.hour, frcDate.minute);
    }
}
