package uni.web_lab2.Category;

import org.springframework.stereotype.Service;

@Service
public class CategoryDTOMapper{

    public Category toEntity(CategoryCreateDTO categoryCreateDTO){
        return new Category(
            categoryCreateDTO.title(),
            categoryCreateDTO.description()
        );
    }

    public static Category toEntity(CategoryResponseDTO categoryResponseDTO){
        return new Category(
                categoryResponseDTO.id(),
                categoryResponseDTO.title(),
                categoryResponseDTO.description(),
                categoryResponseDTO.totalAmount()
        );
    }

    public static CategoryResponseDTO toResponse(Category category){
        return new CategoryResponseDTO(
                category.getId(),
                category.getTitle(),
                category.getAmountOfSpendings(),
                category.getDescription()
        );
    }
}
