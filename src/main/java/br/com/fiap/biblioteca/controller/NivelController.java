package br.com.fiap.biblioteca.controller;

import br.com.fiap.biblioteca.dominio.Nivel;
import br.com.fiap.biblioteca.infra.dao.NivelDAO;
import br.com.fiap.biblioteca.repositorio.RepositorioNivel;
import br.com.fiap.biblioteca.service.NivelService;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.HttpHeaders;

import java.util.ArrayList;

@Path("/niveis")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class NivelController {

    private RepositorioNivel repositorioNivel;
    private NivelService nivelService;

    public NivelController() {
        this.repositorioNivel = new NivelDAO();
        this.nivelService = new NivelService(repositorioNivel);
    }

    // ---------- Habilitar CORS ----------
    @OPTIONS
    @Path("{any: .*}")
    public Response corsPreflight(@Context HttpHeaders headers) {
        return Response.ok()
                .header("Access-Control-Allow-Origin", "*")
                .header("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS")
                .header("Access-Control-Allow-Headers", "Content-Type, Authorization")
                .build();
    }
    // -----------------------------------

    @POST
    public Response salvar(Nivel nivel) {
        try {
            nivelService.adicionar(nivel);
            return Response.status(Response.Status.CREATED)
                    .header("Access-Control-Allow-Origin", "*")
                    .build();
        } catch (RuntimeException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .header("Access-Control-Allow-Origin", "*")
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

        return Response.status(status)
                .header("Access-Control-Allow-Origin", "*")
                .entity(nivel)
                .build();
    }

    @GET
    @Path("/nome/{nome}")
    public Response buscarPorNome(@PathParam("nome") String nome) {
        Nivel nivel = repositorioNivel.buscarPorNome(nome);

        Response.Status status =
                (nivel == null) ? Response.Status.NOT_FOUND : Response.Status.OK;

        return Response.status(status)
                .header("Access-Control-Allow-Origin", "*")
                .entity(nivel)
                .build();
    }

    @GET
    public Response listarTodos() {
        ArrayList<Nivel> niveis = repositorioNivel.listarTodos();

        Response.Status status =
                niveis.isEmpty() ? Response.Status.NOT_FOUND : Response.Status.OK;

        return Response.status(status)
                .header("Access-Control-Allow-Origin", "*")
                .entity(niveis)
                .build();
    }

    @PUT
    @Path("/id/{id}")
    public Response atualizar(@PathParam("id") int id, Nivel nivelAtualizado) {
        Nivel existente = repositorioNivel.buscarPorId(id);

        if (existente == null) {
            return Response.status(Response.Status.NOT_FOUND)
                    .header("Access-Control-Allow-Origin", "*")
                    .entity("Nível não encontrado")
                    .build();
        }

        // força manter o mesmo ID
        nivelAtualizado.setId_nivel(id);

        nivelService.atualizar(nivelAtualizado);

        return Response.ok(nivelAtualizado)
                .header("Access-Control-Allow-Origin", "*")
                .build();
    }

    @DELETE
    @Path("/id/{id}")
    public Response excluir(@PathParam("id") int id) {
        nivelService.excluir(id);

        return Response.status(Response.Status.NO_CONTENT)
                .header("Access-Control-Allow-Origin", "*")
                .build();
    }
}
