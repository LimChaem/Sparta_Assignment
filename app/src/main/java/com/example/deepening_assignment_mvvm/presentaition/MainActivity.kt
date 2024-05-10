package com.example.deepening_assignment_mvvm.presentaition

import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import com.example.deepening_assignment_mvvm.databinding.ActivityMainBinding
import com.example.deepening_assignment_mvvm.data.model.SearchImageViewModel
import com.example.deepening_assignment_mvvm.data.model.SearchImageViewModelFactory
import com.example.deepening_assignment_mvvm.presentaition.entity.DocumentsEntity

class MainActivity : AppCompatActivity(), ReceiveData {
    private val imageViewModel by viewModels<SearchImageViewModel> {
        SearchImageViewModelFactory()
    }
    private val binding by lazy{
        ActivityMainBinding.inflate(layoutInflater)
    }
    private val selectedImages = mutableListOf<DocumentsEntity>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(binding.root) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        if(savedInstanceState == null) {
            setSearchFragment()
        }

        changedFragment()

    }

    private fun setSearchFragment() {
        val pref = getSharedPreferences("lastWord",0)
        val lastWord = pref.getString(SEARCH_WORD, "")
        Log.d(TAG, "Loaded word: $lastWord")

        val searchFragment = SearchFragment()
        loadToLastWord(searchFragment, lastWord ?: "")
        supportFragmentManager
            .beginTransaction()
            .replace(binding.frameLayout1.id, searchFragment)
            .commit()
    }
    private fun setMyBoxFragment(){
        supportFragmentManager
            .beginTransaction()
            .replace(binding.frameLayout1.id, MyBoxFragment())
            .commit()
    }
    private fun changedFragment(){
        with(binding) {
            btn1.setOnClickListener {
                setSearchFragment()
            }
            btn2.setOnClickListener {
                setMyBoxFragment()
            }
        }
    }
    private fun savedLastKeyWord(word: String){
        val pref = getSharedPreferences("lastWord", 0)
        val edit : SharedPreferences.Editor = pref.edit()
        edit.putString(SEARCH_WORD, word).apply()
        Log.d(TAG, "Saved word: $word")

    }
    override fun receiveData(data: String) {
        savedLastKeyWord(data)
    }
    private fun loadToLastWord(fragment: Fragment, data: String){
        val bundle = Bundle()
        bundle.putString("data", data)
        fragment.arguments = bundle
        Log.d(TAG, "Data passed to fragment: $data")
        Log.d(TAG, "Bundle set with data: ${bundle.getString("data")}")
    }
    fun addImageToSelectedList(document: DocumentsEntity) {
        if (!selectedImages.contains(document)) {
            selectedImages.add(document)
        }
    }
    fun removeImageFromSelectedList(document: DocumentsEntity) {
        selectedImages.remove(document)
    }
    fun getSelectedImages(): MutableList<DocumentsEntity> = selectedImages
    companion object{
        const val SEARCH_WORD = "lastSearchWord"
        const val TAG = "sharedPref"
    }
}