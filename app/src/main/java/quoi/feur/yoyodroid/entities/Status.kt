package quoi.feur.yoyodroid.entities


enum class Status {
    NON_EVALUE,ABSENT,EN_COURS,ACQUIS;

    companion object{

        val all : Array<Status> = arrayOf(NON_EVALUE, ABSENT, EN_COURS, ACQUIS)
        fun getStatus(index : Int) : Status{
            val indexModulo = (index % 4) + 1
            return when (indexModulo){
                1 -> NON_EVALUE
                2 -> ABSENT
                3 -> EN_COURS
                4 -> ACQUIS
                else -> {
                    throw Exception("This isn't supposed to happen")
                }
            }
        }

    }

    override fun toString(): String {
        return when (this) {
            NON_EVALUE -> "Non-évlaué"
            ABSENT -> "Absent"
            EN_COURS -> "En cours"
            ACQUIS -> "Acquis"
        }
    }

    fun getCouleur():String{
        return when (this) {
            NON_EVALUE -> "#F3F3F3"
            ABSENT -> "#FFFFFF"
            EN_COURS -> "#F5F5DC"
            ACQUIS -> "#7FFF7F"
        }
    }

    fun getId() : Int{
        return when (this) {
            NON_EVALUE -> 1
            ABSENT -> 2
            EN_COURS -> 3
            ACQUIS -> 4
        }
    }

}