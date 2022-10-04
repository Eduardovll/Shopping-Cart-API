package me.ifood.shoppingcart.resource;

import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import me.ifood.shoppingcart.model.Cart;
import me.ifood.shoppingcart.model.Item;
import me.ifood.shoppingcart.resource.dto.ItemDto;
import me.ifood.shoppingcart.service.CartService;
import org.springframework.web.bind.annotation.*;

@Api(value = "/ifood-dev/cart", tags = {"Cart"})
@RestController
@RequestMapping("/ifood-dev/cart") //Definindo a URL referindo se a cart
@RequiredArgsConstructor
public class CartResource {
    private final CartService cartService;


    @PostMapping
    public Item incluirItemNaSacola(@RequestBody ItemDto itemDto) {
        return cartService.incluirItemNaSacola(itemDto);
    }

    @GetMapping("/{id}")
    public Cart verCart(@PathVariable("id") Long id) {
        return cartService.verCart(id);
    }

    @PatchMapping("/fecharCart/{cartId}")
    public Cart fecharCart(@PathVariable("cartId") Long cartId, @RequestParam("formaPagamento") int formaPagamento){
        return cartService.fecharCart(cartId, formaPagamento);
    }
}
