package com.github.marcello.service;

import com.github.marcello.domain.Produto;

public interface IProdutoService {

    Produto buscarPorId(Long codigo);
    Produto buscarPorCodigo(String codigo);
}
