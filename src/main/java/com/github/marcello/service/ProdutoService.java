package com.github.marcello.service;

import com.github.marcello.dao.ProdutoDAO;
import com.github.marcello.domain.Produto;


import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.NotFoundException;
import java.util.Collection;

@Transactional
@ApplicationScoped
public class ProdutoService  implements IProdutoService{

    @Inject
    protected ProdutoDAO produtoDAO;

    public Produto cadastrar(Produto produto) {
        Produto newProduto = produtoDAO.cadastrar(produto);
        return newProduto;
    }

    public Collection<Produto> buscarTodos() {
        return produtoDAO.buscarTodos();
    }

    @Override
    public Produto buscarPorId(Long codigo) {
       try {
           return this.produtoDAO.consultar(codigo);
       } catch (Exception e) {
           throw new NotFoundException("Produto não encontrado para este código.");
       }
    }

    @Override
    public Produto buscarPorCodigo(String codigo) {
        return this.produtoDAO.consultarPorCodigo(codigo);
    }
}
