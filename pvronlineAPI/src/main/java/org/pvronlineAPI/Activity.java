package org.pvronlineAPI;

import javax.ws.rs.core.Response;

import org.pvronlineModel.AuthBean;
import org.pvronlineModel.MailBean;
import org.pvronlineModel.UserBean;
/**
 * 
 * @author ankit
 *
 */
public interface Activity {
	/**
	 * 
	 * @param signup
	 * @return
	 */
	public Response getSignUp(AuthBean signup);
	/**
	 * 
	 * @param login
	 * @return
	 */
	public Response getLogin(AuthBean login);
	/**
	 * 
	 * @param user
	 * @return
	 */
	public Response addUser(UserBean user);
	/**
	 * 
	 * @param mail
	 * @return
	 */
	public Response sendMail(MailBean mail);
	/**
	 * 
	 * @return
	 */
	public Response getAllUsers();
	/**
	 * 
	 * @return
	 */
	public Response getPvrDetails();
	/**
	 * 
	 * @param movieCode
	 * @return
	 */
	public Response getMailIds(int movieCode);
	
}
