package com.mustafatoker.motta.common.config.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "spring.couchbase")
public record CouchbaseProperties(String connectionString, String username, String password) {
}