package net.ebuy.apiapp.config;

import org.springframework.beans.factory.annotation.Value;

import org.springframework.stereotype.Component;
 
/**
 * @author LongNguyen
 *
 */

@Component
public class ApplicationProperties {
	
	@Value("${security.oauth2-endpoint}")
	private String oauthEndpoint;

	@Value("${adamapp.api-domain}")
	private String apiDomain;
	
	@Value("${security.default-password-external-login}")
	private String externalLoginPassword;
	
	@Value("${resource.path}")
	private String resourcePath;
	
    public String getOauthEndpoint() {
    		return oauthEndpoint;
    }

	/**
	 * @return the apiDomain
	 */
	public String getApiDomain() {
		return apiDomain;
	}

	/**
	 * @param apiDomain the apiDomain to set
	 */
	public void setApiDomain(String apiDomain) {
		this.apiDomain = apiDomain;
	}

	/**
	 * @return the externalLoginPassword
	 */
	public String getExternalLoginPassword() {
		return externalLoginPassword;
	}

	/**
	 * @return the resourcePath
	 */
	public String getResourcePath() {
		return resourcePath;
	}

	/**
	 * @param resourcePath the resourcePath to set
	 */
	public void setResourcePath(String resourcePath) {
		this.resourcePath = resourcePath;
	}
}