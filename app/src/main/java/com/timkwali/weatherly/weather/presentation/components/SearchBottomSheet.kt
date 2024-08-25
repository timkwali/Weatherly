package com.timkwali.weatherly.weather.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.BottomSheetDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import com.timkwali.weatherly.R
import com.timkwali.weatherly.core.presentation.theme.WeatherlyBlue
import com.timkwali.weatherly.core.presentation.theme.WeatherlyDeepBlue
import com.timkwali.weatherly.weather.domain.model.searchlocation.LocationState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchBottomSheet(
    sheetState: SheetState,
    onDismiss: () -> Unit,
    searchQuery: String,
    isLoading: Boolean,
    onSearchChange: (newSearchValue: String) -> Unit,
    searchCity: (searchQuery: String) -> Unit,
    locations: List<LocationState>?,
    onLocationClick: (latitude: String, longitude: String) -> Unit,
    modifier: Modifier = Modifier,
) {
    val controller = LocalSoftwareKeyboardController.current

    LaunchedEffect(Unit) {
        controller?.show()
    }

    ModalBottomSheet(
        sheetState =  sheetState,
        onDismissRequest = { onDismiss() },
        modifier = modifier
            .heightIn(400.dp)
            .fillMaxWidth(),
        containerColor = MaterialTheme.colorScheme.secondary,
    ) {
        Column(
            modifier = modifier
                .padding(all = 20.dp)
                .fillMaxWidth(),
        ) {
            TextField(
                value = searchQuery,
                onValueChange = { onSearchChange(it) },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
                    .border(
                        width = 2.dp,
                        color = WeatherlyBlue,
                        shape = RoundedCornerShape(20.dp)
                    )
                    .background(Color.Transparent),
                trailingIcon = { Icon(imageVector = Icons.Outlined.Search, contentDescription = "search icon", tint = MaterialTheme.colorScheme.onSecondary) },
                colors = TextFieldDefaults.textFieldColors(
                    containerColor = Color.Transparent,
                    cursorColor = MaterialTheme.colorScheme.primary,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    disabledIndicatorColor = Color.Transparent,
                    focusedTextColor = MaterialTheme.colorScheme.onSecondary
                ),
                placeholder = { Text(text = stringResource(id = R.string.search_city), style = typography.bodyMedium, color = MaterialTheme.colorScheme.surface) },
                maxLines = 1,
                keyboardActions = KeyboardActions(
                    onSearch = {
                        controller?.hide()
                        searchCity(searchQuery)
                    },
                ),
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
            )

            Spacer(modifier = Modifier.height(10.dp))

            if(isLoading) {
                Box(
                    modifier = Modifier.fillMaxWidth(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator(color = MaterialTheme.colorScheme.primary)
                }
            }

            if(!isLoading && locations != null) {
                LazyColumn {
                    itemsIndexed(locations) { index, item ->
                        Spacer(modifier = Modifier.height(10.dp))
                        LocationItem(location = item) {
                            onLocationClick(item.latitude, item.longitude)
                            onDismiss()
                        }
                        Spacer(modifier = Modifier.height(10.dp))
                    }
                }
            }
        }
    }
}