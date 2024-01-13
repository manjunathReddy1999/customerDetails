package com.example.customer.entityModel;

import com.example.customer.enumeration.CustomerOccupation;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
/**
 * the persistent class for the CUSTOMER_DETAILS database table
 * @author manjunathreddy1999
 */
@Table(name = "CUSTOMER_DETAILS", uniqueConstraints = @UniqueConstraint(columnNames = {"occupation", "dob","customerGroup"}))
public class CustomerDetails implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID", nullable = false, updatable = false)
    private Long id;

    @Column(name = "NAME", nullable = false)
    private String name;

    @Column(name = "EMAIL_ID", unique = true, nullable = false)
    private String email;

    @Column(name = "DOB", nullable = false)
    private String dob;

    @Column(name = "OCCUPATION", nullable = false)
    @Enumerated(EnumType.STRING)
    private CustomerOccupation occupation;

    @Column(name = "CUSTOMER_GROUP", nullable = false)
    private String customerGroup;

    @JsonFormat(pattern = "dd/MM/yyyy")
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    @Column(name = "CREATED_DATE")
    private LocalDate createdDate;

    @Column(name = "CREATED_BY")
    private String createdBy;
}
