package br.com.fiap.biblioteca.controller;

import br.com.fiap.biblioteca.dominio.Challenge;
import br.com.fiap.biblioteca.infra.dao.ChallengeDAO;
import br.com.fiap.biblioteca.repositorio.RepositorioChallenge;
import br.com.fiap.biblioteca.service.ChallengeService;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/challenges")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ChallengeController {

    private RepositorioChallenge repositorioChallenge;
    private ChallengeService challengeService;

    public ChallengeController() {
        this.repositorioChallenge = new ChallengeDAO();
        this.challengeService = new ChallengeService(repositorioChallenge);
    }

    // -------- CORS -------- //
    @OPTIONS
    @Path("{any:.*}")
    public Response options() {
        return Response.ok()
                .header("Access-Control-Allow-Origin", "*")
                .header("Access-Control-Allow-Headers", "origin, content-type, accept, authorization")
                .header("Access-Control-Allow-Credentials", "true")
                .header("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS")
                .build();
    }

    @POST
    public Response salvar(Challenge challenge) {
        try {
            challengeService.salvar(challenge);
            return Response.status(Response.Status.CREATED)
                    .header("Access-Control-Allow-Origin", "*")
                    .build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Erro ao salvar Challenge: " + e.getMessage())
                    .header("Access-Control-Allow-Origin", "*")
                    .build();
        }
    }

    @GET
    @Path("/id/{id}")
    public Response buscarPorId(@PathParam("id") Long id) {
        Challenge challenge = challengeService.buscarPorId(id);

        return Response
                .status(challenge == null ? Response.Status.NOT_FOUND : Response.Status.OK)
                .entity(challenge)
                .header("Access-Control-Allow-Origin", "*")
                .build();
    }

    @GET
    @Path("/usuario/{idUsuario}")
    public Response buscarPorUsuario(@PathParam("idUsuario") Long idUsuario) {
        List<Challenge> challenges = repositorioChallenge.buscarPorUsuario(idUsuario);

        return Response
                .status(challenges.isEmpty() ? Response.Status.NOT_FOUND : Response.Status.OK)
                .entity(challenges)
                .header("Access-Control-Allow-Origin", "*")
                .build();
    }

    @GET
    @Path("/curso/{idCurso}")
    public Response buscarPorCurso(@PathParam("idCurso") Long idCurso) {
        List<Challenge> challenges = repositorioChallenge.buscarPorCurso(idCurso);

        return Response
                .status(challenges.isEmpty() ? Response.Status.NOT_FOUND : Response.Status.OK)
                .entity(challenges)
                .header("Access-Control-Allow-Origin", "*")
                .build();
    }

    @GET
    public Response listarTodos() {
        List<Challenge> challenges = repositorioChallenge.listarTodos();

        return Response
                .status(challenges.isEmpty() ? Response.Status.NOT_FOUND : Response.Status.OK)
                .entity(challenges)
                .header("Access-Control-Allow-Origin", "*")
                .build();
    }


    @DELETE
    @Path("/id/{id}")
    public Response excluir(@PathParam("id") Long id) {
        challengeService.excluir(id);

        return Response.status(Response.Status.NO_CONTENT)
                .header("Access-Control-Allow-Origin", "*")
                .build();
    }

}
