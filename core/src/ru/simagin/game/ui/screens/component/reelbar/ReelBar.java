package ru.simagin.game.ui.screens.component.reelbar;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.Group;
import ru.simagin.game.service.GameService;
import ru.simagin.game.ui.screens.component.reel.Reel;

import java.util.List;

public class ReelBar extends Group implements Reel.Callback {

    private final int NUMBER_REELS;
    private final int SPIN_REEL_TIME;

    private volatile int countFinishedSlot;
    private GameService gameService;
    private List<Integer> spinResults;

    private Sound sound;

    public interface CallBack {
        void notifyStop();
    }

    private ReelBar.CallBack callBack;

    Reel[] reels;

    public ReelBar(int numberReels, int spinReelTime) {
        this.NUMBER_REELS = numberReels;
        this.SPIN_REEL_TIME = spinReelTime;
        this.reels = new Reel[NUMBER_REELS];

        sound = Gdx.audio.newSound(Gdx.files.internal("sounds/drop.mp3"));


        Texture texture = new Texture(Gdx.files.internal("textures/vault5.png"));
        for (int i = 0; i < reels.length; i++) {
            Reel reel = new Reel(texture, 3, 100, 100);
            reel.registerCallBack(this);
            int random = MathUtils.random(1, 8);
            reel.setCurrentItem(random);
            addActor(reel);
            reel.setPosition(i * texture.getWidth() + (i * 2), 0);
            this.reels[i] = reel;
        }
    }

    private synchronized void updateCount() {
        countFinishedSlot++;
//        sound.play();
        if (countFinishedSlot == NUMBER_REELS) {
            if (callBack != null) {
                callBack.notifyStop();
            }
        }
    }


    public void start() {
        countFinishedSlot = 0;
        spinResults = gameService.getSpinResults();
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (Reel reel : reels) {
                    reel.startSpin();
                }
                try {
                    Thread.sleep(SPIN_REEL_TIME);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                reels[0].stopSpin(spinResults.get(0));

            }
        }).start();

    }

    @Override
    public void notifyStop() {
        updateCount();
        if (countFinishedSlot < reels.length) {
            reels[countFinishedSlot].stopSpin(spinResults.get(countFinishedSlot));
        }
    }

    public void registerCallBack(ReelBar.CallBack callBack) {
        this.callBack = callBack;
    }

    public GameService getGameService() {
        return gameService;
    }

    public void setGameService(GameService gameService) {
        this.gameService = gameService;
    }
}
