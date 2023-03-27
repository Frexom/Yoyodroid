package quoi.feur.yoyodroid.entities


class Formation {
    lateinit var id: Integer
    lateinit var name: String
    lateinit var level: Level
    lateinit var managers : List<Initiator>


    constructor(id: Integer, name:String, level: Level, managers: List<Initiator>){
        this.id = id
        this.name = name
        this.level = level
    }
}