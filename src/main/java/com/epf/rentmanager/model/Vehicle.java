package com.epf.rentmanager.model;

import java.util.Objects;

public class Vehicle {

    private long identifier;
    private String constructor;
    private String model;
    private int nbPlaces;

    public Vehicle() {
    }

    public Vehicle(long identifier, String constructor,  int nbPlaces) {
        this.identifier = identifier;
        this.constructor = constructor;
        this.nbPlaces = nbPlaces;
    }
    public Vehicle(long identifier) {
        this.identifier = identifier;
    }

    public Vehicle( String constructor, String model, int nbPlaces) {

        this.constructor = constructor;
        this.model = model;
        this.nbPlaces = nbPlaces;
    }

    public long getIdentifier() {
        return identifier;
    }

    public void setIdentifier(long identifier) {
        this.identifier = identifier;
    }

    public String getConstructor() {
        return constructor;
    }

    public void setConstructor(String constructor) {
        this.constructor = constructor;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getNbPlaces() {
        return nbPlaces;
    }

    public void setNbPlaces(int nbPlaces) {
        this.nbPlaces = nbPlaces;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vehicle vehicle = (Vehicle) o;
        return identifier == vehicle.identifier && nbPlaces == vehicle.nbPlaces && constructor.equals(vehicle.constructor) && model.equals(vehicle.model);
    }

    @Override
    public int hashCode() {
        return Objects.hash(identifier, constructor, model, nbPlaces);
    }

    @Override
    public String toString() {
        return "Vehicle{" +
                "identifier=" + identifier +
                ", constructor='" + constructor + '\'' +
                ", model='" + model + '\'' +
                ", nbPlaces=" + nbPlaces +
                '}';
    }


}
