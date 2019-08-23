package utkarshsundaram.kotlinproject.persistance.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import utkarshsundaram.kotlinproject.model.MusicModel

@Dao
interface MusicDao {
    @Query("SELECT * FROM musicmodel ")
    fun getAll(): List<MusicModel>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(vararg users: MusicModel)

    @Query("delete from musicmodel  WHERE login = :content")
    fun delete(content: String)
}