package br.com.fiap.biblioteca.controller;

import br.com.fiap.biblioteca.dominio.Challenge;
import br.com.fiap.biblioteca.repositorio.RepositorioChallenge;
import br.com.fiap.biblioteca.infra.dao.ChallengeDAO;
import br.com.fiap.biblioteca.service.ChallengeService;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/challenges")
public class ChallengeController {

    private RepositorioChallenge repositorioChallenge;
    private ChallengeService challengeService;

    public ChallengeController() {
        this.repositorioChallenge = new ChallengeDAO();
        this.challengeService = new ChallengeService(repositorioChallenge);
    }

    @POST
    public Response salvar(Challenge challenge) {
        try {
            challengeService.salvar(challenge);
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
    public Response buscarPorId(@PathParam("id") Long id) {
        Challenge challenge = repositorioChallenge.buscarPorId(id);

        Response.Status status =
                (challenge == null) ? Response.Status.NOT_FOUND : Response.Status.OK;

        return Response
                .status(status)
                .entity(challenge)
                .build();
    }

    @GET
    @Path("/usuario/{idUsuario}")
    public Response buscarPorUsuario(@PathParam("idUsuario") Long idUsuario) {
        List<Challenge> challenges = repositorioChallenge.buscarPorUsuario(idUsuario);

        Response.Status status =
                challenges.isEmpty() ? Response.Status.NOT_FOUND : Response.Status.OK;

        return Response
                .status(status)
                .entity(challenges)
                .build();
    }

    @GET
    @Path("/curso/{idCurso}")
    public Response buscarPorCurso(@PathParam("idCurso") Long idCurso) {
        List<Challenge> challenges = repositorioChallenge.buscarPorCurso(idCurso);

        Response.Status status =
                challenges.isEmpty() ? Response.Status.NOT_FOUND : Response.Status.OK;

        return Response
                .status(status)
                .entity(challenges)
                .build();
    }

    @GET
    public Response listarTodos() {
        List<Challenge> challenges = repositorioChallenge.listarTodos();

        Response.Status status =
                challenges.isEmpty() ? Response.Status.NOT_FOUND : Response.Status.OK;

        return Response
                .status(status)
                .entity(challenges)
                .build();
    }
}
