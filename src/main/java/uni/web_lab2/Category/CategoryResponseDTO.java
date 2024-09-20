package uni.web_lab2.Category;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_DEFAULT)
public record CategoryResponseDTO(
        Long id,
        String title,
        Double totalAmount,
        String description
) {
}
