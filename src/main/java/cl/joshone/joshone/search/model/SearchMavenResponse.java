package cl.joshone.joshone.search.model;

public class SearchMavenResponse {
	
	private Response response;
	private ResponseHeader responseHeader;
	
	public SearchMavenResponse() {
		this.response = new Response();
		this.responseHeader = new ResponseHeader();
	}
	public SearchMavenResponse(Response response, ResponseHeader responseHeader) {
		this.response = response;
		this.responseHeader = responseHeader;
	}
	public Response getResponse() {
		return response;
	}
	public void setResponse(Response response) {
		this.response = response;
	}
	public ResponseHeader getResponseHeader() {
		return responseHeader;
	}
	public void setResponseHeader(ResponseHeader responseHeader) {
		this.responseHeader = responseHeader;
	}
}
