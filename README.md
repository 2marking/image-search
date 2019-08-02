# 카카오페이 2019 클라이언트 인턴 채용 - 안드로이드 과제

고재두

## UI
- 노란색/갈색/흰색

### 스플래시 화면
- 0.6초 후 메인으로 이동
- Image Searcher 
- 돋보기 + 그림
- 고재두 nadarm

### 메인 화면
- 툴바에 이름

#### 검색창
- searchView : 검색어 입력 받기
- 리스트 스크롤 아래로 이동하면 숨기기
- 위로 이동하면 나오기
- 텍스트 입력 중 자동완성 목록

#### 최근 본 이미지
- 처음엔 없고 검색어 상관없이 보면 검색창 아래 추가됨
- 리스트 스크롤 아래로 이동하면 숨기기
- 위로 이동하면 나오기
- 항상 숨기기 버튼(스크롤 최상위일 때만 나오게)
- pagerSnapHelper viewPager

#### 리스트
- 그리드
- 썸네일 이미지 4 * 3
- 처음 12개 불러온 후 스크롤 내리면 6개씩 추가
- 이미지 불러올 때 로딩 스피너 보여주기 - progress
 
### 디테일 화면
- 카카오톡 사진함처럼
- 이미지 전체 화면
- 터치 하면 위 아래 반투명 검정배경으로 윗쪽 뒤로가기, 아랫쪽 이미지 정보
- 원본 웹페이지 링크
- 좌우 스크롤하면 이전/다음 이미지
- 확대
