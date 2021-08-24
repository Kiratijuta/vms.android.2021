package database

import androidx.lifecycle.LiveData
import androidx.room.*
import vms.android.antibodyresultlist.Student
import java.util.*

@Dao
interface StudentDao {

    @Query("SELECT * FROM student")
    fun getStudents(): LiveData<List<Student>>

    @Query("SELECT * FROM student WHERE id = (:id)")
    fun getStudent(id: UUID): LiveData<Student?>

    @Insert
    fun insertStudent(student: Student)

    @Update
    fun updateStudent(student: Student)

}