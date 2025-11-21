package br.com.fiap.biblioteca.controller;

import br.com.fiap.biblioteca.dominio.Conversa;
import br.com.fiap.biblioteca.infra.dao.ConversaDAO;
import br.com.fiap.biblioteca.repositorio.RepositorioConversa;
import br.com.fiap.biblioteca.service.ConversaService;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/conversas")
public class ConversaController {

    private RepositorioConversa repositorioConversa;
    private ConversaService conversaService;

    public ConversaController() {
        this.repositorioConversa = new ConversaDAO();
        this.conversaService = new ConversaService(repositorioConversa);
    }

    @POST
    public Response salvar(Conversa conversa) {
        try {
            conversaService.adicionar(conversa);
            return Response.status(Response.Status.CREATED).build();
        } catch (RuntimeException e) {
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
        }
    }

    @GET
    @Path("/id/{id}")
    public Response buscarPorId(@PathParam("id") String id) {
        Conversa conversa = conversaService.buscarPorId(id);

        Response.Status status =
                (conversa == null) ? Response.Status.NOT_FOUND : Response.Status.OK;

        return Response.status(status).entity(conversa).build();
    }

    @GET
    @Path("/usuario/{idUsuario}")
    public Response buscarPorUsuario(@PathParam("idUsuario") int idUsuario) {
        List<Conversa> conversas = conversaService.buscarPorUsuario(idUsuario);

        Response.Status status =
                conversas.isEmpty() ? Response.Status.NOT_FOUND : Response.Status.OK;

        return Response.status(status).entity(conversas).build();
    }

    @GET
    public Response listarTodos() {
        List<Conversa> conversas = conversaService.listarTodas();

        Response.Status status =
                conversas.isEmpty() ? Response.Status.NOT_FOUND : Response.Status.OK;

        return Response.status(status).entity(conversas).build();
    }
}
