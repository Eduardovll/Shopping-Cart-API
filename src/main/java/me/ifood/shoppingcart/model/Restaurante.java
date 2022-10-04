package me.ifood.shoppingcart.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@AllArgsConstructor // Cria os Construtor com argumentos
@Builder // Criar objeto de forma simples
@Data // Cria os Getters e Setters
@Entity // Para criar a tabela no DB
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@NoArgsConstructor // Cria Construtor sem argumentos
public class Restaurante {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String nome;
    @OneToMany(cascade = CascadeType.ALL)
    private List<Produto> produto;
    @Embedded //
    private Endereco endereco;
}
