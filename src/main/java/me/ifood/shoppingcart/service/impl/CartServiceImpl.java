package me.ifood.shoppingcart.service.impl;

import lombok.RequiredArgsConstructor;
import me.ifood.shoppingcart.enumeration.FormaPagamento;
import me.ifood.shoppingcart.model.Cart;
import me.ifood.shoppingcart.model.Item;
import me.ifood.shoppingcart.model.Restaurante;
import me.ifood.shoppingcart.repository.CartRepository;
import me.ifood.shoppingcart.repository.ItemRepository;
import me.ifood.shoppingcart.repository.ProdutoRepository;
import me.ifood.shoppingcart.resource.dto.ItemDto;
import me.ifood.shoppingcart.service.CartService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {
    private final CartRepository cartRepository;
    private final ProdutoRepository produtoRepository;
    private final ItemRepository itemRepository;
    @Override
    public Item incluirItemNaSacola(ItemDto itemDto) {
        Cart cart = verCart(itemDto.getCartId());

        if (cart.isFechada()) {
            throw new RuntimeException("Este carrinho esta finalizado.");
        }

        Item itemParaSerInserido = Item.builder()
                .quantidade(itemDto.getQuantidade())
                .cart(cart)
                .produto(produtoRepository.findById(itemDto.getProdutoId()).orElseThrow(
                        () -> {
                            throw new RuntimeException("Produto não encontrado!");
                        }
                ))
                .build();

        List<Item> itensDoCart = cart.getItens();
        if (itensDoCart.isEmpty()) {
            itensDoCart.add(itemParaSerInserido);
        } else {
            Restaurante restauranteAtual = itensDoCart.get(0).getProduto().getRestaurante();
            Restaurante restauranteDoItemParaAdicionar = itemParaSerInserido.getProduto().getRestaurante();

            if(restauranteAtual.equals(restauranteDoItemParaAdicionar)) {
                itensDoCart.add(itemParaSerInserido);
            } else{
                throw new RuntimeException("Não é possivel adicionar produtos de restaurantes diferentes. Finalize o carrinho ou esvazie!");
            }
        }

        List<Double> valorItens = new ArrayList<>();
        for(Item itemDoCart : itensDoCart){
            double valorTotalItem =
                    itemDoCart.getProduto().getValorUnitario() * itemDoCart.getQuantidade();
            valorItens.add(valorTotalItem);
        }

        double valorTotalCart = valorItens.stream()
                .mapToDouble(valorTotalDeCadaItem -> valorTotalDeCadaItem)
                .sum();

        cart.setValorTotal(valorTotalCart);
        cartRepository.save(cart);

        return itemParaSerInserido;
    }

    @Override
    public Cart verCart(Long id) {
        return cartRepository.findById(id).orElseThrow(
                () -> {
                    throw new RuntimeException(("Carrinho não encontrado!"));
                }
        );
    }

    @Override
    public Cart fecharCart(Long id, int numeroFormaPagamento) {
        Cart cart = verCart(id);

        if (cart.getItens().isEmpty()) {
            throw new RuntimeException("Inclua itens na sacola!");
        }

        FormaPagamento formaPagamento =
                numeroFormaPagamento == 0 ? FormaPagamento.DINHEIRO : FormaPagamento.MAQUINETA;
        cart.setFormaPagamento(formaPagamento);
        cart.setFechada(true);

        return cartRepository.save(cart);
    }
}
