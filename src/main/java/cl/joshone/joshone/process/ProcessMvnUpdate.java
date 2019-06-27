package cl.joshone.joshone.process;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import cl.joshone.joshone.model.DependencyUpdate;
import cl.joshone.joshone.util.UtilOS;
import cl.joshone.joshone.util.UtilProperties;

public class ProcessMvnUpdate {
	UtilProperties utilProp = new UtilProperties();
	
	public List<DependencyUpdate> getDependenciesToUpdate(String path) throws IOException{
		
		String mvn_home = utilProp.getPropValue("mvn_home");
		String mvn_cmd = utilProp.getPropValue("mvn.update.command");
		System.out.println(mvn_cmd);
		ProcessBuilder builder = null;
		List<String> lineas = new ArrayList<String>();
		if(UtilOS.isWindows()) {
			builder = new ProcessBuilder("cmd.exe", "/c", 
	    			"cd "+path+" && "+ mvn_home+mvn_cmd);
			
		}else if(UtilOS.isMac()) {
			builder = new ProcessBuilder("sh", "-c", "cd "+path+" && "+ mvn_home+mvn_cmd);
		}
		builder.redirectErrorStream(true);
    	
		Process p = builder.start();
		BufferedReader r = new BufferedReader(new InputStreamReader(p.getInputStream()));
	
		String line;
		int i=0;
		while(true) {
			i++;
			line = r.readLine();
			if(line == null || line.equals("sh: mvn: command not found")) {
				break;
			}
			if(i >= 9) {
				line = line.replace("[INFO] ", "");
				lineas.add(line);
			}
		}
		List<String>toRemove = new ArrayList<String>();
		for(int j=0;j< lineas.size();j++) {
			if(lineas.get(j).startsWith("[INFO] ") || lineas.get(j).startsWith("[WARNING] ") || 
					lineas.get(j).contains("                                                                  ") ) {
			
				toRemove.add(lineas.get(j));
				
			}
		}
	
		for(int k = 0;k<toRemove.size(); k++) {
			lineas.remove(toRemove.get(k));
		}
		
		lineas = juntandoStrings(lineas);
		List<DependencyUpdate> dependencies = parsingDependencies(lineas);
				
		return dependencies;
	}
		
	
	public static List<String> juntandoStrings(List<String> lineas){
		
		List<String> temp = new ArrayList<String>();
	
		for(int i=0; i<lineas.size();i++) {
			if(lineas.get(i).endsWith("...")) {
				String dosLineas = lineas.get(i).trim()+lineas.get(i+1).trim();
				dosLineas = dosLineas.trim();
				temp.add(dosLineas);
				i++;
			
			}else {
				temp.add(lineas.get(i));
			}
		}
	
	return temp;
	
}


public static List<DependencyUpdate> parsingDependencies(List<String> lineas){
		
		List <DependencyUpdate> temp = new ArrayList<DependencyUpdate>();
		for(int i=0; i<lineas.size();i++) {
			if(!lineas.get(i).contains("WARNING") || !lineas.get(i).contains("                                                                      ")
					|| !lineas.get(i).equals("") || !lineas.get(i).contains("The following dependencies in") || !lineas.get(i).contains("-------------------------------")
					|| !lineas.get(i).contains("BUILD SUCCESS") || !lineas.get(i).contains("Total time")|| !lineas.get(i).contains("Finished at")) {
				
				try {
					DependencyUpdate dependency = new DependencyUpdate();
    				String [] tramas = lineas.get(i).split("\\.\\.\\.");
    				
    				dependency.setGroupId(tramas[0].split(":")[0].trim());
    				dependency.setArtifactId(tramas[0].split(":")[1]);
    				
    				String [] versiones = tramas [tramas.length-1].split("->");
    				String versionActual = "";
    				
    				try {
    					versionActual = versiones[0].split(" ")[1];
    				}catch(Exception e) {
    					versionActual = versiones[0];
    				}
    				
    				String nuevaVersion = versiones[1].trim();
    				dependency.setCurrentVersion(versionActual);
    				dependency.setNewerVersion(nuevaVersion);
    				
    				temp.add(dependency);
				}catch(Exception e) {
//					System.out.println("linea: "+i);
				}
				
			}
		}
		
		return temp;
}

}
