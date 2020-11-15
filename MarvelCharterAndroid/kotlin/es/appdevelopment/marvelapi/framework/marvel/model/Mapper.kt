package es.appdevelopment.marvelapi.framework.marvel.model

import es.appdevelopment.domain.Superheroe
import es.appdevelopment.marvelapi.framework.marvel.model.Character as MarvelCharacter

fun MarvelCharacter.toSuperheroe(): Superheroe {
    return Superheroe(this.id, this.name, this.description, this.modified, "${this.thumbnail.path}.${this.thumbnail.extension}")
}