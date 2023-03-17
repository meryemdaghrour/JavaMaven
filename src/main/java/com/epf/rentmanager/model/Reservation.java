package com.epf.rentmanager.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

public class Reservation {

    private long identifier;
    private Client client;
    private Vehicle vehicle;
    private LocalDate debut;
    private LocalDate fin;

    public long getIdentifier() {
        return identifier;
    }

    public void setIdentifier(long identifier) {
        this.identifier = identifier;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public LocalDate getDebut() {
        return debut;
    }

    public void setDebut(LocalDate debut) {
        this.debut = debut;
    }

    public LocalDate getFin() {
        return fin;
    }

    public void setFin(LocalDate fin) {
        this.fin = fin;
    }

    public Reservation(long identifier, Client client, Vehicle vehicle, LocalDate debut, LocalDate fin) {
        this.identifier = identifier;
        this.client = client;
        this.vehicle = vehicle;
        this.debut = debut;
        this.fin = fin;
    }
    public Reservation( Client client, Vehicle vehicle, LocalDate debut, LocalDate fin) {

        this.client = client;
        this.vehicle = vehicle;
        this.debut = debut;
        this.fin = fin;
    }
    public Reservation(){}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Reservation that = (Reservation) o;
        return identifier == that.identifier && client == that.client && vehicle == that.vehicle && debut.equals(that.debut) && fin.equals(that.fin);
    }

    @Override
    public int hashCode() {
        return Objects.hash(identifier, client, vehicle, debut, fin);
    }

    @Override
    public String toString() {
        return "Reservation{" +
                "identifier=" + identifier +
                ", client=" + client.getName() + client.getLastName()+
                ", vehicle=" + vehicle.getConstructor() +vehicle.getModel()+
                ", debut=" + debut +
                ", fin=" + fin +
                '}';
    }
}
