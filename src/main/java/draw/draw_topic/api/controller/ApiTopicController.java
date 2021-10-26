package draw.draw_topic.api.controller;

import draw.draw_topic.api.service.ApiTopicService;
import draw.draw_topic.vo.TopicVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.thymeleaf.util.StringUtils;

import java.util.*;

@RestController
@RequestMapping("/api")
public class ApiTopicController {

    @Autowired
    private ApiTopicService apiTopicListService;

    @GetMapping("/list/topic")
    public Object getTopicList() throws Exception {
        List resultData = apiTopicListService.getTopicList(); // topic-header에 관한 리스트
        List topicList = new ArrayList<>(); // topic에 관한 정보를 담을 리스트

        // 주제에 해당하는 리스트 가져오는 반복문
        for(Iterator<Map<String, Object>> iterator = resultData.iterator(); iterator.hasNext();){
            Map<String, Object> map = (Map<String, Object>) iterator.next(); // 단일 topic 객체
            String topicListId = (map.get("topicListId")).toString(); // topicListId 가져옴

            List<Map<String, Object>> subDto = apiTopicListService.getSubTopic(topicListId); // 해당 topicListId 에 대한 subTopic

            Map<String, Object> topicInfo = new HashMap<>(); // 변경된 topic 객체
            topicInfo.put("topicListId", topicListId);
            topicInfo.put("topicNm", map.get("topicNm"));
            topicInfo.put("subTopic", subDto);

            topicList.add(topicInfo); // 배열에 변경된 topic 객체 담기
        }

        return topicList;
    }
}
