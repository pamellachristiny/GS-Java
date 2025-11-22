package br.com.fiap.biblioteca.controller;

import br.com.fiap.biblioteca.dominio.NivelUsuario;
import br.com.fiap.biblioteca.infra.dao.NivelUsuarioDAO;
import br.com.fiap.biblioteca.repositorio.RepositorioNivelUsuario;
import br.com.fiap.biblioteca.service.NivelUsuarioService;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;

import java.util.ArrayList;

@Path("/niveis-usuarios")
public class NivelUsuarioController {

    private RepositorioNivelUsuario repositorio;
    private NivelUsuarioService service;

    public NivelUsuarioController() {
        this.repositorio = new NivelUsuarioDAO();
        this.service = new NivelUsuarioService(repositorio);
    }

    @POST
    public Response salvar(NivelUsuario nivelUsuario) {
        try {
            service.adicionar(nivelUsuario);
            return Response.status(Response.Status.CREATED).build();
        } catch (RuntimeException e) {
            e.printStackTrace();
            return Response
                    .status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(e.getMessage())
                    .build();
        }
    }

    @GET
    @Path("/usuario/{idUsuario}/nivel/{idNivel}")
    public Response buscar(
            @PathParam("idUsuario") int idUsuario,
            @PathParam("idNivel") int idNivel) {

        NivelUsuario nivelUsuario = repositorio.buscar(idUsuario, idNivel);

        Response.Status status =
                (nivelUsuario == null) ? Response.Status.NOT_FOUND : Response.Status.OK;

        return Response
                .status(status)
                .entity(nivelUsuario)
                .build();
    }

    @GET
    @Path("/usuario/{idUsuario}")
    public Response buscarPorUsuario(@PathParam("idUsuario") int idUsuario) {
        ArrayList<NivelUsuario> lista = repositorio.buscarPorUsuario(idUsuario);

        Response.Status status =
                lista.isEmpty() ? Response.Status.NOT_FOUND : Response.Status.OK;

        return Response
                .status(status)
                .entity(lista)
                .build();
    }

    @GET
    public Response listarTodos() {
        ArrayList<NivelUsuario> lista = repositorio.listarTodos();

        Response.Status status =
                lista.isEmpty() ? Response.Status.NOT_FOUND : Response.Status.OK;

        return Response
                .status(status)
                .entity(lista)
                .build();
    }

    @DELETE
    @Path("/usuario/{idUsuario}/nivel/{idNivel}")
    public Response excluir(
            @PathParam("idUsuario") int idUsuario,
            @PathParam("idNivel") int idNivel) {

        try {
            repositorio.excluir(idUsuario, idNivel);
            return Response.status(Response.Status.NO_CONTENT).build();

        } catch (RuntimeException e) {
            return Response
                    .status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(e.getMessage())
                    .build();
        }
    }

    @PUT
    @Path("/usuario/{idUsuario}/nivel/{idNivel}")
    public Response atualizar(
            @PathParam("idUsuario") int idUsuario,
            @PathParam("idNivel") int idNivel,
            NivelUsuario novoNivel) {

        NivelUsuario existente = repositorio.buscar(idUsuario, idNivel); // ✔ CORRETO

        if (existente == null) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Registro não encontrado")
                    .build();
        }

        existente.setData_hora_nivel(novoNivel.getData_hora_nivel());

        service.atualizar(existente);

        return Response.ok(existente).build();
    }


}
