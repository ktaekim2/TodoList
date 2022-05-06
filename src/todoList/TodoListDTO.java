package todoList;

public class TodoListDTO {

	private Long todoId;
	private String todoContents;
	private String categoryName;
	private String todoDeadline;

	public TodoListDTO() {
	}

	public TodoListDTO(Long todoId, String todoContents, String categoryName, String todoDeadline) {
		this.todoId = todoId;
		this.todoContents = todoContents;
		this.categoryName = categoryName;
		this.todoDeadline = todoDeadline;
	}

	public Long getTodoId() {
		return todoId;
	}

	public void setTodoId(Long todoId) {
		this.todoId = todoId;
	}

	public String getTodoContents() {
		return todoContents;
	}

	public void setTodoContents(String todoContents) {
		this.todoContents = todoContents;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public String getTodoDeadline() {
		return todoDeadline;
	}

	public void setTodoDeadline(String todoDeadline) {
		this.todoDeadline = todoDeadline;
	}

	@Override
	public String toString() {
		return "TodoListDTO [todoId=" + todoId + ", todoContents=" + todoContents + ", categoryName=" + categoryName
				+ ", todoDeadline=" + todoDeadline + "]";
	}

}
