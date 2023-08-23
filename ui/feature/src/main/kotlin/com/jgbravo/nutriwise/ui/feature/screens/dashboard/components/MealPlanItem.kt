package com.jgbravo.nutriwise.ui.feature.screens.dashboard.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jgbravo.nutriwise.common.app.models.PlanState
import com.jgbravo.nutriwise.common.app.models.PlanState.ACTIVE
import com.jgbravo.nutriwise.common.utils.DatePatterns.SPANISH_DATE_PATTERN
import com.jgbravo.nutriwise.common.utils.DateTimeUtil
import com.jgbravo.nutriwise.common.utils.DateTimeUtil.formatDate
import com.jgbravo.nutriwise.domain.usecases.models.MealPlan
import com.jgbravo.nutriwise.ui.api.theme.GreenActive
import com.jgbravo.nutriwise.ui.api.theme.GreyInactive
import com.jgbravo.nutriwise.ui.api.theme.LightBlueGrey
import com.jgbravo.nutriwise.ui.api.theme.RedStop
import com.jgbravo.nutriwise.ui.feature.R
import java.util.Locale

@Composable
fun MealPlanItem(
    mealPlan: MealPlan,
    onMealPlanClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .clip(RoundedCornerShape(5.dp))
            .background(LightBlueGrey)
            .clickable { onMealPlanClick() }
            .padding(16.dp)
    ) {
        Text(
            text = mealPlan.goal,
            fontWeight = FontWeight.SemiBold,
            fontSize = 18.sp
        )
        Spacer(modifier = Modifier.padding(4.dp))
        Row(
            horizontalArrangement = Arrangement.Absolute.Left,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_calendar),
                contentDescription = "Start date",
                modifier = Modifier.size(16.dp)
            )
            Spacer(modifier = Modifier.padding(4.dp))
            Text(
                text = mealPlan.startDate.formatDate(SPANISH_DATE_PATTERN),
                fontSize = 14.sp
            )
        }
        Spacer(modifier = Modifier.padding(4.dp))
        Row(
            horizontalArrangement = Arrangement.Absolute.Left,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_calories),
                contentDescription = "Kcal",
                modifier = Modifier.size(16.dp)
            )
            Spacer(modifier = Modifier.padding(4.dp))
            Text(
                text = "${mealPlan.kcal} kcal",
                fontSize = 14.sp
            )
        }
        Spacer(modifier = Modifier.padding(4.dp))
        Row(
            horizontalArrangement = Arrangement.Absolute.Left,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = mealPlan.state.value.replaceFirstChar {
                    if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString()
                },
                fontSize = 14.sp,
                color = when (mealPlan.state) {
                    PlanState.ACTIVE -> GreenActive
                    PlanState.STOPPED -> RedStop
                    PlanState.END -> GreyInactive
                }
            )
        }
    }
}

@Preview
@Composable
fun MealPlanItemPreview() {
    MealPlanItem(
        mealPlan = MealPlan(
            id = "1",
            person = "John Doe",
            startDate = DateTimeUtil.now().date,
            goal = "Pérdida de grasa",
            kcal = 2600,
            meals = emptyList(),
            state = ACTIVE
        ),
        onMealPlanClick = {}
    )
}