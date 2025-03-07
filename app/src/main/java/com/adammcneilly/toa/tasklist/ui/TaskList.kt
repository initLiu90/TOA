package com.adammcneilly.toa.tasklist.ui

import android.content.res.Configuration
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.adammcneilly.toa.R
import com.adammcneilly.toa.core.models.Task
import com.adammcneilly.toa.core.ui.components.Material3Card
import com.adammcneilly.toa.core.ui.theme.TOATheme

@OptIn(ExperimentalFoundationApi::class)
@Composable
@Suppress("LongMethod")
fun TaskList(
    incompleteTasks: List<Task>,
    completedTasks: List<Task>,
    onRescheduleClicked: (Task) -> Unit,
    onDoneClicked: (Task) -> Unit,
    modifier: Modifier = Modifier,
) {
    LazyColumn(
        contentPadding = PaddingValues(dimensionResource(id = R.dimen.list_padding)),
        verticalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.list_padding)),
        modifier = modifier,
    ) {
        item {
            SectionHeader(text = stringResource(R.string.incomplete_tasks_header))
        }

        if (incompleteTasks.isEmpty()) {
            item {
                EmptySectionCard(
                    text = stringResource(R.string.no_incomplete_tasks_label),
                )
            }
        } else {
            items(
                items = incompleteTasks,
                key = {
                    it.id
                },
            ) { task ->
                TaskListItem(
                    task = task,
                    onRescheduleClicked = {
                        onRescheduleClicked(task)
                    },
                    onDoneClicked = {
                        onDoneClicked(task)
                    },
                    modifier = Modifier
                        .testTag("INCOMPLETE_TASK_${task.id}")
                        .animateItemPlacement(),
                )
            }
        }

        item {
            SectionHeader(text = stringResource(R.string.completed_tasks_header))
        }

        if (completedTasks.isEmpty()) {
            item {
                EmptySectionCard(
                    text = stringResource(R.string.no_completed_tasks_label),
                )
            }
        } else {
            items(
                items = completedTasks,
                key = {
                    it.id
                },
            ) { task ->
                TaskListItem(
                    task = task,
                    onRescheduleClicked = {
                        onRescheduleClicked(task)
                    },
                    onDoneClicked = {
                        onDoneClicked(task)
                    },
                    modifier = Modifier
                        .testTag("COMPLETED_TASK_${task.id}")
                        .animateItemPlacement(),
                )
            }
        }
    }
}

@Composable
private fun EmptySectionCard(
    text: String,
    modifier: Modifier = Modifier,
) {
    Material3Card(
        modifier = modifier,
    ) {
        Text(
            text = text,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .padding(
                    vertical = 32.dp,
                    horizontal = 24.dp,
                ),
        )
    }
}

@Composable
private fun SectionHeader(
    text: String,
) {
    Text(
        text = text,
        style = MaterialTheme.typography.headlineSmall,
    )
}

@Preview(
    name = "Night Mode",
    uiMode = Configuration.UI_MODE_NIGHT_YES,
)
@Preview(
    name = "Day Mode",
    uiMode = Configuration.UI_MODE_NIGHT_NO,
)
@Composable
@Suppress("UnusedPrivateMember")
private fun FullTaskListPreview() {
    val incompleteTasks = (1..3).map { index ->
        Task(
            id = "$index",
            description = "Test task: $index",
            scheduledDateMillis = 0L,
            completed = false,
        )
    }

    val completedTasks = (1..3).map { index ->
        Task(
            id = "$index",
            description = "Test task: $index",
            scheduledDateMillis = 0L,
            completed = true,
        )
    }

    TOATheme {
        TaskList(
            incompleteTasks = incompleteTasks,
            completedTasks = completedTasks,
            onRescheduleClicked = {},
            onDoneClicked = {},
        )
    }
}

@Preview(
    name = "Night Mode",
    uiMode = Configuration.UI_MODE_NIGHT_YES,
)
@Preview(
    name = "Day Mode",
    uiMode = Configuration.UI_MODE_NIGHT_NO,
)
@Composable
@Suppress("UnusedPrivateMember")
private fun NoIncompleteTasksListPreview() {
    val completedTasks = (1..3).map { index ->
        Task(
            id = "$index",
            description = "Test task: $index",
            scheduledDateMillis = 0L,
            completed = true,
        )
    }

    TOATheme {
        TaskList(
            incompleteTasks = emptyList(),
            completedTasks = completedTasks,
            onRescheduleClicked = {},
            onDoneClicked = {},
        )
    }
}

@Preview(
    name = "Night Mode",
    uiMode = Configuration.UI_MODE_NIGHT_YES,
)
@Preview(
    name = "Day Mode",
    uiMode = Configuration.UI_MODE_NIGHT_NO,
)
@Composable
@Suppress("UnusedPrivateMember")
private fun NoCompletedTasksListPreview() {
    val incompleteTasks = (1..3).map { index ->
        Task(
            id = "$index",
            description = "Test task: $index",
            scheduledDateMillis = 0L,
            completed = false,
        )
    }

    TOATheme {
        TaskList(
            incompleteTasks = incompleteTasks,
            completedTasks = emptyList(),
            onRescheduleClicked = {},
            onDoneClicked = {},
        )
    }
}

@Preview(
    name = "Night Mode",
    uiMode = Configuration.UI_MODE_NIGHT_YES,
)
@Preview(
    name = "Day Mode",
    uiMode = Configuration.UI_MODE_NIGHT_NO,
)
@Composable
@Suppress("UnusedPrivateMember")
private fun NoTasksListPreview() {
    TOATheme {
        TaskList(
            incompleteTasks = emptyList(),
            completedTasks = emptyList(),
            onRescheduleClicked = {},
            onDoneClicked = {},
        )
    }
}
