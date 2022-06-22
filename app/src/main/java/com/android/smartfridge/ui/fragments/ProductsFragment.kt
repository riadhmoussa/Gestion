package com.android.smartfridge.ui.fragments

import android.os.AsyncTask
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.android.smartfridge.base.BaseActivity
import com.android.smartfridge.base.BaseFragment
import com.android.smartfridge.dataApp.model.Annonce
import com.android.smartfridge.dataApp.model.User
import com.android.smartfridge.dataApp.repository.DataRepository
import com.android.smartfridge.dataApp.repository.GestionApi
import com.android.smartfridge.databinding.FragmentNotificationsBinding
import com.android.smartfridge.utils.Utils
import com.android.smartfridge.viewModels.ProductViewModel
import com.halal.halalproject.adapter.AnnonceAdapter
import org.json.JSONArray
import org.json.JSONObject
import java.io.BufferedInputStream
import java.io.InputStream
import java.net.HttpURLConnection
import java.net.URL


class ProductsFragment : BaseFragment<ProductViewModel, FragmentNotificationsBinding,DataRepository>(){
    var adapter: AnnonceAdapter?=null
    override fun getViewBinding(): FragmentNotificationsBinding =
        FragmentNotificationsBinding.inflate(layoutInflater)


    override fun getViewModel(): Class<ProductViewModel> = ProductViewModel::class.java
    override fun getFragmentRepository(): DataRepository {
        return DataRepository(
            remoteDataSource.buildApi(
                GestionApi::class.java, requireContext()
            )
        )
    }

    companion object;
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return mViewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    private fun initView() {
        mViewBinding.lifecycleOwner=this
        var list = ArrayList<Annonce>()
        var user2 = User("1","","Fatma ","Alaya","Alaya1177@gmail.com","52525252")
        var user3 = User("1","","Aziz","Ben ali","benali@gmail.com","22222222")
        var user4 = User("1","","Test","Test","test@gmail.com","43698742")
        var images1 = ArrayList<String>()
        images1.add("https://www.papillesetpupilles.fr/wp-content/uploads/2017/12/Semoule-de-couscous-c-Alan-Sheffield-CC-BY-NC-2.0.jpg")
        var images2 = ArrayList<String>()
        images2.add("data:image/jpeg;base64,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")
        var images3 = ArrayList<String>()
        images3.add("https://www.lemarche.tn/wp-content/uploads/2019/04/57611826_392029521382982_7364632937016328192_n.jpg")
        var product1 = Annonce(5,"Sabrine",images3,"50","4900",user2
            ,"GHD45")
        var product2 = Annonce(5,"Smouule ",images1,"60000","1.000",user2
            ,"FFFG36")
        var product3 = Annonce(5,"Sonbla",images2,"100000","490",user2
            ,"ZRRE14")


        list.add(product1)
        list.add(product2)
        list.add(product3)
        adapter=  AnnonceAdapter(list,requireActivity() as BaseActivity<*, *,*>)

        mViewBinding.rcvPub.adapter=adapter

        val url = "http://10.0.2.2/fatma/users.php"

        MyAsyncTaskgetNews().execute(url)

    }


    // get news from server
    class MyAsyncTaskgetNews : AsyncTask<String?, String?, String?>() {
        override fun onPreExecute() {
            //before works
        }

        override fun doInBackground(vararg params: String?): String? {
            // TODO Auto-generated method stub
            try {
                val NewsData: String?
                //define the url we have to connect with
                val url = URL(params[0])
                //make connect with url and send request
                val urlConnection: HttpURLConnection = url.openConnection() as HttpURLConnection
                //waiting for 7000ms for response
                urlConnection.setConnectTimeout(7000) //set timeout to 5 seconds
                try {
                    //getting the response data
                    val inputStream: InputStream = BufferedInputStream(urlConnection.getInputStream())
                    //convert the stream to string
                    NewsData = Utils.ConvertInputToStringNoChange(inputStream)
                    //send to display data
                    publishProgress(NewsData)
                } finally {
                    //end connection
                    urlConnection.disconnect()
                }
            } catch (ex: Exception) {
            }
            return null
        }

        override fun onProgressUpdate(vararg progress: String?) {
            try {
                //JSONObject json= new JSONObject(progress[0]);
                val json = JSONArray(progress.get(0))
                for (i in 0 until json.length()) {
                    val product: JSONObject = json.getJSONObject(i)

                }

            } catch (ex: java.lang.Exception) {
            }

        }

        }



}