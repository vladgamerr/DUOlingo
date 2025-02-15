import android.util.Log
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment

class FragmentNavigator(private val activity: AppCompatActivity) {

    fun openFragment(fragment: Fragment, containerId: Int) {
        activity.supportFragmentManager
            .beginTransaction()
            .replace(containerId, fragment)
            .addToBackStack(null)
            .commit()
        Log.d("debug", fragment.parentFragmentManager.backStackEntryCount.toString())
    }

    fun openFragment(fragment: Fragment, containerId: Int, ignoreBackStack: Boolean) {
        activity.supportFragmentManager
            .beginTransaction()
            .replace(containerId, fragment)
            .commit()
        Log.d("debug", fragment.parentFragmentManager.backStackEntryCount.toString())
    }

    fun setupBackNavigation(fragment: Fragment) {
        val callback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                Log.d("debug", fragment.parentFragmentManager.backStackEntryCount.toString())
                if (fragment.parentFragmentManager.backStackEntryCount > 0) {

                    fragment.parentFragmentManager.popBackStack()
                } else {
                    isEnabled = false // Отключаем колбэк
                    activity.onBackPressed() // Стандартное поведение
                }
            }
        }
        activity.onBackPressedDispatcher.addCallback(fragment, callback)
    }
}