package me.ifood.shoppingcart.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import me.ifood.shoppingcart.enumeration.FormaPagamento;
import org.hibernate.engine.internal.Cascade;

import javax.persistence.*;
import java.util.List;

@AllArgsConstructor // Cria os Construtor com argumentos
@Builder
@Data // cria os Getters e Setters
@Entity // Para criar a tabela no DB
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@NoArgsConstructor // Cria Construtor sem argumentos
public class Cart {
    @Id // Primary Key
    @GeneratedValue(strategy = GenerationType.AUTO) //Qual forma sera gerado os @Id, auto increment
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY, optional = false) // relacionamento entre tabelas, um cliente pode ter uma sacola
    @JsonIgnore // para ignorar caso apareca algm erro no console
    private Cliente cliente;
    @OneToMany(cascade = CascadeType.ALL)
    private List<Item> itens;
    private Double valorTotal;

    @Enumerated // declara a class enumeration
    private FormaPagamento formaPagamento;
    private boolean fechada;


}
