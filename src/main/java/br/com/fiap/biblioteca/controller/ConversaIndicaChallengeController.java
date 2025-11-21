package br.com.fiap.biblioteca.controller;

import br.com.fiap.biblioteca.dominio.ConversaIndicaChallenge;
import br.com.fiap.biblioteca.infra.dao.ConversaIndicaChallengeDAO;
import br.com.fiap.biblioteca.repositorio.RepositorioConversaIndicaChallenge;
import br.com.fiap.biblioteca.service.ConversaIndicaChallengeService;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;

import java.util.ArrayList;

@Path("/conversas-indicam-challenge")
public class ConversaIndicaChallengeController {

    private RepositorioConversaIndicaChallenge repositorio;
    private ConversaIndicaChallengeService service;

    public ConversaIndicaChallengeController() {
        this.repositorio = new ConversaIndicaChallengeDAO();
        this.service = new ConversaIndicaChallengeService(repositorio);
    }

    @POST
    public Response salvar(ConversaIndicaChallenge conversa) {
        try {
            service.adicionar(conversa);
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
    @Path("/id/{id}")
    public Response buscarPorId(@PathParam("id") int id) {
        ConversaIndicaChallenge conversa = repositorio.buscarPorId(id);

        Response.Status status =
                (conversa == null) ? Response.Status.NOT_FOUND : Response.Status.OK;

        return Response
                .status(status)
                .entity(conversa)
                .build();
    }

    @GET
    @Path("/conversa/{idConversa}")
    public Response buscarPorIdConversa(@PathParam("idConversa") String idConversa) {
        ArrayList<ConversaIndicaChallenge> lista =
                repositorio.buscarPorIdConversa(idConversa);

        Response.Status status =
                (lista.isEmpty()) ? Response.Status.NOT_FOUND : Response.Status.OK;

        return Response
                .status(status)
                .entity(lista)
                .build();
    }

    @GET
    @Path("/challenge/{idChallenge}")
    public Response buscarPorIdChallenge(@PathParam("idChallenge") int idChallenge) {
        ArrayList<ConversaIndicaChallenge> lista =
                repositorio.buscarPorIdChallenge(idChallenge);

        Response.Status status =
                (lista.isEmpty()) ? Response.Status.NOT_FOUND : Response.Status.OK;

        return Response
                .status(status)
                .entity(lista)
                .build();
    }

    @GET
    public Response listarTodos() {
        ArrayList<ConversaIndicaChallenge> lista =
                repositorio.buscarTodos();

        Response.Status status =
                lista.isEmpty() ? Response.Status.NOT_FOUND : Response.Status.OK;

        return Response
                .status(status)
                .entity(lista)
                .build();
    }
}
