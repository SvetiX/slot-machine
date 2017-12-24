package ru.simagin.game.service;

import com.badlogic.gdx.math.MathUtils;

import java.util.ArrayList;
import java.util.List;

public class GameServiceStub implements GameService {

    @Override
    public List<Integer> getSpinResults() {
        List<Integer> results = new ArrayList<Integer>();
        for (int i = 0; i < 5; i++) {
            results.add(MathUtils.random(1, 8));
        }
        return results;
    }
}
