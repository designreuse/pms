package com.pms.app.view.vo;

public class UploadFile {

	private String base64ImageString;
	private String fileExtension;
	private String fileName;
	private Long licenseId;
	private Long siteId;
	private Long assetId;
	private Long ticketId;
	
	
	
	public String getBase64ImageString() {
		return base64ImageString;
	}
	public void setBase64ImageString(String base64ImageString) {
		this.base64ImageString = base64ImageString;
	}
	public String getFileExtension() {
		return fileExtension;
	}
	public void setFileExtension(String fileExtension) {
		this.fileExtension = fileExtension;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public Long getLicenseId() {
		return licenseId;
	}
	public void setLicenseId(Long licenseId) {
		this.licenseId = licenseId;
	}
	public Long getSiteId() {
		return siteId;
	}
	public void setSiteId(Long siteId) {
		this.siteId = siteId;
	}
	public Long getAssetId() {
		return assetId;
	}
	public void setAssetId(Long assetId) {
		this.assetId = assetId;
	}
	public Long getTicketId() {
		return ticketId;
	}
	public void setTicketId(Long ticketId) {
		this.ticketId = ticketId;
	}
	
}
