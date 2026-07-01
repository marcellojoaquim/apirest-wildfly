package com.github.marcello.domain;

import javax.persistence.*;

@Entity
@Table(name="produtos_tb")
public class Produto implements Persistente{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, name = "nome_produto", length = 100)
    private String nome;

    @Column(
            nullable = false,
            unique = true,
            name = "codigo_produto",
            length = 50,
            updatable = false
    )
    private String codigo;

    @Column(name = "produto_descricao", nullable = false)
    private String descricao;

    public Produto() {
    }

    public Produto(Long id, String nome, String codigo, String descricao) {
        this.id = id;
        this.nome = nome;
        this.codigo = codigo;
        this.descricao = descricao;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }
}
