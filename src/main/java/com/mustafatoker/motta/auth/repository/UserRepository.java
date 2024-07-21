package com.mustafatoker.motta.auth.repository;

import com.mustafatoker.motta.auth.domain.User;
import com.mustafatoker.motta.auth.domain.Username;
import org.springframework.data.couchbase.repository.CouchbaseRepository;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends CouchbaseRepository<User, String> {
    Optional<User> findByUsername(Username username);

    default User findByUsernameOrThrow(Username username) {
        return findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("User not found with"));
    }
}
