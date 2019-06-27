package cl.joshone.joshone.model;

public class DependencyUpdate {
	
	private String groupId;
	private String artifactId;
	private String currentVersion;
	private String newerVersion;
	
	public DependencyUpdate() {
		this.groupId = "";
		this.artifactId = "";
		this.currentVersion = "";
		this.newerVersion = "";
	}
	
	public DependencyUpdate(String groupId, String artifactId, String currentVersion, String newerVersion) {
		this.groupId = groupId;
		this.artifactId = artifactId;
		this.currentVersion = currentVersion;
		this.newerVersion = newerVersion;
	}
	public String getGroupId() {
		return groupId;
	}
	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}
	public String getArtifactId() {
		return artifactId;
	}
	public void setArtifactId(String artifactId) {
		this.artifactId = artifactId;
	}
	public String getCurrentVersion() {
		return currentVersion;
	}
	public void setCurrentVersion(String currentVersion) {
		this.currentVersion = currentVersion;
	}
	public String getNewerVersion() {
		return newerVersion;
	}
	public void setNewerVersion(String newerVersion) {
		this.newerVersion = newerVersion;
	}
}
