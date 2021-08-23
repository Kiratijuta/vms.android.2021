package vms.android.antibodyresultlist

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity
data class Student(@PrimaryKey val id: UUID = UUID.randomUUID(),
                   var name: String = "",
                   var testResult: Boolean = false)