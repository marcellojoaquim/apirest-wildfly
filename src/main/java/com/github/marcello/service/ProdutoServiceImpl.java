package com.github.marcello.service;

import com.github.marcello.dao.ProdutoDAO;
import com.github.marcello.domain.Produto;
import com.github.marcello.exception.DAOException;


import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import javax.ws.rs.NotFoundException;
import java.util.Collection;
import java.util.Objects;

@Transactional
@ApplicationScoped
public class ProdutoServiceImpl implements ProdutoService {

    @Inject
    protected ProdutoDAO produtoDAO;

    public Produto cadastrar(Produto produto) throws DAOException {
        Produto newProduto = produtoDAO.cadastrar(produto);
        return newProduto;
    }

    public Collection<Produto> buscarTodos() {
        return produtoDAO.buscarTodos();
    }

    public Produto atualizar(String codigo, Produto novoProduto) throws DAOException {
        if(!Objects.equals(novoProduto.getCodigo(), codigo)){
            throw new IllegalArgumentException("Codigo do produto deve ser igual ao parâmetro código");
        }
        Produto produtoLocal = produtoDAO.consultarPorCodigo(codigo);
        if(produtoLocal == null){
            throw new EntityNotFoundException("Produto não encontrado para o codigo: "+codigo);
        }
        produtoLocal.setNome(novoProduto.getNome());
        produtoLocal.setDescricao(novoProduto.getDescricao());

        return produtoDAO.atualizar(produtoLocal);
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
    public Produto buscarPorCodigo(String codigo) throws DAOException {
        return this.produtoDAO.consultarPorCodigo(codigo);
    }
}
