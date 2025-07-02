package org.generalstore.modules.cart.core.entity;

import jakarta.persistence.*;
import lombok.*;
import org.generalstore.modules.cart.cartitem.entity.CartItem;
import org.generalstore.modules.user.entity.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Entity
@Table(name = "carts")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(fetch = FetchType.LAZY)
    private List<CartItem> items = new ArrayList<>();

    public void mergeCart(Cart cart) {
        System.out.println("scalanie koszyków");
    }

    public void addItem(CartItem cartItem) {
        Optional<CartItem> existItem = getItems().stream()
                .filter(item -> item.getProduct().getName().equals(cartItem.getProduct().getName()))
                .findFirst();

        if (existItem.isPresent()) {
            int actualQuantity = existItem.get().getQuantity();
            existItem.get().setQuantity(actualQuantity + 1);
        } else {
            getItems().add(cartItem);
        }
    }
}
