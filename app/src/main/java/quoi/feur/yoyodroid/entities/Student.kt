package quoi.feur.yoyodroid.entities

class Student {

    lateinit var id: Integer
    lateinit var name: String
    lateinit var formation: Formation
    lateinit var phone: String


    constructor(id: Integer, name:String, formation: Formation, phone:String) {
        this.id = id
        this.name = name
        this.formation = formation
        this.phone = phone
    }
}