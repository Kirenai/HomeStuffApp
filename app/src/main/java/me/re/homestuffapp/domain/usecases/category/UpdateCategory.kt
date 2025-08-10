package me.re.homestuffapp.domain.usecases.category

import me.re.homestuffapp.data.dao.CategoryDao
import me.re.homestuffapp.data.entity.CategoryEntity
import me.re.homestuffapp.domain.model.Category
import javax.inject.Inject

class UpdateCategory @Inject constructor(
    private val categoryDao: CategoryDao
) {
    suspend operator fun invoke(
        category: Category
    ) {
        println("category = $category")
        categoryDao.updateCategory(
            CategoryEntity(
                categoryId = category.categoryId,
                name = category.name,
            )
        )
    }
}