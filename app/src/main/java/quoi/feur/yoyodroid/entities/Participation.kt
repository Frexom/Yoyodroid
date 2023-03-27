package quoi.feur.yoyodroid.entities

class Participation {
    lateinit var id:Integer
    lateinit var student: Student
    lateinit var status: Status
    lateinit var commentary: String

    constructor(id:Integer, student:Student, status: Status, commentary:String) {
        this.id = id
        this.student = student
        this.status = status
        this.commentary = commentary
    }
}