package ru.simagin.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import ru.simagin.game.SlotMachineGame;

public class DesktopLauncher {
    public static void main(String[] arg) {
        LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        //config.title = "VaultSlot";
        config.width = 800;
        config.height = 480;
        config.resizable = false;
        new LwjglApplication(new SlotMachineGame(), config);
    }
}

