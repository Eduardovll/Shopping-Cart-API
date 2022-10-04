package me.ifood.shoppingcart.service;

import me.ifood.shoppingcart.model.Cart;
import me.ifood.shoppingcart.model.Item;
import me.ifood.shoppingcart.resource.dto.ItemDto;

public interface CartService {
    Item incluirItemNaSacola(ItemDto itemDto);
    Cart verCart(Long id);
    Cart fecharCart(Long id, int formaPagamento);
}
