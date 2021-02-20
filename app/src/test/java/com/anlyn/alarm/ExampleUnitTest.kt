package com.anlyn.alarm

import com.anlyn.data.db.remote.PhraseDataSource
import org.junit.Test

import org.junit.Assert.*
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.temporal.TemporalAdjusters

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        var localDate = LocalDate.now();
        System.out.printf("today: %s%n", localDate);
        var date3 = localDate.with(DayOfWeek.MONDAY);
        System.out.printf("next Monday: %s%n", date3);
//        assertEquals(4, 2 + 2)
    }
}