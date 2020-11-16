package es.appdevelopment.marvelapi.ui.presenters

import es.appdevelopment.marvelapi.ui.model.MarvelSuperheroeForDetail
import es.appdevelopment.marvelapi.ui.model.toMarvelSuperheroeFromDetail
import es.appdevelopment.usecases.ShowSuperheroe
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class DetailPresenter(
    private var view: ViewDetail?,
    private val showSuperheroe: ShowSuperheroe
) {

    fun create(marvelSuperheroeId: Int) {
        view?.showLoading()
        renderSuperheroe(marvelSuperheroeId)
    }

    private fun renderSuperheroe(marvelSuperheroeId: Int) {
        GlobalScope.launch(Dispatchers.Main) {
            val superheroe = withContext(Dispatchers.IO) { showSuperheroe(marvelSuperheroeId) }
            view?.hideLoading()

            if (superheroe.id < 0) {
                view?.showEmptyMessage()
                view?.finishActivity()
            } else {
                val marvelSuperHeroe = superheroe.toMarvelSuperheroeFromDetail()
                view?.updateActionBar(marvelSuperHeroe.name)
                view?.showSuperheroe(marvelSuperHeroe)
            }
        }
    }

    fun onDestroy() {
        view = null
    }

    interface ViewDetail {
        fun hideLoading()
        fun showLoading()
        fun showEmptyMessage()
        fun finishActivity()
        fun updateActionBar(marvelSuperheroeName: String)
        fun showSuperheroe(marvelSuperHeroe: MarvelSuperheroeForDetail)
    }
}
