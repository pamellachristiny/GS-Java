package br.com.fiap.biblioteca.controller;

import br.com.fiap.biblioteca.dominio.Nivel;
import br.com.fiap.biblioteca.infra.dao.NivelDAO;
import br.com.fiap.biblioteca.repositorio.RepositorioNivel;
import br.com.fiap.biblioteca.service.NivelService;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;
import java.util.ArrayList;

@Path("/niveis")
public class NivelController {

    private RepositorioNivel repositorioNivel;
    private NivelService nivelService;

    public NivelController() {
        this.repositorioNivel = new NivelDAO();
        this.nivelService = new NivelService(repositorioNivel);
    }

    @POST
    public Response salvar(Nivel nivel) {
        try {
            nivelService.adicionar(nivel);
            return Response.status(Response.Status.CREATED).build();
        } catch (RuntimeException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(e.getMessage())
                    .build();
        }
    }

    @GET
    @Path("/id/{id}")
    public Response buscarPorId(@PathParam("id") int id) {
        Nivel nivel = repositorioNivel.buscarPorId(id);

        Response.Status status =
                (nivel == null) ? Response.Status.NOT_FOUND : Response.Status.OK;

        return Response.status(status).entity(nivel).build();
    }

    @GET
    @Path("/nome/{nome}")
    public Response buscarPorNome(@PathParam("nome") String nome) {
        Nivel nivel = repositorioNivel.buscarPorNome(nome);

        Response.Status status =
                (nivel == null) ? Response.Status.NOT_FOUND : Response.Status.OK;

        return Response.status(status).entity(nivel).build();
    }

    @GET
    public Response listarTodos() {
        ArrayList<Nivel> niveis = repositorioNivel.listarTodos();

        Response.Status status =
                niveis.isEmpty() ? Response.Status.NOT_FOUND : Response.Status.OK;

        return Response.status(status).entity(niveis).build();
    }

    @DELETE
    @Path("/id/{id}")
    public Response excluir(@PathParam("id") int id) {
        nivelService.excluir(id);
        return Response.status(Response.Status.NO_CONTENT).build();
    }
}
