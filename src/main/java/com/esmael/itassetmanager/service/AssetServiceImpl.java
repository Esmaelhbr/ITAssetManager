package com.esmael.itassetmanager.service;


import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.esmael.itassetmanager.entities.Asset;
import com.esmael.itassetmanager.entities.User;
import com.esmael.itassetmanager.enums.AssetStatus;
import com.esmael.itassetmanager.repositories.AssetRepository;
import com.esmael.itassetmanager.repositories.UserRepository;


@Service
public class AssetServiceImpl implements AssetService {

	private final AssetRepository assetRepository;
	private final UserRepository userRepository;
	
	
	
	

	public AssetServiceImpl(AssetRepository assetRepository, UserRepository userRepository) {
		
		this.assetRepository = assetRepository;
		this.userRepository = userRepository;
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

	@Override
	public Asset assignAssetToUser(Long assetId, Long userId) {
		Asset asset = assetRepository.findById(assetId)
			    .orElseThrow(() -> new RuntimeException("Asset not found"));
		User user = userRepository.findById(userId)
			    .orElseThrow(() -> new RuntimeException("User not found"));
		
		if (asset.getStatus() != AssetStatus.AVAILABLE) {
		    throw new RuntimeException("Asset is not available");
		}
		
		asset.setAssignedTo(user);
		asset.setStatus(AssetStatus.ASSIGNED);
		return assetRepository.save(asset);
	}

	@Override
	public Asset unassignAsset(Long assetId) {
		
		Asset asset = assetRepository.findById(assetId)
			    .orElseThrow(() -> new RuntimeException("Asset not found"));
		
		if(asset.getAssignedTo() == null) {
			throw new RuntimeException("Asset is not assigned");
		}
		
		asset.setAssignedTo(null);
		asset.setStatus(AssetStatus.AVAILABLE);
		return assetRepository.save(asset);
	}

}
