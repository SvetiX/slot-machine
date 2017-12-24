package ru.simagin.game.ui.screens.component.animated.button;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class AnimatedButton extends Actor {

    private Animation animation;
    private final TextureRegion[]  region;
    private TextureRegion currenFrame;
    private float stateTime;
    private final float DEFAULT_DURATION;
    private boolean enabled = true;

    public AnimatedButton(TextureRegion[] region, float default_duration) {
        this.region = region;
        DEFAULT_DURATION = default_duration;
        animation = new Animation(DEFAULT_DURATION, (Object[]) this.region);
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        if (stateTime > 100000) {
            stateTime = 0;
        }
        stateTime += Gdx.graphics.getDeltaTime();
        currenFrame = (TextureRegion) animation.getKeyFrame(stateTime, true);
        if (enabled) {
            animation.setFrameDuration(DEFAULT_DURATION);
        } else {
            animation.setFrameDuration(0F);
        }
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        batch.draw(currenFrame, getX(), getY(), getWidth(), getHeight());
    }


    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
}
