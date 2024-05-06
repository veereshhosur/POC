package com.lbg.integrator.api.repository;

import com.lbg.integrator.api.entity.IntegratorRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IntegratorRequestRepository extends JpaRepository<IntegratorRequest, String> {
}
