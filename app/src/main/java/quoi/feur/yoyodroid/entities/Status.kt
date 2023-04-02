package quoi.feur.yoyodroid.entities

enum class Status {
    NON_EVALUE,ABSENT,EN_COURS,ACQUIS;

    companion object{
        fun getStatus(index : Int) : Status{
            var indexModulo = (index % 4) + 1
            return when (indexModulo){
                1 -> Status.NON_EVALUE
                2 -> Status.ABSENT
                3 -> Status.EN_COURS
                4 -> Status.ACQUIS
                else -> {
                    throw Exception("This isn't supposed to happen")
                }
            }
        }
    }

    public override fun toString(): String {
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
}