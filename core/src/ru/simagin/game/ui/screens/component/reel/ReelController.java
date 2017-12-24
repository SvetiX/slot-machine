package ru.simagin.game.ui.screens.component.reel;

class ReelController {

    private final ReelModel model;

    public ReelController(ReelModel model) {
        this.model = model;
    }

    /**
     * Запустить вращение барабана
     */
    public void startSpin() {
        model.startSpin();
    }


    public void event(float delta) {
        model.spin(delta);
    }

    /**
     * Запустить процесс остановки барабана
     * @param itemNumber слот, на котором завершится остановка
     */
    public void stopSpin(int itemNumber) {
        model.stopSnip(itemNumber);
    }

    /**
     * Установить стартовую позицию барабана
     * @param currentItem
     */
    public void setCurrentItem(int currentItem) {
        model.setCurrentItem(currentItem);
    }
}
