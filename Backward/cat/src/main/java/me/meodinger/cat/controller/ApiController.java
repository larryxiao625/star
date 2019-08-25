package me.meodinger.cat.controller;

import me.meodinger.cat.entity.Data;
import me.meodinger.cat.entity.Info;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api")
public class ApiController {

    @GetMapping(value = "/cat")
    public Data get(Info info) {
        return dealWith(info);
    }

    @PostMapping(value = "/cat")
    public Data post(Info info) {
        return dealWith(info);
    }

    /**
     * @param info 用户的信息
     * @return 处理用户信息的结果
     */
    private Data dealWith(Info info) {
        String name = info.getName();
        String gender = info.getGender();
        int age = info.getAge();
        String breed = info.getBreed();

        if (StringUtils.isEmpty(name)) {
            return new Data().setStatusCode(500).setErrorCode(-1).setResult("Name can not be blank.");
        }

        if (StringUtils.isEmpty(gender)) {
            return new Data().setStatusCode(500).setErrorCode(-2).setResult("Gender can not be blank.");
        } else if (gender.equalsIgnoreCase("m")) {
            gender = "boy";
        } else if (gender.equalsIgnoreCase("f")) {
            gender = "girl";
        } else {
            return new Data().setStatusCode(500).setErrorCode(-2).setResult("Gender is not legal");
        }

        if (age < 0) {
            return new Data().setStatusCode(500).setErrorCode(-3).setResult("Age can not be less than zero.");
        }

        String result = "The " + gender +
                (StringUtils.isEmpty(breed) ? "" : " " + breed) +
                " cat is" +
                ((age > 1) ? (" " + age + " years") : (" " + age + " year")) + " old. " +
                (gender.equals("boy") ? "His" : "Her") + " name is " + name;

        return new Data().setStatusCode(200).setResult(result);
    }
}