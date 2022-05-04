# 프로젝트 기획안
**작성일 : 2022. 05. 04**  
**작성자 : 김경태**  
**프로젝트 명 : TodoList**

## 기획 의도 
- 할 일들을 잊지않게 기록하고 중요도에 따라 우선순위를 매기는 프로그램.
- 일상에서 잊지 말고 해야 할 일들을 기록하고 우선순위를 매겨서 급한 일부터 먼저 처리할 수 있게 도와줄 수 있는 프로그램이 유용하다고 생각하여 기획하게 됨.
- 할 일 목록을 추가, 읽기, 수정, 삭제가 가능하다.

## 벤치마킹
- google tasks를 참고
- 마감 날짜와 시간이 빠른 순으로 목록을 출력
- 완료한 할 일 목록을 따로 저장
- 완료한 할 일 모두 삭제 기능
- 할 일 목록을 새로 만들어서 카테고리를 나눌 수 있다.
- 반복 기능(특정 날짜에 같은 할 일을 반복할 수 있는 기능): 내 실력으로 불가능
  
## 주요 기능 
### 새 할 일 추가
- 할 일 번호: 1부터 자동으로 올라가도록 만듬.
- 할 일 내용: Scan으로 내용을 받는다.
- 카테고리: 할 일을 어느 카테고리 목록에 추가할 지 선택, 카테고리 목록(categoryList)을 보여주고 scan으로 받는다. 카테고리가 존재하지 않으면 다시 입력하도록 만듬.
- 마감일: String으로 마감 날짜를 받는다.
- 작성일: LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy년MM월dd일 HH:mm:ss")

### 카테고리 추가
- 기본 카테고리와 완료한 할 일 카테고리는 프로그램 실행 시 미리 만들어져 있어야 한다.
- 카테고리 번호: 3부터 자동으로 올라가도록 만듬.
- 카테고리 이름: Scan으로 내용을 받는다.
- 카테고리 설명: Scan으로 내용을 받는다.

### 할 일 목록 보기
- 마감일 빠른 순으로 출력(default): arrayList.sort((d1,d2) -> d1.compareTo(d2));
- 모든 할 일 조회: todoList의 모든 목록 조회
- 카테고리 별 조회: todoList의 todoCategory가 특정 이름인 목록만 조회
- 완료한 할 일 목록 조회: 카테고리 이름이 '완료한 할 일'인 목록 조회

### 할 일 목록 수정
- 할 일 내용 수정: todoContents를 수정
- 할 일 카테고리 이동: todoCategory를 수정
- 할 일 마감일 수정: todoDeadline을 수정
- 할 일 완료로 표시(완료 목록으로 이동): todoCategory를 수정

### 할 일 목록 삭제
- 삭제: 삭제 할 todoId를 받고, List.remove(i);
- 특정 카테고리 할 일 목록 모두 삭제 기능: for문으로 특정 카테고리를 찾고, List.remove(i);
- 카테고리 삭제: categoryList에서 그 카테고리를 삭제하고, 그 카테고리를 가진 할 일들을 전부 기본 카테고리로 변경
- 완료한 할 일 모두 삭제 기능: 특정 카테고리 할 일 목록 모두 삭제 기능에서 '완료한 할 일' 카테고리만 삭제
  
## 기타
- 기능 선택 창에서 뒤로가기 기능 추가
- 할 일 검색 기능: 내용 중 키워드를 포함하는 할 일 목록 조회

## 할 일 정보(TodoListDTO)
- 할 일 번호: todoId
- 할 일 내용: todoContents
- 할 일 카테고리: todoCategory
- 할 일 마감일: todoDeadline
- 할 일 작성일: todoCreatedDate

## 카테고리 정보(CategoryDTO)
- 카테고리 번호: categoryId
- 카테고리 이름: categoryName
- 카테고리 설명: categoryDiscription

## Class 구성
- TodoListMain Class
- TodoListService Class
- TodoListRepository Class
- TodoListDTO Class
- CategoryDTO Class
