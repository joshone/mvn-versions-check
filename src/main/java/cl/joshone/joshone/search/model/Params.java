package cl.joshone.joshone.search.model;

public class Params {
	
	private String q;
	private String core;
	private String indent;
	private String fl;
	private String start;
	private String sort;
	private String rows;
	private String version;
	private String wt;
	
	public Params() {
	}
	public Params(String q, String core, String indent, String fl, String start, String sort, String rows,
			String version, String wt) {
		this.q = q;
		this.core = core;
		this.indent = indent;
		this.fl = fl;
		this.start = start;
		this.sort = sort;
		this.rows = rows;
		this.version = version;
		this.wt = wt;
	}
	public String getQ() {
		return q;
	}
	public void setQ(String q) {
		this.q = q;
	}
	public String getCore() {
		return core;
	}
	public void setCore(String core) {
		this.core = core;
	}
	public String getIndent() {
		return indent;
	}
	public void setIndent(String indent) {
		this.indent = indent;
	}
	public String getFl() {
		return fl;
	}
	public void setFl(String fl) {
		this.fl = fl;
	}
	public String getStart() {
		return start;
	}
	public void setStart(String start) {
		this.start = start;
	}
	public String getSort() {
		return sort;
	}
	public void setSort(String sort) {
		this.sort = sort;
	}
	public String getRows() {
		return rows;
	}
	public void setRows(String rows) {
		this.rows = rows;
	}
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	public String getWt() {
		return wt;
	}
	public void setWt(String wt) {
		this.wt = wt;
	}

}
