package uni.web_lab2.Category;


import jakarta.validation.constraints.*;

public record CategoryCreateDTO(

        @NotEmpty(message = "Title must not be empty")
        @Size(max = 55, message = "Title should contain less than 55 characters")
        String title,

        @NotEmpty(message = "Description must not be empty")
        @Size(max = 100, message = "Title should contain less than 100 characters")
        String description
) {
}
