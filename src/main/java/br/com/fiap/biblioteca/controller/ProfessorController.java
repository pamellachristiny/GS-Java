package br.com.fiap.biblioteca.controller;

import br.com.fiap.biblioteca.dominio.Professor;
import br.com.fiap.biblioteca.infra.dao.ConnectionFactory;
import br.com.fiap.biblioteca.infra.dao.ProfessorDAO;
import br.com.fiap.biblioteca.repositorio.RepositorioProfessor;
import br.com.fiap.biblioteca.service.ProfessorService;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.sql.Connection;
import java.util.ArrayList;

@Path("/professores")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ProfessorController {

    private final RepositorioProfessor repositorioProfessor;
    private final ProfessorService professorService;

    public ProfessorController() {
        Connection conexao = new ConnectionFactory().getConnection();
        this.repositorioProfessor = new ProfessorDAO(conexao);
        this.professorService = new ProfessorService(repositorioProfessor);
    }

    private Response.ResponseBuilder cors(Response.ResponseBuilder builder) {
        return builder
                .header("Access-Control-Allow-Origin", "*")
                .header("Access-Control-Allow-Headers", "origin, content-type, accept, authorization")
                .header("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
    }

    @OPTIONS
    @Path("{any:.*}")
    public Response preflight() {
        return cors(Response.ok()).build();
    }

    @POST
    public Response salvar(Professor professor) {
        try {
            professorService.adicionar(professor);
            return cors(Response.status(Response.Status.CREATED).entity(professor)).build();
        } catch (RuntimeException e) {
            e.printStackTrace();
            return cors(
                    Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage())
            ).build();
        }
    }

    @GET
    @Path("/id/{id}")
    public Response buscarPorId(@PathParam("id") int id) {
        Professor professor = repositorioProfessor.buscarPorId(id);

        if (professor == null)
            return cors(Response.status(Response.Status.NOT_FOUND)).build();

        return cors(Response.ok(professor)).build();
    }

    @GET
    @Path("/email/{email}")
    public Response buscarPorEmail(@PathParam("email") String email) {
        Professor professor = repositorioProfessor.buscarPorEmail(email);

        if (professor == null)
            return cors(Response.status(Response.Status.NOT_FOUND)).build();

        return cors(Response.ok(professor)).build();
    }

    @GET
    public Response listarTodos() {
        ArrayList<Professor> professores = repositorioProfessor.listarTodos();
        Response.Status status = professores.isEmpty() ? Response.Status.NOT_FOUND : Response.Status.OK;

        return cors(Response.status(status).entity(professores)).build();
    }

    @PUT
    @Path("/{id}")
    public Response atualizar(@PathParam("id") int id, Professor professorAtualizado) {
        Professor professorExistente = repositorioProfessor.buscarPorId(id);

        if (professorExistente == null) {
            return cors(
                    Response.status(Response.Status.NOT_FOUND)
                            .entity("Professor não encontrado")
            ).build();
        }

        professorExistente.setNome_professor(professorAtualizado.getNome_professor());
        professorExistente.setEspecialidade_professor(professorAtualizado.getEspecialidade_professor());
        professorExistente.setEmail_professor(professorAtualizado.getEmail_professor());
        professorExistente.setSenha_professor(professorAtualizado.getSenha_professor());

        repositorioProfessor.atualizar(professorExistente);

        return cors(Response.ok(professorExistente)).build();
    }

    @DELETE
    @Path("/{id}")
    public Response deletar(@PathParam("id") int id) {
        try {
            boolean removido = professorService.excluir(id);

            if (!removido) {
                return cors(
                        Response.status(Response.Status.NOT_FOUND)
                                .entity("Professor não encontrado.")
                ).build();
            }

            return cors(Response.noContent()).build();
        } catch (RuntimeException e) {
            e.printStackTrace();
            return cors(
                    Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                            .entity(e.getMessage())
            ).build();
        }
    }
}
