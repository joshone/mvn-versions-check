package cl.joshone.joshone.search.model;

import java.util.List;

public class Response {
	private Long numFound;
	private Long start;
	private List<Doc> docs;
	
	public Response() {
		
	}
	public Response(Long numFound, Long start, List<Doc> docs) {
		this.numFound = numFound;
		this.start = start;
		this.docs = docs;
	}
	public Long getNumFound() {
		return numFound;
	}
	public void setNumFound(Long numFound) {
		this.numFound = numFound;
	}
	public Long getStart() {
		return start;
	}
	public void setStart(Long start) {
		this.start = start;
	}
	public List<Doc> getDocs() {
		return docs;
	}
	public void setDocs(List<Doc> docs) {
		this.docs = docs;
	}
	
}
