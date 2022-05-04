package todoList;

import java.util.ArrayList;
import java.util.List;

public class TodoListRepository {

	static List<TodoListDTO> todoList = new ArrayList<>();
	static List<CategoryDTO> categoryList = new ArrayList<>();

	// findAllCategory
	public List<CategoryDTO> findAllCategory() {
		return categoryList;
	}

}
