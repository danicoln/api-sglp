package com.sglp.sglp_api.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@ConfigurationProperties("sglp")
public class SglpApiProperty {

    private String origenPermitida = "http://localhost:4200";

    private final Seguranca seguranca = new Seguranca();

    public Seguranca getSeguranca() {
        return seguranca;
    }

    public String getOrigenPermitida() {
        return origenPermitida;
    }

    public void setOrigenPermitida(String origenPermitida) {
        this.origenPermitida = origenPermitida;
    }

    public static class Seguranca {

        private List<String> redirectsPermitidos;
        private String authServerUrl;

        public List<String> getRedirectsPermitidos() {
            return redirectsPermitidos;
        }

        public void setRedirectsPermitidos(List<String> redirectsPermitidos) {
            this.redirectsPermitidos = redirectsPermitidos;
        }

        public String getAuthServerUrl() {
            return authServerUrl;
        }

        public void setAuthServerUrl(String authServerUrl) {
            this.authServerUrl = authServerUrl;
        }
    }

    public static class Mail {

        private String host;

        private Integer port;

        private String username;

        private String password;

        public String getHost() {
            return host;
        }

        public void setHost(String host) {
            this.host = host;
        }

        public Integer getPort() {
            return port;
        }

        public void setPort(Integer port) {
            this.port = port;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }
    }
}
