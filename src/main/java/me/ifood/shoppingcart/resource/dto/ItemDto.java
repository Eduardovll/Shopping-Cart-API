package me.ifood.shoppingcart.resource.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;

@AllArgsConstructor // Cria os Construtor com argumentos
@Builder // Criar objeto de forma simples
@Data // Cria os Getters e Setters
@Embeddable // Esta class nao vai ser uma tabela no BD
@NoArgsConstructor // Cria Construtor sem argumentos

public class ItemDto {
    private Long produtoId;
    private int quantidade;
    private Long cartId;

}
