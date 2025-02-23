package wade.owen.watt.mood_diary.domain.model

data class Diary (
    val id: Int?,
    val mood: Int?,
    val title: String?,
    val dateTime: String?,
    val liked: Boolean?,
)