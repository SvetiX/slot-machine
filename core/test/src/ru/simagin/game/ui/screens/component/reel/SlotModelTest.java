package ru.simagin.game.ui.screens.component.reel;

import org.junit.Test;

import static org.junit.Assert.*;

public class SlotModelTest {

    ReelModel model = new ReelModel(800);

    @Test
    public void correctPosition() throws Exception {
        assertEquals(0, model.correctPosition(900));
        assertEquals(1, model.correctPosition(901));
        assertEquals(-1, model.correctPosition(-901));
        assertEquals(0, model.correctPosition(-900));
        assertEquals(799, model.correctPosition(799));
        assertEquals(-799, model.correctPosition(-799));
        assertEquals(0, model.correctPosition(0));
    }

}