package com.rest.Spring_Boot_EmployeeManagementSystem.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String department;
    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL)
    private List<Asset> assets;

    public Employee() {
        assets = new ArrayList<>();
    }

    public Employee(Long id, String name, String department) {
        this.id = id;
        this.name = name;
        this.department = department;
        this.assets = new ArrayList<>();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Asset> getAssets() {
        return assets;
    }

    public void setAssets(List<Asset> assets) {
        this.assets = assets;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public void addAsset(Asset asset) {
        assets.add(asset);
        asset.setEmployee(this);
    }
    public void removeAsset(Asset asset) {
        assets.remove(asset);
        asset.setEmployee(null);
    }

}
