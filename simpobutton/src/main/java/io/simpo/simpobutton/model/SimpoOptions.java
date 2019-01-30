package io.simpo.simpobutton.model;

public class SimpoOptions {
    private SimpoUser user;
    private String uuid;
    private boolean show;
    private String position;
    private int width;
    private int height;

    public SimpoOptions(SimpoUser user, String uuid,  boolean show, String position, int width, int height) {
        this.user = user;
        this.show = show;
        this.position = position;
        this.width = width;
        this.height = height;
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

    public String getPosition() {
        return position;
    }
}
