package quoi.feur.yoyodroid.entities

class Aptitude {
    lateinit var id: Integer
    lateinit var name: String
    lateinit var skill :Skill

    constructor(id: Integer, name:String, skill:Skill){
        this.id = id
        this.name = name
        this.skill = skill
    }
}