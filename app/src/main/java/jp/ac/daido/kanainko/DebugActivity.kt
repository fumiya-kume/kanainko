package jp.ac.daido.kanainko

import android.os.Bundle
import android.os.PersistableBundle
import androidx.core.app.ActivityCompat
import androidx.databinding.DataBindingUtil.setContentView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import jp.ac.daido.kanainko.record.RecordFragment

internal class DebugActivity: FragmentActivity() {

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)

        setContentView(R.layout.activity_debug)

        val transaction = supportFragmentManager.beginTransaction()
            val fragment = RecordFragment()
            transaction.add(R.id.main_fragment, fragment)
            transaction.commit()

    }
}