package com.bookstore.jpa.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "TB_REVIEW")
@Table(name = "TB_REVIEW")
@Data
public class ReviewModel   implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", nullable = false)
    private UUID id;

    @Column(nullable = false)
    //    @Lob //Usada para informar ao banco que essa coluna receberá uma grande quantidade de caracteres, superior aos 255 fornecidos pelo String. Caso dê erro o seguinte código deve ser executado no banco: ALTER TABLE tb_review ALTER COLUMN comment TYPE oid USING comment::oid;
    private String comment;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @JoinColumn(name = "book_id", nullable = false)
    @OneToOne
    private BookModel book;

}
