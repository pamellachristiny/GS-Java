package br.com.fiap.biblioteca.controller;

import br.com.fiap.biblioteca.dominio.Curso;
import br.com.fiap.biblioteca.infra.dao.CursoDAO;
import br.com.fiap.biblioteca.repositorio.RepositorioCurso;
import br.com.fiap.biblioteca.service.CursoService;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;

import java.util.ArrayList;

@Path("/cursos")
public class CursoController {

    private RepositorioCurso repositorioCurso;
    private CursoService cursoService;

    public CursoController() {
        this.repositorioCurso = new CursoDAO();
        this.cursoService = new CursoService(repositorioCurso);
    }

    @POST
    public Response salvar(Curso curso) {
        try {
            cursoService.adicionar(curso);
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
        Curso curso = repositorioCurso.buscarPorId(id);

        Response.Status status =
                (curso == null) ? Response.Status.NOT_FOUND : Response.Status.OK;

        return Response
                .status(status)
                .entity(curso)
                .build();
    }

    @GET
    public Response listarTodos() {
        ArrayList<Curso> cursos = repositorioCurso.buscarTodos();

        Response.Status status =
                cursos.isEmpty() ? Response.Status.NOT_FOUND : Response.Status.OK;

        return Response
                .status(status)
                .entity(cursos)
                .build();
    }

    @GET
    @Path("/professor/{idProfessor}")
    public Response buscarPorProfessor(@PathParam("idProfessor") Long idProfessor) {
        ArrayList<Curso> cursos = repositorioCurso.buscarPorProfessor(idProfessor);

        Response.Status status =
                cursos.isEmpty() ? Response.Status.NOT_FOUND : Response.Status.OK;

        return Response
                .status(status)
                .entity(cursos)
                .build();
    }
}
