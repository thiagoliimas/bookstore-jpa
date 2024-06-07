package com.bookstore.jpa.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "TB_BOOK")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class BookModel  implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", nullable = false)
    private UUID id;

    @Column(nullable = false, unique = true)
    private String title;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)

    @JoinColumn(name = "publisher_id")
    @ManyToOne(fetch = FetchType.LAZY) //Quando for buscar pelos livros não traz automaticamente as informações da editora
    private PublisherModel publisher;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @ManyToMany(fetch = FetchType.LAZY) //Quando for buscar pelos livros não traz automaticamente as informações dos autores
    @JoinTable( //Cria uma tabela de associação entre authores e livros
            name = "tb_book_author", //nome da tabela
            joinColumns = @JoinColumn(name = "book_id"), //Um dos campos
            inverseJoinColumns = @JoinColumn(name = "author_id")) //o outro campo, sendo que um vira a chave estrangeira do outro
    private Set<AuthorModel> authors = new HashSet<>();

    @OneToOne(mappedBy = "book", cascade = CascadeType.ALL) //COnsidera na transação toda a cascata de ações desse relacionamento, exemplo ao deletar um livro ele automaticamente deleta o review associado a esse book (TEM QUE SE TER BASTANTE CUIDADO COM ESSA DECLARAÇÃO)
    ReviewModel review;
}
