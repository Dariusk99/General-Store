package org.generalstore.modules.user.service.domain;

import jakarta.persistence.EntityExistsException;
import lombok.RequiredArgsConstructor;
import org.generalstore.modules.user.entity.User;
import org.generalstore.modules.user.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
class UserDomainServiceImpl implements UserDomainService {

    private final UserRepository repository;

    @Override
    public User register(User user) {
        if (repository.existsByEmail(user.getEmail())) {
            throw new EntityExistsException("User already exist with email: " + user.getEmail());
        }
        return repository.save(user);
    }
}
