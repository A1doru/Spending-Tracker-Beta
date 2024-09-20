package uni.web_lab2.Spending;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface SpendingRepository
        extends JpaRepository<Spending, Long> {

    @Modifying
    @Transactional
    @Query("DELETE FROM Spending s WHERE s.category.id = :categoryId")
    void deleteByCategoryId(@Param("categoryId") Long categoryId);
}
