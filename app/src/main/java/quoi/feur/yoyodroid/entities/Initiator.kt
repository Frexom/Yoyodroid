package quoi.feur.yoyodroid.entities

class Initiator {

    lateinit var id : Integer
    lateinit var name : String
    lateinit var email : String
    lateinit var pass : String
    lateinit var level:Level

    constructor(id:Integer, name:String, email:String, pass:String,level: Level){
        this.id=id
        this.name = name
        this.email = email
        this.pass = pass
        this.level = level
    }
}