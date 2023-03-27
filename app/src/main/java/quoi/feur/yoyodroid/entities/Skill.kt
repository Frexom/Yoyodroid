package quoi.feur.yoyodroid.entities

class Skill {
    lateinit var id: Integer
    lateinit var name: String
    lateinit var level :Level

    constructor(id: Integer, name:String, level:Level){
        this.id = id
        this.name = name
        this.level = level
    }
}