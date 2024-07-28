package com.dgmltn.todo.data

import com.dgmltn.todo.Constants
import com.dgmltn.todo.domain.ToDo
import io.realm.kotlin.Realm
import io.realm.kotlin.ext.query
import io.realm.kotlin.mongodb.AppConfiguration
import io.realm.kotlin.mongodb.Credentials
import io.realm.kotlin.mongodb.sync.SyncConfiguration
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map

class RealmRepo {
    
    private val app by lazy {
        check(Constants.APP_ID.isNotEmpty() && Constants.APP_ID != "[your app id]") {
            "Please setup your AppID in Constants.APP_ID"
        }
            
        val configuration = AppConfiguration
            .Builder(Constants.APP_ID)
            .build()
        io.realm.kotlin.mongodb.App.create(configuration)
    }

    private val credentials by lazy {
        Credentials.anonymous()
//        Credentials.emailPassword("", "")
    }

    lateinit var realm: Realm

    private suspend fun tryLogin() {
        if (this::realm.isInitialized) {
            return
        }

        val user = app.currentUser ?: app.login(credentials)

        val config = SyncConfiguration
            .Builder(user, setOf(RealmToDo::class))
            .initialSubscriptions { realm ->
                // only can write data, which cover in initialSubscriptions
                add(
                    query = realm.query<RealmToDo>(),
                    updateExisting = true
                )
            }
            .build()
        realm = Realm.open(config)
    }

    suspend fun insert(todo: ToDo) {
        tryLogin()
        realm.write {
            copyToRealm(todo.toRealmToDo())
        }
    }

    private fun loggedInQuery(): Flow<List<ToDo>> =
        realm.query<RealmToDo>()
        .asFlow()
        .map { it.list }
        .map { it.map { it.toDomain() } }
    
    fun query(): Flow<List<ToDo>> = flow {
        tryLogin()
        emitAll(loggedInQuery())
    }
    
    suspend fun update(todo: ToDo) {
        tryLogin()

        val realmToDo = todo.toRealmToDo()
        val id = realmToDo._id

        realm.query<RealmToDo>("_id = $0", id)
            .first()
            .find()
            ?.also { result ->
                realm.write {
                    findLatest(result)?.updateFrom(realmToDo)
                }
            }
    }
    
    suspend fun delete(todo: ToDo) {
        tryLogin()
        realm.write {
            val id = todo.toRealmToDo()._id
            val findById = query<RealmToDo>("_id = $0", id)
            delete(findById)
        }
    }
}
