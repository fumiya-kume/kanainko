package jp.ac.daido.kanainko

import android.os.Bundle
import android.os.PersistableBundle
import androidx.fragment.app.FragmentActivity
import kuu.nagoya.feature.record.view.RecordFragment

internal class DebugActivity : FragmentActivity() {

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)

        setContentView(R.layout.activity_debug)

        supportFragmentManager
            .beginTransaction()
            .apply {
                val fragment = RecordFragment()
                this.add(R.id.main_fragment, fragment)
                this.commit()
            }
    }
}
