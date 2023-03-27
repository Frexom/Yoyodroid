package quoi.feur.yoyodroid.entities

class Level {
    lateinit var id: Integer
    lateinit var name: String

    constructor(id: Integer, name:String){
        this.id = id
        this.name = name
    }
}