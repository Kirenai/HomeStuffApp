package me.kire.re.homestuffapp.domain.usecases.category

import kotlinx.coroutines.flow.Flow
import me.kire.re.homestuffapp.data.dao.CategoryDao
import me.kire.re.homestuffapp.data.entity.CategoryEntity
import javax.inject.Inject

class GetCategories @Inject constructor(
    private val categoryDao: CategoryDao
) {
    operator fun invoke(): Flow<List<CategoryEntity>> = categoryDao.getCategories()
}