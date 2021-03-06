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
- 데드라인이 빠른 순으로 목록을 출력
- 완료한 할 일 목록을 따로 저장
- 완료한 할 일 모두 삭제 기능
- 할 일 목록을 새로 만들어서 카테고리를 나눌 수 있다.
  
## 주요 기능 
### 새 할 일 추가
- 할 일 번호: 1부터 자동으로 올라가도록 만듬.
- 할 일 내용: Scan으로 내용을 받는다.
- 카테고리: 할 일을 어느 카테고리 목록에 추가할 지 선택, 카테고리 목록(categoryList)을 보여주고 scan으로 받는다. 카테고리가 존재하지 않으면 다시 입력하도록 만듬.
- 마감일: String으로 마감 날짜를 받는다.
- 작성일: LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy년MM월dd일 HH:mm:ss")

### 카테고리 추가
- 기본 카테고리와 완료한 할 일 카테고리는 프로그램 실행 시 미리 만들어져 있어야 한다.
- 카테고리 번호: 1부터 자동으로 올라가도록 만듬.
- 카테고리 이름: Scan으로 내용을 받는다.
- 카테고리 설명: Scan으로 내용을 받는다.

### 할 일 목록 보기
- 모든 할 일 조회: todoList의 모든 목록 조회(완료한 할 일 제외)
- 카테고리 별 조회: categoryList를 보여주고, todoList의 categoryName가 특정 이름인 목록만 조회
- 완료한 할 일 목록 조회: 카테고리 이름이 '완료한 할 일'인 목록 조회

### 할 일 목록 수정
- 할 일 내용 수정: todoContents를 수정
- 할 일 완료로 표시(완료 목록으로 이동): todoCategory를 수정
- 할 일 카테고리 이동: categoryName를 수정
- 할 일 마감일 수정: todoDeadline을 수정

### 할 일 목록 삭제
- 삭제: 삭제 할 todoId를 받고, List.remove(i);
- 특정 카테고리 할 일 목록 모두 삭제 기능: for문으로 특정 카테고리를 찾고, List.remove(i);
- 카테고리 삭제: categoryList에서 그 카테고리를 삭제하고, 그 카테고리를 가진 할 일들을 전부 기본 카테고리로 변경
- 완료한 할 일 모두 삭제 기능: 특정 카테고리 할 일 목록 모두 삭제 기능에서 '완료한 할 일' 카테고리만 삭제
  
## 기타
- 두 번째 이상 기능 선택 창부터 뒤로가기 기능 추가
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

## 20220504
- 각 DTO 필드, 생성자, getter/setter, toString 구성 완료
- 초기 메서드 포멧 구성 완료

## 20220506
### 기능 추가
- TodoListDTO와 CategoryDTO의 공통된 data의 변수 이름 'categoryName'으로 통일(todoCategory -> categoryName)
- default category 2개가 프로그램 실행 시 만들어지도록 만듬(defaultCategorySave())
- main class에서 선택 후 추가 선택 기능 추가
- main class에서 추가 선택에서 뒤로가기 기능 추가
- 키워드로 할 일 검색 기능 추가
- 카테고리(CategoryDTO) 수정기능 추가
- 카테고리 중복검사 메서드 추가
- selectNumber에 예외처리 코드 추가
- 할 일을 추가 시, 카테고리 이름이 아닌 id로 받는 기능, 조회 여부 메세지 추가
- 카테고리 별 할 일 조회 시, 이름이 아닌 id로 받는 기능, 조회 여부 메세지 추가
- todoList를 **마감일 빠른순**으로 조회 기능 추가
- isEmpty()메서드를 활용해서 리스트가 비어있을때 메세지 출력 기능 추가

## 기능 삭제
- '할 일 설명'필드 삭제
- '특정 카테고리 할 일 모두 삭제'기능 삭제
- createdTime 필드 삭제

### error
- 모두 삭제 기능 오류 발견: arraylist는 앞 index가 삭제되면 뒤에 있던 녀석이 앞으로 당겨지면서 index번호 또한 당겨지므로 for문으로 삭제 불가능.(해결: https://hi-dot.tistory.com/4)
- 카테고리 이름 변경 시, todoList의 카테고리 이름도 변경해야 함.

## 20220509
### 기능 추가
- 카테고리 이름 변경 시, todoList의 할 일들 카테고리 이름도 변경되도록 만듬.
- 카테고리 삭제 시, todoList의 삭제된 카테고리에 해당하는 할 일들의 카테고리를 "내 할 일 목록" 카테고리로 이동하도록 만듬.

### 개인프로젝트 하면서 느낀점
- '할 일 설명' 필드, '특정 카테고리 할 일 모두 삭제'기능, createdTime 필드 등 만드는 과정에서 삭제된 코드를 처음 기획 단계에서부터 기능이나 필드 하나하나의 필요성을 좀 더 깊게 고민하고 생각해서 없는채로 시작했다면, 시간이 더 단축되었을 것이라 생각된다.
- arraylist를 날짜 빠른 순으로 정렬하는 코드라던지, 모두 삭제 기능에서 앞 index가 삭제되면 뒷 index가 앞으로 당겨져서 for문으로 특정 조건을 만족하는 요소 일괄 삭제가 안되었던 문제를 구글링으로 해결했는데 그렇게 가져온 코드를 내가 온전히 이해하지 못한채로 그냥 사용했다는 점이 신경 쓰인다.

