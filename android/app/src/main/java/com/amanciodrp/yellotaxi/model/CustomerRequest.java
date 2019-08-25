package com.amanciodrp.yellotaxi.model;

public class CustomerRequest {

    public String getCustomerRideId() {
        return customerRideId;
    }

    public void setCustomerRideId(String customerRideId) {
        this.customerRideId = customerRideId;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getDestinationLat() {
        return destinationLat;
    }

    public void setDestinationLat(String destinationLat) {
        this.destinationLat = destinationLat;
    }

    public String getDestinationLng() {
        return destinationLng;
    }

    public void setDestinationLng(String destinationLng) {
        this.destinationLng = destinationLng;
    }

    private String customerRideId;
    private String destination;
    private String destinationLat;
    private String destinationLng;
    private String name;
    private String phone;

    public CustomerRequest(String customerRideId, String destination, String destinationLat, String destinationLng, String name, String phone) {
        this.customerRideId = customerRideId;
        this.destination = destination;
        this.destinationLat = destinationLat;
        this.destinationLng = destinationLng;
        this.name = name;
        this.phone = phone;
    }

    public CustomerRequest() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
