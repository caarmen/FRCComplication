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

import android.preference.PreferenceManager;
import android.support.wearable.complications.ComplicationData;
import android.support.wearable.complications.ComplicationManager;
import android.support.wearable.complications.ComplicationProviderService;
import android.support.wearable.complications.ComplicationText;

import ca.rmen.lfrc.FrenchRevolutionaryCalendarDate;

public class DateComplication extends ComplicationProviderService {
    @Override
    public void onComplicationUpdate(int complicationId, int type, ComplicationManager complicationManager) {
        FrenchRevolutionaryCalendarDate frcDate = ComplicationUtils.getNow(this);
        boolean useRomanNumeral = PreferenceManager.getDefaultSharedPreferences(this).getBoolean(getString(R.string.setting_key_roman_numeral), false);
        String year = useRomanNumeral ? getRomanNumeral(frcDate.year) : String.valueOf(frcDate.year);
        ComplicationData data = new ComplicationData.Builder(type)
                .setLongText(ComplicationText.plainText(frcDate.dayOfMonth + " " + frcDate.getMonthName() + " " + year))
                .build();
        complicationManager.updateComplicationData(complicationId, data);
    }

    // http://stackoverflow.com/questions/12967896/converting-integers-to-roman-numerals-java
    private static final int ROMAN_NUMERAL_MIN_VALUE = 1;
    private static final int ROMAN_NUMERAL_MAX_VALUE = 4999;
    private static final String[] RN_1000 = {"", "M", "MM", "MMM", "MMMM"};
    private static final String[] RN_100 = {"", "C", "CC", "CCC", "CD", "D", "DC", "DCC", "DCCC", "CM"};
    private static final String[] RN_10 = {"", "X", "XX", "XXX", "XL", "L", "LX", "LXX", "LXXX", "XC"};
    private static final String[] RN_1 = {"", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX"};

    /**
     * @param number must be between 1 and 4999 inclusive, to return a roman numeral.
     * @return the roman numeral for the given number, if it is within the bounds.  Otherwise the arabic numeral is returned.
     */
    private static String getRomanNumeral(int number) {
        if (number < ROMAN_NUMERAL_MIN_VALUE || number > ROMAN_NUMERAL_MAX_VALUE) {
            return String.valueOf(number);
        }

        return RN_1000[number / 1000] +
                RN_100[number % 1000 / 100] +
                RN_10[number % 100 / 10] +
                RN_1[number % 10];
    }
}
