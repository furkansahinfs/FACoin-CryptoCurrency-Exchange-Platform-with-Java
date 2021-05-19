package fileio.parser;

import java.io.IOException;
import java.io.StringReader;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.json.JSONException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import exception.FileReadException;
import factory.UserFactory;
import fileio.repository.IRepository;
import fileio.repository.UserRepository;
import model.User;

public class UserParser {
	
	private UserFactory userFactory;
	/**
	 * The UserParser parses the gotten contract file content and creates user
	 * objects
	 */
	protected UserParser() {
		userFactory = new UserFactory();
	}

	/**
	 * The function parses gotten file content and returns the user container which
	 * holds created users
	 * 
	 * @param fileAll = user file content
	 * @param creator = creator object
	 * @param outfits = outfit container which holds all outfits
	 * @return User Container
	 * @throws ParserConfigurationException 
	 * @throws IOException 
	 * @throws SAXException 
	 * @throws FileReadException 
	 * @throws Exception 
	 * @throws XMLException
	 */
	protected void parseUsers(String fileAll) throws SAXException, IOException, ParserConfigurationException, FileReadException 
			 {
		
		Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder()
				.parse(new InputSource(new StringReader(fileAll)));
		IRepository<User> userRepository = new UserRepository();// initialise user container
		parse(doc, userRepository);// parse all to users
	}

	/**
	 * The function parses gotten Document object and creates users. After creating
	 * each user, add the user to the users
	 * 
	 * @param doc   = Document object of file content
	 * @param users = contract container
	 * @throws FileReadException 
	 * @throws SAXException 
	 * @throws JSONException
	 */
	private void parse(Document doc, IRepository<User> userRepository) throws FileReadException, SAXException  {

		doc.getDocumentElement().normalize();
		if (!doc.getDocumentElement().getNodeName().equals("users"))
			throw new FileReadException("File is not in initial format");
		// get user nodes of xml.
		NodeList nodeListOfUsers = doc.getElementsByTagName("user");

		// travel to each user
		for (int i = 0; i < nodeListOfUsers.getLength(); i++) {
			// get user node
			Node userNode = nodeListOfUsers.item(i);
			if (userNode.getNodeType() == Node.ELEMENT_NODE) {
				Element userNodeElement = (Element) userNode;
				// create user invoking createUser()
				User user = createUser(userNodeElement);
				if(user != null)
				{
					userRepository.addEntity(user); // add created user to the users
				}
				else
				{
					throw new SAXException("FileFormatError for users.xml");
				}
				
			}
		}
	}

	/**
	 * The function creates a user according to userNodeElement values
	 * 
	 * @param userNodeElement = user node element
	 * @return User
	 * @throws SAXException
	 */
	private User createUser(Element userNodeElement) throws SAXException {
		String userId, userName, cryptoWalletId, bankWalletId, password, favorites = "";
		
		// get values
		userId = userNodeElement.getAttribute("id");
		userName = getTagValue("username", userNodeElement);
		password = getTagValue("password", userNodeElement);
		cryptoWalletId = getTagValue("crypto_wallet", userNodeElement);
		bankWalletId = getTagValue("bank_wallet", userNodeElement);	
		favorites = getTagValue("favorites", userNodeElement);

		User user = null;
		user = userFactory.createUser(userId, userName, password, cryptoWalletId, bankWalletId, favorites);
		return user;
	}
	

	// gets value by tag name
	private static String getTagValue(String tag, Element element) {
		try {
			return element.getElementsByTagName(tag).item(0).getTextContent();
		} catch (Exception e) {
			return "";
		}
	}

}
