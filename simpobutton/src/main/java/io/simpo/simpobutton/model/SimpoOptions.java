package io.simpo.simpobutton.model;

public class SimpoOptions {
    private SimpoUser simpoUser;
    private String uuid;
    private boolean show;
    private String position;
    private int dimension;

    public SimpoOptions() {
    }

    public SimpoOptions(SimpoUser simpoUser, String uuid, boolean show, String position, int dimension) {
        this.simpoUser = simpoUser;
        this.uuid = uuid;
        this.show = show;
        this.position = position;
        this.dimension = dimension;
    }

    public SimpoUser getSimpoUser() {
        return simpoUser;
    }

    public void setSimpoUser(SimpoUser simpoUser) {
        this.simpoUser = simpoUser;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public boolean isShow() {
        return show;
    }

    public void setShow(boolean show) {
        this.show = show;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public int getDimension() {
        return dimension;
    }

    public void setDimension(int dimension) {
        this.dimension = dimension;
    }

}
