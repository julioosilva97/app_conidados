package com.convidados.service.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "guest")
class GuestModel{

    @PrimaryKey(autoGenerate = true)
    //@ColumnInfo(name="id") se não tiver o @ColumnInfo, o nome salvo é o nome do atributo
    var id : Int = 0

    var name : String = ""

    var presence : Boolean = true

}