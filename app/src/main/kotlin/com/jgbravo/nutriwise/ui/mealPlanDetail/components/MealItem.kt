package com.jgbravo.nutriwise.ui.mealPlanDetail.components

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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jgbravo.nutriwise.R
import com.jgbravo.nutriwise.R.drawable
import com.jgbravo.nutriwise.ui.mealPlanDetail.models.Meal
import com.jgbravo.nutriwise.ui.mealPlanDetail.models.MealType.BREAKFAST
import com.jgbravo.nutriwise.ui.mealPlanDetail.models.getNameRes

@Composable
fun MealItem(
    meal: Meal,
    backgroundColor: Color,
    onMealClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    val grams = context.getString(R.string.grams)

    Column(
        modifier = modifier
            .clip(RoundedCornerShape(5.dp))
            .background(backgroundColor)
            .clickable { onMealClick() }
            .padding(16.dp)
    ) {
        Text(
            text = context.getString(meal.mealType.getNameRes()),
            fontWeight = FontWeight.SemiBold,
            fontSize = 20.sp
        )
        Spacer(modifier = Modifier.padding(16.dp))
        Row(
            horizontalArrangement = Arrangement.Absolute.Left,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            Icon(
                painter = painterResource(id = drawable.ic_carbs),
                contentDescription = "carbs icon",
                modifier = Modifier.size(24.dp),
            )
            Spacer(modifier = Modifier.padding(4.dp))
            Text(
                text = "${meal.carbs}$grams ${context.getString(R.string.carbs)}",
                fontWeight = FontWeight.Light
            )
        }
        Spacer(modifier = Modifier.padding(4.dp))
        Row(
            horizontalArrangement = Arrangement.Absolute.Left,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            Icon(
                painter = painterResource(id = drawable.ic_protein),
                contentDescription = "carbs icon",
                modifier = Modifier.size(24.dp),
            )
            Spacer(modifier = Modifier.padding(4.dp))
            Text(
                text = "${meal.protein}$grams ${context.getString(R.string.protein)}",
                fontWeight = FontWeight.Light
            )
        }
        Spacer(modifier = Modifier.padding(4.dp))
        Row(
            horizontalArrangement = Arrangement.Absolute.Left,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            Icon(
                painter = painterResource(id = drawable.ic_fat),
                contentDescription = "carbs icon",
                modifier = Modifier.size(24.dp),
            )
            Spacer(modifier = Modifier.padding(4.dp))
            Text(
                text = "${meal.fat}$grams ${context.getString(R.string.fat)}",
                fontWeight = FontWeight.Light
            )
        }
    }
}

@Preview
@Composable
fun MealItemPreview() {
    MealItem(
        Meal(
            mealType = BREAKFAST,
            carbs = 63,
            protein = 33,
            fat = 20,
        ),
        onMealClick = {},
        backgroundColor = Color.White
    )
}