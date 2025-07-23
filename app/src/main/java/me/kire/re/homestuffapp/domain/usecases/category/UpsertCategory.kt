package me.kire.re.homestuffapp.domain.usecases.category

import me.kire.re.homestuffapp.data.dao.CategoryDao
import me.kire.re.homestuffapp.data.entity.CategoryEntity
import javax.inject.Inject

class UpsertCategory @Inject constructor(
    private val categoryDao: CategoryDao
) {
    suspend operator fun invoke(
        name: String
    ) {
        categoryDao.insert(
            CategoryEntity(
                name = name,
            )
        )
    }
}