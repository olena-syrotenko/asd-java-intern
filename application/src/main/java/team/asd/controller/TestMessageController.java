package team.asd.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;
import team.asd.entity.Test;
import team.asd.entity.TestMessage;
import team.asd.mapper.TestMapper;

import java.time.LocalDate;

@RestController
@ApiIgnore
public class TestMessageController {
    private final TestMapper testMapper;

    @Autowired
    public TestMessageController(TestMapper testMapper) {
        this.testMapper = testMapper;
    }

    @GetMapping("/test/message")
    public TestMessage sendTestMessage() {
        return new TestMessage(LocalDate.now(), "Test message from Spring Boot Application!");
    }

    @PostMapping("/test/create")
    public Test createTest(@RequestBody Test test) {
        testMapper.insertValue(test.getValue());
        return test;
    }
}
