package com.jgbravo.data.repository.models

import io.realm.kotlin.ext.realmListOf
import io.realm.kotlin.types.RealmInstant
import io.realm.kotlin.types.RealmList
import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.Index
import io.realm.kotlin.types.annotations.PrimaryKey
import org.mongodb.kbson.ObjectId

class MealPlanEntity : RealmObject {
    @PrimaryKey
    var _id: ObjectId = ObjectId.invoke()
    var person: String = ""
    var startDate: String? = null //LocalDateTime = DateTimeUtil.now()
    var description: String? = null
    var kcal: Int = 0
    var meals: RealmList<MealEntity> = realmListOf()
    var state: String = PlanState.ACTIVE.value

    @Index
    var timestamp: RealmInstant = RealmInstant.now()
}

class MealEntity : RealmObject {
    var type: String = ""
    var carbs: Int = 0
    var protein: Int = 0
    var fat: Int = 0
}

enum class PlanState(val value: String) {
    ACTIVE("active"),
    STOPPED("stopped"),
    END("end")
}