package com.ey.todo.model;



import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@ToString
@Getter
@Setter
@Builder
public class GetUserRequest {

    private List<ToDoItem> todoList = new ArrayList<ToDoItem>();

}
