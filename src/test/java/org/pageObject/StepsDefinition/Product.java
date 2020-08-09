package org.pageObject.StepsDefinition;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Product {
    private final String title;
    private final Double price;
    private final Double salePrice;
}
