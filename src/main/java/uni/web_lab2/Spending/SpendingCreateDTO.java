package uni.web_lab2.Spending;

import jakarta.validation.constraints.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

public record SpendingCreateDTO(

        @Size(max = 50, message = "Note should contain less than 55 characters")
        String note,

        @NotNull(message = "Choose category")
        @Positive(message = "Chosen category does not exist")
        Long categoryId,

        @NotNull(message = "Amount must not be empty or equal to zero")
        @DecimalMin(value = "0.1", inclusive = false, message = "Amount must be greater than zero")
        Double amount,

        //Do not validate wether the spending in the future
        @NotNull(message = "Date must not be empty")
        @DateTimeFormat(pattern = "yyyy-MM-dd")
        LocalDate date,

        Boolean isRepeated
){
}
