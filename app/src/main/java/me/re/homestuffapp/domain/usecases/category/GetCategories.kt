package me.re.homestuffapp.domain.usecases.category

import kotlinx.coroutines.flow.Flow
import me.re.homestuffapp.data.dao.CategoryDao
import me.re.homestuffapp.domain.model.CategoryWithItemCount
import javax.inject.Inject

class GetCategories @Inject constructor(
    private val categoryDao: CategoryDao
) {
    operator fun invoke(): Flow<List<CategoryWithItemCount>> = categoryDao.getCategoriesWithItemsCount()
}