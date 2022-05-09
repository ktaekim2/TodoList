package todoList;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

public class TodoListRepository {

	static List<TodoListDTO> todoList = new ArrayList<>();
	static List<CategoryDTO> categoryList = new ArrayList<>();

	// findAllCategory()
	public List<CategoryDTO> findAllCategory() {
		return categoryList;
	}

	// categoryDuplicationCheck()
	public boolean categoryDuplicationCheck(String categoryName) {
		boolean result = false;
		for (int i = 0; i < categoryList.size(); i++) {
			if (categoryList.get(i).getCategoryName().equals(categoryName)) {
				result = true;
			}
		}
		return result;
	}

	// findNameById()
	public String findNameById(Long categoryId) {
		for (int i = 0; i < categoryList.size(); i++) {
			if (categoryList.get(i).getCategoryId() == categoryId) {
				return categoryList.get(i).getCategoryName();
			}
		}
		return null;
	}

	// todoSave()
	public boolean todoSave(TodoListDTO newTodo) {
		return todoList.add(newTodo);
	}

	// catagorySave()
	public boolean catagorySave(CategoryDTO newCategory) {
		return categoryList.add(newCategory);
	}

	// findTodoList()
	public List<TodoListDTO> findTodoList() {
		List<TodoListDTO> todoListExceptcompleted = new ArrayList<>();
		for (int i = 0; i < todoList.size(); i++) {
			if (!todoList.get(i).getCategoryName().equals("완료한 할 일 목록")) {
				todoListExceptcompleted.add(todoList.get(i));
				Collections.sort(todoListExceptcompleted, new Comparator() {
					@Override
					public int compare(Object o1, Object o2) {
						return ((TodoListDTO) o1).getTodoDeadline().compareTo(((TodoListDTO) o2).getTodoDeadline());
					}
				});
			}
		}
		return todoListExceptcompleted;
	}

	// findCompletedTodoList()
	public List<TodoListDTO> findCompletedTodoList() {
		List<TodoListDTO> completedTodoList = new ArrayList<>();
		for (int i = 0; i < todoList.size(); i++) {
			if (todoList.get(i).getCategoryName().equals("완료한 할 일 목록")) {
				completedTodoList.add(todoList.get(i));
				Collections.sort(completedTodoList, new Comparator() {
					@Override
					public int compare(Object o1, Object o2) {
						return ((TodoListDTO) o1).getTodoDeadline().compareTo(((TodoListDTO) o2).getTodoDeadline());
					}
				});
			}
		}
		return completedTodoList;
	}

	// findTodoListByCategory()
	public List<TodoListDTO> findTodoListByCategory(String categoryName) {
		List<TodoListDTO> todoListByCategory = new ArrayList<>();
		for (int i = 0; i < todoList.size(); i++) {
			if (todoList.get(i).getCategoryName().equals(categoryName)) {
				todoListByCategory.add(todoList.get(i));
				Collections.sort(todoListByCategory, new Comparator() {
					@Override
					public int compare(Object o1, Object o2) {
						return ((TodoListDTO) o1).getTodoDeadline().compareTo(((TodoListDTO) o2).getTodoDeadline());
					}
				});
			}
		}
		return todoListByCategory;
	}

	// findTodoListByKeyword()
	public List<TodoListDTO> findTodoListByKeyword(String keyword) {
		List<TodoListDTO> todoListByKeyword = new ArrayList<>();
		for (int i = 0; i < todoList.size(); i++) {
			if (todoList.get(i).getTodoContents().contains(keyword)) {
				todoListByKeyword.add(todoList.get(i));
				Collections.sort(todoListByKeyword, new Comparator() {
					@Override
					public int compare(Object o1, Object o2) {
						return ((TodoListDTO) o1).getTodoDeadline().compareTo(((TodoListDTO) o2).getTodoDeadline());
					}
				});
			}
		}
		return todoListByKeyword;
	}

	// updateTodoContents()
	public boolean updateTodoContents(Long todoId, String todoContents) {
		boolean result = false;
		for (int i = 0; i < todoList.size(); i++) {
			if (todoList.get(i).getTodoId() == todoId) {
				todoList.get(i).setTodoContents(todoContents);
				result = true;
			}
		}
		return result;
	}

	// todoCompleted()
	public boolean todoCompleted(Long todoId) {
		boolean result = false;
		for (int i = 0; i < todoList.size(); i++) {
			if (todoList.get(i).getTodoId() == todoId) {
				todoList.get(i).setCategoryName("완료한 할 일 목록");
				result = true;
			}
		}
		return result;
	}

	// updateTodoCategory()
	public boolean updateTodoCategory(Long todoId, String categoryName) {
		boolean result = false;
		for (int i = 0; i < todoList.size(); i++) {
			if (todoList.get(i).getTodoId() == todoId) {
				todoList.get(i).setCategoryName(categoryName);
				result = true;
			}
		}
		return result;
	}

	// updateDeadline()
	public boolean updateDeadline(Long todoId, String todoDeadline) {
		boolean result = false;
		for (int i = 0; i < todoList.size(); i++) {
			if (todoList.get(i).getTodoId() == todoId) {
				todoList.get(i).setTodoDeadline(todoDeadline);
				result = true;
			}
		}
		return result;
	}

	// updateCategory()
	public boolean updateCategory(Long categoryId, String categoryName) {
		boolean result = false;
		String todoCategoryName = "";
		for (int i = 0; i < categoryList.size(); i++) {
			if (categoryList.get(i).getCategoryId() == categoryId) {
				todoCategoryName = categoryList.get(i).getCategoryName();
				categoryList.get(i).setCategoryName(categoryName);
				result = true;
			}
		}
		for (int i = 0; i < todoList.size(); i++) {
			if (todoList.get(i).getCategoryName().equals(todoCategoryName)) {
				todoList.get(i).setCategoryName(categoryName);
			}
		}
		return result;
	}

	// deleteTodoList()
	public boolean deleteTodoList(Long todoId) {
		boolean result = false;
		for (int i = 0; i < todoList.size(); i++) {
			if (todoList.get(i).getTodoId() == todoId) {
				todoList.remove(i);
				result = true;
			}
		}
		return result;
	}

	// deleteAllOfCompletedList()
	public boolean deleteAllOfCompletedList() {
		boolean result = false;
		for (Iterator<TodoListDTO> it = todoList.iterator(); it.hasNext();) {
			TodoListDTO str = it.next();
			if (str.getCategoryName().equals("완료한 할 일 목록"))
				it.remove();
			result = true;
		}
		return result;
	}

	// deleteCategory()
	public boolean deleteCategory(Long categoryId) {
		boolean result = false;
		String todoCategoryName = "";
		for (int i = 0; i < categoryList.size(); i++) {
			if (categoryList.get(i).getCategoryId() == categoryId) {
				todoCategoryName = categoryList.get(i).getCategoryName();
				categoryList.remove(i);
				result = true;
			}
		}
		for (int i = 0; i < todoList.size(); i++) {
			if (todoList.get(i).getCategoryName().equals(todoCategoryName)) {
				todoList.get(i).setCategoryName("내 할 일 목록");
			}
		}
		return result;
	}

}
