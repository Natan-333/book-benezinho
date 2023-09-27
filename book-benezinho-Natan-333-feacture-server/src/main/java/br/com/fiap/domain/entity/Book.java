package br.com.fiap.domain.entity;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "TB_BOOK", uniqueConstraints = {
        @UniqueConstraint(name = "UK_BOOK_ISBN", columnNames = "ISBN_BOOK")
})
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_BOOK")
    @Column(name = "ID_BOOK")
    private Long id;

    @Column(name = "NM_BOOK", nullable = false)
    private String name;

    @Column(name = "ISBN_BOOK", nullable = false)
    private String ISBN;

    @Column(name = "DT_LANCAMENTO", nullable = false)
    private LocalDate lancamento;

    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "TB_BOOK_AUTHOR",
            joinColumns = {
                    @JoinColumn(
                            name = "BOOK",
                            referencedColumnName = "ID_BOOK",
                            foreignKey = @ForeignKey(name = "FK_BOOK_AUTHOR")
                    )
            },
            inverseJoinColumns = {
                    @JoinColumn(
                            name = "AUTHOR",
                            referencedColumnName = "ID_AUTHOR",
                            foreignKey = @ForeignKey(name = "FK_AUTHOR_BOOK")
                    )
            }
    )
    private Set<Author> writers;


    public Book(Long id, String name, String ISBN, LocalDate lancamento, Set<Author> writers) {
        this.setId(id);
        this.setName(name);
        this.setISBN(ISBN);
        this.setLancamento(lancamento);
        this.writers = Objects.nonNull(writers) ? writers : new LinkedHashSet<>();
    }

    public Book() {
        this.writers = new LinkedHashSet<>();
    }

    public Book addAuthor(Author a) {
        writers.add(a);
        return this;
    }

    public Book removeAuthor(Author a) {
        writers.remove(a);
        return this;
    }


    public Set<Author> getWriters() {
        return Collections.unmodifiableSet(writers);

    }

    public Long getId() {
        return id;
    }

    public Book setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Book setName(String name) {
        this.name = name;
        return this;
    }

    public String getISBN() {
        return ISBN;
    }

    public Book setISBN(String ISBN) {
        this.ISBN = ISBN;
        return this;
    }

    public LocalDate getLancamento() {
        return lancamento;
    }

    public Book setLancamento(LocalDate lancamento) {
        this.lancamento = lancamento;
        return this;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", ISBN='" + ISBN + '\'' +
                ", lancamento=" + lancamento +
//                ", writers=" + writers +
                '}';
    }
}
