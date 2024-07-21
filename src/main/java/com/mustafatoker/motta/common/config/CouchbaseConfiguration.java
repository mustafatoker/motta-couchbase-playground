package com.mustafatoker.motta.common.config;

import com.couchbase.client.java.Bucket;
import com.couchbase.client.java.Cluster;
import com.couchbase.client.java.ClusterOptions;
import com.couchbase.client.java.env.ClusterEnvironment;
import com.mustafatoker.motta.auth.converter.PasswordToStringConverter;
import com.mustafatoker.motta.auth.converter.StringToPasswordConverter;
import com.mustafatoker.motta.auth.converter.StringToUsernameConverter;
import com.mustafatoker.motta.auth.converter.UsernameToStringConverter;
import com.mustafatoker.motta.common.config.properties.CouchbaseProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.couchbase.CouchbaseClientFactory;
import org.springframework.data.couchbase.config.AbstractCouchbaseConfiguration;
import org.springframework.data.couchbase.core.CouchbaseTemplate;
import org.springframework.data.couchbase.core.convert.CouchbaseConverter;
import org.springframework.data.couchbase.core.convert.CouchbaseCustomConversions;
import org.springframework.data.couchbase.core.mapping.event.ValidatingCouchbaseEventListener;
import org.springframework.data.couchbase.repository.config.EnableCouchbaseRepositories;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import java.util.Arrays;

@Configuration
@EnableCouchbaseRepositories(basePackages = {"com.mustafatoker.motta.auth.repository", "com.mustafatoker.motta.todo.repository"})
@RequiredArgsConstructor
public class CouchbaseConfiguration extends AbstractCouchbaseConfiguration {
    private final CouchbaseProperties couchbaseProperties;

    @Override
    public String getConnectionString() {
        return couchbaseProperties.connectionString();
    }

    @Override
    public String getUserName() {
        return couchbaseProperties.username();
    }

    @Override
    public String getPassword() {
        return couchbaseProperties.password();
    }

    @Override
    public String getBucketName() {
        return "motto_app"; // TODO: Change this to a property
    }

    @Bean
    public Cluster couchbaseCluster() {
        return Cluster.connect(getConnectionString(), ClusterOptions.clusterOptions(getUserName(), getPassword())
                .environment(ClusterEnvironment.builder()
                        .build()));
    }

    @Bean
    public Bucket couchbaseBucket(Cluster couchbaseCluster) {
        return couchbaseCluster.bucket(getBucketName());
    }

    @Bean
    public CouchbaseTemplate couchbaseTemplate(CouchbaseClientFactory ccf,
                                               CouchbaseConverter couchbaseConverter) {
        return new CouchbaseTemplate(ccf, couchbaseConverter);
    }

    @Bean
    public LocalValidatorFactoryBean localValidatorFactoryBean() {
        return new LocalValidatorFactoryBean();
    }

    @Bean
    public ValidatingCouchbaseEventListener validatingCouchbaseEventListener(LocalValidatorFactoryBean localValidatorFactoryBean) {
        return new ValidatingCouchbaseEventListener(localValidatorFactoryBean);
    }

    @Override
    public CouchbaseCustomConversions customConversions() {
        var converters = Arrays.asList(
                new UsernameToStringConverter(),
                new StringToUsernameConverter(),
                new StringToPasswordConverter(),
                new PasswordToStringConverter()
        );

        return new CouchbaseCustomConversions(converters);
    }
}
