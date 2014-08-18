package com.rms.interceptor;

import com.sun.jersey.spi.container.ContainerRequest;
import com.sun.jersey.spi.container.ContainerResponse;
import com.sun.jersey.spi.container.ContainerResponseFilter;

import javax.ws.rs.core.Response;

//TODO: Register and throw proper exception (create exception mapper first)

public class ResponseFilter implements ContainerResponseFilter {

    final int INTERNAL_SERVER_ERROR = Response.Status.INTERNAL_SERVER_ERROR.getStatusCode();
    final int OK                    = Response.Status.OK.getStatusCode();
    final int CREATED               = Response.Status.CREATED.getStatusCode();
    final int NOT_FOUND             = Response.Status.NOT_FOUND.getStatusCode();
    final int ACCEPTED              = Response.Status.ACCEPTED.getStatusCode();
    final int NO_CONTENT            = Response.Status.NO_CONTENT.getStatusCode();
    final int NOT_MODIFIED          = Response.Status.NOT_MODIFIED.getStatusCode();
    final int UNAUTHORIZED          = Response.Status.UNAUTHORIZED.getStatusCode();

    @Override
    public ContainerResponse filter(ContainerRequest containerRequest, ContainerResponse containerResponse) {

        PilotInterceptor.MethodType methodType = PilotInterceptor.getMethodType(containerRequest);
        Response response = containerResponse.getResponse();
        int responseStatus = response.getStatus();

        if(responseStatus == INTERNAL_SERVER_ERROR || responseStatus == UNAUTHORIZED || responseStatus == NOT_FOUND) {
            containerResponse.setResponse(response);
            return containerResponse;
        }

        Object entity = containerResponse.getOriginalEntity();

        Response.ResponseBuilder responseBuilder = response.fromResponse(response);

        switch (methodType) {

            case NONE:
                containerResponse.setStatus(INTERNAL_SERVER_ERROR);
            case GET:
                break;
            case PUT:
                break;
            case POST:
                break;
            case DELETE:
                break;
        }

        return containerResponse;
    }
}
