package uk.ac.tees.mad.bp.mainapp.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.firestore.FirebaseFirestore
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import uk.ac.tees.mad.bp.mainapp.model.KitItem
import javax.inject.Inject


@HiltViewModel
class MainViewmodel @Inject constructor(
    private val firestore: FirebaseFirestore
): ViewModel() {

    private val _allKits = MutableStateFlow<List<KitItem>>(emptyList())
    val allKits = _allKits.asStateFlow()

    init {
        getAllKits()
    }

    fun getAllKits() {
        viewModelScope.launch {
            try {
                val kits = firestore.collection("kits")
                    .get()
                    .await()
                    .documents
                    .mapNotNull { doc ->
                        doc.toObject(KitItem::class.java)
                    }

                if (kits.isNotEmpty()) {
                    _allKits.value = kits
                } else {
                    Log.e("getAllKits", "Error fetching kits $kits")
                }
            } catch (e: Exception) {
                Log.e("getAllKits", "Error fetching kits: ${e.message}")
            }
        }
    }


    fun addNewKit(item: KitItem){
        viewModelScope.launch {
            firestore.collection("Kit")
                .document(item.title)
                .set(item)
                .addOnSuccessListener {
                    getAllKits()
                    Log.i("New Kit: ", "New Kit is added successfully!!")
                }
                .addOnFailureListener{
                    Log.i("New Kit: ", "Adding new kit is unsuccessful!")
                }
        }
    }

    fun addNewItem(item: KitItem){
        viewModelScope.launch {

            val addItem = hashMapOf("item" to item.item)

            firestore.collection("Kit")
                .document(item.title)
                .update(addItem as Map<String, Any>)
                .addOnSuccessListener {
                    getAllKits()
                }
                .addOnFailureListener {
                    Log.i("New Item: ", "Adding new item is unsuccessful!")
                }
        }
    }
}