package me.re.homestuffapp.domain.usecases.category

import me.re.homestuffapp.data.dao.CategoryDao
import me.re.homestuffapp.data.entity.CategoryEntity
import me.re.homestuffapp.domain.model.Category
import javax.inject.Inject

class UpsertCategory @Inject constructor(
    private val categoryDao: CategoryDao
) {
    suspend operator fun invoke(
        category: Category
    ) {
        println("category = $category")
        categoryDao.insert(
            CategoryEntity(
                name = category.name,
            )
        )
    }
}