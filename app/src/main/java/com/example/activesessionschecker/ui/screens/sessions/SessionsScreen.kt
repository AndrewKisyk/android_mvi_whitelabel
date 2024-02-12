package com.example.activesessionschecker.ui.screens.sessions

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Checkbox
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.ScaffoldState
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import com.example.activesessionschecker.AppState
import com.example.activesessionschecker.R
import com.example.activesessionschecker.data.domain.models.ActiveSession
import com.example.activesessionschecker.data.source.remote.models.Session
import com.example.activesessionschecker.ui.base.rememberDispatcher
import com.example.activesessionschecker.ui.widgets.LoadingContent
import com.google.accompanist.appcompattheme.AppCompatTheme

import org.reduxkotlin.compose.selectState

@OptIn(ExperimentalLifecycleComposeApi::class)
@Composable
fun SessionsScreen(
    @StringRes userMessage: Int,
    onAddTask: () -> Unit,
    onSessionClick: (ActiveSession) -> Unit,
    onUserMessageDisplayed: () -> Unit,
    openDrawer: () -> Unit,
    modifier: Modifier = Modifier,
    scaffoldState: ScaffoldState = rememberScaffoldState(),
    viewModel: SessionsViewModel = hiltViewModel()
) {
    val uiState by selectState<AppState, SessionUiState> { activeSessions }
    val dispatch = rememberDispatcher(viewModel)

    LaunchedEffect(true) {
        dispatch(SessionEffects.FetchActiveSessionsEffect())
    }

    Scaffold(
        scaffoldState = scaffoldState,
        modifier = modifier.fillMaxSize(),
    ) { paddingValues ->

        SessionsContent(
            loading = uiState.isLoading,
            sessions = uiState.items,
            currentFilteringLabel = uiState.filteringUiInfo.currentFilteringLabel,
            noTasksLabel = uiState.filteringUiInfo.noTasksLabel,
            noTasksIconRes = uiState.filteringUiInfo.noTaskIconRes,
            onRefresh = {},
            onSessionClick = onSessionClick,
            onTaskCheckedChange = {_,_ -> },
            modifier = Modifier.padding(paddingValues)
        )

    }
}

@Composable
private fun SessionsContent(
    loading: Boolean,
    sessions: List<ActiveSession>,
    @StringRes currentFilteringLabel: Int,
    @StringRes noTasksLabel: Int,
    @DrawableRes noTasksIconRes: Int,
    onRefresh: () -> Unit,
    onSessionClick: (ActiveSession) -> Unit,
    onTaskCheckedChange: (ActiveSession, Boolean) -> Unit,
    modifier: Modifier = Modifier
) {
    LoadingContent(
        loading = loading,
        empty = sessions.isEmpty() && !loading,
        emptyContent = { SessionsEmptyContent(noTasksLabel, noTasksIconRes, modifier) },
        onRefresh = onRefresh
    ) {
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(horizontal = dimensionResource(id = R.dimen.horizontal_margin))
        ) {
            Text(
                text = stringResource(currentFilteringLabel),
                modifier = Modifier.padding(
                    horizontal = dimensionResource(id = R.dimen.list_item_padding),
                    vertical = dimensionResource(id = R.dimen.vertical_margin)
                ),
                style = MaterialTheme.typography.h6
            )
            LazyColumn {
                items(sessions) { session ->
                    SessionItem(
                        task = session,
                        onTaskClick = onSessionClick,
                        onCheckedChange = { onTaskCheckedChange(session, it) }
                    )
                }
            }
        }
    }
}

@Composable
private fun SessionItem(
    task: ActiveSession,
    onCheckedChange: (Boolean) -> Unit,
    onTaskClick: (ActiveSession) -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                horizontal = dimensionResource(id = R.dimen.horizontal_margin),
                vertical = dimensionResource(id = R.dimen.list_item_padding),
            )
            .clickable { onTaskClick(task) }
    ) {
        Checkbox(
            checked = false,
            onCheckedChange = onCheckedChange
        )
        Text(
            text = task.title,
            style = MaterialTheme.typography.h6,
            modifier = Modifier.padding(
                start = dimensionResource(id = R.dimen.horizontal_margin)
            ),
        )
    }
}

@Composable
private fun SessionsEmptyContent(
    @StringRes noTasksLabel: Int,
    @DrawableRes noTasksIconRes: Int,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = noTasksIconRes),
            contentDescription = stringResource(R.string.no_tasks_image_content_description),
            modifier = Modifier.size(96.dp)
        )
        Text(stringResource(id = noTasksLabel))
    }
}

@Preview
@Composable
private fun TasksContentPreview() {
    MaterialTheme {
        Surface {
            SessionsContent(
                loading = false,
                sessions = listOf(
                    ActiveSession(
                        title = "Title 1",
                        id = "ID 1"
                    ),
                    ActiveSession(
                        title = "Title 2",
                        id = "ID 2"
                    ),
                    ActiveSession(
                        title = "Title 3",
                        id = "ID 3"
                    ),
                    ActiveSession(
                        title = "Title 4",
                        id = "ID 4"
                    ),
                    ActiveSession(
                        title = "Title 5",
                        id = "ID 5"
                    ),
                ),
                currentFilteringLabel = R.string.label_all,
                noTasksLabel = R.string.no_tasks_all,
                noTasksIconRes = R.drawable.ic_launcher_foreground,
                onRefresh = { },
                onSessionClick = { },
                onTaskCheckedChange = { _, _ -> },
            )
        }
    }
}

@Preview
@Composable
private fun SessionContentEmptyPreview() {
    MaterialTheme {
        Surface {
            SessionsContent(
                loading = false,
                sessions = emptyList(),
                currentFilteringLabel = R.string.label_all,
                noTasksLabel = R.string.no_tasks_all,
                noTasksIconRes = R.drawable.ic_launcher_foreground,
                onRefresh = { },
                onSessionClick = { },
                onTaskCheckedChange = { _, _ -> },
            )
        }
    }
}

@Preview
@Composable
private fun TasksEmptyContentPreview() {
    AppCompatTheme {
        Surface {
            SessionsEmptyContent(
                noTasksLabel = R.string.no_tasks_all,
                noTasksIconRes = R.drawable.ic_launcher_foreground
            )
        }
    }
}

@Preview
@Composable
private fun TaskItemPreview() {
    AppCompatTheme {
        Surface {
            SessionItem(
                task = ActiveSession(
                    title = "Title",
                    id = "ID"
                ),
                onTaskClick = { },
                onCheckedChange = { }
            )
        }
    }
}

@Preview
@Composable
private fun TaskItemCompletedPreview() {
    MaterialTheme {
        Surface {
            SessionItem(
                task = ActiveSession(
                    title = "Title",
                    id = "ID"
                ),
                onTaskClick = { },
                onCheckedChange = { }
            )
        }
    }
}
