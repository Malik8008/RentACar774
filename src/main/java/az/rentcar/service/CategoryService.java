package az.rentcar.service;

import az.rentcar.dto.categoryDTOs.GetCategory;
import az.rentcar.dto.categoryDTOs.PostCategory;
import az.rentcar.dto.categoryDTOs.PutCategory;

import java.util.List;

public interface CategoryService {
    GetCategory getById(Long id);
    List<GetCategory> getAll();
    GetCategory create(PostCategory postDto);
    GetCategory update(Long id, PutCategory putDto);
    void delete(Long id);
}
