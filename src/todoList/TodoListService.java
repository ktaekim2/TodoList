package todoList;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

public class TodoListService {
	Scanner scan = new Scanner(System.in);
	TodoListRepository todoListRepository = new TodoListRepository();
	Long id = 0L;

	// findAllCategory
	public void findAllCategory() {
		List<CategoryDTO> categoryList = todoListRepository.findAllCategory();
		for (CategoryDTO l : categoryList) {
			System.out.println(l);
		}
	}

	// todoSave()
	public void todoSave() {
		System.out.print("할 일: ");
		String todoContents = scan.next();
		findAllCategory();
		System.out.print("카테고리 이름: ");
		String todoCategory = scan.next();
		System.out.print("마감일(0000년00월00일: ");
		String todoDeadline = scan.next();
		LocalDateTime dateTime = LocalDateTime.now();
		String todoCreatedDate = dateTime.format(DateTimeFormatter.ofPattern("yyyy년MM월dd일 HH:mm:ss"));
		TodoListDTO newTodo = new TodoListDTO(++id, todoContents, todoCategory, todoDeadline, todoCreatedDate);

		boolean saveResult = postRepository.save(newPost);
		if (saveResult) {
			System.out.println("글등록 완료");
		} else {
			System.out.println("글등록 실패");
		}
	}

	// catagorySave()
	public void catagorySave() {
		// TODO Auto-generated method stub

	}

	// todoFind()
	public void todoFind() {
		// TODO Auto-generated method stub

	}

	// todoUpdate()
	public void todoUpdate() {
		// TODO Auto-generated method stub

	}

	// todoDelete()
	public void todoDelete() {
		// TODO Auto-generated method stub

	}

}
