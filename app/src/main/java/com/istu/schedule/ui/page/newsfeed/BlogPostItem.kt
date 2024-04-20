package com.istu.schedule.ui.page.newsfeed

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.istu.schedule.ui.theme.AppTheme
import com.istu.schedule.ui.theme.Shape10
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import me.progneo.campus.domain.entities.User
import me.progneo.campus.ui.entities.BlogPostUiModel

@Composable
fun BlogPostItem(
    blogPost: BlogPostUiModel,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier.clip(Shape10),
        colors = CardColors(
            contentColor = AppTheme.colorScheme.textPrimary,
            containerColor = AppTheme.colorScheme.surface,
            disabledContentColor = AppTheme.colorScheme.textSecondary,
            disabledContainerColor = AppTheme.colorScheme.backgroundSecondary
        )
    ) {
        val authorName = blogPost.author?.let {
            ((it.lastName ?: "") + (" " + it.name + " ") + (it.secondName ?: "")).trim()
        } ?: ""

        val dateFormat = SimpleDateFormat("dd.MM HH:mm", Locale("ru-RU"))
        val formattedDate = dateFormat.format(blogPost.datePublished)

        Column(
            modifier = Modifier.padding(15.dp, 20.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            SelectionContainer {
                Text(
                    text = blogPost.title,
                    style = AppTheme.typography.title,
                    color = AppTheme.colorScheme.textPrimary
                )
            }
            if (blogPost.title != blogPost.detailText) {
                SelectionContainer {
                    Text(
                        text = blogPost.detailText,
                        style = AppTheme.typography.bodyMedium,
                        color = AppTheme.colorScheme.secondary
                    )
                }
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = authorName,
                    style = AppTheme.typography.bodySmall,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier.weight(1f)
                )
                Row(modifier = Modifier.align(Alignment.Bottom)) {
                    Text(
                        text = "Дата: ",
                        style = AppTheme.typography.bodySmall,
                        fontWeight = FontWeight.SemiBold
                    )
                    Text(
                        text = formattedDate,
                        style = AppTheme.typography.bodySmall,
                        color = AppTheme.colorScheme.secondary
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun PreviewNewsfeedItem() {
    AppTheme {
        BlogPostItem(
            blogPost = BlogPostUiModel(
                id = 1,
                authorId = 1,
                title = "Уважаемые студенты!",
                detailText = "Провожу консультации, отвечаю на вопросы и принимаю долги каждый четверг с 8.30 до 9.45 в аудитории В-107!",
                datePublished = Date(),
                author = User(
                    id = 1,
                    name = "Олег",
                    lastName = "Бучнев",
                    secondName = "Сергеевич"
                )
            )
        )
    }
}
