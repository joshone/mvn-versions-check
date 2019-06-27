package cl.joshone.joshone.process;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import cl.joshone.joshone.model.ProperDependency;
import cl.joshone.joshone.util.UtilOS;
import cl.joshone.joshone.util.UtilProperties;


public class ProcessMvnDependencyList {
	UtilProperties utilProp = new UtilProperties();
	
	public List<ProperDependency> getProperDependencies(String path) throws IOException{
		
		String mvn_home = utilProp.getPropValue("mvn_home");
		String mvn_cmd = utilProp.getPropValue("mvn.dependency.list");
		System.out.println(mvn_cmd);
		ProcessBuilder builder = null;
		List<String> lineas = new ArrayList<String>();
		if(UtilOS.isWindows()) {
			builder = new ProcessBuilder("cmd.exe", "/c", 
	    			"cd "+path+" && "+mvn_home+mvn_cmd);
			
		}else if(UtilOS.isMac()) {
			builder = new ProcessBuilder("sh", "-c", "cd "+path+" && "+ mvn_home+mvn_cmd);
		}
		builder.redirectErrorStream(true);
    	
		Process p = builder.start();
		BufferedReader r = new BufferedReader(new InputStreamReader(p.getInputStream()));
	
		String line;
		boolean inicio = false;
		while(true) {
			line = r.readLine();
			if(line == null) {
				break;
			}
			if(line.contains("The following files have been resolved")) {
				inicio = true;
				line = r.readLine();
			}
			if(inicio) {
				line = line.replace("[INFO] ", "");
				line = line.replace(" ", "");
				lineas.add(line);
			}
		}
				
		return getProperDependencies(lineas);
	}
	
	
	public List<ProperDependency> getProperDependencies (List<String> lineas){
		List<ProperDependency> properDependencies = new ArrayList<ProperDependency>();
		try {
			for(int i = 0; i< lineas.size(); i++) {
				String [] parametros = lineas.get(i).split(":");
				
				ProperDependency dependency = new ProperDependency();
				
				dependency.setGroupId(parametros[0]);
				dependency.setArtifactId(parametros[1]);
				dependency.setCompiledIn(parametros[2]);
				dependency.setVersion(parametros[3]);
				dependency.setScope(parametros[4]);
				if(!dependency.getScope().equals("test") && !dependency.getScope().equals("provided")) {
					properDependencies.add(dependency);
//					System.out.println(dependency.getGroupId()+":"+dependency.getArtifactId()+":"+dependency.getCompiledIn()+":"+dependency.getVersion()+":"+dependency.getScope());
				}
				
			}
		}catch(Exception e) {
			
		}
		
		return properDependencies;
	}

}
