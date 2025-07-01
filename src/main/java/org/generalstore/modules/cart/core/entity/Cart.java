package org.generalstore.modules.cart.core.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.generalstore.modules.cart.cartitem.entity.CartItem;
import org.generalstore.modules.user.entity.User;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "carts")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(fetch = FetchType.LAZY)
    private List<CartItem> items;

    public void mergeCart(Cart cart) {
        System.out.println("scalanie koszyków");
    }
}
