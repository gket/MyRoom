package com.gket.myroom.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
//Eğer entity yazar ver bırakırsanız table adı Class namei olacaktır ama farklı bir table adı vermek isterseniz
// @Entity (tableName = notes) diyebilirsiniz
data class Note(
    @PrimaryKey(autoGenerate = true)
    var noteId: Int? = null,
    var noteTitle: String = "",
    var noteDescription: String = "",
    var notePoint: Int = 0
)