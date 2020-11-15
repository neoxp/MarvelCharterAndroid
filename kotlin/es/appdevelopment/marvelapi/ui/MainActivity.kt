package es.appdevelopment.marvelapi

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.StaggeredGridLayoutManager
import android.support.v7.widget.StaggeredGridLayoutManager.GAP_HANDLING_MOVE_ITEMS_BETWEEN_SPANS
import android.support.v7.widget.StaggeredGridLayoutManager.VERTICAL
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import es.appdevelopment.marvelapi.data.OrdenationRepository
import es.appdevelopment.marvelapi.R
import es.appdevelopment.marvelapi.framework.MarvelRepository
import es.appdevelopment.marvelapi.framework.OrdenationDataSource
import es.appdevelopment.marvelapi.framework.marvel.MarvelClientService
import es.appdevelopment.marvelapi.ui.adapters.SuperheroesAdapter
import es.appdevelopment.marvelapi.ui.model.MarvelSuperheroeForList
import es.appdevelopment.marvelapi.ui.presenters.MainPresenter
import es.appdevelopment.usecases.*
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity(), MainPresenter.ViewMain {

    private val presenter: MainPresenter
    private lateinit var adapter: SuperheroesAdapter

    private lateinit var menu: Menu

    init {
        val marvelClientService = MarvelClientService()
        val marvelRepository = MarvelRepository(marvelClientService)

        val ordenationDataSource = OrdenationDataSource()

        presenter = MainPresenter(
            this,
            ListSuperheroes(marvelRepository),
            GetOrdenation(ordenationDataSource),
            SaveOrdenation(ordenationDataSource)
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        presenter.create()

        initializeRecyclerView()
    }

    private fun initializeRecyclerView() {
        adapter = SuperheroesAdapter(presenter, {presenter.getMoreSuperheroes()})

        val layoutManager = StaggeredGridLayoutManager(3, VERTICAL)
        layoutManager.gapStrategy = GAP_HANDLING_MOVE_ITEMS_BETWEEN_SPANS

        superheroes_recyclerview.adapter = adapter
        superheroes_recyclerview.layoutManager = layoutManager
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        this.menu =  menu!!

        presenter.calculateIconMenu()

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item!!.itemId) {
            R.id.action_order -> {
                presenter.getSuperheroesWithOrdenation()
            }
        }

        return super.onOptionsItemSelected(item)
    }


    override fun changeIconMenu(iconId: Int) {
        menu.getItem(0).setIcon(iconId)
    }

    override fun listSuperheroes(marvelSuperHeroes: List<MarvelSuperheroeForList>) {
        adapter.addAll(marvelSuperHeroes)
    }

    override fun cleanSuperheroes() {
        adapter.clean()
    }

    override fun showEmptyCase() {
        superheroe_text.visibility = View.VISIBLE
    }

    override fun hideEmptyCase() {
        superheroe_text.visibility = View.GONE
    }

    override fun showSuperheroe(marvelSuperHeroeId: Int) {
        DetailActivity.open(activity = this, marvelSuperHeroeId = marvelSuperHeroeId)
    }

    override fun showToastMessage(stringId: Int) {
        Toast.makeText(this, stringId, Toast.LENGTH_LONG).show()
    }

    override fun showLoading() {
        superheroes_progressbar.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        superheroes_progressbar.visibility = View.GONE
    }

    override fun onDestroy() {
        presenter.onDestroy()
        super.onDestroy()
    }
}
