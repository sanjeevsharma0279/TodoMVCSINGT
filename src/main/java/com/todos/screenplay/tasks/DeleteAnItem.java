package com.todos.screenplay.tasks;

import com.todos.screenplay.actions.JSClick;
import com.todos.screenplay.user_interface.TodoListItem;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;

public class DeleteAnItem {
    public static Performable called(String itemName) {
        return Task.where("{0} deletes the item " + itemName,
                JSClick.on(TodoListItem.DELETE_ITEM.of(itemName))
        );
    }
}