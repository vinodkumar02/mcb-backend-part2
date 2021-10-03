package com.mcb.part2.security.user;

import java.io.Serializable;
import java.util.Set;
import java.util.UUID;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
@Scope(value="request",proxyMode= ScopedProxyMode.TARGET_CLASS)
@Component
public class Oauth2UserDetails implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3366152825082796780L;
	@JsonProperty("access_token")
	private String accessToken;
	@JsonProperty("token_type")
    private String tokenType;
	@JsonProperty("refresh_token")
    private String refreshToken;
	@JsonProperty("client_id")
	private String clientId;
    private Set<String> scope;
    private UUID userTypeId;
    private String agentId;
    private String role;
    private String agentType;
    private String userType;
    private String agentName;
    private UUID appId;
    private String id;
    private UUID roleId;
    private String applicationName;
	private String userId;
	
	public UUID getUserUUId()
	{
		return UUID.fromString(id);
	}
}
