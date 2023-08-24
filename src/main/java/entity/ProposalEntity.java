package entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "proposal")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProposalEntity {

  @Id
  @GeneratedValue
  private Long id;
  private String customer;
  @Column(name = "price_tonne")
  private BigDecimal priceTonne;
  private Integer tonnes;
  private String country;
  @Column(name = "proposal_validity_days")
  private Integer proposalValidityDays;
  private LocalDateTime created;

}
