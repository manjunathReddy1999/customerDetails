package com.example.customer.repository;

import com.example.customer.entityModel.CustomerDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * customerDetailsRepository - Implements JpaRepository Interface for generic CRUD
 * Operations on CUSTOMER_DETAILS Table
 * @author manjunathreddy1999
 */
@Repository
public interface CustomerDetailsRepository extends JpaRepository<CustomerDetails, Long> {

    @Query(value = "Select EMAIL_ID from CUSTOMER_DETAILS Where email_id =:email", nativeQuery = true)
    public List<String> getRecordByEmailId(@Param("email") String email);
}

