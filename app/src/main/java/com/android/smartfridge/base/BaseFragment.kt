package com.android.smartfridge.base
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.viewbinding.ViewBinding
import com.android.smartfridge.dataApp.repository.DataRepository
import com.android.smartfridge.dataApp.services.RemoteDataSource
import com.android.smartfridge.utils.UserPreferences


abstract class BaseFragment<VM : ViewModel, VB : ViewBinding, Rep: DataRepository>  : Fragment() {
    var TAG: String = this.javaClass.simpleName
    protected lateinit var mViewModel: VM
    protected lateinit var mViewBinding: VB
    protected val remoteDataSource = RemoteDataSource()
    protected lateinit var userPreferences: UserPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(null)
        mViewBinding = getViewBinding()
        val factory = ViewModelFactory(requireActivity() as BaseActivity<*, *, *>, getFragmentRepository())
        mViewModel = ViewModelProvider(this, factory)[getViewModel()]
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mViewBinding = getViewBinding()
        userPreferences = UserPreferences(requireContext())


        return mViewBinding.root


    }

    override fun onResume() {
        super.onResume()
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
    }

    override fun onDetach() {
        super.onDetach()
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
    abstract fun getViewBinding(): VB
    abstract fun getViewModel(): Class<VM>
    abstract fun getFragmentRepository(): Rep
}
