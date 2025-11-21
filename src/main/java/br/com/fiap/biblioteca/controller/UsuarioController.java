package br.com.fiap.biblioteca.controller;

import br.com.fiap.biblioteca.dominio.Usuario;
import br.com.fiap.biblioteca.infra.dao.UsuarioDAO;
import br.com.fiap.biblioteca.repositorio.RepositorioUsuario;
import br.com.fiap.biblioteca.service.UsuarioService;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;

import java.util.ArrayList;

@Path("/usuarios")
public class UsuarioController {

    private RepositorioUsuario repositorioUsuario;
    private UsuarioService usuarioService;

    public UsuarioController() {
        this.repositorioUsuario = new UsuarioDAO();
        this.usuarioService = new UsuarioService( repositorioUsuario);
    }

    @POST
    public Response salvar(Usuario usuario) {
        try {
            usuarioService.adicionar(usuario);
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
        Usuario usuario = repositorioUsuario.buscarPorId(id);

        Response.Status status =
                (usuario == null) ? Response.Status.NOT_FOUND : Response.Status.OK;

        return Response
                .status(status)
                .entity(usuario)
                .build();
    }

    @GET
    @Path("/email/{email}")
    public Response buscarPorEmail(@PathParam("email") String email) {
        Usuario usuario = repositorioUsuario.buscarPorEmail(email);

        Response.Status status =
                (usuario == null) ? Response.Status.NOT_FOUND : Response.Status.OK;

        return Response
                .status(status)
                .entity(usuario)
                .build();
    }

    @GET
    public Response listarTodos() {
        ArrayList<Usuario> usuarios = repositorioUsuario.listarTodos();

        Response.Status status =
                usuarios.isEmpty() ? Response.Status.NOT_FOUND : Response.Status.OK;

        return Response
                .status(status)
                .entity(usuarios)
                .build();
    }

    @PUT
    @Path("/{id}")
    public Response atualizar(@PathParam("id") int id, Usuario usuarioAtualizado) {
        try {
            usuarioService.atualizar(id, usuarioAtualizado);
            return Response.status(Response.Status.OK).build();
        } catch (RuntimeException e) {
            e.printStackTrace();
            return Response
                    .status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(e.getMessage())
                    .build();
        }
    }


}
