package com.example.kaffeehaus.orders.control;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.UriBuilder;

public final class LinkBuilder {

    public static UriBuilder baseUriBuilder(HttpServletRequest request) {
        String proto = request.getHeader("X-Forwarded-Proto");
        String host = request.getHeader("X-Forwarded-Host");
        String port = request.getHeader("X-Forwarded-Port");

        return UriBuilder.fromUri("/")
                .scheme(proto != null ? proto : request.getScheme())
                .host(host != null ? host : request.getServerName())
                .port(port != null ? Integer.parseInt(port) : request.getServerPort());
    }

}
