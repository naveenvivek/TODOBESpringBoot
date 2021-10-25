package com.ey.todo.model;

import java.time.LocalDateTime;
import java.util.List;

import com.ey.todo.domain.Todo;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.NotBlank;

@ToString
@Getter
@Setter
@Builder
public class ToDoItem {

    private Long id;

    private String description;

    private Boolean completed;

    private String lastUpdatedTime;


}
