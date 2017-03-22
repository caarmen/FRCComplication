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

import java.util.GregorianCalendar;
import java.util.Locale;

import ca.rmen.lfrc.FrenchRevolutionaryCalendar;
import ca.rmen.lfrc.FrenchRevolutionaryCalendarDate;

final class ComplicationUtils {
    private ComplicationUtils() {
        // prevent instantiation
    }

    static FrenchRevolutionaryCalendarDate getNow() {
        FrenchRevolutionaryCalendar frc = new FrenchRevolutionaryCalendar(Locale.getDefault(), FrenchRevolutionaryCalendar.CalculationMethod.ROMME);
        return frc.getDate((GregorianCalendar) GregorianCalendar.getInstance());
    }
}
