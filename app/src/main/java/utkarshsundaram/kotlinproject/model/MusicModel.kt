package utkarshsundaram.kotlinproject.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class MusicModel(
    @PrimaryKey(autoGenerate = true)
    public var id: Int,
    public var login: String? = null,
    public var avatar_url: String? = null,
    public var type: String? = null

) {
}