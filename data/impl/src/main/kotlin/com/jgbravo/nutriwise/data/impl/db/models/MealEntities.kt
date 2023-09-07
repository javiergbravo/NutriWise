package com.jgbravo.nutriwise.data.impl.db.models

import com.jgbravo.nutriwise.common.app.models.PlanState
import com.jgbravo.nutriwise.data.impl.base.DefaultValues
import io.realm.kotlin.ext.realmListOf
import io.realm.kotlin.types.RealmInstant
import io.realm.kotlin.types.RealmList
import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.Index
import io.realm.kotlin.types.annotations.PrimaryKey
import org.mongodb.kbson.ObjectId

class MealPlanEntity : RealmObject {
    @PrimaryKey
    var id: ObjectId = ObjectId.invoke()
    var person: String = DefaultValues.EMPTY_STRING
    var startDate: String = DefaultValues.EMPTY_STRING
    var goal: String = DefaultValues.EMPTY_STRING
    var kcal: Int = DefaultValues.INVALID_INT
    var meals: RealmList<MealEntity> = realmListOf()
    var state: String = PlanState.ACTIVE.name

    @Index
    var timestamp: RealmInstant = RealmInstant.now()
}

class MealEntity : RealmObject {
    var id: ObjectId = ObjectId.invoke()
    var type: String = DefaultValues.EMPTY_STRING
    var carbs: Int = DefaultValues.INVALID_INT
    var protein: Int = DefaultValues.INVALID_INT
    var fat: Int = DefaultValues.INVALID_INT
}