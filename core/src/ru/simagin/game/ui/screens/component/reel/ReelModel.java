package ru.simagin.game.ui.screens.component.reel;

import com.badlogic.gdx.math.MathUtils;
import lombok.extern.slf4j.Slf4j;

import java.util.Observable;

@Slf4j
class ReelModel extends Observable {

    private final int TEXTURE_HEIGHT;
    private int yPosition = 0;
    private Integer speed;
    private boolean spin = false;
    private boolean stop = false;
    private Reel.Callback callback;
    int finalPosition;


    public ReelModel(int texture_height) {
        TEXTURE_HEIGHT = texture_height;
        this.speed = updateSpeed();
    }

    /**
     * Задает скорость вращения слота
     *
     * @return int в диапазоне 700 - 1000
     */
    private int updateSpeed() {
        return MathUtils.random(500, 800);
    }

    /**
     * Начинает процесс остановки вращения
     *
     * @param itemNumber номер слота, на котором будет завершена остановка
     */
    public void stopSnip(int itemNumber) {
        setSpin(false);
        setStop(true);
        finalPosition = (itemNumber - 10) * 100;
    }

    /**
     * Запускает вращение
     */
    public void startSpin() {
        setSpin(true);
    }


    /**
     * Вызывается каждый "такт" libGdx цикла
     * Использует два поля(флага): spin и stop.
     * Когда активен флаг spin начинается пролистывание текстуры
     * Когда активен флаг stop spin выключается, начинается поиск финальной позиции тектуры, и замедление пролистывания.
     * Когда текстура встала в финальную позицию флаг stop выключается и выполняется callback подписчику.
     *
     * @param delta
     */
    public void spin(float delta) {

        if (spin) {
            int speed = Math.round(this.speed * delta);
            setYPosition(getYPosition() - speed);
        }

        if (stop) {
            int distance = finalPosition < getYPosition() ? finalPosition - getYPosition() : getYPosition() - finalPosition;
            if (distance > -20) {
                int speed = Math.round(50f * 0.01f);
                if (distance  + speed >= 0) {
                    finishSpin();
                    return;
                }
                setYPosition(yPosition - speed);
            } else {
                setYPosition(yPosition - Math.round(this.speed * delta));
            }
        }
    }

    private void finishSpin() {
        speed = updateSpeed();
        setStop(false);
        if (callback != null) {
            callback.notifyStop();
        }

    }


    /**
     * Коректирует значение координаты Y в зависимости от высоты текстуры
     *
     * @param y значение координаты
     * @return
     */
    public int correctPosition(int y) {
        if (Math.abs(y) >= TEXTURE_HEIGHT +100) {
            y = y + TEXTURE_HEIGHT +100;
        }
        return y;
    }

    /**
     * Устанавливает текущую позицию по оси Y. Затем оповещает подписчиков на этот эвент
     *
     * @param yPosition
     */
    private void setYPosition(int yPosition) {
        this.yPosition = correctPosition(yPosition);
        setChanged();
        notifyObservers();
    }

    /**
     * Устанавливает позицию текстуры что бы отобращался заданный слот
     *
     * @param currentNumber
     */
    public void setCurrentItem(int currentNumber) {
        setYPosition((currentNumber - 10) * 100);
    }


    public int getYPosition() {
        return yPosition;
    }

    public boolean isSpin() {
        return spin;
    }

    public void setSpin(boolean spin) {
        this.spin = spin;
    }


    public void setStop(boolean stop) {
        this.stop = stop;
    }


    public void registerCallBack(Reel.Callback callback) {
        this.callback = callback;
    }

}
