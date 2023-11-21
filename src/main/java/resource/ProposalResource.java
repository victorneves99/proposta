package resource;

import org.eclipse.microprofile.jwt.JsonWebToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import dto.ProposalDatailsDTO;
import io.quarkus.security.Authenticated;
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;
import service.ProposalService;

@Path("/api/proposal")
@Authenticated
public class ProposalResource {

  @Inject
  ProposalService proposalService;

  @Inject
  JsonWebToken jsonWebToken;

  private final Logger LOG = LoggerFactory.getLogger(ProposalResource.class);

  @GET
  @Path("/{id}")
  @RolesAllowed({ "user", "manager" })
  public ProposalDatailsDTO findDetailsProposal(@PathParam("id") long id) {

    LOG.info("--- findDetailsProposal ---");
    return proposalService.findFullProposal(id);

  }

  @POST
  @RolesAllowed("proposal-customer")
  public Response createProposal(ProposalDatailsDTO proposalDatailsDTO) {

    try {
      LOG.info("--- criando proposta  ---");
      proposalService.createNewProposal(proposalDatailsDTO);
      return Response.ok().status(Status.CREATED).build();

    } catch (Exception e) {

      return Response.serverError().build();

    }

  }

  @DELETE
  @Path("/{id}")
  @RolesAllowed("manager")
  public Response removeProposal(@PathParam("id") long id) {

    try {

      proposalService.removeProposal(id);
      return Response.noContent().status(Status.NO_CONTENT).build();

    } catch (Exception e) {

      return Response.serverError().build();
    }

  }

}
