package com.github.marcello.service;

import com.github.marcello.domain.Produto;
import com.github.marcello.exception.DAOException;

public interface ProdutoService {

    Produto buscarPorId(Long codigo);
    Produto buscarPorCodigo(String codigo) throws DAOException;
}
