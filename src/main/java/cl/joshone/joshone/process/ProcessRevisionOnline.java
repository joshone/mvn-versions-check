package cl.joshone.joshone.process;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import com.google.gson.Gson;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.client.urlconnection.URLConnectionClientHandler;

import cl.joshone.joshone.config.ConnectionFactory;
import cl.joshone.joshone.model.DependencyUpdate;
import cl.joshone.joshone.model.ProperDependency;
import cl.joshone.joshone.search.model.Doc;
import cl.joshone.joshone.search.model.SearchMavenResponse;
import cl.joshone.joshone.util.UtilProperties;

public class ProcessRevisionOnline {
	UtilProperties utilProp = new UtilProperties();

	public List<DependencyUpdate> getDependenciesLeft(List<DependencyUpdate> dependencyUpdateList,
			List<ProperDependency> properList) throws IOException {

		List<DependencyUpdate> dependenciesLeft = new ArrayList<DependencyUpdate>();
		List<ProperDependency> toReviewList = new ArrayList<ProperDependency>();
		for (int i = 0; i < properList.size(); i++) {
			boolean existe = false;
			ProperDependency properDependency = properList.get(i);

			for (int j = 0; j < dependencyUpdateList.size(); j++) {

				DependencyUpdate dependencyUpdate = dependencyUpdateList.get(j);

				if (properDependency.getGroupId().trim().equals(dependencyUpdate.getGroupId().trim())) {
					if (properDependency.getArtifactId().trim().equals(dependencyUpdate.getArtifactId().trim())) {
						existe = true;
						break;
					}
				} else {
					existe = false;
				}

			}
			if (!existe) {
				toReviewList.add(properDependency);
			}
		}

		boolean isProxeable=Boolean.parseBoolean(utilProp.getPropValue("proxy.enable"));
		System.out.println("Proxy enabled: "+isProxeable);
		for (int x = 0; x < toReviewList.size(); x++) {
			ProperDependency toReview = toReviewList.get(x);
			String q = "g:" + toReview.getGroupId() + "%20AND%20a:" + toReview.getArtifactId();
			String core = "gav";
			
			System.out.println("buscando dependencia: "+toReview.getGroupId()+":"+toReview.getArtifactId());
			SearchMavenResponse searchMavenResponse = null;
			try {
				searchMavenResponse = searchDependency(q, core, isProxeable);
			}catch(Exception e) {
				System.out.println("No se encontró el repositorio: "+e.getMessage());
			}
			
			Doc doc = getLastVersion(searchMavenResponse);
			System.out.println(doc.getG() + ":" + doc.getA() + ":" + doc.getV());
			DependencyUpdate dl = new DependencyUpdate();
			dl.setArtifactId(doc.getA());
			dl.setGroupId(doc.getG());
			dl.setCurrentVersion(null);
			dl.setNewerVersion(doc.getV());

			dependenciesLeft.add(dl);

		}

		return dependenciesLeft;
	}

	public SearchMavenResponse searchDependency(String q, String core, boolean proxyEnabled) throws IOException {
		
		String url = utilProp.getPropValue("url.search");
		Gson gson = new Gson();
		
		
		SearchMavenResponse searchMvnResponse = null;
		try {
			Client restClient = null;
			
			if(!proxyEnabled){//no usa proxy
				restClient = Client.create();
				
			}else if(proxyEnabled){//usa proxy
				URLConnectionClientHandler ch = new URLConnectionClientHandler(new ConnectionFactory());
				ClientConfig clientConfig = new DefaultClientConfig();
				
				restClient = new Client(ch, clientConfig);
			}
			
			WebResource webResource = restClient.resource(url+"q=" + q + "&core=" + core);
			ClientResponse response = webResource.type("application/json").get(ClientResponse.class);
			if(response.getStatus() == 200){
				String responseStr = response.getEntity(String.class);
				searchMvnResponse = gson.fromJson(responseStr, SearchMavenResponse.class);
			}else {
				System.out.println("No se pudo conectar: "+response.getStatus() );
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return searchMvnResponse;
	}

	public Doc getLastVersion(SearchMavenResponse searchDependency) {

		List<Doc> docs = searchDependency.getResponse().getDocs();
		docs.sort(Comparator.comparing(Doc::getV).reversed());
//		Collections.sort(docs);
		return docs.get(0);
	}

}
