package com.rest.modelo;



public class Ticker {

  public String high,low, vol, buy;

    public String getHigh() {
        return high;
    }

    public void setHigh(String high) {
        this.high = high;
    }

    public String getLow() {
        return low;
    }

    public void setLow(String low) {
        this.low = low;
    }

    public String getVol() {
        return vol;
    }

    public void setVol(String vol) {
        this.vol = vol;
    }

    public String getBuy() {
        return buy;
    }

    public void setBuy(String buy) {
        this.buy = buy;
    }

    @Override
    public String toString() {
        return "Ticker{" +
                "high='" + high + '\'' +
                ", low='" + low + '\'' +
                ", vol='" + vol + '\'' +
                ", buy='" + buy + '\'' +
                '}';
    }
}
