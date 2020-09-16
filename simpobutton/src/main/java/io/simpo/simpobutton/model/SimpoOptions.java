package io.simpo.simpobutton.model;

public class SimpoOptions {
    private SimpoUser user;
    private String uuid;
    private boolean showWidget;
    private UtilsGeneral.SimpoWidgetPosition position;
    private int width;
    private int height;

    public SimpoOptions(SimpoUser user, String uuid, boolean showWidget, UtilsGeneral.SimpoWidgetPosition position, int widgetWidth, int widgetHeight) {
        this.uuid = uuid;
        this.user = user;
        this.showWidget = showWidget;
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


    public boolean isShowWidget() {
        return showWidget;
    }

    public UtilsGeneral.SimpoWidgetPosition getPosition() {
        return position;
    }
}
