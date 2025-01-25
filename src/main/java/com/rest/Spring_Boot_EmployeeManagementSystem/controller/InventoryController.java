package com.rest.Spring_Boot_EmployeeManagementSystem.controller;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.rest.Spring_Boot_EmployeeManagementSystem.model.Asset;
import com.rest.Spring_Boot_EmployeeManagementSystem.model.Employee;
import com.rest.Spring_Boot_EmployeeManagementSystem.service.AssetService;
import com.rest.Spring_Boot_EmployeeManagementSystem.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class InventoryController {

    @Autowired
    EmployeeService employeeService;

    @Autowired
    AssetService assetService;

    @GetMapping("/employees")
    public List<Employee> getAllEmployees() {
        return employeeService.getAllEmployees();
    }
    @GetMapping("/employees/{id}")
    public Employee getEmployeeById(@PathVariable Long id) {
        return employeeService.getEmployeeById(id).orElse(null);
    }

    @PostMapping("/employees")
    public ResponseEntity<?> addEmployees(@RequestBody Employee employee) {
        try {

            if (employee.getName() == null || employee.getName().trim().isEmpty()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Name cannot be null");
            }
            if (employee.getDepartment() == null || employee.getDepartment().trim().isEmpty()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Department cannot be null");
            }

            Employee savedEmployee = employeeService.addEmployee(employee);
            return ResponseEntity.ok(savedEmployee);

        } catch (IllegalArgumentException iae) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(iae.getMessage());
        } catch (RuntimeException re) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(re.getMessage());
        }
    }

    @PutMapping("/employees/{id}")
    public ResponseEntity<?> updateEmployee(@PathVariable Long id, @RequestBody Employee employee) {
        try {

            if (employee.getName() == null || employee.getName().trim().isEmpty()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Name cannot be null");
            }
            if (employee.getDepartment() == null || employee.getDepartment().trim().isEmpty()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Department cannot be null");
            }

            Employee updated = employeeService.updateEmployee(id, employee);
            return ResponseEntity.ok(updated);
        } catch (IllegalArgumentException iae) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(iae.getMessage());
        } catch (RuntimeException re) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(re.getMessage());
        }
    }
    @DeleteMapping("/employees/{id}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable Long id) {
        employeeService.deleteEmployee(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/assets")
    public List<Asset> getAllAssets() {
        return assetService.getAllAssets();
    }
    @GetMapping("/assets/{id}")
    public Asset getAssetById(@PathVariable Long id) {
        return assetService.getAssetById(id).orElse(null);
    }
    @PostMapping("/assets")
    public ResponseEntity<?> addAssets(@RequestBody Asset asset) {
        try {
            if (asset.getBrand() == null || asset.getBrand().trim().isEmpty()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Brand cannot be null");
            }
            if (asset.getModel() == null || asset.getModel().trim().isEmpty()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Model  cannot be null");
            }
            Asset savedAsset = assetService.addAsset(asset);
            return ResponseEntity.ok(savedAsset);
        } catch (IllegalArgumentException iae) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(iae.getMessage());
        } catch (RuntimeException re) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(re.getMessage());
        }
    }

    @PutMapping("/assets/{id}")
    public ResponseEntity<?> updateAsset(@PathVariable Long id, @RequestBody Asset asset) {
        try {
            if (asset.getBrand() == null || asset.getBrand().trim().isEmpty()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Brand cannot be null");
            }
            if (asset.getModel() == null || asset.getModel().trim().isEmpty()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Model  cannot be null");
            }

            Asset updated = assetService.updateAsset(id, asset);
            return ResponseEntity.ok(updated);
        } catch (IllegalArgumentException iae) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(iae.getMessage());
        } catch (RuntimeException re) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(re.getMessage());
        }
    }
    @DeleteMapping("/assets/{id}")
    public ResponseEntity<Void> deleteAsset(@PathVariable Long id) {
        assetService.deleteAsset(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/employees/{empID}/assets/{assetID}")
    public Employee addAssetToEmployee(@PathVariable Long empID, @PathVariable Long assetID) {
        Optional<Employee> employeeOptional = employeeService.getEmployeeById(empID);
        Optional<Asset> assetOptional = assetService.getAssetById(assetID);

        if (employeeOptional.isPresent()) {
            Employee employee = employeeOptional.get();
            Asset asset = assetOptional.get();

            employee.addAsset(asset);
            asset.setEmployee(employee);

            assetService.addAsset(asset);
            return employeeService.addEmployee(employee);
        }
        return null;
    }

    @GetMapping("/employees/{empID}/assets")
    public List<Asset> getEmployeeAssets(@PathVariable Long empID) {
        Optional<Employee> employeeOptional = employeeService.getEmployeeById(empID);
        return employeeOptional.map(Employee::getAssets).orElse(null);
    }

    @DeleteMapping("/employees/{empID}/assets/{assetID}")
    public Employee deleteEmployeeAssets(@PathVariable Long empID, @PathVariable Long assetID) {
        Optional<Employee> employeeOptional = employeeService.getEmployeeById(empID);
        Optional<Asset> assetOptional = assetService.getAssetById(assetID);

        if (employeeOptional.isPresent()) {
            Employee employee = employeeOptional.get();
            Asset asset = assetOptional.get();

            employee.removeAsset(asset);
            return employeeService.updateEmployee(empID, employee);
        }
        return null;
    }

}
