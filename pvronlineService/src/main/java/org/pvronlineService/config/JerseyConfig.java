package org.pvronlineService.config;

import org.glassfish.jersey.server.ResourceConfig;
import org.pvronlineAPI.Activity;
import org.pvronlineService.activity.impl.ActivityImpl;
import org.springframework.stereotype.Component;

@Component
public class JerseyConfig extends ResourceConfig{

	public JerseyConfig() {
		register(ActivityImpl.class);
	}
}
