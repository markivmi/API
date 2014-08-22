package com.rms.filter;

import com.rms.auth.BasicAuthenticator;
import com.sun.jersey.core.util.Base64;
import com.sun.jersey.spi.container.ContainerRequest;
import com.sun.jersey.spi.container.ContainerRequestFilter;
import io.dropwizard.auth.AuthenticationException;
import io.dropwizard.auth.basic.BasicCredentials;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

public class RequestFilter implements ContainerRequestFilter {

    static enum MethodType {
        NONE,
        GET,
        PUT,
        POST,
        DELETE
    }

    static MethodType getMethodType(ContainerRequest containerRequest) {
        String methodType = containerRequest.getMethod();
        MethodType output = MethodType.NONE;

        if (methodType.equalsIgnoreCase("get")) {
            output = MethodType.GET;
        } else if (methodType.equalsIgnoreCase("put")) {
            output = MethodType.PUT;
        } else if (methodType.equalsIgnoreCase("post")) {
            output = MethodType.POST;
        } else if (methodType.equalsIgnoreCase("delete")) {
            output = MethodType.DELETE;
        }

        return output;
    }


    @Override
    public ContainerRequest filter(ContainerRequest containerRequest) {
        try {
            authenticate(containerRequest);
        } catch (AuthenticationException e) {
            e.printStackTrace();
            return null;
        }
//        processRequest(containerRequest, getMethodType(containerRequest));

        return containerRequest;
    }

    private void authenticate(ContainerRequest containerRequest) throws AuthenticationException {
        SecurityContext securityContext = containerRequest.getSecurityContext();
        if (securityContext == null) {
            throw new WebApplicationException(Response.Status.UNAUTHORIZED);
        }

        String authToken = containerRequest.getHeaderValue(ContainerRequest.AUTHORIZATION);
        if (authToken == null || authToken.isEmpty()) {
            throw new WebApplicationException(Response.Status.NOT_ACCEPTABLE);
        }

        if (authToken.startsWith("Basic")) {
            authToken = authToken.substring("Basic".length()).trim();
            String[] values = new String(Base64.base64Decode(authToken)).split(":");
            BasicCredentials basicCredentials = new BasicCredentials(values[0], values[1]);
            com.google.common.base.Optional<Boolean> authenticated = new BasicAuthenticator().authenticate
                    (basicCredentials);

            if (!authenticated.isPresent() || !authenticated.get()) {
                throw new WebApplicationException(Response.Status.UNAUTHORIZED);
            }
            return;
        }
    }

//    private void processRequest(ContainerRequest containerRequest, MethodType methodType) {
//
//        switch (methodType) {
//            case GET:
//                processGet(containerRequest);
//                break;
//            case PUT:
//                break;
//            case POST:
//                break;
//            case DELETE:
//                break;
//            default:
//                return;
//        }
//    }
//
//    private void processGet(ContainerRequest containerRequest) {
//        MultivaluedMap<String, String> parameters = containerRequest.getQueryParameters();
//        List<PathSegment> pathSegments = containerRequest.getPathSegments(true);
//    }
//
//    private void checkValidInput(Person entity) {
//
//    }
}
