package com.slendershield.accounts.repository;

import com.slendershield.accounts.entity.Customer;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long>
{
    Optional<Customer> findByMobileNumber(String mobileNumber);

    @Transactional
    @Modifying
    void deleteByCustomerId(Long customerId);
}
