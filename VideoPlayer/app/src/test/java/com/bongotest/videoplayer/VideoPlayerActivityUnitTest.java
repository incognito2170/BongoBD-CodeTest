package com.bongotest.videoplayer;

import com.bongotest.videoplayer.activities.VideoPlayerActivity;

import org.junit.Test;

import static org.junit.Assert.*;

public class VideoPlayerActivityUnitTest {

    @Test
    public void testMillisecondsToTimer_testSingleDigitSecondsWithoutRemainder_returnsTrue() {
        assertEquals("00:09", VideoPlayerActivity.milliSecondsToTimer(9000));
    }

    @Test
    public void testMillisecondsToTimer_testSingleDigitSecondsWithoutRemainder_returnsFalse() {
        assertNotEquals("00:9", VideoPlayerActivity.milliSecondsToTimer(9000));
    }

    @Test
    public void testMillisecondsToTimer_testSingleDigitSecondsWithRemainder_returnsTrue() {
        assertEquals("00:10", VideoPlayerActivity.milliSecondsToTimer(9500));
    }

    @Test
    public void testMillisecondsToTimer_testSingleDigitSecondsWithRemainder_returnsFalse() {
        assertNotEquals("00:09", VideoPlayerActivity.milliSecondsToTimer(9500));
    }

    @Test
    public void testMillisecondsToTimer_testDoubleDigitSecondsWithoutRemainder_returnsTrue() {
        assertEquals("00:15", VideoPlayerActivity.milliSecondsToTimer(15000));
    }

    @Test
    public void testMillisecondsToTimer_testDoubleDigitSecondsWithoutRemainder_returnsFalse() {
        assertNotEquals("00:16", VideoPlayerActivity.milliSecondsToTimer(15000));
    }

    @Test
    public void testMillisecondsToTimer_testDoubleDigitSecondsWithRemainder_returnsTrue() {
        assertEquals("00:16", VideoPlayerActivity.milliSecondsToTimer(15500));
    }

    @Test
    public void testMillisecondsToTimer_testDoubleDigitSecondsWithRemainder_returnsFalse() {
        assertNotEquals("00:15", VideoPlayerActivity.milliSecondsToTimer(15500));
    }

    @Test
    public void testMillisecondsToTimer_testSingleDigitMinutesWithoutRemainder_returnsTrue() {
        assertEquals("01:55", VideoPlayerActivity.milliSecondsToTimer(115000));
    }

    @Test
    public void testMillisecondsToTimer_testSingleDigitMinutesWithoutRemainder_returnsFalse() {
        assertNotEquals("01:16", VideoPlayerActivity.milliSecondsToTimer(115000));
    }

    @Test
    public void testMillisecondsToTimer_testSingleDigitMinutesWithRemainder_returnsTrue() {
        assertEquals("01:56", VideoPlayerActivity.milliSecondsToTimer(115500));
    }

    @Test
    public void testMillisecondsToTimer_testSingleDigitMinutesWithRemainder_returnsFalse() {
        assertNotEquals("01:15", VideoPlayerActivity.milliSecondsToTimer(115500));
    }

    @Test
    public void testMillisecondsToTimer_testDoubleDigitMinutesWithoutRemainder_returnsTrue() {
        assertEquals("16:55", VideoPlayerActivity.milliSecondsToTimer(1015000));
    }

    @Test
    public void testMillisecondsToTimer_testDoubleDigitMinutesWithoutRemainder_returnsFalse() {
        assertNotEquals("01:55", VideoPlayerActivity.milliSecondsToTimer(1015000));
    }

    @Test
    public void testMillisecondsToTimer_testDoubleDigitMinutesWithRemainder_returnsTrue() {
        assertEquals("16:56", VideoPlayerActivity.milliSecondsToTimer(1015500));
    }

    @Test
    public void testMillisecondsToTimer_testDoubleDigitMinutesWithRemainder_returnsFalse() {
        assertNotEquals("16:55", VideoPlayerActivity.milliSecondsToTimer(1015500));
    }

    @Test
    public void testMillisecondsToTimer_testSingleDigitHoursWithoutRemainder_returnsTrue() {
        assertEquals("2:31:55", VideoPlayerActivity.milliSecondsToTimer(9115000));
    }

    @Test
    public void testMillisecondsToTimer_testSingleDigitHoursWithoutRemainder_returnsFalse() {
        assertNotEquals("02:31:55", VideoPlayerActivity.milliSecondsToTimer(9115000));
    }

    @Test
    public void testMillisecondsToTimer_testSingleDigitHoursWithRemainder_returnsTrue() {
        assertEquals("2:31:56", VideoPlayerActivity.milliSecondsToTimer(9115500));
    }

    @Test
    public void testMillisecondsToTimer_testSingleDigitHoursWithRemainder_returnsFalse() {
        assertNotEquals("2:31:55", VideoPlayerActivity.milliSecondsToTimer(9115500));
    }

    @Test
    public void testMillisecondsToTimer_testDoubleDigitHoursWithoutRemainder_returnsTrue() {
        assertEquals("10:51:55", VideoPlayerActivity.milliSecondsToTimer(39115000));
    }

    @Test
    public void testMillisecondsToTimer_testDoubleDigitHoursWithoutRemainder_returnsFalse() {
        assertNotEquals("01:51:55", VideoPlayerActivity.milliSecondsToTimer(39115000));
    }

    @Test
    public void testMillisecondsToTimer_testDoubleDigitHoursWithRemainder_returnsTrue() {
        assertEquals("10:51:56", VideoPlayerActivity.milliSecondsToTimer(39115500));
    }

    @Test
    public void testMillisecondsToTimer_testDoubleDigitHoursWithRemainder_returnsFalse() {
        assertNotEquals("10:51:55", VideoPlayerActivity.milliSecondsToTimer(39115500));
    }
}