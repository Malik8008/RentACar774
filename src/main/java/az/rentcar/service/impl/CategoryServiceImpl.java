package az.rentcar.service.impl;

import az.rentcar.dto.categoryDTOs.GetCategory;
import az.rentcar.dto.categoryDTOs.PostCategory;
import az.rentcar.dto.categoryDTOs.PutCategory;
import az.rentcar.entity.Category;
import az.rentcar.exception.IdNotFoundException;
import az.rentcar.repository.CategoryRepository;
import az.rentcar.service.CategoryService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;
    private final ModelMapper modelMapper;
    @Override
    public GetCategory getById(Long id) {
        Category category = categoryRepository.findByIdAndIsDeletedFalse(id)
                .orElseThrow(()-> new IdNotFoundException("Category not found with id: " + id));
        return modelMapper.map(category, GetCategory.class);
    }

    @Override
    public List<GetCategory> getAll() {
        return categoryRepository.findAllByIsDeletedFalse()
                .stream().map(gt-> modelMapper.map(gt, GetCategory.class)).toList();
    }

    @Override
    public GetCategory create(PostCategory postDto) {
        Category category = new Category();
        category.setName(postDto.getName());
        Category saveCategory = categoryRepository.save(category);
        return modelMapper.map(saveCategory,GetCategory.class);
    }

    @Override
    public GetCategory update(Long id, PutCategory putDto) {
        Category existCategory = categoryRepository.findByIdAndIsDeletedFalse(id)
                .orElseThrow(()-> new IdNotFoundException("Category not found with id: " + id));
        existCategory.setName(putDto.getName());
        Category updateCategory = categoryRepository.save(existCategory);
        return modelMapper.map(updateCategory, GetCategory.class);
    }

    @Override
    public void delete(Long id) {
        Category existCategory = categoryRepository.findByIdAndIsDeletedFalse(id)
                .orElseThrow(()-> new IdNotFoundException("Category not found with id: " + id));
        existCategory.setDeleted(true);
        categoryRepository.save(existCategory);
    }
}
