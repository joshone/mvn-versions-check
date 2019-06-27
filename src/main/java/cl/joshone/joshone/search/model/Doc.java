package cl.joshone.joshone.search.model;

public class Doc implements java.lang.Comparable<Doc> {

	private String id;
	private String g;
	private String a;
	private String v;
	private String p;
	private long timestamp;
	private String [] ec;
	private String [] tags;
	
	public Doc() {
	}
	public Doc(String id, String g, String a, String v, String p, long timestamp, String[] ec, String[] tags) {
		this.id = id;
		this.g = g;
		this.a = a;
		this.v = v;
		this.p = p;
		this.timestamp = timestamp;
		this.ec = ec;
		this.tags = tags;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getG() {
		return g;
	}
	public void setG(String g) {
		this.g = g;
	}
	public String getA() {
		return a;
	}
	public void setA(String a) {
		this.a = a;
	}
	public String getV() {
		return v;
	}
	public void setV(String v) {
		this.v = v;
	}
	public String getP() {
		return p;
	}
	public void setP(String p) {
		this.p = p;
	}
	public long getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}
	public String[] getEc() {
		return ec;
	}
	public void setEc(String[] ec) {
		this.ec = ec;
	}
	public String[] getTags() {
		return tags;
	}
	public void setTags(String[] tags) {
		this.tags = tags;
	}
	
	@Override
	public int compareTo(Doc o) {
		// TODO Auto-generated method stub
		return 0;
	}
}
