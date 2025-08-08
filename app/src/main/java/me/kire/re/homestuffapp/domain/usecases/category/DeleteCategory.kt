package me.kire.re.homestuffapp.domain.usecases.category

import me.kire.re.homestuffapp.data.dao.CategoryDao
import javax.inject.Inject

class DeleteCategory @Inject constructor(
    private val categoryDao: CategoryDao
) {
    suspend operator fun invoke(categoryId: Long) {
        this.categoryDao.deleteCategory(categoryId = categoryId)
    }
}