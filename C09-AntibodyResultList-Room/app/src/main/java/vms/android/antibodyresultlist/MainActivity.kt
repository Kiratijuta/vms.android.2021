package vms.android.antibodyresultlist

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.list_item_student.view.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        student_list.layoutManager = LinearLayoutManager(this)
        student_list.adapter = StudentAdapter(emptyList())

        //sampleStudents()
        getStudentFromDatabase()
    }


    private fun sampleStudents() {
        for (i in 1 until 5) {
            val student = Student()
            student.name = "STUDENT ${i}"
            student.testResult = (1..2).random() % 2 == 0

            // Add New Student Example
            StudentRepository.get().addStudent(student)
        }
    }

    private fun getStudentFromDatabase() {
        StudentRepository.get().getStudents().observe(
            this, Observer { students ->
                students?.let {
                    student_list.adapter = StudentAdapter(students)

                    // Update Student Example
//                    var firstStudent = students[0]
//                    firstStudent.name = "Fair"
//                    StudentRepository.get().updateStudent(firstStudent)
                }
            }
        )
    }

    private inner class StudentHolder(view: View): RecyclerView.ViewHolder(view), View.OnClickListener {
        val studentID = itemView.studentID
        val studentName = itemView.studentName
        val testResult = itemView.testResult

        lateinit var student: Student

        init {
            itemView.setOnClickListener(this)
        }

        fun bind(student: Student) {
            this.student = student

            studentID.text = student.id.toString()
            studentName.text = student.name
            testResult.text = if (student.testResult) "POSITIVE" else "NEGATIVE"
            testResult.setTextColor(
                if (student.testResult) Color.parseColor("#FF0000")
                else Color.parseColor("#CFCFCF")
            )
        }

        override fun onClick(v: View?) {
            Toast.makeText(v!!.context, "${student.name} clicked", Toast.LENGTH_SHORT).show()
        }
    }

    private inner class StudentAdapter(var students: List<Student>): RecyclerView.Adapter<StudentHolder>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentHolder {
            val view = layoutInflater.inflate(R.layout.list_item_student, parent, false)
            return StudentHolder(view)
        }

        override fun onBindViewHolder(holder: StudentHolder, position: Int) {
            val student = students[position]
            holder.bind(student)
        }

        override fun getItemCount(): Int {
            return students.size
        }
    }

}