package it.lastminute.sales.dao.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "product_type_lkp")
@Getter
@Setter
@ToString
public class ProductTypeLkp implements Serializable {

    private static final long serialVersionUID = 4221927019168754906L;

    @Id
    @GeneratedValue
    @Column(name = "id", unique = true, nullable = false)
    private Integer id;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "key", nullable = false)
    private String key;

}
