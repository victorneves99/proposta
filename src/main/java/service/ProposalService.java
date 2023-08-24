package service;

import dto.ProposalDatailsDTO;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public interface ProposalService {

  ProposalDatailsDTO findFullProposal(long id);

  void createNewProposal(ProposalDatailsDTO proposalDatailsDTO);

  void removeProposal(long id);

}
