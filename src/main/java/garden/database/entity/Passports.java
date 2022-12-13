package garden.database.entity;

import jakarta.persistence.Entity;


public class Passports {

    private int passportsId;
    private int shopId;
    private String data;
    private int removed;

    public int getPassportsId() {
        return passportsId;
    }

    public void setPassportsId(int passportsId) {
        this.passportsId = passportsId;
    }

    public int getShopId() {
        return shopId;
    }

    public void setShopId(int shopId) {
        this.shopId = shopId;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public int getRemoved() {
        return removed;
    }

    public void setRemoved(int removed) {
        this.removed = removed;
    }
}
