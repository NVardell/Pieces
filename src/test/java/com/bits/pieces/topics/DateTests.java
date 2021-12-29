package com.bits.pieces.topics;

import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;

import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;

/**
 * Tests Cases for everything Date Related
 * @author Nate Vardell
 * @since 7/3/2019
 */
@Slf4j
public class DateTests {

    private static ZonedDateTime from, to;

    @Before
    public void init(){
        from = ZonedDateTime.now(ZoneOffset.UTC);
        to = from.plusHours(3);
    }


    @Test
    public void testZonedDateTimes() {
        List<String> theWinners = getZonedMap().entrySet().stream()
                .filter(entry -> !entry.getKey().isEqual(to)
                        || entry.getKey().isEqual(from) && !entry.getKey().isEqual(to)
                        || entry.getKey().isAfter(from) && entry.getKey().isBefore(to))
                .map(Map.Entry::getValue)
                .collect(Collectors.toList());
        assertThat(theWinners.size(), is(3));
        assertThat(theWinners, containsInAnyOrder("One", "Two", "Three"));
    }

    private static Map<ZonedDateTime, String> getZonedMap(){
        Map<ZonedDateTime, String> historicalData = new HashMap<>();
        historicalData.put(from, "One");
        historicalData.put(from.plusHours(1), "Two");
        historicalData.put(from.plusHours(2), "Three");
        historicalData.put(to, "Four");
        return historicalData;
    }
}
