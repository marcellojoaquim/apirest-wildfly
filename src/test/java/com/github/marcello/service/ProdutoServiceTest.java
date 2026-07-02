package com.github.marcello.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.github.marcello.dao.ProdutoDAO;
import com.github.marcello.domain.Produto;
import com.github.marcello.exception.DAOException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class ProdutoServiceTest {

    @Mock
    private ProdutoDAO produtoDAO;

    @InjectMocks
    private ProdutoService produtoService;

    private Produto produto;
    private static final String CODIGO_PRODUTO = "prod123";
    private static final Long ID_PRODUTO = 1L;

    @BeforeEach
    public void setUp() {
        produto = new Produto();
        produto.setCodigo("123Prod");
        produto.setDescricao("Produto teste");
        produto.setNome("Produto 01");
    }

    @Test
    public void testCadastrar() throws DAOException {
        when(produtoDAO.cadastrar(produto)).thenReturn(produto);

        Produto result = produtoService.cadastrar(produto);
        verify(produtoDAO, times(1)).cadastrar(produto);
        assertNotNull(result);
        assertEquals(result.getNome(), produto.getNome());
    }

    @Test
    public void testBuscarTodos() {
        produto.setId(ID_PRODUTO);
        List<Produto> list = new ArrayList<>();
        list.add(produto);

        when(produtoDAO.buscarTodos()).thenReturn(list);
        Collection<Produto> result = produtoService.buscarTodos();

        assertNotNull(result);
        assertEquals(1, result.size());
    }

    @Test
    public void testBuscarPorId() throws DAOException{
        produto.setId(ID_PRODUTO);
        when(produtoDAO.consultar(ID_PRODUTO)).thenReturn(produto);
        Produto result = produtoService.buscarPorId(ID_PRODUTO);

        verify(produtoDAO, times(1)).consultar(1L);
        assertEquals(result.getId(), produto.getId());
        assertEquals(result.getNome(), produto.getNome());
    }

    @Test
    public void testBuscarPorCodigo() throws DAOException{
        produto.setId(ID_PRODUTO);
        when(produtoDAO.consultarPorCodigo(CODIGO_PRODUTO)).thenReturn(produto);

        Produto result = produtoService.buscarPorCodigo(CODIGO_PRODUTO);

        verify(produtoDAO, times(1)).consultarPorCodigo(CODIGO_PRODUTO);
        assertEquals(result.getId(), produto.getId());
        assertEquals(result.getNome(), produto.getNome());
    }
}