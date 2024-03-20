package ray.playground.techcaseabnrt.persistence.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;

@Embeddable
@Data
public class QuantityEntity {

    @Column(name = "quantity_type")
    private String quantityType;

    @Column(name = "quantity_amount")
    private double amount;

    @Column(name = "quantity_unit")
    private String units;
}
