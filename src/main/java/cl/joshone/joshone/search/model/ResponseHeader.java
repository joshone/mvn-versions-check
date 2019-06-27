package cl.joshone.joshone.search.model;

public class ResponseHeader {
	
	private Long status;
	private Long QTime;
	private Params params;
	 
	public ResponseHeader() {
	}
	public ResponseHeader(Long status, Long qTime, Params params) {
		this.status = status;
		QTime = qTime;
		this.params = params;
	}
	public Long getStatus() {
		return status;
	}
	public void setStatus(Long status) {
		this.status = status;
	}
	public Long getQTime() {
		return QTime;
	}
	public void setQTime(Long qTime) {
		QTime = qTime;
	}
	public Params getParams() {
		return params;
	}
	public void setParams(Params params) {
		this.params = params;
	}
}
