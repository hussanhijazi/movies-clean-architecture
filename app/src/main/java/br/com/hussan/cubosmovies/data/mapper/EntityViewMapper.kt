package br.com.hussan.cubosmovies.data.mapper

interface EntityViewMapper<T, V> {
    fun mapToView(type: V): T
}
