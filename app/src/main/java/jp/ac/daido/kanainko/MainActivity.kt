package jp.ac.daido.kanainko

import android.animation.ValueAnimator
import android.content.Context
import android.os.Bundle
import android.util.AttributeSet
import android.view.View
import android.view.animation.AccelerateInterpolator
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.google.android.material.bottomsheet.BottomSheetBehavior
import jp.ac.daido.kanainko.databinding.ActivityMainBinding
import jp.ac.daido.kanainko.record.RecordFragment

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        binding =
            DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)

        binding.activityMainRecordFloatingActionButton.setOnClickListener {

            binding.activityMainRecordFloatingActionButton.hide()
            val heightValueAnimator = ValueAnimator.ofFloat(
                binding.activityMainRecordFloatingActionButton.height.toFloat(),
                0.0F
            ).apply {
                addUpdateListener {
                    val value = (it.getAnimatedValue() as Float).toInt()
                    val floatingActionButtonLayoutParams =
                        binding.activityMainRecordFloatingActionButton.layoutParams.apply {
                            height = value
                        }
                    binding.activityMainRecordFloatingActionButton.layoutParams =
                        floatingActionButtonLayoutParams
                }
            }
            val widthValueAnimator = ValueAnimator.ofFloat(
                binding.activityMainRecordFloatingActionButton.width.toFloat(),
                0.0F
            ).apply {
                addUpdateListener {
                    val value = (it.getAnimatedValue() as Float).toInt()
                    val floatingActionButtonLayoutParams =
                        binding.activityMainRecordFloatingActionButton.layoutParams.apply {
                            width = value
                        }
                    binding.activityMainRecordFloatingActionButton.layoutParams =
                        floatingActionButtonLayoutParams
                }
            }

            heightValueAnimator.interpolator = AccelerateInterpolator()
            heightValueAnimator.start()

            widthValueAnimator.interpolator = AccelerateInterpolator()
            widthValueAnimator.start()


            val bottomSheetBehavior =
                BottomSheetBehavior.from(binding.activityMainRecordBottomSheet)
            bottomSheetBehavior.setPeekHeight(400)
            bottomSheetBehavior.state = BottomSheetBehavior.STATE_COLLAPSED

            bottomSheetBehavior.setBottomSheetCallback(object :
                BottomSheetBehavior.BottomSheetCallback() {
                override fun onSlide(bottomSheet: View, slideOffset: Float) {

                }

                override fun onStateChanged(bottomSheet: View, newState: Int) {
                    if (newState == BottomSheetBehavior.STATE_HIDDEN) {

                        fun Int.toDp(context: Context) =
                            ((this * context.resources.displayMetrics.density) + 0.5F).toInt()

                        binding.activityMainRecordFloatingActionButton.layoutParams =
                            binding.activityMainRecordFloatingActionButton.layoutParams.apply {
                                val hoge = 28.toDp(this@MainActivity)
                                val app = 154 / applicationContext.resources.displayMetrics.density
                                val activity = this@MainActivity.resources.displayMetrics.density
                                height = 56.toDp(this@MainActivity)
                                width = 56.toDp(this@MainActivity)
                            }
                    }
                }
            })
        }

        val transaction = supportFragmentManager.beginTransaction()
        transaction.add(R.id.activity_main_bottom_sheet_fragment_container, RecordFragment())
        transaction.commit()
    }

    override fun onCreateView(
        parent: View?,
        name: String,
        context: Context,
        attrs: AttributeSet
    ): View? {
        return super.onCreateView(parent, name, context, attrs)
    }
}
