package ru.simagin.game.ui.screens.component.reel;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import java.util.Observable;
import java.util.Observer;

/**
 * Этот класс отображает участок текстуры по заданным коортинатам
 *
 */
class ReelView extends Sprite implements Observer {

    private ReelController controller;


    public ReelView(TextureRegion region) {
        super(region);
    }


    private void moveToY(int y) {
        setRegion(getRegionX(), y, getRegionWidth(), getRegionHeight());
    }

    @Override
    public void update(Observable o, Object arg) {
        ReelModel model = (ReelModel) o;
        moveToY(model.getYPosition());
    }

    public ReelController getController() {
        return controller;
    }

    public void setController(ReelController controller) {
        this.controller = controller;
    }
}
