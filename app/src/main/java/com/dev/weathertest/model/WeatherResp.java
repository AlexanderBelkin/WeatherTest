package com.dev.weathertest.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Arrays;

public class WeatherResp {

    @SerializedName("coord")
    @Expose
    private Coodr coord;

    @SerializedName("weather")
    @Expose
    private Weather[] weather;

    @SerializedName("main")
    @Expose
    private Main main;


    private class Coodr {

        @SerializedName("lon")
        @Expose
        double lon;

        @SerializedName("lat")
        @Expose
        double lat;

        @Override
        public String toString() {
            return "Coodr{" +
                    "lon=" + lon +
                    ", lat=" + lat +
                    '}';
        }
    }

    private class Weather {

        @SerializedName("main")
        @Expose
        String main;

        @SerializedName("description")
        @Expose
        String description;


        @Override
        public String toString() {
            return "Weather{" +
                    "main='" + main + '\'' +
                    ", description='" + description + '\'' +
                    '}';
        }
    }

    private class Main {

        @SerializedName("temp")
        @Expose
        double temp;

        @SerializedName("pressure")
        @Expose
        double pressure;

        @SerializedName("humidity")
        @Expose
        double humidity;

        @SerializedName("temp_min")
        @Expose
        double temp_min;

        @SerializedName("temp_max")
        @Expose
        double temp_max;

        @SerializedName("sea_level")
        @Expose
        double sea_level;

        @SerializedName("grnd_level")
        @Expose
        double grnd_level;

        @Override
        public String toString() {
            return "Main{" +
                    "temp=" + temp +
                    ", pressure=" + pressure +
                    ", humidity=" + humidity +
                    ", temp_min=" + temp_min +
                    ", temp_max=" + temp_max +
                    ", sea_level=" + sea_level +
                    ", grnd_level=" + grnd_level +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "WeatherResp{" +
                "coord=" + coord +
                ", weather=" + Arrays.toString(weather) +
                ", main=" + main +
                '}';
    }
}
