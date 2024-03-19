package co.istad.sbdemo.dto;

public record ProductRequest(
        String name,
        Double price,
        Integer qty
) {
}
