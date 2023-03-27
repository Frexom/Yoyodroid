package quoi.feur.yoyodroid.entities

enum class Status {
    NON_EVALUE,ABSENT,EN_COURS,ACQUIS;

    fun getCouleur():String{
        return when (this) {
            NON_EVALUE -> "#F3F3F3"
            ABSENT -> "#FFFFFF"
            EN_COURS -> "#F5F5DC"
            ACQUIS -> "#7FFF7F"
        }
    }
}