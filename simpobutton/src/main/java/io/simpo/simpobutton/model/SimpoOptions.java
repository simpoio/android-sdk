package io.simpo.simpobutton.model;

public class SimpoOptions {
    private SimpoUser user;
    private String uuid;
    private boolean show;
    private SimpoWidgetPosition position;
    private int width;
    private int height;

    public SimpoOptions(SimpoUser user, String uuid, boolean show, SimpoWidgetPosition position, int widgetWidth, int widgetHeight) {
        this.uuid = uuid;
        this.user = user;
        this.show = show;
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


    public boolean isShow() {
        return show;
    }

    public SimpoWidgetPosition getPosition() {
        return position;
    }
}
