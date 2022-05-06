package todoList;

import java.util.InputMismatchException;
import java.util.Scanner;

public class TodoListMain {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		boolean run = true;
		int selectNumber = 0;
		TodoListService todoListService = new TodoListService();

		// defaultCategorySave()
		todoListService.defaultCategorySave();

		while (run) {
			try {
				System.out.println("1.추가하기|2.조회하기|3.수정하기|4.삭제하기|5.종료");
				System.out.print("번호입력: ");
				selectNumber = scan.nextInt();

				// saveSelect
				if (selectNumber == 1) {
					boolean run1 = true;
					while (run1) {
						System.out.println("1.새 할 일 추가|2.카테고리 추가|3.뒤로가기");
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

						// back()
						else if (selectNumber == 3) {
							run1 = false;
						} else {
							System.out.println("번호오류");
						}
					}
				}

				// findSelect()
				else if (selectNumber == 2) {
					boolean run2 = true;
					while (run2) {
						System.out.println("1.모든 할 일 조회|2.완료한 할 일 목록 조회|3.카테고리 별 할 일 조회|4.내용 키워드로 찾기|5.뒤로가기");
						System.out.print("번호입력: ");
						selectNumber = scan.nextInt();

						// findTodoList()
						if (selectNumber == 1) {
							todoListService.findTodoList();
						}

						// findCompletedTodoList()
						else if (selectNumber == 2) {
							todoListService.findCompletedTodoList();
						}

						// findTodoListByCategory()
						else if (selectNumber == 3) {
							todoListService.findTodoListByCategory();
						}

						// findTodoListByKeyword()
						else if (selectNumber == 4) {
							todoListService.findTodoListByKeyword();
						}
						// back()
						else if (selectNumber == 5) {
							run2 = false;
						} else {
							System.out.println("번호오류");
						}
					}
				}

				// updateSelect()
				else if (selectNumber == 3) {
					boolean run3 = true;
					while (run3) {
						System.out.println("1.할 일 내용 수정|2.할 일 완료|3.할 일 카테고리 이동|4.할 일 마감일 수정|5.카테고리 이름 수정|6.뒤로가기");
						System.out.print("번호입력: ");
						selectNumber = scan.nextInt();

						// updateTodoContents()
						if (selectNumber == 1) {
							todoListService.updateTodoContents();
						}

						// todoCompleted()
						else if (selectNumber == 2) {
							todoListService.todoCompleted();
						}

						// updateTodoCategory()
						else if (selectNumber == 3) {
							todoListService.updateTodoCategory();
						}

						// updateDeadline()
						else if (selectNumber == 4) {
							todoListService.updateDeadline();
						}

						// updateCategory()
						else if (selectNumber == 5) {
							todoListService.updateCategory();
						}

						// back()
						else if (selectNumber == 6) {
							run3 = false;
						} else {
							System.out.println("번호오류");
						}
					}
				}

				// deleteSelect()
				else if (selectNumber == 4) {
					boolean run4 = true;
					while (run4) {
						System.out.println("1.할 일 삭제|2.완료한 할 일 모두 삭제|3.특정 카테고리 할 일 모두 삭제|4.카테고리 삭제|5.뒤로가기");
						System.out.print("번호입력: ");
						selectNumber = scan.nextInt();

						// deleteTodoList()
						if (selectNumber == 1) {
							todoListService.deleteTodoList();
						}

						// deleteAllOfCompletedList()
						else if (selectNumber == 2) {
							todoListService.deleteAllOfCompletedList();
						}

						// deleteAllByCategory()
						else if (selectNumber == 3) {
							todoListService.deleteAllByCategory();
						}

						// deleteCategory()
						else if (selectNumber == 4) {
							todoListService.deleteCategory();
						}

						// back()
						else if (selectNumber == 5) {
							run4 = false;
						} else {
							System.out.println("번호오류");
						}
					}
				}

				// End
				else if (selectNumber == 5) {
					System.out.println("종료합니다");
					run = false;
				}

				else {
					System.out.println("번호오류");
				}
			} catch (InputMismatchException e) {
				System.out.println("InputMismatchException 예외 발생!!");
				System.out.println("숫자를 입력하세요.");
			} finally {
				scan.nextLine();
				continue;
			}
		}

	}
}
