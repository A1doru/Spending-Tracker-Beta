package uni.web_lab2.Spending;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uni.web_lab2.Category.CategoryDTOMapper;
import uni.web_lab2.Category.CategoryService;
import uni.web_lab2.Exceptions.CategoryNotFoundException;
import uni.web_lab2.Exceptions.SpendingNotFoundException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SpendingService {

    private final SpendingRepository spendingRepository;
    private final CategoryService categoryService;
    private final SpendingDTOMapper mapper;

    @Autowired
    public SpendingService(SpendingRepository spendingRepository,
                           CategoryService categoryService,
                           SpendingDTOMapper mapper) {
        this.spendingRepository = spendingRepository;
        this.categoryService = categoryService;
        this.mapper = mapper;
    }

    public List<SpendingResponseDTO> getAllSpending() {
        return spendingRepository.findAll().stream().map(SpendingDTOMapper::toResponseDTO).collect(Collectors.toList());
    }

/*    public Page<SpendingResponseDTO> getAllSpending(int page, int size) {
        return spendingRepository.findAll(PageRequest.of(page, size, Sort.by("date"))).map(SpendingDTOMapper::toResponseDTO);
    }*/

    public SpendingResponseDTO getSpendingById(Long id){
        return spendingRepository.findById(id)
                .map(SpendingDTOMapper::toResponseDTO)
                .orElseThrow(() -> new SpendingNotFoundException(id));
    }

    @Transactional
    public void addSpending(SpendingCreateDTO spendingCreateDTO) {

        Long id = spendingCreateDTO.categoryId();
        if(!categoryService.existsById(id)){
            throw new CategoryNotFoundException(id);
        }
        Spending spending = mapper.toEntity(spendingCreateDTO);

        categoryService.increaseTotalSpent(id, spending);

        spendingRepository.save(spending);
    }

    public void deleteSpendingById(Long id) {
        Spending spending = spendingRepository.findById(id)
                .orElseThrow(() -> new SpendingNotFoundException(id));
        //Changing total spent field in category
        categoryService.decreaseTotalSpent(
                spending.getCategory().getId(),
                spending);
        spendingRepository.deleteById(id);
    }

    public void updateSpending(Long id, SpendingCreateDTO spendingCreateDTO){
        Spending spending = spendingRepository.findById(id)
                .orElseThrow(()-> new SpendingNotFoundException(id));

        //Decreasing totalSpent field from previous category
        categoryService.decreaseTotalSpent(
                spending.getCategory().getId(),
                spending);

        //Setting new category for spending
        Long newCategoryId = spendingCreateDTO.categoryId();
        if(!categoryService.existsById(newCategoryId)){
            throw new CategoryNotFoundException(newCategoryId);
        }
        spending.setCategory(CategoryDTOMapper
                .toEntity(categoryService.getCategoryById(newCategoryId)));

        //Increasing totalSpent field for new category
        categoryService.increaseTotalSpent(
                newCategoryId,
                mapper.toEntity(spendingCreateDTO)
        );

        spending.setAmount(spendingCreateDTO.amount());
        spending.setNote(spendingCreateDTO.note());
        spending.setDate(spendingCreateDTO.date());
        spending.setRepeated(spendingCreateDTO.isRepeated());

        spendingRepository.save(spending);
    }

    //Deletes all spending from the corresponding category
    public void deleteByCategoryId(Long id){
        spendingRepository.deleteByCategoryId(id);
    }
}

