package uni.web_lab2.Spending;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uni.web_lab2.Exceptions.CategoryNotFoundException;
import uni.web_lab2.Exceptions.SpendingNotFoundException;

import java.util.List;

@RestController
@RequestMapping(path = "test/spending")
public class SpendingController {

    private final SpendingService spendingService;

    @Autowired
    public SpendingController(SpendingService spendingService) {
        this.spendingService = spendingService;
    }

/*
    @GetMapping
    public ResponseEntity<Page<SpendingResponseDTO>> getAllSpendings (@RequestParam(value="page", defaultValue = "0") int page,
                                                                      @RequestParam(value="size", defaultValue = "10") int size){
        return ResponseEntity.ok().body(spendingService.getAllSpending(page, size));
    }
*/

    @GetMapping
    public List<SpendingResponseDTO> getAllSpendings(){
        return spendingService.getAllSpending();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getSpendingById(@PathVariable("id") Long id){
        if(id != null){
            try{
                return ResponseEntity.ok(spendingService.getSpendingById(id));
            } catch(SpendingNotFoundException e){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
            }
        }
        else{
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @PostMapping
    public ResponseEntity<?> addSpending(@Valid @RequestBody SpendingCreateDTO spendingCreateDTO) {
        try {
            spendingService.addSpending(spendingCreateDTO);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (CategoryNotFoundException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
        catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<?> deleteSpending(@PathVariable("id") Long id){
        try{
            spendingService.deleteSpendingById(id);
            return ResponseEntity.status(HttpStatus.OK).build();
        } catch (SpendingNotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PutMapping(path ="/{id}")
    public ResponseEntity<?> updateSpending(@PathVariable("id") Long id,
                                            @Valid @RequestBody SpendingCreateDTO spendingCreateDTO){
        try{
            spendingService.updateSpending(id, spendingCreateDTO);
            return ResponseEntity.status(HttpStatus.OK).build();
        } catch (SpendingNotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}
