package net.ebuy.apiapp.model;
/**
 * @author Donald Trieu
 *
 */
public class TokenModel {

	private String user_name;
	
	private String[] authorities;
	
	private String client_id;
	
	private String[] scope;
	
	private String jti;
	
	private long exp;

	/**
	 * @return the authorities
	 */
	public String[] getAuthorities() {
		return authorities;
	}

	/**
	 * @param authorities the authorities to set
	 */
	public void setAuthorities(String[] authorities) {
		this.authorities = authorities;
	}

	/**
	 * @return the client_id
	 */
	public String getClient_id() {
		return client_id;
	}

	/**
	 * @param client_id the client_id to set
	 */
	public void setClient_id(String client_id) {
		this.client_id = client_id;
	}

	/**
	 * @return the scope
	 */
	public String[] getScope() {
		return scope;
	}

	/**
	 * @param scope the scope to set
	 */
	public void setScope(String[] scope) {
		this.scope = scope;
	}

	/**
	 * @return the jti
	 */
	public String getJti() {
		return jti;
	}

	/**
	 * @param jti the jti to set
	 */
	public void setJti(String jti) {
		this.jti = jti;
	}

	/**
	 * @return the exp
	 */
	public long getExp() {
		return exp;
	}

	/**
	 * @param exp the exp to set
	 */
	public void setExp(long exp) {
		this.exp = exp;
	}

	/**
	 * @return the user_name
	 */
	public String getUser_name() {
		return user_name;
	}

	/**
	 * @param user_name the user_name to set
	 */
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
}
