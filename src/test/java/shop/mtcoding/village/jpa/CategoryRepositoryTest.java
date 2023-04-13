package shop.mtcoding.village.jpa;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;
import shop.mtcoding.village.model.category.Category;
import shop.mtcoding.village.model.category.CategoryRepository;
import shop.mtcoding.village.model.chatRoom.ChatRoom;
import shop.mtcoding.village.model.chatRoom.ChatRoomRepository;

import java.util.List;
import java.util.Optional;

@DataJpaTest
@ExtendWith(SpringExtension.class)
public class CategoryRepositoryTest {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private TestEntityManager entityManager;

    @BeforeEach
    public void init() {
        setUp("운동시설");
    }

    @Test
    @Transactional
    void selectAll() {
        List<Category> categories = categoryRepository.findAll();
        Assertions.assertNotEquals(categories.size(), 0);

        Category category = categories.get(0);
        Assertions.assertEquals(category.getName(), "운동시설");
    }

    @Test
    @Transactional
    void selectAndUpdate() {
        var optionalCategory = this.categoryRepository.findById(4L);

        if(optionalCategory.isPresent()) {
            var result = optionalCategory.get();
            Assertions.assertEquals(result.getName(),"운동시설");

            var name = "운동시설2";
            result.setName(name);
            Category merge = entityManager.merge(result);

            Assertions.assertEquals(merge.getName(), "운동시설2");
        } else {
            Assertions.assertNotNull(optionalCategory.get());
        }
    }

    @Test
    @Transactional
    void insertAndDelete() {
        Category category = setUp("운동시설3");
        Optional<Category> findAddress = this.categoryRepository.findById(category.getId());

        if(findAddress.isPresent()) {
            var result = findAddress.get();
            Assertions.assertEquals(result.getName(), "운동시설3");
            entityManager.remove(category);
            Optional<Category> deleteCategory = this.categoryRepository.findById(category.getId());
            if (deleteCategory.isPresent()) {
                Assertions.assertNull(deleteCategory.get());
            }
        } else {
            Assertions.assertNotNull(findAddress.get());
        }
    }


    private Category setUp(String name) {
        Category category = new Category();
        category.setName(name);
        return this.entityManager.persist(category);
    }
}