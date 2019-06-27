package cl.joshone.joshone.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class UtilProperties {

	
	public String getPropValue(String key) throws IOException {
		
	    Properties mainProperties = new Properties();

	    FileInputStream file;
	    	String path = "";
	    //the base folder is ./, the root of the main.properties file
	    	if (UtilOS.isWindows()) {
	    		path = "C:\\apps\\joshone\\config.properties";
	    	}else if(UtilOS.isMac() || UtilOS.isUnix() || UtilOS.isSolaris()) {
	    		path = "//apps//joshone//config.properties";
	    	}

	    //load the file handle for main.properties
	    file = new FileInputStream(path);

	    //load all the properties from this file
	    mainProperties.load(file);

	    //we have loaded the properties, so close the file handle
	    file.close();

	    return mainProperties.getProperty(key);

	}
}
