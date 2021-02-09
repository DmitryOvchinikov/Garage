package com.classy.common;

public class Garage {
    private String[] Cars;
    private boolean open;
    private String address;
    private String name;

    public String[] getCars() {
        return Cars;
    }

    public void setCars(String[] cars) {
        Cars = cars;
    }

    public boolean isOpen() {
        return open;
    }

    public void setOpen(boolean open) {
        this.open = open;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
