package com.jgbravo.nutriwise.data.impl.db.models

import io.realm.kotlin.types.RealmInstant
import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.Index
import io.realm.kotlin.types.annotations.PrimaryKey
import org.mongodb.kbson.ObjectId

class MealPlanEntity : RealmObject {
    @PrimaryKey
    var _id: ObjectId = ObjectId.invoke()
    var person: String = ""
    var startDate: String = ""
    var goal: String = ""
    var kcal: Int = -1

    //    var meals: RealmList<MealEntity> = realmListOf()
    var state: String = "active"

    @Index
    var timestamp: RealmInstant = RealmInstant.now()
}

class MealEntity : RealmObject {
    var _id: ObjectId = ObjectId.invoke()
    var type: String? = null
    var carbs: Int? = null
    var protein: Int? = null
    var fat: Int? = null
}