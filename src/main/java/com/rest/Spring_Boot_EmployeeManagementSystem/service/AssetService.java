package com.rest.Spring_Boot_EmployeeManagementSystem.service;

import com.rest.Spring_Boot_EmployeeManagementSystem.model.Asset;
import com.rest.Spring_Boot_EmployeeManagementSystem.repository.AssetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AssetService {

    @Autowired
    AssetRepository assetRepository;

    public List<Asset> getAllAssets() {
        return assetRepository.findAll();
    }
    public Optional<Asset> getAssetById(Long id) {
        return assetRepository.findById(id);
    }
    public Asset addAsset(Asset asset) {
        return assetRepository.save(asset);
    }
    public Asset updateAsset(Long id, Asset asset) {
        if (assetRepository.existsById(id)) {
            asset.setId(id);
            return assetRepository.save(asset);
        }
        return null;
    }
    public void deleteAsset(Long id) {
        assetRepository.deleteById(id);
    }

}
