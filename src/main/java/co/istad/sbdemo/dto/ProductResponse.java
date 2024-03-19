package co.istad.sbdemo.dto;

public record ProductResponse(
        String uuid,
        String name,
        Double price,
        Integer qty
) {
}
