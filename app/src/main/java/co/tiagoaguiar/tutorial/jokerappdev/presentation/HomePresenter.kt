package co.tiagoaguiar.tutorial.jokerappdev.presentation

import android.graphics.Color
import android.os.Handler
import android.os.Looper
import android.view.View
import co.tiagoaguiar.tutorial.jokerappdev.data.CategoryRemoteDataSource
import co.tiagoaguiar.tutorial.jokerappdev.data.ListCategoryCallback
import co.tiagoaguiar.tutorial.jokerappdev.model.Category
import co.tiagoaguiar.tutorial.jokerappdev.view.CategoryItem
import co.tiagoaguiar.tutorial.jokerappdev.view.HomeFragment

class HomePresenter (
    private val view: HomeFragment,
    private val dataSource: CategoryRemoteDataSource = CategoryRemoteDataSource()
    ) :ListCategoryCallback {

// INPUT
    fun findAllCategories() {
        view.showProgress()
    dataSource.findAllCategories(this)


    }
// OUTPUT (SUCESSO | FALHA | COMPLETE)
    override fun onSuccess(response: List<String>) {

    val start = 0
    val end = 190
    val diff = (end - start)/ response.size

        val categories = response.mapIndexed { index, s ->
            val hsv = floatArrayOf(
                start + (diff * index).toFloat(),
                100.0f,
                100.0f,
            )

            Category(s,Color.HSVToColor(hsv).toLong()) }

        view.showCategories(categories)

    }

    override fun onError(response: String) {
        view.showFailure(response)
    }

    override fun onComplete() {
        view.hideProgress()
    }


}