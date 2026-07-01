package com.github.marcello.dao;

import com.github.marcello.dao.generic.GenericDAO;
import com.github.marcello.domain.Produto;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ProdutoDAO extends GenericDAO<Produto, Long> {

    public ProdutoDAO() {
        super(Produto.class);
    }


}
