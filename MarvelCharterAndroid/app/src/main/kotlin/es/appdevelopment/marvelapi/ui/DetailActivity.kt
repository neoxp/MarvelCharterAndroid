package es.appdevelopment.marvelapi

import android.app.Activity
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.app.ActionBar
import android.view.View
import es.appdevelopment.marvelapi.R
import es.appdevelopment.marvelapi.framework.MarvelRepository
import es.appdevelopment.marvelapi.framework.marvel.MarvelClientService
import es.appdevelopment.marvelapi.ui.commons.loadImage
import es.appdevelopment.marvelapi.ui.commons.logDebug
import es.appdevelopment.marvelapi.ui.commons.toast
import es.appdevelopment.marvelapi.ui.model.MarvelSuperheroeForDetail
import es.appdevelopment.marvelapi.ui.presenters.DetailPresenter
import es.appdevelopment.usecases.ShowSuperheroe
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : AppCompatActivity(), DetailPresenter.ViewDetail {

    companion object {
        private const val SUPER_HEROE_KEY = "super_heroe_key"

        fun open(activity: Activity, marvelSuperHeroeId: Int) {
            val intent = Intent(activity, DetailActivity::class.java)
            intent.putExtra(SUPER_HEROE_KEY, marvelSuperHeroeId)
            activity.startActivity(intent)
        }
    }

    private val presenter: DetailPresenter
    private var actionbar: ActionBar? = null

    init {
        val marvelClientService = MarvelClientService()
        val marvelRepository = MarvelRepository(marvelClientService)

        presenter = DetailPresenter(
            this,
            ShowSuperheroe(marvelRepository)
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        actionbar = supportActionBar
        actionbar!!.setDisplayHomeAsUpEnabled(true)

        val superheroeId = intent?.extras?.getInt(SUPER_HEROE_KEY)
        presenter.create(superheroeId!!)
    }

    override fun updateActionBar(marvelSuperheroeName: String) {
        this.localClassName.logDebug("******* updateActionBar --> ${marvelSuperheroeName}")

        actionbar!!.title =
            if (marvelSuperheroeName.isEmpty()) resources.getString(R.string.emptyName) else marvelSuperheroeName

    }

    override fun showLoading() {
        superheroes_progressbar.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        superheroes_progressbar.visibility = View.GONE
    }

    override fun showEmptyMessage() {
        this.toast(this, resources.getString(R.string.emptySuperhero))
    }

    override fun finishActivity() {
        finish()
    }

    override fun showSuperheroe(marvelSuperHeroe: MarvelSuperheroeForDetail) {
        superheroe_image.loadImage(marvelSuperHeroe.imageUrl)
        superheroe_description.text =
            if (marvelSuperHeroe.description.isEmpty()) resources.getString(R.string.emptyDesc) else marvelSuperHeroe.description
        superheroe_modified.text = resources.getQuantityString(
            R.plurals.days_last_modified,
            marvelSuperHeroe.daysFormLastModify,
            "${marvelSuperHeroe.daysFormLastModify}"
        )

    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    override fun onDestroy() {
        presenter.onDestroy()
        super.onDestroy()
    }
}
