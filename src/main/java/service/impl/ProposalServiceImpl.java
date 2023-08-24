package service.impl;

import java.time.LocalDateTime;

import dto.ProposalDTO;
import dto.ProposalDatailsDTO;
import entity.ProposalEntity;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import message.KafkaEvent;
import repository.ProposalRepository;
import service.ProposalService;

@ApplicationScoped
@Transactional
public class ProposalServiceImpl implements ProposalService {

  @Inject
  ProposalRepository proposalRepository;

  @Inject
  KafkaEvent event;

  @Override
  public ProposalDatailsDTO findFullProposal(long id) {

    ProposalEntity proposal = proposalRepository.findById(id);

    return ProposalDatailsDTO.builder()
        .country(proposal.getCountry())
        .customer(proposal.getCustomer())
        .priceTonne(proposal.getPriceTonne())
        .proposalId(proposal.getId())
        .proposalValidityDays(proposal.getProposalValidityDays())
        .build();

  }

  @Override
  public void createNewProposal(ProposalDatailsDTO proposalDatailsDTO) {

    ProposalDTO createProposal = biuldAndSaveNewProposal(proposalDatailsDTO);

    event.sendNewKafkaEvent(createProposal);

  }

  @Override
  public void removeProposal(long id) {

    proposalRepository.deleteById(id);

  }

  private ProposalDTO biuldAndSaveNewProposal(ProposalDatailsDTO proposalDatailsDTO) {

    try {

      ProposalEntity proposal = ProposalEntity.builder()
          .created(LocalDateTime.now())
          .proposalValidityDays(proposalDatailsDTO.getProposalValidityDays())
          .country(proposalDatailsDTO.getCountry())
          .customer(proposalDatailsDTO.getCustomer())
          .priceTonne(proposalDatailsDTO.getPriceTonne())
          .tonnes(proposalDatailsDTO.getTonnes())
          .build();

      proposalRepository.persist(proposal);

      return ProposalDTO.builder()
          .proposalId(proposal.getId())
          .customer(proposal.getCustomer())
          .priceTonne(proposal.getPriceTonne())
          .build();

    } catch (Exception e) {

      e.printStackTrace();
      throw new RuntimeException();

    }

  }

}
