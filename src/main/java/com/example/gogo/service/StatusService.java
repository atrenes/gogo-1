package com.example.gogo.service;

import com.example.gogo.entity.Status;
import com.example.gogo.repository.StatusRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StatusService {
    private final StatusRepository statusRepository;

    Optional<Status> findByName(String name) {
        return this.statusRepository.findByName(name);
    }
}
