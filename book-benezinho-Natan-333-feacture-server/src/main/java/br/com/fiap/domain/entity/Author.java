package br.com.fiap.domain.entity;


import jakarta.persistence.*;

import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "TB_AUTHOR")
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_AUTHOR")
    @Column(name = "ID_AUTHOR")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "pessoa", referencedColumnName = "ID_PESSOA", foreignKey = @ForeignKey(name = "FK_PESSOA_AUTHOR"))
    private PessoaFisica pessoa;

    @ManyToMany(mappedBy = "writers", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @OrderBy("name asc")
    private Set<Book> obras;

    public Author() {
        obras = new LinkedHashSet<>();
    }

    public Author(PessoaFisica pessoa) {
        this.pessoa = pessoa;
        obras = new LinkedHashSet<>();
    }

    public Author(Long id, PessoaFisica pessoa, Set<Book> obras) {
        this.id = id;
        this.pessoa = pessoa;
        this.obras = Objects.nonNull( obras ) ? obras : new LinkedHashSet<>();
    }


    public Author addObra(Book b) {
        obras.add( b );
        return this;
    }

    public Author removeObra(Book b) {
        obras.remove( b );
        return this;
    }

    public Set<Book> getObras() {
        return Collections.unmodifiableSet( obras );
    }

    public Long getId() {
        return id;
    }

    public Author setId(Long id) {
        this.id = id;
        return this;
    }


    public PessoaFisica getPessoa() {
        return pessoa;
    }

    public Author setPessoa(PessoaFisica pessoa) {
        this.pessoa = pessoa;
        return this;
    }
}
