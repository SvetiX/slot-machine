package ru.simagin.game.ui.screens.component.reel;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class Reel extends Actor {

    public interface Callback {
        void notifyStop();
    }

    private ReelView view;
    private ReelController controller;
    private ReelModel model;

    private final int ITEMS_ON_SCREEN;
    private final int ITEM_HEIGHT;
    private final int ITEM_WIDTH;

    public Reel(Texture texture, int itemsOnScreen, int itemHeight, int itemWidth) {
        ITEMS_ON_SCREEN = itemsOnScreen;
        ITEM_HEIGHT = itemHeight;
        ITEM_WIDTH = itemWidth;
        texture.setWrap(Texture.TextureWrap.Repeat, Texture.TextureWrap.Repeat);
        view = new ReelView(new TextureRegion(texture, ITEM_WIDTH, ITEM_HEIGHT * ITEMS_ON_SCREEN));
        model = new ReelModel(texture.getHeight());
        model.addObserver(view);
        controller = new ReelController(model);
        view.setController(controller);
    }

    /**
     * Запустить вращение барабана
     */
    public void startSpin() {
        controller.startSpin();
    }

    /**
     * Запустить процесс остановки барабана
     *
     * @param itemNumber слот, на котором завершится остановка
     */
    public void stopSpin(int itemNumber) {
        controller.stopSpin(itemNumber);
    }

    @Override
    public void act(float delta) {
        controller.event(delta);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        view.draw(batch, parentAlpha);
    }

    public void registerCallBack(Reel.Callback callback) {
        model.registerCallBack(callback);
    }

    @Override
    public void setX(float x) {
        super.setX(x);
        view.setX(x);
    }

    @Override
    public void setY(float y) {
        super.setY(y);
        view.setY(y);
    }

    @Override
    public void setPosition(float x, float y) {
        super.setPosition(x, y);
        view.setY(y);
        view.setX(x);
    }

    /**
     * Установить стартовую позицию барабана
     *
     * @param number
     */
    public void setCurrentItem(int number) {
        controller.setCurrentItem(number);
    }
}
