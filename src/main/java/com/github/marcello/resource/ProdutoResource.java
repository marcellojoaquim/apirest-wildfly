package com.github.marcello.resource;

import com.github.marcello.domain.Produto;
import com.github.marcello.exception.DAOException;
import com.github.marcello.service.ProdutoService;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/produtos")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ProdutoResource {

    @Inject
    private ProdutoService produtoService;

    @GET
    public Response listar() {
        return Response.ok(produtoService.buscarTodos()).build();
    }

    @GET
    @Path("/{id}")
    public Response bucarPorId(@PathParam("id") Long id){
        Produto response = produtoService.buscarPorId(id);
        return Response.ok().entity(response).build();
    }

    @GET
    @Path("/codigo/{codigo}")
    public Response buscarPorCodigo(@PathParam("codigo") String codigo) throws DAOException {
        Produto produto = produtoService.buscarPorCodigo(codigo);
        return Response.ok().entity(produto).build();
    }

    @POST
    public Response cadastrar(@Valid Produto produto) throws DAOException {
        produtoService.cadastrar(produto);
        return Response.status(Response.Status.CREATED).entity(produto).build();
    }
}
