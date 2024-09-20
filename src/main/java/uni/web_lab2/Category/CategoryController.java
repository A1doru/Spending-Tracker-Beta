package uni.web_lab2.Category;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uni.web_lab2.Exceptions.CategoryNotFoundException;

import java.util.List;

@RestController
@RequestMapping(path = "test/category")
public class CategoryController {
    private final CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService){
        this.categoryService = categoryService;
    }

    @GetMapping
    public List<CategoryResponseDTO> getAllCategories(){
        return categoryService.getAllCategories();
    }

    @GetMapping(path = "/{id}")
    public CategoryResponseDTO getCategoryById(@PathVariable("id") Long id){
        return categoryService.getCategoryById(id);
    }

    @PostMapping
    public ResponseEntity<?> addCategory(@Valid @RequestBody CategoryCreateDTO category){
        try{
            categoryService.addCategory(category);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        }
        catch (CategoryNotFoundException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<?> updateCategory(@PathVariable("id") Long id,
                                            @Valid @RequestBody CategoryCreateDTO categoryCreateDTO){
        try{
            categoryService.updateCategory(id, categoryCreateDTO);
            return ResponseEntity.status(HttpStatus.OK).build();
        }catch(CategoryNotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<?> deleteCategory(@PathVariable("id") Long id){
        try{
            categoryService.deleteCategory(id);
            return ResponseEntity.status(HttpStatus.OK).build();
        } catch (CategoryNotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}
