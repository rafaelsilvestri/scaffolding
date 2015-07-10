package com.mycompany.customer.model;

import java.io.Serializable;
import java.util.*;
import javax.persistence.*;
import org.hibernate.annotations.GenericGenerator;

/**
 * <pre>
 * {
 *    "name": "Rafael",
 *    "address": {
 *	"address": "Rua xxx",
 *	"number": "123",
 *	"complement": "Prox. ao xxx",
 *	"neighborhood": "Centro",
 *	"city": "Criciuma",
 *	"state": "SC",
 *	"country": "Brasil",
 *	"zipCode": "88802-000"
 *    }
 * }
 * </pre>
 *
 * @author Rafael Cechinel Silvestri
 */
@Entity
@Table(name = "CUSTOMER", schema = "PUBLIC")
public class Customer implements Serializable {

    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(name = "CUSTOMER_PK")
    @Id
    private UUID id;

    @Column(name = "CREATE_DATE", nullable = false, insertable = true, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;

    @Column(name = "LAST_MODIFIED_DATE", insertable = false, updatable = true)
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastModifiedData;

    @Column(name = "NAME")
    private String name;

    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "ADDRESS_ID")
    private Address address;

    public Customer() {
    }

    public UUID getId() {
        return id;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public Date getLastModifiedData() {
        return lastModifiedData;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 67 * hash + Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Customer other = (Customer) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    @PrePersist
    private void prePersist() {
        this.createdDate = new Date();
    }

    @PreUpdate
    private void preUpdate() {
        this.lastModifiedData = new Date();
    }

}
