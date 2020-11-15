package es.appdevelopment.marvelapi.ui.model

import es.appdevelopment.domain.Superheroe
import org.joda.time.DateTime
import org.joda.time.Days

fun Superheroe.toMarvelSuperheroeFromList(): MarvelSuperheroeForList {
    return MarvelSuperheroeForList(this.id, this.name, this.imageUrl)
}

fun Superheroe.toMarvelSuperheroeFromDetail(): MarvelSuperheroeForDetail {
    val dt = DateTime(this.lastModify)
    val now = DateTime()
    val interval = Days.daysBetween(dt.toLocalDate(), now.toLocalDate()).getDays()

    return MarvelSuperheroeForDetail(this.id, this.name, this.description, interval, this.imageUrl)
}

fun emptySuperheroe(): Superheroe {
    return Superheroe(-1, "", "", "", "")
}