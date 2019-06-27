package cl.joshone.joshone;

import java.io.IOException;
import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import cl.joshone.joshone.model.DependencyUpdate;
import cl.joshone.joshone.model.ProperDependency;
import cl.joshone.joshone.process.ProcessFileWriter;
import cl.joshone.joshone.process.ProcessIterateDependencies;
import cl.joshone.joshone.process.ProcessMvnDependencyList;
import cl.joshone.joshone.process.ProcessMvnUpdate;
import cl.joshone.joshone.process.ProcessRevisionOnline;

@SpringBootApplication
public class JoshoneApplication {

	public static void main(String[] args) throws IOException {
		SpringApplication.run(JoshoneApplication.class, args);
		
//		String path="/Users/josesalinas/Documents/GIT/puntaje";
//		String path="C:\\Users\\Josh\\Documents\\GIT\\puntaje";
		String path = args[0];
    	
		ProcessMvnUpdate pmu = new ProcessMvnUpdate();
		ProcessMvnDependencyList pmdl = new ProcessMvnDependencyList();
		ProcessIterateDependencies pid = new ProcessIterateDependencies();
		ProcessRevisionOnline pro = new ProcessRevisionOnline();
		
		List<DependencyUpdate> fullDependenciesToUpdate = pmu.getDependenciesToUpdate(path);
		
		List<ProperDependency> properDependencies = pmdl.getProperDependencies(path);
	
		System.out.println("Buscando data.." );
		System.out.println("mvn dependency:list = "+properDependencies.size() );
		System.out.println("mvn versions:display-dependency-updates = "+fullDependenciesToUpdate.size() );
		System.out.println("-----inicio iterando-----" );
		    		
		List<DependencyUpdate> resultList = pid.getProjectDependenciesToUpdate(fullDependenciesToUpdate, properDependencies);
		
		System.out.println("cant resultados: " +resultList.size());
		try{
			List <DependencyUpdate> revOnline = pro.getDependenciesLeft(fullDependenciesToUpdate, properDependencies);
			System.out.println("añadiendo: " +revOnline.size());
			resultList.addAll(revOnline);
		}catch(Exception e){
			System.out.println("[no se pudo comunicar con el servicio]" );
		}
		
		
		if(resultList.isEmpty()){
			System.out.println("Lista vacia" );
		}else{
			ProcessFileWriter.writeFile(path, resultList);
		}
				
		System.out.println("DONE");
	}
}
