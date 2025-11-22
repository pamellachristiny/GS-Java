package br.com.fiap.biblioteca.controller;

import br.com.fiap.biblioteca.dominio.Curso;
import br.com.fiap.biblioteca.infra.dao.CursoDAO;
import br.com.fiap.biblioteca.repositorio.RepositorioCurso;
import br.com.fiap.biblioteca.service.CursoService;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.ArrayList;

@Path("/cursos")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class CursoController {

    private final RepositorioCurso repositorioCurso;
    private final CursoService cursoService;

    public CursoController() {
        this.repositorioCurso = new CursoDAO();
        this.cursoService = new CursoService(repositorioCurso);
    }

    // ==============================
    // 🔓 CORS (Resolve Front-End)
    // ==============================
    @OPTIONS
    public Response cors() {
        return Response.ok()
                .header("Access-Control-Allow-Origin", "*")
                .header("Access-Control-Allow-Headers", "origin, content-type, accept, authorization")
                .header("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS")
                .build();
    }

    // ==============================
    // POST - Criar curso
    // ==============================
    @POST
    public Response salvar(Curso curso) {
        try {
            cursoService.adicionar(curso);
            return Response.status(Response.Status.CREATED)
                    .header("Access-Control-Allow-Origin", "*")
                    .build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .header("Access-Control-Allow-Origin", "*")
                    .entity(e.getMessage())
                    .build();
        }
    }

    // ==============================
    // GET - Buscar por ID
    // ==============================
    @GET
    @Path("/id/{id}")
    public Response buscarPorId(@PathParam("id") Long id) {
        Curso curso = repositorioCurso.buscarPorId(id);

        Response.Status status = (curso == null)
                ? Response.Status.NOT_FOUND
                : Response.Status.OK;

        return Response.status(status)
                .entity(curso)
                .header("Access-Control-Allow-Origin", "*")
                .build();
    }

    // ==============================
    // GET - Listar todos
    // ==============================
    @GET
    public Response listarTodos() {
        ArrayList<Curso> cursos = repositorioCurso.buscarTodos();

        Response.Status status = cursos.isEmpty()
                ? Response.Status.NOT_FOUND
                : Response.Status.OK;

        return Response.status(status)
                .entity(cursos)
                .header("Access-Control-Allow-Origin", "*")
                .build();
    }

    // ==============================
    // GET - Buscar por professor
    // ==============================
    @GET
    @Path("/professor/{idProfessor}")
    public Response buscarPorProfessor(@PathParam("idProfessor") Long idProfessor) {
        ArrayList<Curso> cursos = repositorioCurso.buscarPorProfessor(idProfessor);

        Response.Status status = cursos.isEmpty()
                ? Response.Status.NOT_FOUND
                : Response.Status.OK;

        return Response.status(status)
                .entity(cursos)
                .header("Access-Control-Allow-Origin", "*")
                .build();
    }

    // ==============================
    // PUT - Atualizar curso
    // ==============================
    @PUT
    @Path("/{id}")
    public Response atualizar(@PathParam("id") Long id, Curso cursoAtualizado) {

        Curso existente = repositorioCurso.buscarPorId(id);

        if (existente == null) {
            return Response.status(Response.Status.NOT_FOUND)
                    .header("Access-Control-Allow-Origin", "*")
                    .entity("Curso não encontrado para atualizar.")
                    .build();
        }

        // Atualiza os dados
        existente.setNomeCurso(cursoAtualizado.getNomeCurso());
        existente.setIdProfessor(cursoAtualizado.getIdProfessor());
        existente.setIdChallenge(cursoAtualizado.getIdChallenge());

        repositorioCurso.atualizar(existente);

        return Response.status(Response.Status.OK)
                .header("Access-Control-Allow-Origin", "*")
                .entity("Curso atualizado com sucesso.")
                .build();
    }

    // ==============================
    // DELETE - Excluir curso
    // ==============================
    @DELETE
    @Path("/{id}")
    public Response excluir(@PathParam("id") Long id) {

        Curso existente = repositorioCurso.buscarPorId(id);

        if (existente == null) {
            return Response.status(Response.Status.NOT_FOUND)
                    .header("Access-Control-Allow-Origin", "*")
                    .entity("Curso não encontrado.")
                    .build();
        }

        repositorioCurso.excluir(id);

        return Response.status(Response.Status.OK)
                .header("Access-Control-Allow-Origin", "*")
                .entity("Curso excluído com sucesso.")
                .build();
    }
}
