package com.sp.loylapclover.intermeditatemvp.topmovies;

/**
 * Created by Daniel on 08/07/2017.
 */

public class ViewModel {
    private String country;
    private String name;

    public ViewModel(String name, String country) {
        this.name = name;
        this.country = country;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
