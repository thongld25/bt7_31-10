package com.example.myapplication

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    private lateinit var studentAdapter: StudentAdapter
    private lateinit var studentList: List<Student>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val editTextSearch = findViewById<EditText>(R.id.editTextSearch)
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)

        // Danh sách mẫu
        studentList = listOf(
            Student("Nguyễn Văn A", "123456"),
            Student("Trần Thị B", "234567"),
            Student("Lê Văn C", "345678"),
            // Thêm các sinh viên khác
        )

        studentAdapter = StudentAdapter(studentList)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = studentAdapter

        // Lắng nghe thay đổi trong ô tìm kiếm
        editTextSearch.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                val keyword = s.toString().trim()
                if (keyword.length > 2) {
                    val filteredList = studentList.filter {
                        it.name.contains(keyword, ignoreCase = true) || it.mssv.contains(keyword)
                    }
                    studentAdapter.filterList(filteredList)
                } else {
                    studentAdapter.filterList(studentList) // Hiển thị toàn bộ danh sách nếu < 3 ký tự
                }
            }

            override fun afterTextChanged(s: Editable?) {}
        })
    }
}