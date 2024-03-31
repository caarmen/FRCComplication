/*
 * French Revolutionary Calendar Android Wear Complications
 * Copyright (C) 2017 - Present, Carmen Alvarez
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

import static ca.rmen.android.frc.complications.ComplicationUtils.getLongTextComplicationData;

import android.os.RemoteException;

import androidx.annotation.NonNull;
import androidx.wear.watchface.complications.data.ComplicationData;
import androidx.wear.watchface.complications.data.ComplicationType;
import androidx.wear.watchface.complications.datasource.ComplicationDataSourceService;
import androidx.wear.watchface.complications.datasource.ComplicationRequest;

public class ObjectOfTheDayComplication extends ComplicationDataSourceService {

    @Override
    public ComplicationData getPreviewData(@NonNull ComplicationType complicationType) {
        return getLongTextComplicationData(getText());
    }

    @Override
    public void onComplicationRequest(@NonNull ComplicationRequest complicationRequest, @NonNull ComplicationRequestListener complicationRequestListener) {
        try {
            complicationRequestListener.onComplicationData(getLongTextComplicationData(getText()));
        } catch (RemoteException e) {
            throw new RuntimeException("Should migrate to kotlin", e);
        }
    }

    private String getText() {
        return ComplicationUtils.getNow(this).getObjectOfTheDay();
    }
}
