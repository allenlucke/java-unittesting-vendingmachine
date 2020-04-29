package com.teamtreehouse.vending;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.*;

public class AlphaNumericChooserTest {

    private AlphaNumericChooser chooser;

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Before
    public void setUp() throws Exception {
        chooser = new AlphaNumericChooser(26, 10);
    }

    @Test
    public void validInputReturnsProperLocation() throws Exception {
        AbstractChooser.Location loc = chooser.locationFromInput("B4");

        assertEquals("proper column", 3, loc.getColumn());
        assertEquals("proper row", 1, loc.getRow());
    }

    @Test(expected = InvalidLocationException.class)
    public void choosingWrongInputIsNotAllowed() throws Exception{
        chooser.locationFromInput("WRONG");
    }

    @Test(expected = InvalidLocationException.class)
    public void choosingLargerThanMaxNotAllowed() throws Exception{
        chooser.locationFromInput("B52");
    }

    @Test
    public void constructingLargerThanAlphabetNotAllowed() throws Exception {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("Maximum rows supported is 26");
        new AlphaNumericChooser(27, 10);
    }
}