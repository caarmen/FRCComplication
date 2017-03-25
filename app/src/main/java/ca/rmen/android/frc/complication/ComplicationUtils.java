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

import android.content.ComponentName;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.wearable.complications.ComplicationProviderService;
import android.support.wearable.complications.ProviderUpdateRequester;

import java.util.GregorianCalendar;
import java.util.Locale;

import ca.rmen.lfrc.FrenchRevolutionaryCalendar;
import ca.rmen.lfrc.FrenchRevolutionaryCalendarDate;

final class ComplicationUtils {
    private ComplicationUtils() {
        // prevent instantiation
    }

    static void updateAllComplications(Context context) {
        updateComplications(context, DateComplication.class);
        updateComplications(context, ObjectOfTheDayComplication.class);
        updateComplications(context, TimeComplication.class);
        updateComplications(context, WeekdayComplication.class);
    }

    private static void updateComplications(Context context, Class<? extends ComplicationProviderService> providerClass) {
        ComponentName componentName = new ComponentName(context, providerClass);
        ProviderUpdateRequester requester = new ProviderUpdateRequester(context, componentName);
        requester.requestUpdateAll();
    }

    static FrenchRevolutionaryCalendarDate getNow(Context context) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        String language = prefs.getString(context.getString(R.string.setting_key_language), context.getString(R.string.setting_default_language));
        Locale locale = new Locale(language);
        String methodIndex = prefs.getString(context.getString(R.string.setting_key_method), "1");
        FrenchRevolutionaryCalendar.CalculationMethod method = FrenchRevolutionaryCalendar.CalculationMethod.values()[Integer.valueOf(methodIndex)];
        FrenchRevolutionaryCalendar frc = new FrenchRevolutionaryCalendar(locale, method);
        return frc.getDate((GregorianCalendar) GregorianCalendar.getInstance());
    }

}
