package org.disruptiontables.rest;

import org.glassfish.jersey.server.ResourceConfig;
import org.secnod.shiro.jaxrs.ShiroExceptionMapper;
import org.secnod.shiro.jersey.AuthorizationFilterFeature;
import org.secnod.shiro.jersey.ShiroResourceFilterFactory;
import org.secnod.shiro.jersey.AuthInjectableProvider;
import org.secnod.shiro.jersey.AuthInjectionBinder;
import org.secnod.shiro.jersey.SubjectFactory;
import org.secnod.shiro.jersey.SubjectInjectableProvider;

/**
 * An example JAX-RS application using Apache Shiro.
 */
public class TflAplication extends ResourceConfig {

    public ExampleApplication() {
    	 register(new ShiroResourceFilterFactory());
         register(new SubjectInjectableProvider());
         register(new AuthInjectableProvider());
    }
}