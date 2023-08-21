package com.jgbravo.nutriwise.data.db.models

import io.realm.kotlin.ext.realmListOf
import io.realm.kotlin.types.RealmInstant
import io.realm.kotlin.types.RealmList
import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.Index
import io.realm.kotlin.types.annotations.PrimaryKey
import org.mongodb.kbson.ObjectId

internal class MealPlanEntity : RealmObject {
    @PrimaryKey
    var _id: ObjectId = ObjectId.invoke()
    var person: String? = null
    var startDate: String? = null //LocalDateTime = DateTimeUtil.now()
    var goal: String? = null
    var kcal: Int? = null
    var meals: RealmList<MealEntity> = realmListOf()
    var state: String = "active"

    @Index
    var timestamp: RealmInstant = RealmInstant.now()
}

internal class MealEntity : RealmObject {
    var type: String? = null
    var carbs: Int? = null
    var protein: Int? = null
    var fat: Int? = null
}