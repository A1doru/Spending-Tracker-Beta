package uni.web_lab2.Spending;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_DEFAULT)
public record SpendingResponseDTO(
        Long id,
        String note,
        String categoryName,
        Double amount
) {

}
