@file:Suppress("MemberVisibilityCanBePrivate", "unused")

package com.example.demo.data.model

import javafx.beans.property.SimpleIntegerProperty
import javafx.beans.property.SimpleStringProperty
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.IdTable
import org.jetbrains.exposed.sql.ResultRow
import tornadofx.*

/**
 * @author forntoh
 * @version 1.0
 * @created 12-Jun-2020 11:17:28 AM
 */
object SpecialitiesTbl : IdTable<Int>() {
    override val id = integer("SpecialityID").autoIncrement().entityId()
    val Name = varchar("Name", 64)
    override val primaryKey = PrimaryKey(columns = *arrayOf(id))
}

class Speciality(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<Speciality>(SpecialitiesTbl)

    var name by SpecialitiesTbl.Name
}

fun ResultRow.toSpecialityEntry() = SpecialityEntry(
        this[SpecialitiesTbl.id].value,
        this[SpecialitiesTbl.Name]
)

class SpecialityEntry(id: Int, name: String) {
    var idProperty = SimpleIntegerProperty(id)
    val id by idProperty

    var nameProperty = SimpleStringProperty(name)
    val name by nameProperty
}

class SpecialityModel : ItemViewModel<SpecialityEntry>() {
    val id = bind { item?.idProperty }
    val name = bind { item?.nameProperty }
}