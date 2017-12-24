package ru.simagin.game.ui.screens.component;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.Label;




public class GameHUD extends Group {

    private Label label;


    public GameHUD() {
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/fallout_type.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = 12;
        BitmapFont font12 = generator.generateFont(parameter);
        this.label = new Label("", new Label.LabelStyle(font12, Color.GREEN));
        addActor(label);

    }

    @Override
    public void act(float delta) {
        super.act(delta);
        label.setText("FPS: " + Gdx.graphics.getFramesPerSecond());
    }
}
