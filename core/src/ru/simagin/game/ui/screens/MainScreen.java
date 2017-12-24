package ru.simagin.game.ui.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import ru.simagin.game.service.GameServiceStub;
import ru.simagin.game.ui.screens.component.GameHUD;
import ru.simagin.game.ui.screens.component.reelbar.ReelBar;
import ru.simagin.game.ui.screens.component.animated.button.AnimatedButton;


public class MainScreen implements Screen, ReelBar.CallBack {

    private Stage stage;
    private GameHUD gameHUD;
    private Image backGround;
    private AnimatedButton button;
    private ReelBar reelBar;
    private Image gameFrame;

    public MainScreen() {
        this.stage = new Stage();

        backGround = new Image(new Texture(Gdx.files.internal("backgrounds/background3.png")));

        gameHUD = new GameHUD();
        gameHUD.setPosition(255, 50);

        Texture texture = new Texture(Gdx.files.internal("buttons/button3.png"));
        TextureRegion[] region = new TextureRegion[2];
        for (int i = 0; i < region.length; i++) {
            region[i] = new TextureRegion(texture, 0, i * texture.getHeight() / region.length, texture.getWidth(), texture.getHeight() / region.length);
        }

        button = new AnimatedButton(region, 0.50f);
        button.setPosition(25, 30);
        button.setHeight(128);
        button.setWidth(128);

        reelBar = new ReelBar(5, 1000);
        reelBar.setPosition(260, 89);
        reelBar.registerCallBack(this);
        reelBar.setGameService(new GameServiceStub());

        button.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (button.isEnabled()) {
                    button.setEnabled(false);
                    reelBar.start();
                }

            }
        });

        gameFrame = new Image(new Texture(Gdx.files.internal("backgrounds/game_frame2.png")));
        gameFrame.setPosition(240, 0);

    }


    @Override
    public void show() {
        Gdx.input.setInputProcessor(this.stage);
        this.stage.addActor(backGround);
        this.stage.addActor(button);
        this.stage.addActor(reelBar);
        this.stage.addActor(gameFrame);
        this.stage.addActor(gameHUD);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act(delta);
        stage.draw();

    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        stage.dispose();
    }

    @Override
    public void notifyStop() {
        button.setEnabled(true);
    }
}
