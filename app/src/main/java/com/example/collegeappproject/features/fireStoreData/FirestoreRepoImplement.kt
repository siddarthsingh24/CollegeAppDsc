package com.example.collegeappproject.features.fireStoreData

import com.example.collegeappproject.models.NotesModel
import com.example.collegeappproject.utils.ResultState
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import javax.inject.Inject

class FirestoreRepoImplement
@Inject
constructor(private val db :FirebaseFirestore) : FirestoreRepo {


    override fun getFirestoreData(): Flow<ResultState<List<NotesModel>>> = callbackFlow {


        trySend(ResultState.Loading)

        db.collection("notes").get()
            .addOnSuccessListener {

                val items = it.map {data->
                    NotesModel(
                       item = NotesModel.FirestoreItem(
                           name = data["name"] as String?,
                           link = data["link"] as String?




                       ),
                        key = data.id
                    )

                }
                trySend(ResultState.Success(items))

            }
            .addOnFailureListener {
                trySend(ResultState.Failure(it))
            }

        awaitClose{
            close()
        }






    }

    override fun getAssignmentData(): Flow<ResultState<List<NotesModel>>> = callbackFlow{


        trySend(ResultState.Loading)

        db.collection("assignment").get()
            .addOnSuccessListener {

                val items = it.map {data->
                    NotesModel(
                        item = NotesModel.FirestoreItem(
                            name = data["name"] as String?,
                            link = data["link"] as String?




                        ),
                        key = data.id
                    )

                }
                trySend(ResultState.Success(items))

            }
            .addOnFailureListener {
                trySend(ResultState.Failure(it))
            }

        awaitClose{
            close()
        }






    }

}