package loc.example.viewdemoapp71224

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.widget.LinearLayout
import android.widget.LinearLayout.VERTICAL
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import loc.example.viewdemoapp71224.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var bind: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
//        setContentView(R.layout.activity_main)
        bind = ActivityMainBinding.inflate(layoutInflater).also { setContentView(it.root) }
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val container = LinearLayout(this).apply {
            layoutParams = ViewGroup.LayoutParams(MATCH_PARENT, WRAP_CONTENT)
            orientation = VERTICAL
        }.also { bind.main.addView(it) }
        addRow(container, "One", "$1.00", 100)
        addRow(container, "Two", "$2.00", 0)
        addRow(container, "Three", "$3.00", 0)
        addRow(container, "Four", "$4.00", 0)
    }

    private fun addRow(
        container: ViewGroup,
        start: String,
        end: String,
        marginTop: Int
    ) {
        val view =
            LayoutInflater.from(this).inflate(R.layout.list_item_default, container, false)
                .apply {
                    layoutParams = ConstraintLayout.LayoutParams(MATCH_PARENT, WRAP_CONTENT).apply {
                        topMargin = marginTop
                    }
                }.also { container.addView(it) }
        val startLabel = view.findViewById<TextView>(R.id.start)
        startLabel.text = start
        val endLabel = view.findViewById<TextView>(R.id.end)
        endLabel.text = end
    }
}