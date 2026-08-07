package az.rentcar.controller;

import az.rentcar.dto.categoryDTOs.GetCategory;
import az.rentcar.dto.categoryDTOs.PostCategory;
import az.rentcar.dto.categoryDTOs.PutCategory;
import az.rentcar.service.impl.CategoryServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/category")
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryServiceImpl categoryService;

    @GetMapping("/{id}")
    public ResponseEntity<GetCategory> getById(@PathVariable Long id) {
        return ResponseEntity.ok(categoryService.getById(id));
    }

    @GetMapping
    public ResponseEntity<List<GetCategory>> getAll() {
        return ResponseEntity.ok(categoryService.getAll());
    }

    @PostMapping
    public ResponseEntity<GetCategory> create(@RequestBody PostCategory postDto) {
        return ResponseEntity.ok(categoryService.create(postDto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<GetCategory> update(@PathVariable Long id,
                                              @RequestBody PutCategory putDto) {
        return ResponseEntity.ok(categoryService.update(id, putDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        categoryService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
