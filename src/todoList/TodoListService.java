package todoList;

import java.util.List;
import java.util.Scanner;

public class TodoListService {
	Scanner scan = new Scanner(System.in);
	TodoListRepository todoListRepository = new TodoListRepository();
	Long todoId = 0L;
	Long categoryId = 0L;

	// defaultCategorySave()
	public void defaultCategorySave() {
		String categoryName = "내 할 일 목록";
		CategoryDTO newCategory = new CategoryDTO(++categoryId, categoryName);
		todoListRepository.catagorySave(newCategory);
		categoryName = "완료한 할 일 목록";
		newCategory = new CategoryDTO(++categoryId, categoryName);
		todoListRepository.catagorySave(newCategory);
	}

	// findAllCategory
	public void findAllCategory() {
		List<CategoryDTO> categoryList = todoListRepository.findAllCategory();
		for (CategoryDTO l : categoryList) {
			System.out.println(l);
		}
	}

	// categoryDuplicationCheck()
	private boolean categoryDuplicationCheck(String categoryName) {
		if (todoListRepository.categoryDuplicationCheck(categoryName)) {
			System.out.println("유효한 카테고리");
			return true;
		} else {
			System.out.println("카테고리 없음");
			return false;
		}
	}

	// returnTodoId()
	public Long returnTodoId(String whatTodo) {
		findTodoList();
		System.out.print(whatTodo + " 할 일 번호: ");
		return todoId = scan.nextLong();
	}

	// returnCategoryId()
	public Long returnCategoryId(String whatTodo) {
		findAllCategory();
		System.out.print(whatTodo + " 카테고리 번호: ");
		return categoryId = scan.nextLong();
	}

	// todoSave()
	public void todoSave() {
		System.out.print("할 일: ");
		String todoContents = scan.nextLine();
		findAllCategory();
		System.out.print("카테고리 번호: ");
		Long categoryId = scan.nextLong();
		scan.nextLine();
		String categoryName = todoListRepository.findNameById(categoryId);
		if (categoryName != null) {
			System.out.print("마감일(0000년00월00일): ");
			String todoDeadline = scan.nextLine();
			TodoListDTO newTodo = new TodoListDTO(++todoId, todoContents, categoryName, todoDeadline);
			if (todoListRepository.todoSave(newTodo)) {
				System.out.println("할 일 등록 완료");
			} else {
				System.out.println("할 일 등록 실패");
			}
		} else {
			System.out.println("정확한 번호를 입력하세요.");
		}

	}

	// catagorySave()
	public void catagorySave() {
		findAllCategory();
		System.out.print("추가할 카테고리 이름: ");
		String categoryName = scan.nextLine();
		if (!categoryDuplicationCheck(categoryName)) {
			CategoryDTO newCategory = new CategoryDTO(++categoryId, categoryName);
			if (todoListRepository.catagorySave(newCategory)) {
				System.out.println("카테고리 등록 완료");
			} else {
				System.out.println("카테고리 등록 실패");
			}
			findAllCategory();
		} else {
			System.out.println("카테고리 이름 중복");
		}

	}

	// findTodoList()
	public void findTodoList() {
		List<TodoListDTO> todoListExceptcompleted = todoListRepository.findTodoList();
		if (todoListExceptcompleted.isEmpty()) {
			System.out.println("목록이 존재하지 않음");
		} else {
			for (TodoListDTO l : todoListExceptcompleted) {
				System.out.println(l);
			}
		}
	}

	// findCompletedTodoList()
	public void findCompletedTodoList() {
		List<TodoListDTO> completedTodoList = todoListRepository.findCompletedTodoList();
		if (completedTodoList.isEmpty()) {
			System.out.println("목록이 존재하지 않음");
		} else {
			for (TodoListDTO l : completedTodoList) {
				System.out.println(l);
			}
		}
	}

	// findTodoListByCategory()
	public void findTodoListByCategory() {
		Long categoryId = returnCategoryId("찾을");
		scan.nextLine();
		String categoryName = todoListRepository.findNameById(categoryId);
		if (categoryName != null) {
			List<TodoListDTO> todoListByCategory = todoListRepository.findTodoListByCategory(categoryName);
			if (todoListByCategory.isEmpty()) {
				System.out.println("목록이 존재하지 않음");
			} else {
				for (TodoListDTO l : todoListByCategory) {
					System.out.println(l);
				}
			}
		} else {
			System.out.println("정확한 번호를 입력하세요.");
		}

	}

	// findTodoListByKeyword()
	public void findTodoListByKeyword() {
		System.out.print("할 일 내용 키워드: ");
		String keyword = scan.nextLine();
		List<TodoListDTO> todoListByKeyword = todoListRepository.findTodoListByKeyword(keyword);
		if (todoListByKeyword.isEmpty()) {
			System.out.println("목록이 존재하지 않음");
		} else {
			for (TodoListDTO l : todoListByKeyword) {
				System.out.println(l);
			}
		}
	}

	// updateTodoContents()
	public void updateTodoContents() {
		Long todoId = returnTodoId("수정할");
		scan.nextLine();
		System.out.print("수정할 할 일 내용: ");
		String todoContents = scan.nextLine();
		if (todoListRepository.updateTodoContents(todoId, todoContents)) {
			System.out.println("수정 완료");
		} else {
			System.out.println("수정 실패");
		}
		findTodoList();
	}

	// todoCompleted()
	public void todoCompleted() {
		Long todoId = returnTodoId("완료할");
		scan.nextLine();
		if (todoListRepository.todoCompleted(todoId)) {
			System.out.println("수정 완료");
		} else {
			System.out.println("수정 실패");
		}
		findTodoList();
	}

	// updateTodoCategory()
	public void updateTodoCategory() {
		Long todoId = returnTodoId("이동할");
		Long categoryId = returnCategoryId("이동할");
		scan.nextLine();
		String categoryName = todoListRepository.findNameById(categoryId);
		if (categoryName != null) {
			if (todoListRepository.updateTodoCategory(todoId, categoryName)) {
				System.out.println("카테고리 이동 완료");
			} else {
				System.out.println("카테고리 이동 실패");
			}
			findTodoList();
		} else {
			System.out.println("정확한 번호를 입력하세요.");
		}

	}

	// updateDeadline()
	public void updateDeadline() {
		Long todoId = returnTodoId("수정할");
		scan.nextLine();
		System.out.print("수정할 마감일(0000년00월00일): ");
		String todoDeadline = scan.nextLine();
		if (todoListRepository.updateDeadline(todoId, todoDeadline)) {
			System.out.println("마감일 수정 완료");
		} else {
			System.out.println("마감일 수정 실패");
		}
		findTodoList();
	}

	// updateCategory()
	public void updateCategory() {
		Long categoryId = returnCategoryId("수정할");
		scan.nextLine();
		System.out.print("수정할 카테고리 이름: ");
		String categoryName = scan.nextLine();
		if (todoListRepository.updateCategory(categoryId, categoryName)) {
			System.out.println("카테고리 수정 완료");
		} else {
			System.out.println("카테고리 수정 실패");
		}
		findAllCategory();
	}

	// deleteTodoList()
	public void deleteTodoList() {
		Long todoId = returnTodoId("삭제할");
		scan.nextLine();
		if (todoListRepository.deleteTodoList(todoId)) {
			System.out.println("삭제 완료");
		} else {
			System.out.println("삭제 실패");
		}
		findTodoList();
	}

	// deleteAllOfCompletedList()
	public void deleteAllOfCompletedList() {
		findCompletedTodoList();
		if (todoListRepository.deleteAllOfCompletedList()) {
			System.out.println("삭제 완료");
		} else {
			System.out.println("삭제할 목록이 없음");
		}
		findCompletedTodoList();
	}

	// deleteCategory()
	public void deleteCategory() {
		Long categoryId = returnCategoryId("삭제할");
		scan.nextLine();
		if (todoListRepository.deleteCategory(categoryId)) {
			System.out.println("삭제 완료");
		} else {
			System.out.println("삭제 실패");
		}
		findAllCategory();
	}

}
