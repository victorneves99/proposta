package message;

import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import dto.ProposalDTO;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class KafkaEvent {

  private final Logger LOG = LoggerFactory.getLogger(KafkaEvent.class);

  @Channel("proposal")
  Emitter<ProposalDTO> proposalRequest;

  public void sendNewKafkaEvent(ProposalDTO proposalDTO) {
    LOG.info("-- Enviando Nova Proposta para topico Kafka --");

    proposalRequest.send(proposalDTO).toCompletableFuture().join();

  }

}
