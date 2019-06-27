package cl.joshone.joshone.process;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import cl.joshone.joshone.model.DependencyUpdate;

public class ProcessFileWriter {
	
	public static void writeFile(String path, List<DependencyUpdate> listDependencies) throws IOException{
		
		File f= new File(path+"//dependencias.xml");
		FileWriter fw = new FileWriter(f, false);
		
//		fw.append("<dependencies>\n");
		for(int i= 0;i <listDependencies.size(); i++){
			DependencyUpdate dependency = listDependencies.get(i);
			
			fw.append("<dependency>\n");
			fw.append("	<groupId>"+dependency.getGroupId().trim()+"</groupId>\n");
			fw.append("	<artifactId>"+dependency.getArtifactId().trim()+"</artifactId>\n");
			fw.append("	<version>"+dependency.getNewerVersion().trim()+"</version>\n");
			fw.append("</dependency>\n");			
		}
		
//		fw.append("</dependencies>");
		
		fw.flush();
		fw.close();
	}
	
	
}
