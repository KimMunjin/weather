package zerobase.weather.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import zerobase.weather.domain.Diary;
import zerobase.weather.repository.DateWeatherRepository;
import zerobase.weather.repository.DiaryRepository;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@Transactional
class DiaryServiceTest {
    @Autowired
    DiaryService diaryService;
    @Autowired
    DiaryRepository diaryRepository;
    @Autowired
    DateWeatherRepository dateWeatherRepository;

    @Test
    void createDiaryTest(){
        LocalDate date = LocalDate.parse("2022-04-20");
        String text = "테스트중";
        diaryService.createDiary(date,text);

        List<Diary> searchingByDate = diaryRepository.findAllByDate(date);
        assertEquals(searchingByDate.get(searchingByDate.size()-1).getText(),"테스트중");
    }

    @Test
    void readDiaryTest(){
        LocalDate date = LocalDate.now();
        String text = "테스트중";
        diaryService.createDiary(date,text);
        List<Diary> diary = diaryService.readDiary(date);
        assertEquals(diary.get(diary.size()-1).getText(),"테스트중");
    }

    @Test
    void readDiariesTest(){
        LocalDate date1 = LocalDate.parse("2022-03-04");
        LocalDate date2 = LocalDate.parse("2022-05-20");
        String text1 = "테스트중1";
        String text2 = "테스트중2";

        diaryService.createDiary(date1,text1);
        diaryService.createDiary(date2,text2);

        List<Diary> diaries = diaryService.readDiaries(LocalDate.parse("2022-01-01"), LocalDate.parse("2022-12-31"));
        System.out.println(diaries.size());
        assertEquals(diaries.get(diaries.size()-2).getText(),"테스트중1");
        assertEquals(diaries.get(diaries.size()-1).getText(),"테스트중2");
    }

    @Test
    void updateDiaryTest(){
        LocalDate date = LocalDate.parse("2022-03-28");
        String text = "수정용 테스트 생성";
        String text2 = "텍스트 수정";
        diaryService.createDiary(date,text);
        diaryService.updateDiary(date,text2);
        List<Diary> diary = diaryRepository.findAllByDate(date);
        assertEquals(diary.get(0).getText(),"텍스트 수정");
    }

    @Test
    void deleteDiaryTest(){
        LocalDate date = LocalDate.parse("2022-03-30");
        String text = "삭제용 테스트 생성";
        diaryService.createDiary(date,text);
        List<Diary> diary = diaryRepository.findAllByDate(date);
        System.out.println(diary.size());
        assertEquals(diary.size(),1);

        diaryService.deleteDiary(date);
        diary = diaryRepository.findAllByDate(date);
        assertEquals(diary.size(),0);
    }
}