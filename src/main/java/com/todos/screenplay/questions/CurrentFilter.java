package com.todos.screenplay.questions;

import com.todos.screenplay.model.TodoStatusFilter;
import com.todos.screenplay.user_interface.TodoList;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.questions.Text;

public class CurrentFilter {

    public static Question<TodoStatusFilter> selected() {
        return Text.of(TodoList.SELECTED_FILTER)
                .describedAs("the current filter")
                .asEnum(TodoStatusFilter.class);
    }
}