package cl.joshone.joshone.model;

public class ProperDependency {
	
	private String groupId;
	private String artifactId;
	private String compiledIn;
	private String version;
	private String scope;
	
	public ProperDependency() {
		this.groupId = "";
		this.artifactId = "";
		this.compiledIn = "";
		this.version = "";
		this.scope = "";
	}
	
	public ProperDependency(String groupId, String artifactId, String compiledIn, String version, String scope) {
		this.groupId = groupId;
		this.artifactId = artifactId;
		this.compiledIn = compiledIn;
		this.version = version;
		this.scope = scope;
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
	public String getCompiledIn() {
		return compiledIn;
	}
	public void setCompiledIn(String compiledIn) {
		this.compiledIn = compiledIn;
	}
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	public String getScope() {
		return scope;
	}
	public void setScope(String scope) {
		this.scope = scope;
	}
}
