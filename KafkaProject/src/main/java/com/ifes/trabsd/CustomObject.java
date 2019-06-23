package com.ifes.trabsd;

public class CustomObject {

    private Float temperature;
    private Float humidity;
    private Float pressure;
    private String date;

    public CustomObject(Float temperature, Float humidity, Float pressure, String date) {
        this.temperature = temperature;
        this.humidity = humidity;
        this.pressure = pressure;
        this.date = date;
    }

    public CustomObject() {
    }

    public Float getTemperature() {
        return temperature;
    }

    public void setTemperature(Float temperature) {
        this.temperature = temperature;
    }

    public Float getHumidity() {
        return humidity;
    }

    public void setHumidity(Float humidity) {
        this.humidity = humidity;
    }

    public Float getPressure() {
        return pressure;
    }

    public void setPressure(Float pressure) {
        this.pressure = pressure;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "CustomObject{" +
                "temperature='" + this.temperature + '\'' +
                ", humidity='" + this.humidity + '\'' +
                ", pressure=" + this.pressure + '\'' +
                ", date=" + this.date +
                '}';
    }
}
