package br.com.fiap.biblioteca.controller;

import br.com.fiap.biblioteca.dominio.Conversa;
import br.com.fiap.biblioteca.infra.dao.ConversaDAO;
import br.com.fiap.biblioteca.repositorio.RepositorioConversa;
import br.com.fiap.biblioteca.service.ConversaService;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.MediaType;

import java.util.List;

@Path("/conversas")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ConversaController {

    private RepositorioConversa repositorio;
    private ConversaService service;

    public ConversaController() {
        this.repositorio = new ConversaDAO();
        this.service = new ConversaService(repositorio);
    }

    // ------------------ CORS ------------------
    @OPTIONS
    @Path("{any:.*}")
    public Response options() {
        return Response.ok()
                .header("Access-Control-Allow-Origin", "*")
                .header("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS")
                .header("Access-Control-Allow-Headers", "Content-Type")
                .build();
    }
    // ------------------------------------------

    @POST
    public Response salvar(Conversa conversa) {
        service.adicionar(conversa);
        return Response.status(Response.Status.CREATED).build();
    }

    @GET
    @Path("/id/{id}")
    public Response buscarPorId(@PathParam("id") String id) {
        Conversa conversa = service.buscarPorId(id);

        if (conversa == null)
            return Response.status(Response.Status.NOT_FOUND).build();

        return Response.ok(conversa).build();
    }

    @GET
    @Path("/usuario/{idUsuario}")
    public Response buscarPorUsuario(@PathParam("idUsuario") int idUsuario) {
        List<Conversa> conversas = service.buscarPorUsuario(idUsuario);

        return conversas.isEmpty()
                ? Response.status(Response.Status.NOT_FOUND).build()
                : Response.ok(conversas).build();
    }

    @GET
    public Response listarTodas() {
        List<Conversa> conversas = service.listarTodas();
        return Response.ok(conversas).build();
    }

    @PUT
    @Path("/id/{id}")
    public Response atualizar(@PathParam("id") String id, Conversa conversa) {
        Conversa existente = service.buscarPorId(id);

        if (existente == null)
            return Response.status(Response.Status.NOT_FOUND).build();

        // garante que o ID não mudará
        Conversa nova = new Conversa(
                id,
                conversa.getIdUsuario(),
                conversa.getDataHora(),
                conversa.getConteudo()
        );

        service.atualizar(nova);

        return Response.ok().build();
    }

    @DELETE
    @Path("/id/{id}")
    public Response excluir(@PathParam("id") String id) {
        service.excluir(id);
        return Response.status(Response.Status.NO_CONTENT).build();
    }

}
