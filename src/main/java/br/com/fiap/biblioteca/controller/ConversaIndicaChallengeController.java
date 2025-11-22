package br.com.fiap.biblioteca.controller;

import br.com.fiap.biblioteca.dominio.ConversaIndicaChallenge;
import br.com.fiap.biblioteca.infra.dao.ConversaIndicaChallengeDAO;
import br.com.fiap.biblioteca.repositorio.RepositorioConversaIndicaChallenge;
import br.com.fiap.biblioteca.service.ConversaIndicaChallengeService;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.ArrayList;

@Path("/conversas-indicam-challenge")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ConversaIndicaChallengeController {

    private final RepositorioConversaIndicaChallenge repositorio;
    private final ConversaIndicaChallengeService service;

    public ConversaIndicaChallengeController() {
        this.repositorio = new ConversaIndicaChallengeDAO();
        this.service = new ConversaIndicaChallengeService(repositorio);
    }

    @OPTIONS
    public Response cors() {
        return Response.ok()
                .header("Access-Control-Allow-Origin", "*")
                .header("Access-Control-Allow-Headers", "origin, content-type, accept, authorization")
                .header("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS")
                .build();
    }

    @POST
    public Response salvar(ConversaIndicaChallenge conversa) {
        try {
            service.adicionar(conversa);
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
        ConversaIndicaChallenge conversa = repositorio.buscarPorId(id);

        Response.Status status =
                (conversa == null) ? Response.Status.NOT_FOUND : Response.Status.OK;

        return Response.status(status)
                .entity(conversa)
                .header("Access-Control-Allow-Origin", "*")
                .build();
    }

    @GET
    @Path("/conversa/{idConversa}")
    public Response buscarPorIdConversa(@PathParam("idConversa") String idConversa) {
        ArrayList<ConversaIndicaChallenge> lista =
                repositorio.buscarPorIdConversa(idConversa);

        Response.Status status =
                (lista.isEmpty()) ? Response.Status.NOT_FOUND : Response.Status.OK;

        return Response.status(status)
                .entity(lista)
                .header("Access-Control-Allow-Origin", "*")
                .build();
    }

    @GET
    @Path("/challenge/{idChallenge}")
    public Response buscarPorIdChallenge(@PathParam("idChallenge") int idChallenge) {
        ArrayList<ConversaIndicaChallenge> lista =
                repositorio.buscarPorIdChallenge(idChallenge);

        Response.Status status =
                (lista.isEmpty()) ? Response.Status.NOT_FOUND : Response.Status.OK;

        return Response.status(status)
                .entity(lista)
                .header("Access-Control-Allow-Origin", "*")
                .build();
    }

    @GET
    public Response listarTodos() {
        ArrayList<ConversaIndicaChallenge> lista = repositorio.buscarTodos();

        Response.Status status =
                lista.isEmpty() ? Response.Status.NOT_FOUND : Response.Status.OK;

        return Response.status(status)
                .entity(lista)
                .header("Access-Control-Allow-Origin", "*")
                .build();
    }

    @PUT
    @Path("/{id}")
    public Response atualizar(@PathParam("id") int id, ConversaIndicaChallenge nova) {

        ConversaIndicaChallenge existente = repositorio.buscarPorId(id);

        if (existente == null) {
            return Response.status(Response.Status.NOT_FOUND)
                    .header("Access-Control-Allow-Origin", "*")
                    .entity("Registro não encontrado para atualização.")
                    .build();
        }

        existente.setId_conversa(nova.getId_conversa());
        existente.setId_challenge(nova.getId_challenge());

        repositorio.atualizar(existente);

        return Response.status(Response.Status.OK)
                .header("Access-Control-Allow-Origin", "*")
                .entity("Atualizado com sucesso.")
                .build();
    }

    @DELETE
    @Path("/{id}")
    public Response excluir(@PathParam("id") int id) {

        ConversaIndicaChallenge existente = repositorio.buscarPorId(id);

        if (existente == null) {
            return Response.status(Response.Status.NOT_FOUND)
                    .header("Access-Control-Allow-Origin", "*")
                    .entity("Registro não encontrado.")
                    .build();
        }

        repositorio.excluir(id);

        return Response.status(Response.Status.OK)
                .header("Access-Control-Allow-Origin", "*")
                .entity("Excluído com sucesso.")
                .build();
    }
}
