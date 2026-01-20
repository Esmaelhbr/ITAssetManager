package com.esmael.itassetmanager.service;


import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.esmael.itassetmanager.entities.Asset;
import com.esmael.itassetmanager.repositories.AssetRepository;


@Service
public class AssetServiceImpl implements AssetService {

	private final AssetRepository assetRepository;
	
	
	
	public AssetServiceImpl(AssetRepository assetRepository) {
		
		this.assetRepository = assetRepository;
	}

	@Override
	public Asset createAsset(Asset asset) {
		
		return assetRepository.save(asset);
	}

	@Override
	public Asset getAssetById(Long id) {
		
		return assetRepository.findById(id).orElse(null);
	}

	@Override
	public Optional<Asset> getAssetByAssetTag(String assetTag) {
		
		return assetRepository.findByAssetTag(assetTag);
	}

	@Override
	public List<Asset> getAllAssets() {
		
		return assetRepository.findAll();
	}

	@Override
	public Asset updateAsset(Long id, Asset asset) {
		Asset existingAsset = getAssetById(id);

        if (existingAsset == null) {
            return null;
        }

        
        existingAsset.setAssetTag(asset.getAssetTag());
        existingAsset.setName(asset.getName());
        existingAsset.setType(asset.getType());
        existingAsset.setStatus(asset.getStatus());
        existingAsset.setAssignedTo(asset.getAssignedTo());

        return assetRepository.save(existingAsset);
	}

	@Override
	public void deleteAsset(Long id) {
		assetRepository.deleteById(id);

	}

}
