package uni.web_lab2.Category;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import uni.web_lab2.Exceptions.CategoryNotFoundException;
import uni.web_lab2.Spending.Spending;
import uni.web_lab2.Spending.SpendingService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryService {
    private final CategoryRepository categoryRepository;
    private final CategoryDTOMapper mapper;
    private final SpendingService spendingService;

    @Autowired
    public CategoryService(CategoryRepository categoryRepository,
                           CategoryDTOMapper categoryDTOMapper,
                           @Lazy SpendingService spendingService) {
        this.categoryRepository = categoryRepository;
        this.mapper = categoryDTOMapper;
        this.spendingService = spendingService;
    }

    public List<CategoryResponseDTO> getAllCategories() {
        return categoryRepository.findAll().stream().map(CategoryDTOMapper::toResponse).collect(Collectors.toList());
    }

    public CategoryResponseDTO getCategoryById(Long id){
        return categoryRepository.findById(id)
                .map(CategoryDTOMapper::toResponse)
                .orElseThrow(() -> new CategoryNotFoundException(id));
    }

    @Transactional
    public void addCategory(CategoryCreateDTO categoryCreateDTO) {
        Category category = mapper.toEntity(categoryCreateDTO);
        categoryRepository.save(category);
    }

    public void increaseTotalSpent(Long id, Spending spending){
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new CategoryNotFoundException(id));
        category.setAmountOfSpendings(category.getAmountOfSpendings() + spending.getAmount());
        categoryRepository.save(category);
    }

    public void decreaseTotalSpent(Long id, Spending spending){
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new CategoryNotFoundException(id));
        category.setAmountOfSpendings(category.getAmountOfSpendings() - spending.getAmount());
        categoryRepository.save(category);
    }

    public void updateCategory(Long id, CategoryCreateDTO categoryCreateDTO){
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new CategoryNotFoundException(id));
        category.setTitle(categoryCreateDTO.title());
        category.setDescription(categoryCreateDTO.description());

        categoryRepository.save(category);
    }

    public void deleteCategory(Long id){
        if(!categoryRepository.existsById(id)){
            throw new CategoryNotFoundException(id);
        }
        spendingService.deleteByCategoryId(id);
        categoryRepository.deleteById(id);
    }

    public boolean existsById(Long id){
        return categoryRepository.existsById(id);
    }
}
