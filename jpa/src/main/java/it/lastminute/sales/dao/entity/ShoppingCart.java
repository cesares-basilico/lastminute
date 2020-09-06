package it.lastminute.sales.dao.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Type;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "shopping_cart")
@Getter
@Setter
@ToString
public class ShoppingCart implements Serializable {

    private static final long serialVersionUID = 4221927019168754906L;

    @Id
    @GeneratedValue
    @Column(name = "id", unique = true, nullable = false)
    @Type(type = "uuid-char")
    private UUID id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "taxes", nullable = false)
    private Long taxes;

    @OneToMany(mappedBy = "shoppingCart", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<ShoppingCartLine> shoppingCartList = new HashSet<ShoppingCartLine>();

    @Column(name = "total", nullable = false)
    private Long total;

    @Transient
    public void increaseTotal(Long price) {
        total += price;
    }

    @Transient
    public void increaseTaxes(Long tax) {
        taxes += tax;
    }
}
