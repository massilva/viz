package org.viz.model;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class Configuration {
	private String remoteRepositoryLogin;
	private String remoteRepositoryPassword;
	private String localRepositoryPath;
	private String localRepositoryName;
	private String localRepositoryOwner;
	private boolean createTableFlag;
	private String jdbc_user;
	private String jdbc_password;

	public Configuration() {
		
	}
	
	/**
	 * 
	 * @param localRepositoryPath
	 * @param localRepositoryName
	 * @param localRepositoryOwner
	 * @param createTableFlag
	 * @param jdbc_user
	 * @param jdbc_password
	 */
	public Configuration(String localRepositoryPath,
			String localRepositoryName, String localRepositoryOwner,
			boolean createTableFlag, String jdbc_user, String jdbc_password) {
		this.localRepositoryPath = localRepositoryPath;
		this.localRepositoryName = localRepositoryName;
		this.localRepositoryOwner = localRepositoryOwner;
		this.createTableFlag = createTableFlag;
		this.jdbc_user = jdbc_user;
		this.jdbc_password = jdbc_password;
	}
	
	/**
	 * 
	 * @param localRepositoryPath
	 * @param localRepositoryName
	 * @param localRepositoryOwner
	 * @param createTableFlag
	 * @param jdbc_user
	 * @param jdbc_password
	 * @param remoteRepositoryLogin
	 * @param remoteRepositoryPassword
	 */	
	public Configuration(String remoteRepositoryLogin,
			String remoteRepositoryPassword, String localRepositoryPath,
			String localRepositoryName, String localRepositoryOwner,
			boolean createTableFlag, String jdbc_user, String jdbc_password) {
		this.remoteRepositoryLogin = remoteRepositoryLogin;
		this.remoteRepositoryPassword = remoteRepositoryPassword;
		this.localRepositoryPath = localRepositoryPath;
		this.localRepositoryName = localRepositoryName;
		this.localRepositoryOwner = localRepositoryOwner;
		this.createTableFlag = createTableFlag;
		this.jdbc_user = jdbc_user;
		this.jdbc_password = jdbc_password;
	}

	/**
	 * 
	 * @throws ParserConfigurationException
	 * @throws TransformerException
	 * @return create config.xml file
	 * 
	 */
	public void writeXmlFile() throws ParserConfigurationException, TransformerException{
		DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

		//root elements
		Document doc = docBuilder.newDocument();
		Element rootElement = doc.createElement("configuration");
		doc.appendChild(rootElement);
		
		/**** Begin RemoteRepository node ****/
		Element remoteRepository = doc.createElement("remoteRepository");
		rootElement.appendChild(remoteRepository);
		
		//remoteRepositoryLogin element;
		Element eRRL = doc.createElement("login");
		eRRL.appendChild(doc.createTextNode(this.remoteRepositoryLogin));
		remoteRepository.appendChild(eRRL);

		//remoteRepositoryPassword element;
		Element eRRP = doc.createElement("password");
		eRRP.appendChild(doc.createTextNode(this.remoteRepositoryPassword));
		remoteRepository.appendChild(eRRP);		
		/**** End RemoteRepository node ****/
		
		/**** Begin localRepository node ****/
		Element localRepository = doc.createElement("localRepository");
		rootElement.appendChild(localRepository);
		
		//localRepositoryPath element;
		Element eLRP = doc.createElement("path");
		eLRP.appendChild(doc.createTextNode(this.localRepositoryPath));
		localRepository.appendChild(eLRP);

		//localRepositoryName element;
		Element eLRN = doc.createElement("name");
		eLRN.appendChild(doc.createTextNode(this.localRepositoryName));
		localRepository.appendChild(eLRN);

		//localRepositoryOwner element;
		Element eLRO = doc.createElement("owner");
		eLRO.appendChild(doc.createTextNode(this.localRepositoryOwner));
		localRepository.appendChild(eLRO);

		//createTableFlag element;
		Element eCTF = doc.createElement("createTableFlag");
		eCTF.appendChild(doc.createTextNode(""+this.createTableFlag));
		localRepository.appendChild(eCTF);
		/**** End localRepository node ****/
		
		/**** Begin JDBC node ****/
		Element jdbc = doc.createElement("jdbc");
		rootElement.appendChild(jdbc);
		
		//jdbc_user element;
		Element eJU = doc.createElement("user");
		eJU.appendChild(doc.createTextNode(this.jdbc_user));
		jdbc.appendChild(eJU);

		//jdbc_password element;
		Element eJP = doc.createElement("password");
		eJP.appendChild(doc.createTextNode(this.jdbc_password));
		jdbc.appendChild(eJP);
		/**** End JDBC node ****/
		
		// write the content into xml file
		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transformer = transformerFactory.newTransformer();
		doc.getDocumentElement().normalize();
		DOMSource source = new DOMSource(doc);
		StreamResult result = new StreamResult(new File("config.xml"));
		//Output to console for testing
		//StreamResult result = new StreamResult(System.out);
		transformer.transform(source, result);
	}

	/**
	 * 
	 * @param XmlFile
	 * @return  
	 * @throws ParserConfigurationException
	 * @throws SAXException
	 * @throws IOException
	 */
	public Document readXmlFile(File XmlFile) throws ParserConfigurationException, SAXException, IOException{
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder;
		dBuilder = dbFactory.newDocumentBuilder();
		Document doc = dBuilder.parse(XmlFile);
		/*
		 * optional, but recommended
		 * read this - http://stackoverflow.com/questions/13786607/normalization-in-dom-parsing-with-java-how-does-it-work
		 */
		doc.getDocumentElement().normalize();
		return doc;
	}
	
	public String docToString(Document doc){
		String ln = "\n", tab="\t";
		String str = doc.getDocumentElement().getNodeName()+ln;
		NodeList nList = doc.getElementsByTagName("remoteRepository");
		str += tab + nList.item(0).getNodeName(); 
		for(int temp = 0; temp < nList.getLength(); temp++) {
			Node nNode = nList.item(temp);
			if (nNode.getNodeType() == Node.ELEMENT_NODE) {
				Element eElement = (Element) nNode;
				str +=ln
					+ tab + tab + "login          : " + eElement.getElementsByTagName("login").item(0).getTextContent()
					+ ln
					+ tab + tab + "password       : " + eElement.getElementsByTagName("password").item(0).getTextContent()
					+ ln;
			}
		}
		nList = doc.getElementsByTagName("localRepository");
		str += tab + nList.item(0).getNodeName(); 
		for(int temp = 0; temp < nList.getLength(); temp++) {
			Node nNode = nList.item(temp);
			if (nNode.getNodeType() == Node.ELEMENT_NODE) {
				Element eElement = (Element) nNode;
				str +=ln
					+ tab + tab + "path           : " + eElement.getElementsByTagName("path").item(0).getTextContent()
					+ ln
					+ tab + tab + "name           : " + eElement.getElementsByTagName("name").item(0).getTextContent()
				    + ln
					+ tab + tab + "owner          : " + eElement.getElementsByTagName("owner").item(0).getTextContent()
				    + ln
					+ tab + tab + "createTableFlag: " + eElement.getElementsByTagName("createTableFlag").item(0).getTextContent()
					+ ln;
			}
		}
		nList = doc.getElementsByTagName("jdbc");
		str += tab + nList.item(0).getNodeName(); 
		for(int temp = 0; temp < nList.getLength(); temp++) {
			Node nNode = nList.item(temp);
			if (nNode.getNodeType() == Node.ELEMENT_NODE) {
				Element eElement = (Element) nNode;
				str +=ln
					+ tab + tab + "user           : " + eElement.getElementsByTagName("user").item(0).getTextContent() 
				    + ln
					+ tab + tab + "password       : " + eElement.getElementsByTagName("password").item(0).getTextContent()
					+ ln; 
			}
		}
		return str;
	}
	
	// SET's and GET's 
	
	public String getLocalRepositoryPath() {
		return localRepositoryPath;
	}

	public String getRemoteRepositoryLogin() {
		return remoteRepositoryLogin;
	}

	public void setRemoteRepositoryLogin(String remoteRepositoryLogin) {
		this.remoteRepositoryLogin = remoteRepositoryLogin;
	}

	public String getRemoteRepositoryPassword() {
		return remoteRepositoryPassword;
	}

	public void setRemoteRepositoryPassword(String remoteRepositoryPassword) {
		this.remoteRepositoryPassword = remoteRepositoryPassword;
	}

	public void setLocalRepositoryPath(String localRepositoryPath) {
		this.localRepositoryPath = localRepositoryPath;
	}

	public String getLocalRepositoryName() {
		return localRepositoryName;
	}

	public void setLocalRepositoryName(String localRepositoryName) {
		this.localRepositoryName = localRepositoryName;
	}

	public String getLocalRepositoryOwner() {
		return localRepositoryOwner;
	}

	public void setLocalRepositoryOwner(String localRepositoryOwner) {
		this.localRepositoryOwner = localRepositoryOwner;
	}

	public boolean isCreateTableFlag() {
		return createTableFlag;
	}

	public void setCreateTableFlag(boolean createTableFlag) {
		this.createTableFlag = createTableFlag;
	}

	public String getJdbc_user() {
		return jdbc_user;
	}

	public void setJdbc_user(String jdbc_user) {
		this.jdbc_user = jdbc_user;
	}

	public String getJdbc_password() {
		return jdbc_password;
	}

	public void setJdbc_password(String jdbc_password) {
		this.jdbc_password = jdbc_password;
	}

}
