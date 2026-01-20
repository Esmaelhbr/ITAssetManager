package com.esmael.itassetmanager.entities;



import com.esmael.itassetmanager.enums.AssetStatus;
import com.esmael.itassetmanager.enums.AssetType;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "assets")
public class Asset {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "asset_tag", nullable = false, unique = true)
	@NotBlank
	private String assetTag;
	
	 @Column(nullable = false)
	private String name;
	
	
	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	@NotNull
	private AssetType type;
	
	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private AssetStatus status;
	
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User assignedTo;
	
	public Asset() {
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getAssetTag() {
		return assetTag;
	}

	public void setAssetTag(String assetTag) {
		this.assetTag = assetTag;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public AssetType getType() {
		return type;
	}

	public void setType(AssetType type) {
		this.type = type;
	}

	public AssetStatus getStatus() {
		return status;
	}

	public void setStatus(AssetStatus status) {
		this.status = status;
	}

	public User getAssignedTo() {
		return assignedTo;
	}

	public void setAssignedTo(User assignedTo) {
		this.assignedTo = assignedTo;
	}

	@Override
	public String toString() {
		return "Asset [id=" + id + ", assetTag=" + assetTag + ", name=" + name + ", type=" + type + ", status=" + status
				+ ", assignedTo=" + assignedTo + "]";
	}
	
	
	
	

}
