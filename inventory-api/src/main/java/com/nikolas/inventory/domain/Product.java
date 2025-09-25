
package com.nikolas.inventory.domain;
import jakarta.persistence.*; import lombok.*; import java.math.BigDecimal;
@Entity @Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Product { @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
@Column(nullable=false, unique=true) private String sku; @Column(nullable=false) private String name; private BigDecimal price; private Integer stock; }
