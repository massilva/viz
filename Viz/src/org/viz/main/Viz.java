package org.viz.main;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.persistence.config.PersistenceUnitProperties;
import org.visminer.main.VisMiner;
import org.viz.model.Configuration;

public class Viz {
	
	//Local repository properties
	private String LOCAL_REPOSITORY_PATH;
    private String LOCAL_REPOSITORY_NAME;
    private String LOCAL_REPOSITORY_OWNER;
    
    //Persistence properties using in database connection
    private String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	private String JDBC_URL = "jdbc:mysql://localhost/visminer";
	private String JDBC_USER;
	private String JDBC_PASSWORD; 
	
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
    public Viz(Configuration cfg){
    	LOCAL_REPOSITORY_PATH 	= cfg.getLocalRepositoryPath();
        LOCAL_REPOSITORY_NAME 	= cfg.getLocalRepositoryName();
        LOCAL_REPOSITORY_OWNER  = cfg.getLocalRepositoryOwner();
    	JDBC_USER				= cfg.getJdbc_user();
    	JDBC_PASSWORD			= cfg.getJdbc_password(); 
    	createTableFlag 		= cfg.isCreateTableFlag();
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
    	//IF is new repository SET flag for create tables
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
	public String getLocalRepositoryPath() {
		return LOCAL_REPOSITORY_PATH;
	}

	/**
	 * 
	 * @return The local repository name
	 */
	public String getLocalRepositoryName() {
		return LOCAL_REPOSITORY_NAME;
	}

	/**
	 * 
	 * @return The local repository owner
	 */
	public String getLocalRepositoryOwner() {
		return LOCAL_REPOSITORY_OWNER;
	}

	@Override
	public String toString() {
		return "Viz [LOCAL_REPOSITORY_PATH=" + LOCAL_REPOSITORY_PATH
				+ ", LOCAL_REPOSITORY_NAME=" + LOCAL_REPOSITORY_NAME
				+ ", LOCAL_REPOSITORY_OWNER=" + LOCAL_REPOSITORY_OWNER
				+ ", JDBC_DRIVER=" + JDBC_DRIVER + ", JDBC_URL=" + JDBC_URL
				+ ", JDBC_USER=" + JDBC_USER + ", JDBC_PASSWORD="
				+ JDBC_PASSWORD + ", createTableFlag=" + createTableFlag + "]";
	}
	
}