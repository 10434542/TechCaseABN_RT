package ray.playground.techcaseabnrt.domain.models;

import lombok.Builder;
import lombok.Value;

@Value
@Builder(toBuilder = true)
public class Quantity {

    private QuantityType quantityType;

    private double amount;

    private String units;
}
