package todoList;

import java.util.Scanner;

public class TodoListMain {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		boolean run = true;
		int selectNumber = 0;
		TodoListService todoListService = new TodoListService();

		while (run) {
			System.out.println("1.새 할 일 추가|2.카테고리 추가|3.할 일 목록 보기|4.할 일 목록 수정|5.할 일 목록 삭제|6.종료");
			System.out.print("번호입력: ");
			selectNumber = scan.nextInt();

			// todoSave()
			if (selectNumber == 1) {
				todoListService.todoSave();
			}

			// catagorySave()
			else if (selectNumber == 2) {
				todoListService.catagorySave();
			}

			// todoFind()
			else if (selectNumber == 3) {
				todoListService.todoFind();
			}

			// todoUpdate()
			else if (selectNumber == 4) {
				todoListService.todoUpdate();
			}

			// todoDelete()
			else if (selectNumber == 5) {
				todoListService.todoDelete();
			}

			// End
			else if (selectNumber == 6) {
				System.out.println("종료합니다");
				run = false;
			}

			else {
				System.out.println("번호오류");
			}
		}
	}
}
