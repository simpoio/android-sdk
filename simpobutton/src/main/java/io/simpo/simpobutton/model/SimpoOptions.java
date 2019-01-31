package io.simpo.simpobutton.model;

public class SimpoOptions {
    private SimpoUser user;
    private String uuid;
    private boolean hideWidget;
    private SimpoWidgetPosition position;
    private int width;
    private int height;

    public SimpoOptions(SimpoUser user, String uuid, boolean hideWidget, SimpoWidgetPosition position, int widgetWidth, int widgetHeight) {
        this.uuid = uuid;
        this.user = user;
        this.hideWidget = hideWidget;
        this.position = position;
        this.width = widgetWidth;
        this.height = widgetHeight;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public SimpoUser getUser() {
        return user;
    }


    public boolean isHideWidget() {
        return hideWidget;
    }

    public SimpoWidgetPosition getPosition() {
        return position;
    }
}
