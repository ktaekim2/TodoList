package todoList;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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

	// todoSave()
	public void todoSave() {
		System.out.print("할 일: ");
		String todoContents = scan.nextLine();
		findAllCategory();
		System.out.print("카테고리 이름: ");
		String categoryName = scan.nextLine();
		if (categoryDuplicationCheck(categoryName)) {
			System.out.print("마감일(0000년00월00일): ");
			String todoDeadline = scan.nextLine();
			LocalDateTime dateTime = LocalDateTime.now();
			String todoCreatedDate = dateTime.format(DateTimeFormatter.ofPattern("yyyy년MM월dd일 HH:mm:ss"));
			TodoListDTO newTodo = new TodoListDTO(++todoId, todoContents, categoryName, todoDeadline, todoCreatedDate);
			if (todoListRepository.todoSave(newTodo)) {
				System.out.println("할 일 등록 완료");
			} else {
				System.out.println("할 일 등록 실패");
			}
		} else {
			System.out.println("카테고리를 추가한 후 다시 시도하세요.");
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
		for (TodoListDTO l : todoListExceptcompleted) {
			System.out.println(l);
		}
	}

	// findCompletedTodoList()
	public void findCompletedTodoList() {
		List<TodoListDTO> completedTodoList = todoListRepository.findCompletedTodoList();
		for (TodoListDTO l : completedTodoList) {
			System.out.println(l);
		}
	}

	// findTodoListByCategory()
	public void findTodoListByCategory() {
		findAllCategory();
		System.out.print("찾을 카테고리 이름: ");
		String categoryName = scan.nextLine();
		List<TodoListDTO> todoListByCategory = todoListRepository.findTodoListByCategory(categoryName);
		for (TodoListDTO l : todoListByCategory) {
			System.out.println(l);
		}
	}

	// findTodoListByKeyword()
	public void findTodoListByKeyword() {
		System.out.print("할 일 내용 키워드: ");
		String keyword = scan.nextLine();
		List<TodoListDTO> todoListByKeyword = todoListRepository.findTodoListByKeyword(keyword);
		for (TodoListDTO l : todoListByKeyword) {
			System.out.println(l);
		}
	}

	// updateTodoContents()
	public void updateTodoContents() {
		findTodoList();
		System.out.print("수정할 id: ");
		Long todoId = scan.nextLong();
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
		findTodoList();
		System.out.print("완료할 할 일 id: ");
		Long todoId = scan.nextLong();
		if (todoListRepository.todoCompleted(todoId)) {
			System.out.println("수정 완료");
		} else {
			System.out.println("수정 실패");
		}
		findTodoList();
	}

	// updateTodoCategory()
	public void updateTodoCategory() {
		findTodoList();
		System.out.print("이동할 할 일 id: ");
		Long todoId = scan.nextLong();
		scan.nextLine();
		System.out.print("이동할 카테고리: ");
		String categoryName = scan.nextLine();
		if (todoListRepository.updateTodoCategory(todoId, categoryName)) {
			System.out.println("카테고리 이동 완료");
		} else {
			System.out.println("카테고리 이동 실패");
		}
		findTodoList();
	}

	// updateDeadline()
	public void updateDeadline() {
		findTodoList();
		System.out.print("수정할 할 일 id: ");
		Long todoId = scan.nextLong();
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
		findAllCategory();
		System.out.print("수정할 카테고리 id: ");
		Long categoryId = scan.nextLong();
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
		findTodoList();
		System.out.print("삭제할 할 일 id: ");
		Long todoId = scan.nextLong();
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

	// deleteAllByCategory()
	public void deleteAllByCategory() {
		findTodoList();
		System.out.print("삭제할 할 일 카테고리: ");
		String categoryName = scan.nextLine();
		if (todoListRepository.deleteAllByCategory(categoryName)) {
			System.out.println("삭제 완료");
		} else {
			System.out.println("삭제 실패");
		}
		findTodoList();
	}

	// deleteCategory()
	public void deleteCategory() {
		findAllCategory();
		System.out.print("삭제할 카테고리 id: ");
		Long categoryId = scan.nextLong();
		if (todoListRepository.deleteCategory(categoryId)) {
			System.out.println("삭제 완료");
		} else {
			System.out.println("삭제 실패");
		}
		findAllCategory();
	}

}
