package uni.web_lab2.Spending;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uni.web_lab2.Category.CategoryDTOMapper;
import uni.web_lab2.Category.CategoryService;

@Service
public class SpendingDTOMapper {

    private final CategoryService categoryService;
    private final CategoryDTOMapper mapper;

    @Autowired
    public SpendingDTOMapper(CategoryService categoryService, CategoryDTOMapper mapper) {
        this.categoryService = categoryService;
        this.mapper = mapper;
    }

    public static SpendingResponseDTO toResponseDTO(Spending spending) {
        return new SpendingResponseDTO(
                spending.getId(),
                spending.getNote(),
                spending.getCategory().getTitle(),
                spending.getAmount()
        );
    }

    public Spending toEntity(SpendingCreateDTO spendingCreateDTO) {
        return new Spending(
                spendingCreateDTO.note(),
                spendingCreateDTO.amount(),
                mapper.toEntity(categoryService.getCategoryById(spendingCreateDTO.categoryId())),
                spendingCreateDTO.date(),
                spendingCreateDTO.isRepeated()
        );
    }
}
