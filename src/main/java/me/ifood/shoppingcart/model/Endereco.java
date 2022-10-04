package me.ifood.shoppingcart.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import javax.persistence.Entity;

@AllArgsConstructor // Cria os Construtor com argumentos
@Builder // Criar objeto de forma simples
@Data // Cria os Getters e Setters
@Embeddable // Esta class nao vai ser uma tabela no BD
@NoArgsConstructor // Cria Construtor sem argumentos
public class Endereco {
    private String cep;
    private String complemento;
}
