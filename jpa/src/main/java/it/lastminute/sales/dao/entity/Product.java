package it.lastminute.sales.dao.entity;

import java.io.Serializable;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Type;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "product")
@Getter
@Setter
@ToString
public class Product implements Serializable {

    private static final long serialVersionUID = 4221927019168754906L;

    @Id
    @GeneratedValue
    @Column(name = "id", unique = true, nullable = false)
    @Type(type = "uuid-char")
    private UUID id;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "price", nullable = false)
    private Long price;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "product_type_id", nullable = false)
    private ProductTypeLkp productTypeLkp;

    @Column(name = "imported", nullable = false, columnDefinition = "int")
    private Boolean imported;

    @Transient
    private Long priceTaxed;
}
