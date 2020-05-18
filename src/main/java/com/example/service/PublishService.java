package com.example.service;

import com.example.mapper.QuestionMapper;
import com.example.model.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Author by admin, Email xx@xx.com, Date on 2020/4/7.
 * PS: Not easy to write code, please indicate.
 */
@Service
public class PublishService {

    @Autowired
    private QuestionMapper questionMapper;


    public Question getquetionByid(Integer id) {
        return questionMapper.getquetionByid(id);
    }


    public void creat(Question question) {
        questionMapper.creat(question);
    }

    public boolean updateQuestion(Question question) {
       return questionMapper.updateById(question) > 0;
    }
}
