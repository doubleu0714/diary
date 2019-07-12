package io.doubleu0714.diary.service;

public interface DiaryService {
    /**
     * 
     * @param diaryContent
     * @param memberId
     * @return
     */
    long save(String diaryContent, String memberId);

    /**
     * 
     * @param diaryId
     * @param diaryContent
     * @param memberId
     * @return
     */
    long modify(long diaryId, String diaryContent, String memberId);
}