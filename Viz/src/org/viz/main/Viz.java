package org.viz.main;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.persistence.config.PersistenceUnitProperties;
import org.visminer.main.VisMiner;

public class Viz {
	
	//Local repository properties
	private static String LOCAL_REPOSITORY_PATH  = "/home/massilva/workspace/Visminer/.git";
    private static String LOCAL_REPOSITORY_NAME  = "Visminer";
    private static String LOCAL_REPOSITORY_OWNER = "visminer";
    
    //Persistence properties using in database connection
    private static String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	private static String JDBC_URL = "jdbc:mysql://localhost/visminer";
	private static String JDBC_USER = "root";
	private static String JDBC_PASSWORD = "123"; 
	
    private boolean createTableFlag; //flag to indicate whether to create tables or not.
    
    //instance of VisMiner API
    protected VisMiner visminer;
    
    /**
	 * 
	 * @throws IOException
	 * @throws GitAPIException
	 */
    public Viz(){
    	this.createTableFlag = false;
    }
    
    /**
	 * 
	 * @param createTableFlag flag to indicate whether to create tables or not.
	 * @throws IOException
	 * @throws GitAPIException
	 */
    public Viz(boolean createTableFlag){
    	this.createTableFlag = createTableFlag;
	}
	
	/****************************************************************
	 *                    SETS AND GETS METHODS
	 ***************************************************************/
	
    /**
     * 
     * @return a variable of type VisMiner.
     */
	public VisMiner getVisminer() throws IOException, GitAPIException{		
		Map<String, String> props = new HashMap<String, String>();
		props.put(PersistenceUnitProperties.JDBC_DRIVER, JDBC_DRIVER);
    	props.put(PersistenceUnitProperties.JDBC_URL, JDBC_URL);
    	props.put(PersistenceUnitProperties.JDBC_USER, JDBC_USER);
    	props.put(PersistenceUnitProperties.JDBC_PASSWORD, JDBC_PASSWORD); 
		
		if(this.createTableFlag){
			props.put(PersistenceUnitProperties.DDL_GENERATION, "create-tables");
		}
		
		Map<Integer, String> api_cfg = new HashMap<Integer, String>();
		api_cfg.put(VisMiner.LOCAL_REPOSITORY_PATH ,LOCAL_REPOSITORY_PATH );
		api_cfg.put(VisMiner.LOCAL_REPOSITORY_NAME ,LOCAL_REPOSITORY_NAME );
		api_cfg.put(VisMiner.LOCAL_REPOSITORY_OWNER,LOCAL_REPOSITORY_OWNER);
		
		return new VisMiner(props, api_cfg);
	}

	/**
	 * 
	 * @return The local repository path
	 */
	public static String getLocalRepositoryPath() {
		return LOCAL_REPOSITORY_PATH;
	}

	/**
	 * 
	 * @return The local repository name
	 */
	public static String getLocalRepositoryName() {
		return LOCAL_REPOSITORY_NAME;
	}

	/**
	 * 
	 * @return The local repository owner
	 */
	public static String getLocalRepositoryOwner() {
		return LOCAL_REPOSITORY_OWNER;
	}

}