# weather
account 실습 때 배웠던 mockito등을 이용한 테스트 방법을 써보려고 했으나 아직 제대로 사용하질 못하여 Service 부분만 Test 작성하였습니다.<br>
강의 때 만든 weather 프로젝트에서 날씨 일기 구현에 필요하지 않은 부분들을 제거하였고(domain에 Memo, Repository의 JdbcMemoRepository, JpaMemoRepository)<br>
인증키는 insert_apikey라고 변경하여 git에 올렸습니다.<br>
강의 마지막까지 따라했을 때 create하면 date 날짜가 아닌 무조건 현재 날짜로 들어가는 문제가 발생했습니다.<br>
중간에 getWeatherFromApi메서드에서 dateWeather.setDate(LocalDate.now());로 인한 문제라고 생각하여 getWeatherFromApi에서 매개변수로 LocalDate date를 받게 하여 이를 dateWeather.setDate(date);로 바꾸고<br>
매일 새벽 1시에 DB에 저장하는 메서드에도 getWeatherFromApi에 변수로 LocalDate.now()를 넣어 데이터를 저장하게 수정하였습니다.
