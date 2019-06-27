package cl.joshone.joshone.process;

import java.util.ArrayList;
import java.util.List;

import cl.joshone.joshone.model.DependencyUpdate;
import cl.joshone.joshone.model.ProperDependency;


public class ProcessIterateDependencies {
	
	public List<DependencyUpdate> getProjectDependenciesToUpdate(List<DependencyUpdate> allDependencies, List<ProperDependency> properDependencies){
		
		List <DependencyUpdate> listToProject = new ArrayList<DependencyUpdate> ();
		
		for(int i= 0; i< properDependencies.size();i++) {
//			System.out.println("buscando [{}]: "+properDependencies.get(i).getGroupId()+":"+properDependencies.get(i).getArtifactId());
			ProperDependency dependencyFromDependencyList = properDependencies.get(i);
			for(int j=0; j< allDependencies.size();j++ ) {
//				System.out.println("	comparando [{}]: "+allDependencies.get(j).getGroupId()+":"+allDependencies.get(j).getArtifactId());
				DependencyUpdate dependencyFromMvnUpdate = allDependencies.get(j);
				
				if(dependencyFromMvnUpdate.getGroupId().equals(dependencyFromDependencyList.getGroupId()) ){
//					System.out.println("mvn updates: "+dependencyFromMvnUpdate.getGroupId()+":"+dependencyFromMvnUpdate.getArtifactId());
//					System.out.println("mvn dependency: "+dependencyFromDependencyList.getGroupId()+":"+dependencyFromDependencyList.getArtifactId());
					if( dependencyFromMvnUpdate.getArtifactId().trim().equals(dependencyFromDependencyList.getArtifactId().trim())){
//						System.out.println(dependencyFromMvnUpdate.getGroupId().trim()+":"+dependencyFromMvnUpdate.getArtifactId().trim()+":"+dependencyFromMvnUpdate.getNewerVersion().trim());
						dependencyFromMvnUpdate.setCurrentVersion(null);
						listToProject.add(dependencyFromMvnUpdate);
					}
				}
			}
			
		}
		return listToProject;
	}
	
	
}
