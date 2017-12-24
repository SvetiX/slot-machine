package ru.simagin.game;

import com.badlogic.gdx.Game;
import ru.simagin.game.ui.screens.MainScreen;

public class SlotMachineGame extends Game {


    @Override
    public void create() {
        setScreen(new MainScreen());
    }
}
