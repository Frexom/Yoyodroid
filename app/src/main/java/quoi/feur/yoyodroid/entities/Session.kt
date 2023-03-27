package quoi.feur.yoyodroid.entities


class Session {

    lateinit var id:Integer
    lateinit var date:String
    lateinit var formation:Formation

    constructor(id:Integer, date:String, formation: Formation) {
        this.id = id
        this.date = date
        this.formation = formation
    }

}