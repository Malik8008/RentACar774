package az.rentcar.repository;

import az.rentcar.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category,Long> {
    Optional<Category> findByIdAndIsDeletedFalse(Long id);
    List<Category> findAllByIsDeletedFalse();
}
