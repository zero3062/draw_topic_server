package draw.draw_topic.api.service;

import draw.draw_topic.vo.SubTopicVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

@Service("apiTopicListService")
public class ApiTopicService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    // topic_list 불러오기
    public List getTopicList() throws Exception {
        return jdbcTemplate.queryForList("select * from topic_list");
    }

    // sub_topic_list 불러오기
    public List<Map<String, Object>> getSubTopic(String topicIndex) throws Exception {
        return jdbcTemplate.queryForList("select subTopicId, subTopicIndex, subTopicNm from sub_topic_list where topicIndex =?", topicIndex);
    }
}