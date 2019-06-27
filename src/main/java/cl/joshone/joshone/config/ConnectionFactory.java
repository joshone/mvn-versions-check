package cl.joshone.joshone.config;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.URL;

import com.sun.jersey.client.urlconnection.HttpURLConnectionFactory;

import cl.joshone.joshone.util.UtilProperties;

public class ConnectionFactory implements HttpURLConnectionFactory {
	UtilProperties utilProp = new UtilProperties();
	Proxy proxy;
	
	private void initializeProxy() throws IOException {
		String host = utilProp.getPropValue("proxy.host");
		Integer port = Integer.parseInt(utilProp.getPropValue("proxy.port"));
		proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress(host, port));
	}

	@Override
	public HttpURLConnection getHttpURLConnection(URL arg0) throws IOException {
		initializeProxy();
		return (HttpURLConnection) arg0.openConnection(proxy);
	}

}
