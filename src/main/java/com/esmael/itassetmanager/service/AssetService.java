package com.esmael.itassetmanager.service;

import java.util.List;
import java.util.Optional;

import com.esmael.itassetmanager.entities.Asset;

public interface AssetService {
	
	  Asset createAsset(Asset asset);
	  Asset getAssetById(Long id);
	  Optional<Asset> getAssetByAssetTag(String assetTag);
	  List<Asset> getAllAssets();
	  Asset updateAsset(Long id, Asset asset);
	  void deleteAsset(Long id);
	  
	  Asset assignAssetToUser(Long assetId, Long userId);
	  Asset unassignAsset(Long assetId);

}
