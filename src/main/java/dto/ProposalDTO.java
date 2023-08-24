package dto;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.jackson.Jacksonized;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Jacksonized
public class ProposalDTO {

  private Long proposalId;

  private String customer;

  private BigDecimal priceTonne;

}
