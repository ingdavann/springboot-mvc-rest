package co.istad.sbdemo.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@Data
public class Product {
    private Integer id;
    private String uuid;
    private String name;
    private Double price;
    private Integer qty;
    private Boolean isStock;
    private LocalDateTime createAt;
}
